package org.petstore.aspects;

import org.petstore.soplets.SopActions;
import org.petstore.soplets.SopMenus;


public @interface Menuable {

	public SopMenus parent() default SopMenus.NULL;
	public SopActions action() default SopActions.NULL; 
	
}
