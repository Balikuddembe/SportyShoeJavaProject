package com.to.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.to.entities.Product;
import com.to.exceptions.EtResourceNotFoundException;

@Repository
public class UserProductDisplayImpl implements UserProductDisplay {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static String sQL_FIND_ALL_PRODUCT_BY_CAT_ID = "select * from product where cid=?";

	@Override
	public List<Product> getAllProductByCategory(Integer catId) {
		try {
			return jdbcTemplate.query(sQL_FIND_ALL_PRODUCT_BY_CAT_ID, new Object[] { catId }, productRowMapper);

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
