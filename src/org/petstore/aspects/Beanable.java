package org.petstore.aspects;

public @interface Beanable {

	public Class javaType();
	public int length() default 0;
	public boolean mandatory() default false;
	public boolean readOnly() default false;
	
}
