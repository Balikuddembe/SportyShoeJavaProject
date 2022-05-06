package com.to.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Category;
import com.to.entities.Product;
import com.to.services.CategoryService;
import com.to.services.ProductServices;

@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryResource {

	@Autowired
	CategoryService categoryService;

	// controller for getting the all category details
	@GetMapping("")
	public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {
		// getting the admin id
		String adminId = (String) request.getAttribute("adminId");

		List<Category> categories = categoryService.getAllCategories();
		System.out.println(categories);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	// controller for getting the specific category details
	@GetMapping("/{catId}")
	public ResponseEntity<Category> getCategoryById(HttpServletRequest request, @PathVariable("catId") Integer catId) {

		// getting the admin Id
		String adminId = (String) request.getAttribute("adminId");

		// searching the category
		Category category = categoryService.getCategoryById(catId);
		System.out.println("get  category by id called" + category + " " + catId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	// controller for deleting the specific category details

	@DeleteMapping("/delete/{catId}")
	public ResponseEntity<Map<String, String>> deleteCategoryById(HttpServletRequest request,
			@PathVariable("catId") Integer catId) {

		// getting the admin Id
		String adminId = (String) request.getAttribute("adminId");

		// deleting the category
		categoryService.deleteCategory(catId);
		Map<String, String> map = new HashMap<>();
		map.put("Success", "Category Id : " + catId + " Deleted Successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	// controller for adding the new category details
	@PostMapping("")
	public ResponseEntity<Map<String, String>> addCategory(HttpServletRequest request,
			@RequestBody Map<String, Object> catMap) {

		// getting the admin id from tocken provided
		String adminId = (String) request.getAttribute("adminId");

		System.out.println("Control is here");
		// adding category details into the bean
		Category category = new Category();
		category.setCatName((String) catMap.get("cname"));

		// calling the service method to save the data
		categoryService.createCategory(category.getCatName());

		System.out.println("Control is here aftefr product");

		Map<String, String> map = new HashMap<>();
		map.put("msg", "Category Details added");
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	// controller for updating existing category details
	@PatchMapping("")
	public ResponseEntity<Map<String, String>> updateCategory(HttpServletRequest request,
			@RequestBody Map<String, Object> catMap) {

		// getting the admin id from tocken provided
		String adminId = (String) request.getAttribute("adminId");

		System.out.println("Control is here");
		// adding category details into the bean
		Category category = new Category();
		category.setCatId(Integer.parseInt((String) catMap.get("cid")));
		category.setCatName((String) catMap.get("cname"));

		// calling the service method to save the data
		categoryService.updateCategory(category.getCatId(), category.getCatName());

		Map<String, String> map = new HashMap<>();
		map.put("msg", "Category Details updates Successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

}
