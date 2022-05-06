package com.to.repositories;

import org.apache.logging.log4j.message.Message;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.to.entities.AdminLogin;
import com.to.entities.User;
import com.to.exceptions.EtAuthException;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

@Repository
public class AdminLoginRepositoryImpl implements AdminLoginRepository {

	private static final String SQL_FIND_BY_ADMIN_ID_PASS = "select * from admin_login WHERE adminId=? and adminPass=?";
	private static final String SQL_FIND_BY_ADMIN_ID = "select * from admin_login WHERE adminId=?";
	private static final String SQL_UPDATE_PASS = "update admin_login set adminPass=? where adminId=?";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public AdminLogin findById(String adminId, String password) throws EtResourceNotFoundException {

		try {
			// getting the user record from db
			AdminLogin adminLogin = jdbcTemplate.queryForObject(SQL_FIND_BY_ADMIN_ID, new Object[] { adminId },
					adminRowMapper);

			// checking the password
			if (!password.equals(adminLogin.getPassword()))
				throw new EtAuthException("Invalid AdminId/Pasword");

			return adminLogin;

		} catch (Exception e) {
			throw new EtAuthException("Invalid AdminId/Pasword");
		}

	}

	@Override
	public void update(String adminId, String password, String newPass) throws EtBadRequestException {

		try {
			// getting the user record from db
			AdminLogin adminLogin = jdbcTemplate.queryForObject(SQL_FIND_BY_ADMIN_ID, new Object[] { adminId },
					adminRowMapper);
			adminLogin.setNewPass(newPass);
			System.out.println(adminLogin);
			// checking the password

			if (password.equals(newPass))
				throw new EtAuthException("Enter Old Password and new  Pasword should be different");
			else if (!password.equals(adminLogin.getPassword()))
				throw new EtAuthException("Enter Correct Old Password ");
			else {
				// update the admin password with new password
				jdbcTemplate.update(SQL_UPDATE_PASS, newPass, adminId);
			}

		} catch (EtAuthException e) {
			throw new EtAuthException(e.getMessage());
		}

		catch (Exception e) {
			throw new EtAuthException("Invalid AdminId/Pasword");
		}

	}

	// row mapper
	private RowMapper<AdminLogin> adminRowMapper = ((rs, rowNum) -> {
		return new AdminLogin(rs.getString("adminId"), rs.getString("adminPass"));

	});

}
