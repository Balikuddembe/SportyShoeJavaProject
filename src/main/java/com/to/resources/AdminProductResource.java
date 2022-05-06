package com.to.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Product;
import com.to.entities.UserPurchase;
import com.to.services.ProductServices;

@RestController
@RequestMapping("/api/admin/product")
public class AdminProductResource {
	@Autowired
	ProductServices productServices;

	// controller for getting the all product details
	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProducts(HttpServletRequest request) {
		String adminId = (String) request.getAttribute("adminId");
		List<Product> products = productServices.getAllProductDetails();
		System.out.println(products);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// controller for getting the specific product details
	@GetMapping("/{pId}")
	public ResponseEntity<Product> getProductById(HttpServletRequest request, @PathVariable("pId") Integer pId) {
		// getting the admin Id
		String adminId = (String) request.getAttribute("adminId");
		// searching the product
		Product products = productServices.getProductById(pId);
		System.out.println("get  product by id called" + products + " " + pId);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// controller for deleting the specific product details
	@DeleteMapping("/delete/{pId}")
	public ResponseEntity<Map<String, String>> deleteProductById(HttpServletRequest request,
			@PathVariable("pId") Integer pId) {
		// getting the admin Id
		String adminId = (String) request.getAttribute("adminId");
		// deleting the product
		productServices.deleteProduct(pId);
		Map<String, String> map = new HashMap<>();
		map.put("Success", "Product Id : " + pId + " Deleted Successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	// controller for adding the new product details
	@PostMapping("")
	public ResponseEntity<Map<String, String>> addProduct(HttpServletRequest request,
			@RequestBody Map<String, Object> productMap) {
		// getting the admin add from tocken provide
		String adminId = (String) request.getAttribute("adminId");
		System.out.println("Control is here");
		// adding product details into the bean
		Product prodcut = new Product();
		prodcut.setPname((String) productMap.get("pname"));
		prodcut.setPdescription((String) productMap.get("pdescription"));
		prodcut.setPrice(Integer.parseInt(productMap.get("price").toString()));
		prodcut.setGender((String) productMap.get("gender"));
		prodcut.setCid(Integer.parseInt(productMap.get("cid").toString()));
		// calling the service method to save the data
		productServices.createNewProduct(prodcut);
		System.out.println("Control is here aftefr product");
		Map<String, String> map = new HashMap<>();
		map.put("msg", "Product Details added");
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

}
