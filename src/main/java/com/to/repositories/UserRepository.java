package com.to.repositories;

import com.to.entities.User;
import com.to.exceptions.EtAuthException;

public interface UserRepository {

	Integer create(String fname, String lname, String email, String password) throws EtAuthException;

	User findByEmailAndPassword(String email, String password) throws EtAuthException;

	Integer getCountByEmail(String email);

	User findById(Integer userId);
}
