package com.ecommerce.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;

public class ProductDao {
	
	private SessionFactory factory;

	public ProductDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	public boolean saveProduct(Product product) {
		boolean f=false;
		
		try {
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
			
			
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
	
	public List<Product> getProducts() {
		
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			Query<Product> productQuery = session.createQuery("from Product");
			List<Product> productList = productQuery.list();
			
			
			transaction.commit();
			session.close();
			return productList;
	}
	
	public List<Product> getCategoryFilter(int cId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Product> categoryFilterQuery = session.createQuery("from Product as p where p.category.categoryId=:cId");
		categoryFilterQuery.setParameter("cId", cId);
		List<Product> categoryFilter = categoryFilterQuery.list();
		
		transaction.commit();
		session.close();
		
		return categoryFilter;
		
		
		
	}
	


	public List<Product> getProductById(int userId) {

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Product> pqUery = session.createQuery("from Product where userId=:userId");
		pqUery.setParameter("userId", userId);
		List<Product> productForCart = pqUery.list();
		
		
		transaction.commit();
		session.close();
		
	
	return productForCart;
}
	
	public Product getOneProducyById(int pId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query<Product> productQuery = session.createQuery("from Product where pId=:productId");
		productQuery.setParameter("productId", pId);
		Product getOneProductById = productQuery.uniqueResult();
		
		transaction.commit();
		session.close();
		return getOneProductById;
		
	}
	
	public List<Product> getProductsForSearchBar(String searchKey) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Query searchBarQuery =  session.createQuery("from Product where pName LIKE :searchKey");
		searchBarQuery.setParameter("searchKey", "%"+searchKey+"%");
		List<Product> searchBarList = searchBarQuery.list();
		
		
		transaction.commit();
		session.close();
		return searchBarList;
	}

	
		
	

}
