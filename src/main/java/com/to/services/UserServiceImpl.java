package com.to.services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.to.entities.User;
import com.to.exceptions.EtAuthException;
import com.to.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	// method for validating the user name password for logging
	@Override
	public User validateUser(String email, String password) throws EtAuthException {

		if (email != null)
			email = email.toLowerCase();

		// returnig the user
		return userRepository.findByEmailAndPassword(email, password);

	}

	@Override
	public User registerUser(String fname, String lname, String email, String password) throws EtAuthException {

		// pettern for validating email
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		if (email != null)
			email = email.toLowerCase();

		if (!pattern.matcher(email).matches())
			throw new EtAuthException("Invalid email format");

		// getting the email count
		Integer count = userRepository.getCountByEmail(email);
		if (count > 0)
			throw new EtAuthException("Email Already in use");

		// creating the user and getting the user id back
		Integer userId = userRepository.create(fname, lname, email, password);

		// returning the user detail wth jwt tocken
		return userRepository.findById(userId);

	}

}
