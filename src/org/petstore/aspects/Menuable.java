package org.petstore.aspects;

import org.petstore.soplets.SopActions;
import org.petstore.soplets.SopMenu;


public @interface Menuable {

	public SopMenu parent() default SopMenu.NULL;
	public SopActions action() default SopActions.NULL; 
	
}
