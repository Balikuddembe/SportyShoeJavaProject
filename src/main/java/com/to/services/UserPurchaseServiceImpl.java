package com.to.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;
import com.to.repositories.UserProductDisplay;
import com.to.repositories.UserPurchaseRepository;

@Service
@Transactional
public class UserPurchaseServiceImpl implements UserPurchaseService {

	@Autowired
	UserPurchaseRepository userPurchaseRepository;
	@Autowired
	UserProductDisplay userProductDisplay;

	@Override
	public List<UserPurchase> fetchAllPurchases(Integer userId) {
		return userPurchaseRepository.fetchAll(userId);
	}

	@Override
	public UserPurchase fetchUserPurchaseById(Integer userId, Integer purId) throws EtResourceNotFoundException {

		return userPurchaseRepository.findById(userId, purId);
	}

	// method for saving the purchased product details
	public void addPurchase(UserPurchase userPurchase) throws EtBadRequestException {
		userPurchaseRepository.create(userPurchase);

	}

	@Override
	public void updatePurchase(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> getAllProductByCategory(Integer catId) {

		return userProductDisplay.getAllProductByCategory(catId);
	}

}
