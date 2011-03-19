package org.petstore.dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.petstore.entity.MCustomer;
import org.petstore.entity.MOrder;
import org.petstore.entity.MOrderDetail;
import org.petstore.soplets.SopArticle;
import org.petstore.soplets.SopExceptions;
import org.petstore.util.PizzaLogger;
import org.petstore.util.PizzaUtil;


public class DaoOrder {
	
	private static DaoOrder INSTANCE;

	public static DaoOrder getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoOrder();
		}
		return INSTANCE;
	}
	
	private DaoOrder(){		
	}
	
	public void saveOrder(MOrder order) {
		Session session = null;
		try {
			session = PizzaUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(order);
			session.getTransaction().commit();
		} catch (Exception ex) {
			PizzaLogger.log(SopExceptions.EX_0002, ex);
		} finally {
			session.close();
		}
	}
	
	public List<MOrder> loadOrders() {
		Session session = null;
		try {
			session = PizzaUtil.getSessionFactory().openSession();
	        Criteria c = session.createCriteria(MOrder.class);
	        return (List<MOrder>)c.list();
		} catch (Exception ex) {
			PizzaLogger.log(SopExceptions.EX_0002, ex);
			return new Vector<MOrder>();
		} finally {
			session.close();
		}
	}

	public void delete(MOrder order) {
		Session session = null;
		try {
			session = PizzaUtil.getSessionFactory().openSession();
			session.delete(order);
		} catch (Exception ex) {
			PizzaLogger.log(SopExceptions.EX_0002, ex);
		} finally {
			session.close();
		}
	}

    
//    private void runTest() {
//    	try {
//    		
//	    	session.beginTransaction();
//	    	MCustomer customer = new MCustomer();
//	    	customer.setName("John");
//	    	session.save(customer);
//	    	
//
//	    	MOrder o = new MOrder();
//	    	o.setAddress("5Address1");
//	    	o.setCustomer(customer);
//	    	o.setDetails(new Vector<MOrderDetail>());
//
//	    	MOrderDetail od = new MOrderDetail();
//	    	od.setQuantity(15);
//	    	od.setArticle(SopArticle.coke);
//	    	session.save(od);
//	    	o.getDetails().add(od);
//	    	
//	    	od = new MOrderDetail();
//	    	od.setQuantity(22);
//	    	od.setArticle(SopArticle.beerHeineken);
//	    	session.save(od);
//	    	o.getDetails().add(od);
//
//
//	    	session.getTransaction().commit();
//	    	session.beginTransaction();
//	    	session.save(o);
//
//	    	for (MOrder order : loadOrders()) {
//	    		System.out.println(order.getAddress() + " ::: " + order.getCustomer());
//	    		if (order.getDetails() != null) {
//	    			for (Object oo : order.getDetails()) {
//	    				MOrderDetail od2 = (MOrderDetail)oo;
//	    	    		System.out.println("OD::: " + od2.getQuantity() + " " + od2.getArticle());
//	    			}
//	    		}
//	    	}
//	    	session.getTransaction().commit();
//	    	finishEntityManager();
//    		System.out.println("DONE");
//
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    	}
//    }
//    
//    public static void main(String... args) {
//    	DaoOrder dao = new DaoOrder();
//    	dao.runTest();
//    }
}
