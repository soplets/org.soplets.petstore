package org.petstore.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class PizzaUtil {

	private static SessionFactory sessionFactory;
	
	public static void showWarning(ValidationException ve) {
		// TODO Auto-generated method stub
		
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			} catch (Exception w) {
				w.printStackTrace();
			}
		}
		return sessionFactory;		
	}
}
