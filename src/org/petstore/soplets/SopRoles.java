package org.petstore.soplets;

import lombok.Soplet;

import org.petstore.aspects.Artifact;

@Soplet(aspects=Artifact.class)
public enum SopRoles {

	@Sop(
		description="The guy taking the orders")
	clerk,
	
	@Sop(
		description="The boss!")
	chief,
	
	ANY;	
}
