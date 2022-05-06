package com.to.services;

import com.to.entities.User;
import com.to.exceptions.EtAuthException;

public interface UserService {

	User validateUser(String email, String password) throws EtAuthException;

	User registerUser(String fname, String lname, String email, String password) throws EtAuthException;

}
