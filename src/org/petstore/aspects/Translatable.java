package org.petstore.aspects;


public @interface Translatable {

	public String textDE();
	public String textEN() default "";
	public String textFR() default "";

}
