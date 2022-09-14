package com.ecommerce.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="users")

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	@Column(name="user_name", length=200)
	private String userName;
	@Column(unique=true, name="user_email", length=100)
	private String userEmail;
	@Column(name="user_password")
	private String userPassword;
	@Column(unique=true,name="user_phone", length=15)
	private String userPhone;
	@Column(name="user_pic")
	private String userPic;
	@Column(name="user_address", length=2500)
	private String userAddress;
	@Column(name="user_role")
	private String userType;
	@Temporal(TemporalType.DATE)
	private Date addedDate;
	@Column(name="user_suspend")
	private String userSuspend;
	
	
	public User(int userId, String userName, String userEmail, String userPassword, String userPhone, String userPic,
			String userAddress, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userAddress = userAddress;
		this.userType = userType;
	}


	public User(String userName, String userEmail, String userPassword, String userPhone, String userPic,
			String userAddress, String userType) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userAddress = userAddress;
		this.userType = userType;
	}
	
	


	public User(String userName, String userEmail, String userPassword, String userPhone, String userPic,
			String userAddress, String userType, Date addedDate) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userAddress = userAddress;
		this.userType = userType;
		this.addedDate = addedDate;
	}
	

	


	public User(String userName, String userEmail, String userPassword, String userPhone, String userPic,
			String userAddress, String userType, Date addedDate, String userSuspend) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userAddress = userAddress;
		this.userType = userType;
		this.addedDate = addedDate;
		this.userSuspend = userSuspend;
	}
	
	


	public User(String userName, String userEmail, String userPassword, String userPhone, String userPic,
			String userAddress, Date addedDate, String userSuspend) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userAddress = userAddress;
		this.addedDate = addedDate;
		this.userSuspend = userSuspend;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserPic() {
		return userPic;
	}


	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public Date getAddedDate() {
		return addedDate;
	}


	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}


	public String getUserSuspend() {
		return userSuspend;
	}


	public void setUserSuspend(String userSuspend) {
		this.userSuspend = userSuspend;
	}


	


	
	
	

}
