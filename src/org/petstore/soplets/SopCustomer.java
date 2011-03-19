package org.petstore.soplets;

import lombok.Soplet;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Beanable;
import org.petstore.aspects.Translatable;

@Soplet(aspects={Artifact.class, Beanable.class, Translatable.class})
public enum SopCustomer {

	@Sop (
		textEN = "Zip code",
		textDE = "PLZ", 
		description = "The postal code of the place where the customer lives",
		javaType = String.class,
		length = 5)
	zipCode,
	 
	@Sop (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = String.class)
	name,
	
	@Sop (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = String.class)
	address,
	
	@Sop (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = String.class)
	phone,

	@Sop (
		textEN = "",
		textDE = "", 
		description = "",
		javaType = SopRegion.class)
	region;
}
