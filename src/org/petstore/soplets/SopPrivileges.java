package org.petstore.soplets;

import lombok.Soplet;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Privilegable;

@Soplet(aspects={Artifact.class, Privilegable.class})
public enum SopPrivileges {

	@Sop (
		description="View the earnings of an order or order detail",	
		roles=SopRoles.chief)
	viewEarnings;	
}
