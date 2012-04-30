package org.petstore.soplets;

import lombok.soplets.Beanable;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Editable;
import org.petstore.aspects.Translatable;

@Sop(
	aspects={Artifact.class, Beanable.class, Editable.class, Translatable.class})
public enum SopCustomer {

	@Soplet (
		
		textDE = "PLZ", 
		description = "The postal code of the place where the customer lives",
		javaType = String.class,
		length = 5)
	zipCode,
	 
	@Soplet (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = String.class)
	name,
	
	@Soplet (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = String.class)
	address,
	
	@Soplet (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = String.class)
	phone,

	@Soplet (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = SopRegion.class)
	region;
}
