package com.to.repositories;

import java.util.List;

import com.to.entities.Category;
import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

public interface CategoryRepository {
	
	
	void create(String categoryName);
	
	void update(Integer catId, String catName) ;
	
	void delete(Integer catId) ;
	
    Category getById(Integer catid);
    
    List<Category> getAllCategories();
	
	

}
