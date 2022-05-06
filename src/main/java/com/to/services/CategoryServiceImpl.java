package com.to.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.to.entities.Category;
import com.to.exceptions.EtBadRequestException;
import com.to.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public void createCategory(String categoryName) {
		categoryRepository.create(categoryName);

	}

	@Override
	public void updateCategory(Integer catId, String catName) throws EtBadRequestException {
		categoryRepository.update(catId, catName);

	}

	@Override
	public void deleteCategory(Integer catId) {
		categoryRepository.delete(catId);

	}

	@Override
	public Category getCategoryById(Integer catid) {

		return categoryRepository.getById(catid);
	}

	@Override
	public List<Category> getAllCategories() {

		return categoryRepository.getAllCategories();
	}

}
