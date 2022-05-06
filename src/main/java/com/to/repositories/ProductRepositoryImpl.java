package com.to.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.to.entities.Product;
import com.to.entities.User;
import com.to.entities.UserPurchase;
import com.to.exceptions.EtAuthException;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private static final String SQL_CREATE = "insert into product(pname,pdescription,price,gender,cid) values(?,?,?,?,?)";
	private static final String SQL_FIND_ALL = "select * from product";
	private static final String SQL_FIND_BY_ID = "select * from product where pid=?";
	private static final String SQL_DELETE_BY_ID = "delete from product where pid=?";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void create(Product product) throws EtBadRequestException {

		try {
			jdbcTemplate.update(SQL_CREATE, product.getPname(), product.getPdescription(), product.getPrice(),
					product.getGender(), product.getCid());

		} catch (Exception e) {
			throw new EtAuthException("Failed to insert into product table try again" + e.getMessage());
		}

	}

	@Override
	public void update(Integer userId, Integer purId, UserPurchase userPurchase) throws EtBadRequestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer pId) {
		try {
			int i = jdbcTemplate.update(SQL_DELETE_BY_ID, new Object[] { pId });
			if (i == 0)
				throw new EtResourceNotFoundException("Sorry The given produt id: " + pId + " not available ");

		} catch (Exception e) {
			throw new EtResourceNotFoundException(e.getMessage());
		}

	}

	@Override
	public Product getById(Integer pid) throws EtResourceNotFoundException {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] { pid }, productRowMapper);

		} catch (Exception e) {
			throw new EtResourceNotFoundException(
					"Sorry The given produt id: " + pid + " not available " + e.getMessage());
		}
	}

	// method for getting the all product details
	public List<Product> getAllProduct() throws EtResourceNotFoundException {

		try {
			return jdbcTemplate.query(SQL_FIND_ALL, productRowMapper);

		} catch (Exception e) {
			throw new EtResourceNotFoundException("Failed to fetch the all product details " + e.getMessage());
		}
	}

	// row mapper
	private RowMapper<Product> productRowMapper = ((rs, rowNum) -> {

		return new Product(rs.getInt("pid"), rs.getString("pname"), rs.getString("pdescription"), rs.getInt("price"),
				rs.getString("gender"), rs.getInt("cid"), rs.getString("imgpath"));
	});

}
