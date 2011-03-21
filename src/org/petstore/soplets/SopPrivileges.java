package org.petstore.soplets;

import lombok.Soplet;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Privilegable;

@Sop(aspects={Artifact.class, Privilegable.class})
public enum SopPrivileges {

	@Soplet (
		description="View the earnings of an order or order detail",	
		roles=SopRoles.chief)
	viewEarnings;	
}
