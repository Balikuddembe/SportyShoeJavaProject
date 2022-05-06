package com.to.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Repository;

import com.to.entities.UserPurchase;
import com.to.exceptions.EtAuthException;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

@Repository
public class UserPurchaseRepositoryImpl implements UserPurchaseRepository {
	private static final String SQL_FIND_ALL = "SELECT * FROM user_purchases where user_id=?";
	private static final String SQL_CREATE = "INSERT INTO user_purchases(product_id, pdate, cat_id, quantity, price, total_price,user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM user_purchases where pid=? and user_id=?";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<UserPurchase> fetchAll(Integer userId) throws EtResourceNotFoundException {

		try {
			return jdbcTemplate.query(SQL_FIND_ALL, new Object[] { userId }, userPurchaseRowMapper);

		} catch (Exception e) {
			throw new EtResourceNotFoundException(
					"Purchase details not found for user id :" + userId + " purchased id :");
		}
	}

	@Override
	public UserPurchase findById(Integer userId, Integer purId) throws EtResourceNotFoundException {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { purId, userId, }, userPurchaseRowMapper);

		} catch (Exception e) {
			throw new EtResourceNotFoundException(
					"Purchase details not found for user id :" + userId + " purchased id :" + purId);
		}

	}

	// row mapper
	private RowMapper<UserPurchase> userPurchaseRowMapper = ((rs, rowNum) -> {

		return new UserPurchase(rs.getInt("pid"), rs.getInt("product_id"), rs.getString("pdate"), rs.getInt("cat_id"),
				rs.getInt("quantity"), rs.getInt("price"), rs.getInt("total_price"), rs.getInt("user_id"));
	});

	@Override
	public void create(UserPurchase userPurchase) throws EtBadRequestException {

		try {
			jdbcTemplate.update(SQL_CREATE, userPurchase.getProduct_id(), userPurchase.getPdate(),
					userPurchase.getCat_id(), userPurchase.getQuantity(), userPurchase.getPrice(),
					userPurchase.getTotal_price(), userPurchase.getUser_id());

		} catch (Exception e) {
			throw new EtAuthException("Invalid details. Failed to insert user purchase details");
		}

	}

	@Override
	public void update(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException {
		// TODO Auto-generated method stub

	}

}
