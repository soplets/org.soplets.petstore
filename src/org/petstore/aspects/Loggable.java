package org.petstore.aspects;

import org.petstore.soplets.SopLoggingLevel;


public @interface Loggable {

	public String problem();
	public String solution();
	public SopLoggingLevel level();
	
}
