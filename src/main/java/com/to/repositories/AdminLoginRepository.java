package com.to.repositories;

import com.to.entities.AdminLogin;
import com.to.exceptions.EtBadRequestException;
import com.to.exceptions.EtResourceNotFoundException;

public interface AdminLoginRepository {

	AdminLogin findById(String adminId, String password) throws EtResourceNotFoundException;

	void update(String adminId, String password, String newPass) throws EtBadRequestException;

}
