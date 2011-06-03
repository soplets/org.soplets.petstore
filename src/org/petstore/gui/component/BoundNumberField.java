package org.petstore.gui.component;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import org.petstore.Feature;
import org.petstore.Feature.DemoFeature;
import org.petstore.util.BindableEntity;
import org.petstore.util.PizzaUtil;

@DemoFeature(Feature.binding)
public class BoundNumberField<T> extends JFormattedTextField implements Bindable<T> {

	private static final long serialVersionUID = 1L;

	private BindableEntity fieldDef;

	public BoundNumberField() {
		super(NumberFormat.getNumberInstance());
		setColumns(10);		
	}
	
	public void bind(BindableEntity fieldDef) {
		this.fieldDef = fieldDef;
	}

	public void load(T entity) {
		setValue(PizzaUtil.extractValue(entity, fieldDef));
	}

	public void save(T entity) throws IllegalArgumentException {
		Object value = getValue();
		PizzaUtil.injectValue(entity, fieldDef, getValue());
	}

	@Override
	public BindableEntity getBoundField() {
		return fieldDef;
	}
}
