package com.to.entities;

public class UserPurchase {
	private Integer pid;
	private Integer product_id;
	private String pdate;
	private Integer cat_id;
	private Integer quantity;
	private Integer price;
	private Integer total_price;
	private Integer user_id;

	// constructor

	public UserPurchase() {
	}

	public UserPurchase(Integer pid, Integer product_id, String pdate, Integer cat_id, Integer quantity, Integer price,
			Integer total_price, Integer user_id) {
		super();
		this.pid = pid;
		this.product_id = product_id;
		this.pdate = pdate;
		this.cat_id = cat_id;
		this.quantity = quantity;
		this.price = price;
		this.total_price = total_price;
		this.user_id = user_id;
	}

	public UserPurchase(Integer product_id, String pdate, Integer cat_id, Integer quantity, Integer price,
			Integer total_price, Integer user_id) {
		super();
		// this.pid = pid;
		this.product_id = product_id;
		this.pdate = pdate;
		this.cat_id = cat_id;
		this.quantity = quantity;
		this.price = price;
		this.total_price = total_price;
		this.user_id = user_id;
	}

	// getter and settetrs
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public Integer getCat_id() {
		return cat_id;
	}

	public void setCat_id(Integer cat_id) {
		this.cat_id = cat_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	// to string method
	@Override
	public String toString() {
		return "UserPurchase [pid=" + pid + ", product_id=" + product_id + ", pdate=" + pdate + ", cat_id=" + cat_id
				+ ", quantity=" + quantity + ", price=" + price + ", total_price=" + total_price + ", user_id="
				+ user_id + "]";
	}

}
