package org.petstore.util;

import java.lang.reflect.Method;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


public class PetStoreUtil {

	private static SessionFactory sessionFactory;
	
	public static void showWarning(ValidationException ve) {
		// TODO
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
	
	/**
	 * retrieve the value of a bound field by using reflection
	 */
	public static Object extractValue(Object entity, BindableEntity fieldDef) {
		if (entity == null) {
			return null;
		}
		Class<?> clazz = entity.getClass();
		String getterName = fieldDef.name();
		getterName = getterName.substring(0, 1).toUpperCase()
				+ getterName.substring(1);
		getterName = "get" + getterName;
		try {
			Method getter = clazz.getMethod(getterName);
			return getter.invoke(entity);
		} catch (Exception e) {
			e.printStackTrace(); // TODO Logging
		}
		return null;
	}
	
	/**
	 * set a new value to bound field by using reflection
	 */
	public static void injectValue(Object entity, BindableEntity fieldDef, Object newValue) {
		Class<?> dataType = fieldDef.javaType();
		Class<?> clazz = entity.getClass();
		String setterName = fieldDef.name();
		setterName = setterName.substring(0, 1).toUpperCase()
				+ setterName.substring(1);
		setterName = "set" + setterName;
		try {
			Method setter = clazz.getMethod(setterName, dataType);			
			setter.invoke(entity, newValue);
		} catch (Exception e) {
			e.printStackTrace(); // TODO Logging
		}
	}
}
