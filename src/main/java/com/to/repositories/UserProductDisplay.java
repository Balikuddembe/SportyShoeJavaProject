package com.to.repositories;

import java.util.List;

import com.to.entities.Product;
import com.to.exceptions.EtResourceNotFoundException;

public interface UserProductDisplay {
	
	List<Product> getAllProductByCategory(Integer catId);	

}
