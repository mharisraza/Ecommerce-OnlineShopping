package com.ecommerce.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="cart")
public class Cart {
	
	@Id
	@Column(name="identifier_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identifier_id;
	
	@Column(name="order_id")
	private String orderId;
	@Column(name="product_ids")
	private String productIds;
	@Column(name="product_quantities")
	private String orderQuantities;
	@Column(name="order_total_price")
	private int orderTotalPrice;
	@Column(name="order_date")
	@Temporal(TemporalType.DATE)
    private Date orderDate;
	@Column(name="delivery_date")
	@Temporal(TemporalType.DATE)
    private Date deliveryDate;
	@Column(name="order_status")
	private String orderStatus;
	@Column(name="order_by")
	private String orderBy;
	@Column(name="order_to")
	private String orderTo;

	
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cart(String orderQuantities, int orderTotalPrice, Date orderDate, Date deliveryDate, String orderStatus,
			String orderBy) {
		super();
		this.orderQuantities = orderQuantities;
		this.orderTotalPrice = orderTotalPrice;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.orderStatus = orderStatus;
		this.orderBy = orderBy;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getOrderQuantities() {
		return orderQuantities;
	}


	public void setOrderQuantities(String orderQuantities) {
		this.orderQuantities = orderQuantities;
	}


	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}


	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Date getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(Date DeliveryDate) {
		this.deliveryDate = DeliveryDate;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	public String getProductIds() {
		return productIds;
	}


	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}


	public String getOrderTo() {
		return orderTo;
	}


	public void setOrderTo(String orderTo) {
		this.orderTo = orderTo;
	}


	public int getIdentifier_id() {
		return identifier_id;
	}


	public void setIdentifier_id(int identifier_id) {
		this.identifier_id = identifier_id;
	}

	
}
