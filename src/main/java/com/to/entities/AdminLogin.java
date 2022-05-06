package com.to.entities;

public class AdminLogin {
	private String adminId;
	private String password;
	private String newPass;

	// constructor
	public AdminLogin(String adminId, String password, String newPass) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.newPass = newPass;
	}

	public AdminLogin(String adminId, String password) {
		super();
		this.adminId = adminId;
		this.password = password;
	}

	// getter and setters
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Override
	public String toString() {
		return "AdminLogin [adminId=" + adminId + ", password=" + password + ", newPass=" + newPass + "]";
	}

}
