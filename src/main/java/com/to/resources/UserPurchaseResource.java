package com.to.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.services.UserPurchaseService;

@RestController
@RequestMapping("/api/userPurchase")
public class UserPurchaseResource {

	@Autowired
	UserPurchaseService userPurchaseService;

	@GetMapping("")
	public ResponseEntity<List<UserPurchase>> getAllPurchase(HttpServletRequest request) {
		int userId = (Integer) request.getAttribute("userId");
		List<UserPurchase> userPurchases = userPurchaseService.fetchAllPurchases(userId);
		System.out.println(userPurchases);
		return new ResponseEntity<>(userPurchases, HttpStatus.OK);
	}

	@GetMapping("/products/{catId}")
	public ResponseEntity<List<Product>> getAllProductCategorywise(HttpServletRequest request,
			@PathVariable("catId") Integer catId) {
		int userId = (Integer) request.getAttribute("userId");
		List<Product> products = userPurchaseService.getAllProductByCategory(catId);
		System.out.println(products);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// controller for adding the purchase details
	@PostMapping("")
	public ResponseEntity<Map<String, String>> addUserPurchase(HttpServletRequest request,
			@RequestBody Map<String, Object> userMap) {
		Integer userId = (Integer) request.getAttribute("userId");

		System.out.println("Control is here");

		UserPurchase userPurchase = new UserPurchase();
		userPurchase.setProduct_id(Integer.parseInt(userMap.get("product_id").toString()));
		userPurchase.setPdate((String) userMap.get("pdate"));
		userPurchase.setCat_id(Integer.parseInt(userMap.get("cat_id").toString()));
		userPurchase.setQuantity(Integer.parseInt(userMap.get("quantity").toString()));
		userPurchase.setPrice(Integer.parseInt(userMap.get("price").toString()));
		userPurchase.setTotal_price(Integer.parseInt(userMap.get("total_price").toString()));
		userPurchase.setUser_id(userId);

		System.out.println("Control is here before add purchase");
		userPurchaseService.addPurchase(userPurchase);
		System.out.println("Control is here aftefr add purchase");
		Map<String, String> map = new HashMap<>();
		map.put("msg", "Purchase Details added" + userPurchase);

		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	// method for getting the purchase details by id
	@GetMapping("/{purId}")
	public ResponseEntity<UserPurchase> getUserPurchaseById(HttpServletRequest request,
			@PathVariable("purId") Integer purId) {
		// getting the logged user id
		int userId = (Integer) request.getAttribute("userId");
		// calling the fetch method
		UserPurchase userPurchase = userPurchaseService.fetchUserPurchaseById(userId, purId);
		return new ResponseEntity<>(userPurchase, HttpStatus.OK);
	}

}
