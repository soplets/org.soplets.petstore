package org.petstore.util;

import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.petstore.entity.MBase;
import org.petstore.entity.MCustomer;
import org.petstore.gui.component.Bindable;
import org.petstore.gui.component.BoundComboBox;
import org.petstore.gui.component.BoundNumberField;
import org.petstore.gui.component.BoundTextField;

/**
 * A rudimentary editor factory which creates an 
 * preconfigured editor component according to the
 * BindableEntity properties 
 * 
 * @author chrismay
 *
 */
public class EditorFactory {

	public static Bindable<?> getEditor(BindableEntity field) {
		Bindable bindable = null;
		Class javaType = field.javaType();
		if (List.class.isAssignableFrom(javaType)) {
			//TODO
		} else if (MBase.class.isAssignableFrom(javaType)) {
			bindable = new BoundComboBox();	
			Session session = null;
			try {
				session = PizzaUtil.getSessionFactory().openSession();
		        Criteria c = session.createCriteria(javaType);
		        List<MCustomer> list = (List)c.list();
				for (Object dataEntry : list) {
					((BoundComboBox)bindable).addItem(dataEntry);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				session.close();
			}
		} else if (Enum.class.isAssignableFrom(javaType)) {
			bindable = new BoundComboBox();			
			try {
				Method valuesMethod = javaType.getMethod("values");
				Object[] valueEntries = (Object[])valuesMethod.invoke(javaType);
				for (Object entry : valueEntries) {
					((BoundComboBox)bindable).addItem(entry);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Integer.class.isAssignableFrom(javaType)) {
			bindable = new BoundNumberField();
		} else {
			bindable = new BoundTextField();
		}
		bindable.bind(field);
		return bindable;
	}
}
