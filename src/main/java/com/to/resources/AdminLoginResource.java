package com.to.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.to.Constants;
import com.to.entities.AdminLogin;
import com.to.entities.User;
import com.to.services.AdminLoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/admin")
public class AdminLoginResource {

	@Autowired
	AdminLoginService adminLoginService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody Map<String, Object> adminMap) {
		String adminId = (String) adminMap.get("adminId");
		String password = (String) adminMap.get("password");
		// checking the admin id and password is correct or not
		AdminLogin adminLogin = adminLoginService.adminLogin(adminId, password);
		return new ResponseEntity<>(generateJWTToken(adminLogin), HttpStatus.OK);
	}

	@PostMapping("/changeAdminPass")
	public Map<String, String> changeAdminPass(@RequestBody Map<String, Object> adminMap) {
		String adminId = (String) adminMap.get("adminId");
		String password = (String) adminMap.get("password");
		String newPass = (String) adminMap.get("newPass");

		// checking the admin id and password is correct or not
		adminLoginService.adminPasswordChange(adminId, password, newPass);

		Map<String, String> map = new HashMap<>();
		map.put("Message", "Password Changed Successfully Login with new Password " + adminId);
		return map;

	}

	// methodn for generating the tocken

	private Map<String, String> generateJWTToken(AdminLogin adminLogin) {
		// getting the current time in milisecods
		long timestamp = System.currentTimeMillis();
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY_STRING)
				.setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("adminId", adminLogin.getAdminId()).compact();

		Map<String, String> map = new HashMap<>();
		map.put("Succes", "Login Successfull Admin Use the given token");
		map.put("token", token);
		return map;

	}

}
