package com.to.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.to.entities.Category;
import com.to.entities.Product;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static String SQL_INSERT = "INSERT INTO category(cname) values(?)";
	private static String SQL_SELECT = "select * from category";
	private static String SQL_SELECT_BY_ID = "select * from category where cid=?";
	private static String SQL_DELETE = "delete from category where cid=?";
	private static String SQL_UPDATE = "update category set cname=? where cid=?";

	@Override
	public void create(String categoryName) {
		try {
			jdbcTemplate.update(SQL_INSERT, new Object[] { categoryName });
		} catch (Exception e) {
			throw new EtBadRequestException(
					"Something went wrong while creating the category try again" + e.getMessage());
		}

	}

	@Override
	public void update(Integer catId, String catName) {
		try {
			int i = jdbcTemplate.update(SQL_UPDATE, new Object[] { catName, catId });
			if (i == 0)
				throw new EtResourceNotFoundException(
						"Sorry Given Category Is not present first add the category then update");
		} catch (Exception e) {
			throw new EtBadRequestException(
					"Something went wrong while updating the category try again Reason :" + e.getMessage());
		}

	}

	@Override
	public void delete(Integer catId) {
		try {
			int i = jdbcTemplate.update(SQL_DELETE, new Object[] { catId });
			if (i == 0)
				throw new EtResourceNotFoundException("Sorry The given cat id: " + catId + " not available ");
		} catch (Exception e) {
			throw new EtResourceNotFoundException(e.getMessage());
		}
	}

	// row mapper
	private RowMapper<Category> catRowMapper = ((rs, rowNum) -> {

		return new Category(rs.getInt("cid"), rs.getString("cname"));
	});

	@Override
	public Category getById(Integer catid) {

		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[] { catid }, catRowMapper);
		} catch (Exception e) {
			throw new EtResourceNotFoundException(" category not available details " + e.getMessage());
		}

	}

	@Override
	public List<Category> getAllCategories() {
		try {
			return jdbcTemplate.query(SQL_SELECT, catRowMapper);
		} catch (Exception e) {
			throw new EtResourceNotFoundException("Failed to fetch the all category details " + e.getMessage());
		}
	}

}
