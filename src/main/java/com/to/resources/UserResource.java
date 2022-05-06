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
import com.to.entities.User;
import com.to.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@RestController
@RequestMapping("/api/users")
public class UserResource {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
		String firstName = (String) userMap.get("fname");
		String lastName = (String) userMap.get("lname");
		String email = (String) userMap.get("email");
		String password = (String) userMap.get("password");
		User user = userService.registerUser(firstName, lastName, email, password);

		return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
	}

	@PostMapping("/loginuser")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
		String emailString = (String) userMap.get("email");
		String passwordString = (String) userMap.get("password");
		// checking the user id and password is correct or not
		User user = userService.validateUser(emailString, passwordString);

		return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
	}

	// methodn for generating the tocken

	private Map<String, String> generateJWTToken(User user) {
		// getting the current time in milisecods
		long timestamp = System.currentTimeMillis();
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY_STRING)
				.setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
				.claim("userId", user.getUserId()).claim("email", user.getEmail()).claim("firstName", user.getFname())
				.claim("lastName", user.getLname()).compact();

		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		return map;

	}

}
