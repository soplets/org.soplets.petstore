package org.petstore.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.petstore.entity.MCustomer;
import org.petstore.soplets.SopException;
import org.petstore.util.PizzaLogger;
import org.petstore.util.PizzaUtil;

public class DemoDataInit {

	public static void init() {
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
	        	MCustomer c3 = new MCustomer();
	        	c3.setName("Tick, Trick & Track");
	        	session.save(c3);
	        	list.add(c3);
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
			PizzaLogger.log(SopException.EX_0002, ex);
		} finally {
			session.close();
		}
	}
}
