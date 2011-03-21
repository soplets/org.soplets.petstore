package org.petstore.soplets;

import lombok.Soplet;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;

@Sop(aspects=Artifact.class)
public enum SopRoles {

	@Soplet(
		description="The guy taking the orders")
	clerk,
	
	@Soplet(
		description="The boss!")
	chief,
	
	ANY;	
}
