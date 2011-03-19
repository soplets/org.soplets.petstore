package org.petstore.aspects;

import org.petstore.soplets.SopDeveloper;


public @interface Artifact {
	
	public SopDeveloper author() default SopDeveloper.johnDoe; 
	public String description();
	
}
