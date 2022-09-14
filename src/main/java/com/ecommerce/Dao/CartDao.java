package com.ecommerce.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.entities.Cart;


public class CartDao {
	
	private SessionFactory factory;

	public CartDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	public boolean saveToCart(Cart cart) {
		boolean f=false;
		
		try {
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(cart);
			
			
			transaction.commit();
			session.close();
			f=true;
			
			
			
			
		}
		catch(Exception e) {
			f=false;
			e.printStackTrace();
		}
		return f;
		
	}
	

}
