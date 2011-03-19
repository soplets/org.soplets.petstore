package org.petstore.aspects;

import org.petstore.soplets.SopRoles;

public @interface Privilegable {

	public SopRoles[] roles();
}
