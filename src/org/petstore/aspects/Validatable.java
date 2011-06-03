package org.petstore.aspects;

import org.petstore.soplets.SopValidators;

/**
 * Aspect defining the (input) validator of a field
 * 
 * See the BoundTextField class for an example how to call this
 * feature. 
 *  
 * Note: In the future the validation could be woven into the lombok-generated
 * byte code, comparable to the JSR 303 (Bean Validation) 
 * 
 * @author chrismay
 */
public @interface Validatable {

	public SopValidators validator() default SopValidators.NULL;
	
}
 