package com.to.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.to.entities.User;

@Repository
public class AdminReportsImpl implements AdminReports {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static String SQL_ALL_USERS = "select * from user_login";

	@Override
	public List<User> getAllLoggedUsers() {

		return jdbcTemplate.query(SQL_ALL_USERS, userRowMapper);
	}

	// row mapper
	private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
		return new User(rs.getInt("userid"), rs.getString("first_name"), rs.getString("last_name"),
				rs.getString("email"));
	});

}
