package org.petstore.util;

import org.petstore.soplets.SopValidators;

public interface BindableEntity {

	public String name();
	public String defaultValue();
	public int length();
	public Class javaType();
	public boolean readOnly();
	public SopValidators validator();

}
