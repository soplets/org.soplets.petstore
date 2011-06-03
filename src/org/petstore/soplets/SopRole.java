package org.petstore.soplets;

import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;

@Sop(aspects=Artifact.class)
public enum SopRole {

	@Soplet(
		description="The guy taking the orders")
	clerk,
	
	@Soplet(
		description="The boss!")
	chief,
	
	ANY;	
}
