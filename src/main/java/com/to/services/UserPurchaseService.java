package com.to.services;

import java.util.List;

import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

public interface UserPurchaseService {
	List<UserPurchase> fetchAllPurchases(Integer userId);

	UserPurchase fetchUserPurchaseById(Integer userId, Integer purId) throws EtResourceNotFoundException;

	void addPurchase(UserPurchase userPurchase) throws EtBadRequestException;

	void updatePurchase(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException;

	List<Product> getAllProductByCategory(Integer catId);

}
