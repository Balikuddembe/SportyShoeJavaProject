package com.to.entities;

public class Category {
	private Integer catId;
	private String catName;

//constructor using fields
	public Category(Integer catId, String catName) {
		super();
		this.catId = catId;
		this.catName = catName;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

	// getter and setters
	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + "]";
	}

}
