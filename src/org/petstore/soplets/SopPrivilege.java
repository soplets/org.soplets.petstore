package org.petstore.soplets;

import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Privilegable;

@Sop(aspects={Artifact.class, Privilegable.class})
public enum SopPrivilege {

	@Soplet (
		description="View the earnings of an order or order detail",	
		roles=SopRole.chief)
	viewEarnings;	
}
