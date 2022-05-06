package com.to.resources;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.to.entities.Product;
import com.to.entities.User;
import com.to.services.AdminReportService;
import com.to.services.ProductServices;

@RestController
@RequestMapping("/api/admin/report")
public class AdminReportsResource {
	@Autowired
	AdminReportService adminReportService;

	// controller for getting the all product details
	@GetMapping("/loggedUsers")
	public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
		//getting the admin od from tocken
		String adminId = (String) request.getAttribute("adminId");
		
		List<User> users = adminReportService.getAllLoggedUsersDetails();
		System.out.println(users);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
}