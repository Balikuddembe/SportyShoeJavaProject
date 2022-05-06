package com.to.repositories;

import java.beans.Statement;
import java.security.interfaces.RSAKey;
import java.sql.PreparedStatement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.to.entities.User;
import com.to.exceptions.EtAuthException;
import com.to.services.UserService;
import net.bytebuddy.asm.Advice.Return;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private static final String SQL_CREATE = "INSERT INTO user_login (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
	private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM user_login WHERE email=?";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM user_login WHERE userid=?";
	private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM user_login WHERE email=?";
	private static final String SQL_FIND_BY_EMAIL_USER_ID = "SELECT * FROM user_login WHERE email=?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	// method for creating new user
	@Override
	public Integer create(String fname, String lname, String email, String password) throws EtAuthException {
		Integer userId = null;
		// generating the hashed password
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

		try {
			jdbcTemplate.update(SQL_CREATE, fname, lname, email, hashedPassword);
			User user;
			user = findByEmail_userId(email);
			userId = user.getUserId();

		} catch (Exception e) {
			throw new EtAuthException("Invalid details. Failed to create account");
		}

		return userId;
	}

	// method for getting the user by email id
	public User findByEmail_userId(String email) {

		return jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL_USER_ID, new Object[] { email }, userRowMapper);
	}

//method for checking the email id and password is correct or not
	@Override
	public User findByEmailAndPassword(String email, String password) throws EtAuthException {

		try {
			// getting the user record from db
			User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[] { email }, userRowMapper);

			// checking the password
			if (!BCrypt.checkpw(password, user.getPassword()))
				throw new EtAuthException("Invalid Email/Pasword");
			return user;

		} catch (Exception e) {
			throw new EtAuthException("Invalid Email/Pasword");
		}
	}

	// method for checking the email is already used or not
	@Override
	public Integer getCountByEmail(String email) {

		return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[] { email }, Integer.class);
	}

	// method for finding the user by id
	@Override
	public User findById(Integer userId) {

		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { userId }, userRowMapper);
	}

	// row mapper
	private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
		return new User(rs.getInt("userid"), rs.getString("first_name"), rs.getString("last_name"),
				rs.getString("email"), rs.getString("password"));
	});

}
