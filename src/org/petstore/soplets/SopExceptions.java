package org.petstore.soplets;

import lombok.Soplet;

import org.petstore.aspects.Loggable;

@Soplet(aspects=Loggable.class)
public enum SopExceptions {
	
	@Sop(
		level=SopLoggingLevel.SEVERE,	
		problem="Problems while storing order data to the database",
		solution="Check the database connection, or look for stale data")
	EX_0001,

	@Sop(
		level=SopLoggingLevel.SEVERE, 	
		problem="Problems while retrieving order data from the database",
		solution="Check the database connection")
	EX_0002;

}
