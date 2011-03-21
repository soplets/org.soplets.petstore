package org.petstore.aspects;

public @interface Beanable2 {

	public int length() default 0;
	public boolean mandatory() default false;
	public boolean readOnly() default false;
	
}
