package org.petstore.dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.petstore.entity.MCustomer;
import org.petstore.entity.MOrder;
import org.petstore.soplets.SopExceptions;
import org.petstore.util.PizzaLogger;
import org.petstore.util.PizzaUtil;

public class DaoCustomer {

	private static DaoCustomer INSTANCE;

	public static DaoCustomer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoCustomer();
		}
		return INSTANCE;
	}
	
	private DaoCustomer(){		
	}

	public List<MCustomer> loadCustomer() {
		Session session = null;
		try {
			session = PizzaUtil.getSessionFactory().openSession();
	        Criteria c = session.createCriteria(MCustomer.class);
	        List<MCustomer> list = (List<MCustomer>)c.list();
	        if (list.size() == 0) {
	        	MCustomer c1 = new MCustomer();
	        	c1.setName("John Doe");
	        	session.save(c1);
	        	list.add(c1);
	        	MCustomer c2 = new MCustomer();
	        	c2.setName("Donald Duck");
	        	session.save(c2);
	        	list.add(c2);
	        }
	        return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			PizzaLogger.log(SopExceptions.EX_0002, ex);
			return new Vector<MCustomer>();
		} finally {
			session.close();
		}
	}
	
	public void save(MCustomer customer) {
		// TODO Auto-generated method stub
		
	}

	public MCustomer findByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
