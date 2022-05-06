package com.to.repositories;

import java.util.List;
import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

public interface ProductRepository {

	void create(Product product) throws EtBadRequestException;

	void update(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException;

	void delete(Integer pId);

	Product getById(Integer pid) throws EtResourceNotFoundException;

	List<Product> getAllProduct() throws EtResourceNotFoundException;

}
