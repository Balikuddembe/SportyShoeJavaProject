package com.to.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.to.entities.User;
import com.to.repositories.AdminReports;

@Service
@Transactional
public class AdminReportServiceImpl implements AdminReportService {
	@Autowired
	AdminReports adminReports;

	@Override
	public List<User> getAllLoggedUsersDetails() {
		// TODO Auto-generated method stub
		return adminReports.getAllLoggedUsers();
	}

}
