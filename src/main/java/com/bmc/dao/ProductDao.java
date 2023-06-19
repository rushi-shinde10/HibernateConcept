package com.bmc.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.bmc.config.HibernateConfiguration;
import com.bmc.entity.Product;
import com.bmc.entity.ProductModel;

public class ProductDao {
	SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
	
	
	
	public String addProduct(Product product) {
	Session session= null;
	String msg = null;
		try {
			session = sessionFactory.openSession();
			session.save(product);
			session.beginTransaction().commit();

			msg="Product Added";
			/*
			 * if (save!=null) { msg="Product Added"; }else { msg="Product Not Added"; }
			 */
		} catch (Exception e) {
			msg="Opps....Product Already Exists with Product ID "+product.getProductId();
		}finally {
			if (session!=null) {
				session.close();
			}
		}
		return msg;
	}
	
	
public Product getProductById(long productId) {
	Session session= null;
    Product product=null;
		try {
			session = sessionFactory.openSession();
			product=session.get(Product.class, productId);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session!=null) {
				session.close();
			}
		}
		return product;
	}


public String deleteProductById(long productId) {
	Session session=null;
	String msg = null;
	try {
	    session = sessionFactory.openSession();
		Product product = getProductById(productId);
		if (product!=null) {
			session.delete(product);
			session.beginTransaction().commit();
			msg="Product Deleted";
		}else {
			System.out.println("Product Not Deleted Due To product Not Found");
		}
	} catch (Exception e) {
     	e.printStackTrace();
	}finally {
		if (session!=null) {
			session.close();
		}
	}
	return msg;
}


public String updateProduct(Product product) {
	Session session = null;
	String msg = null;
	try {
		session = sessionFactory.openSession();
		session.update(product);
		session.beginTransaction().commit();
		msg = "Product Updated Successfully";
		
	} catch (OptimisticLockException e) {
		msg = "For Product Id = " + product.getProductId()+ " Product Not Found";
	} catch (Exception e) {
		e.printStackTrace();
		msg = "Somethig Wrong while updating";

	} finally {
		if (session != null) {
			session.close();
		}
	}
	return msg;
}

public List<Product> getAllProducts() {
	
	Session session=null;
	List<Product> list=null;
	
	try {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setFirstResult(5);
		criteria.setMaxResults(10);
		list=criteria.list();
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session!=null) {
			session.close();
		}
		
	}
	return list;
}

public List <Product> sortProducts(String orderType, String propertyName) {
	Session session=null;
	List <Product> list=null;
	try {
		session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		
		if (orderType.equals("asc")) {
			criteria.addOrder(Order.asc(propertyName));
		} else {
            criteria.addOrder(Order.desc(propertyName));
		}
		list=criteria.list();
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session!=null) {
			session.close();
		}
}
	return list;
}


public List<Product> restrictionExp(){
	
	Session session=null;
	List<Product> list=null;
	double val=1000;
	try {
		session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		Criteria add = criteria.add(Restrictions.gt("productPrice", val));
		list = criteria.list();
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session!=null) {
			session.close();
		}
}
	return list;
}

public List<Product> projectionExp(){
	Session session=null;
	List<Product> list=null;
	double maxPrice;
	try {
		session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		//Criteria setProjection = criteria.setProjection(Projections.max("productPrice"));
		Criteria setProjectionMin = criteria.setProjection(Projections.min("productPrice"));
		
		//Criteria setProjectionAvg = criteria.setProjection(Projections.avg("productPrice"));
		maxPrice = (Double) criteria.list().get(0);
		
		Criteria criteria1 = session.createCriteria(Product.class); // setProjection and add() can't work simultaneously  on criteria
		criteria1.add(Restrictions.eq("productPrice", maxPrice));
		list = criteria1.list();
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if (session!=null) {
			session.close();
		}
}
	
	return list;
}


public List<ProductModel> queryExp() {
 
	Session session=null;
	//List <Product> list=null;
	//List<Product> object=null;
	
	List<Object[]> list = null;
	List<ProductModel> product = new ArrayList();
	ProductModel model = null;
	
	try {
		 session = sessionFactory.openSession();

		 // HQL Query for Greater Than Operator
			
			  Query query = session.createQuery("SELECT p.productId,p.productName,p.productPrice FROM Product p where p.productPrice > : price");
			  
			  query.setDouble("price", 1000); 
			  list=query.list();
			  Iterator it = list.iterator();

				while (it.hasNext()) {
					model = new ProductModel();

					Object[] obj = (Object[]) it.next();

					model.setProductId(obj[0]);
					model.setProductName(obj[1]);
					model.setProductPrice(obj[2]);
					product.add(model);
				}
		
		//HQL example - Get Product with id
		/*
		 * Query query = session.createQuery("FROM Product where productId= :id");
		 * query.setLong("id", 1); Product product = (Product) query.uniqueResult();
		 * System.out.println("Product Name="+product.getProductId()+", Name="+product.
		 * getProductName());
		 */
		 
		 /*HQL pagination example
			 * query = session.createQuery("from Product"); query.setFirstResult(0);
			 * //starts with 0
			 * query.setFetchSize(2); 
			 * productList = query.list(); 
			 * for(Product product : productList){
			 * 
			 * System.out.println("Paginated Products::"+product.getProductId()+","+product.getProductName());
			 * }
			 */
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	       
			
	return product;
}





}
