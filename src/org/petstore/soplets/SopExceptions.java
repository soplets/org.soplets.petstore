package org.petstore.soplets;

import lombok.Soplet;
import lombok.soplets.Sop;

import org.petstore.aspects.Loggable;

@Sop(aspects=Loggable.class)
public enum SopExceptions {
	
	@Soplet(
		level=SopLoggingLevel.SEVERE,	
		problem="Problems while storing order data to the database",
		solution="Check the database connection, or look for stale data")
	EX_0001,

	@Soplet(
		level=SopLoggingLevel.SEVERE, 	
		problem="Problems while retrieving order data from the database",
		solution="Check the database connection")
	EX_0002;

}
