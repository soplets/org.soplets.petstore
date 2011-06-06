package org.petstore.dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.petstore.entity.MOrder;
import org.petstore.entity.MOrderDetail;
import org.petstore.soplets.SopException;
import org.petstore.util.PetStoreLogger;
import org.petstore.util.PetStoreUtil;


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
			session = PetStoreUtil.getSessionFactory().openSession();
			session.beginTransaction();
			for (Object o : order.getDetails()) {
				MOrderDetail detail = (MOrderDetail)o;
				session.saveOrUpdate(o);
			}
			session.saveOrUpdate(order);
			session.getTransaction().commit();
		} catch (Exception ex) {
			PetStoreLogger.log(SopException.EX_0002, ex);
		} finally {
			session.close();
		}
	}
	
	public List<MOrder> loadOrders() {
		Session session = null;
		try {
			session = PetStoreUtil.getSessionFactory().openSession();
	        Criteria criteria = session.createCriteria(MOrder.class);
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        return (List<MOrder>)criteria.list();
		} catch (Exception ex) {
			PetStoreLogger.log(SopException.EX_0002, ex);
			return new Vector<MOrder>();
		} finally {
			session.close();
		}
	}

	public void delete(MOrder order) {
		Session session = null;
		try {
			session = PetStoreUtil.getSessionFactory().openSession();
			session.delete(order);
			session.flush();
		} catch (Exception ex) {
			PetStoreLogger.log(SopException.EX_0002, ex);
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
