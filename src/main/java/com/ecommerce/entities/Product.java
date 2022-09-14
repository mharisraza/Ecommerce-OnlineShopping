package com.ecommerce.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.Part;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int pId;
	@Column(name="product_name")
	private String pName;
	@Column(name="product_description", length=99999999)
	private String pDesc;
	@Column(name="product_photo")
	private String pPhoto;
	@Column(name="product_price")
	private int pPrice;
	@Column(name="product_discount")
	private int pDiscount;
	@Column(name="product_quantity")
	private int pQuantity;
	@Column(name="added_by")
	private int userId;
	@Column(name="seller")
	private String seller;
	
	@ManyToOne
	private Category category;
	
	
	public Product(int pId, String pName, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
	}


	public Product(String pName, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity) {
		super();
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
	}
	

	public Product(int pId, String pName, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity,
			Category category) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.category = category;
	}
	
	


	public Product(String pName, String pDesc, String pPhoto, int pPrice, int pDiscount, int pQuantity,
			Category category) {
		super();
		this.pName = pName;
		this.pDesc = pDesc;
		this.pPrice = pPrice;
		this.pDiscount = pDiscount;
		this.pQuantity = pQuantity;
		this.category = category;
	}

	




	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public int getpId() {
		return pId;
	}


	public void setpId(int pId) {
		this.pId = pId;
	}


	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public String getpDesc() {
		return pDesc;
	}


	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	



	public int getpPrice() {
		return pPrice;
	}


	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}


	public int getpDiscount() {
		return pDiscount;
	}


	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}


	public int getpQuantity() {
		return pQuantity;
	}


	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

	/*
	 * public String getpPhoto() { return pPhoto; }
	 * 
	 * 
	 * public void setpPhoto(String pPhoto) { this.pPhoto = pPhoto; }
	 */
	
	


	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pName=" + pName + ", pDesc=" + pDesc + ", pPhoto=" + pPhoto + ", pPrice="
				+ pPrice + ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + "]";
	}


	public String getpPhoto() {
		return pPhoto;
	}


	public void setpPhoto(String parts) {
		this.pPhoto = parts;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	//calculating price after discount
	
	public String getSeller() {
		return seller;
	}


	public void setSeller(String seller) {
		this.seller = seller;
	}


	public int getPrice() {
		
		int discount = (int) ((this.getpDiscount()/100.0)*this.getpPrice());
		return this.getpPrice()-discount;
		
	}
	
	
	
	
	
	
	
	
	

}
