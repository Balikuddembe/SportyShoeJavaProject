package com.to.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.to.entities.AdminLogin;
import com.to.exceptions.EtAuthException;
import com.to.repositories.AdminLoginRepository;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	AdminLoginRepository adminLoginRepository;

	@Override
	public AdminLogin adminLogin(String adminId, String password) throws EtAuthException {
		return adminLoginRepository.findById(adminId, password);
	}

	@Override
	public void adminPasswordChange(String adminId, String password, String newPass) throws EtAuthException {

		adminLoginRepository.update(adminId, password, newPass);
	}

}
