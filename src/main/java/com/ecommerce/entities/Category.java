package com.ecommerce.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="category_id")
	private int categoryId;
	@Column(name="category_title")
	private String categoryTitle;
	@Column(name="category_description")
	private String categoryDesc;
	@Column(name="added_by")
	private int user_id;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products=new ArrayList<Product>();
	
	
	public Category(int categoryId, String categoryTitle, String categoryDesc) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}


	public Category(String categoryTitle, String categoryDesc) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}
	
	
	public Category(String categoryTitle, String categoryDesc, List<Product> products) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
		this.products = products;
	}


	public Category(int categoryId, String categoryTitle, String categoryDesc, List<Product> products) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
		this.products = products;
	}


	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryTitle() {
		return categoryTitle;
	}


	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}


	public String getCategoryDesc() {
		return categoryDesc;
	}


	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	
	

	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDesc="
				+ categoryDesc + "]";
	}

	
	

}
