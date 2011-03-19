package org.petstore.gui.component;

import org.petstore.util.BindableEntity;
import org.petstore.util.ValidationException;

public interface Bindable<T> {

	public void load(T entity);
	
	public void save(T entity) throws ValidationException;

	public void bind(BindableEntity field);
	
	public BindableEntity getBoundField();

}
