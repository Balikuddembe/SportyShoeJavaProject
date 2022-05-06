package com.to.repositories;

import java.util.List;

import com.to.entities.UserPurchase;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

public interface UserPurchaseRepository {

	List<UserPurchase> fetchAll(Integer userId) throws EtResourceNotFoundException;

	UserPurchase findById(Integer userId, Integer purId) throws EtResourceNotFoundException;

	void create(UserPurchase userPurchase) throws EtBadRequestException;

	void update(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException;

}
