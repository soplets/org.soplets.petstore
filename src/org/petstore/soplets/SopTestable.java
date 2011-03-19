package org.petstore.soplets;

import org.petstore.test.HPTest;

public @interface SopTestable {

	HPTest testCase();

	String description();

}
