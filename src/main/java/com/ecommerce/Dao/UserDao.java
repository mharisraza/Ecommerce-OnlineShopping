package com.ecommerce.Dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import com.ecommerce.entities.User;

public class  UserDao {
	private  SessionFactory factory;

	public UserDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	
	//getting user by email and password
	
	public UserDao() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User getUserByEmailAndPassword(String userEmail, String userPassword) {
		User user= null;
		
		try {
			
			String query="from User where userEmail =:email and userPassword =:password";
			Session session= factory.openSession();
			Query q = session.createQuery(query);
			
			q.setParameter("email", userEmail);
			q.setParameter("password", userPassword);
			user=(User) q.uniqueResult();
			
			session.close();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByEmail(String userEmail){
		
	    Session session=factory.openSession();
	    Query query = session.createQuery("from User where userEmail=:email");
	    query.setParameter("email", userEmail);
	    List<User> user = query.list();


	    session.close();
	    return user;
	}
	@SuppressWarnings("unchecked")
	public List<User> getUserByPhone(String userPhone){
		
		Session session=factory.openSession();
		Query query = session.createQuery("from User where userPhone=:phone");
		query.setParameter("phone", userPhone);
		List<User> user = query.list();
		
		
		session.close();
		return user;
	}
	
	public List<User> getUsers() {
		Session session=factory.openSession();
		Query query = session.createQuery("from User");
		List<User> user = query.list();
		
		session.close();
		return user;
		
	}

}
