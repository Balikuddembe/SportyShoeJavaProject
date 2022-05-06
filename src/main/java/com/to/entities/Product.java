package com.to.entities;

public class Product {
	private Integer pid;
	private String pname;
	private String pdescription;
	private Integer price;
	private String gender;
	private Integer cid;
	private String imagePath;

	// constructor using fields
	public Product(Integer pid, String pname, String pdescription, Integer price, String gender, Integer cid,
			String imagePath) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pdescription = pdescription;
		this.price = price;
		this.gender = gender;
		this.cid = cid;
		this.imagePath = imagePath;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdescription() {
		return pdescription;
	}

	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", pdescription=" + pdescription + ", price=" + price
				+ ", gender=" + gender + ", cid=" + cid + ", imagePath=" + imagePath + "]";
	}

}
