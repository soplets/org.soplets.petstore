package org.petstore.aspects;

public @interface Priceable {
	public double price() default 0.0;
}