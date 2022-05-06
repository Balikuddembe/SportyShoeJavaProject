package com.to.services;

import java.util.List;

import com.to.entities.Category;
import com.to.exceptions.EtBadRequestException;

public interface CategoryService {
	
	void createCategory(String categoryName);
	
	void updateCategory(Integer catId, String catName) throws EtBadRequestException;
	
	void deleteCategory(Integer catId) ;
	
    Category getCategoryById(Integer catid);
    
    List<Category> getAllCategories();
	
	

}
