package org.petstore.aspects;

import org.petstore.soplets.SopTestSetup;

public @interface Testable {

	public SopTestSetup preConditions();
	public String risk();
	public String expectedResult();
	
}
