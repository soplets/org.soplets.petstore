package org.petstore.gui.component;

import java.lang.reflect.Method;

import javax.swing.JComboBox;

import org.petstore.Feature;
import org.petstore.Feature.DemoFeature;
import org.petstore.util.BindableEntity;

@DemoFeature(Feature.binding)
public class BoundComboBox<T> extends JComboBox implements Bindable<T> {

	private static final long serialVersionUID = 1L;
	
	private BindableEntity field;

	public BoundComboBox() {
		//empty
	}

	/**
	 * Create a new bound combo, with values provided by a Soplet enumeration
	 *  
	 * @param javaType must be a Soplet enum class
	 */
	public BoundComboBox(Class javaType) {
		try {
			Method valuesMethod = javaType.getMethod("values");
			Object[] valueEntries = (Object[])valuesMethod.invoke(javaType);
			for (Object entry : valueEntries) {
				addItem(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load(T entity) {
		if (entity == null) {
			setSelectedItem(null);
			return;
		}
		Class<?> clazz = entity.getClass();
		String getterName = field.name();
		getterName = getterName.substring(0, 1).toUpperCase()
				+ getterName.substring(1);
		getterName = "get" + getterName;
		try {
			Method getter = clazz.getMethod(getterName);
			Object value = getter.invoke(entity);
			setSelectedItem(value);
		} catch (Exception e) {
			e.printStackTrace(); // TODO Logging
		}
	}

	public void save(T entity) throws IllegalArgumentException {
		if (entity == null) {
			return;
		}
		Class<?> clazz = entity.getClass();
		String setterName = field.name();
		setterName = setterName.substring(0, 1).toUpperCase() + setterName.substring(1);
		setterName = "set" + setterName;
		try {
			//apply the value
			Class<?> javaType = field.javaType();
			Method setter = clazz.getMethod(setterName, javaType);			
			setter.invoke(entity, getSelectedItem());
		} catch (Exception e) {
			e.printStackTrace(); // TODO Logging
		}
	}

	@Override
	public void bind(BindableEntity field) {
		this.field = field;
	}
	
	@Override
	public BindableEntity getBoundField() {
		return field;
	}
}
