package com.ecommerce.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ecommerce.entities.Category;

public class CategoryDao {
	private SessionFactory factory;

	public CategoryDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	public int saveCategory(Category cat) {
		
		Session session = factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		int catId = (Integer) session.save(cat);
		transaction.commit();
		session.close();
		
		
		return catId;
		
		
		
	}
	
	public List<Category> getCategories() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Category.class);
		List<Category> list= criteria.list();
		return list;
	}
	
	public Category getCategoryById(int catId) {
		Category cat = null;
		
		try {
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			cat = session.get(Category.class, catId);
			
			
			transaction.commit();
			session.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
		
		

}
