package org.petstore.aspects;

import org.petstore.soplets.SopValidators;


public @interface Validatable {

	public SopValidators validator() default SopValidators.NULL;
	
}
 