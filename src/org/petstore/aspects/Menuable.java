package org.petstore.aspects;

import org.petstore.soplets.SopAction;
import org.petstore.soplets.SopMenu;


public @interface Menuable {

	public SopMenu parent() default SopMenu.NULL;
	public SopAction action() default SopAction.NULL; 
	
}
