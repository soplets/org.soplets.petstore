package org.petstore.aspects;

import org.petstore.soplets.SopLoggingLevel;


public @interface Problemable {

	public String problem();
	public String solution();
	public SopLoggingLevel level();
	
}
