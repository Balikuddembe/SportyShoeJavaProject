package com.to.services;

import com.to.entities.AdminLogin;
import com.to.exceptions.EtAuthException;


public interface AdminLoginService {
	
	AdminLogin adminLogin(String adminId, String password) throws  EtAuthException;

	void adminPasswordChange(String adminId, String password, String newPass)  throws EtAuthException;

}
