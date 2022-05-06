package com.to.services;

import java.util.List;
import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

public interface ProductServices {

	void createNewProduct(Product product) throws EtBadRequestException;

	void updateProduct(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException;

	void deleteProduct(Integer pId) throws EtResourceNotFoundException;

	Product getProductById(Integer pid) throws EtResourceNotFoundException;

	List<Product> getAllProductDetails() throws EtResourceNotFoundException;

}
