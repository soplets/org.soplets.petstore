package org.petstore.aspects;

import org.petstore.soplets.SopRole;

public @interface Privilegable {

	public SopRole[] roles();
}
