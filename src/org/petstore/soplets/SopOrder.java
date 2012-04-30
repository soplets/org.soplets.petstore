package org.petstore.soplets;

import java.awt.List;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.soplets.Beanable;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Editable;
import org.petstore.aspects.Translatable;
import org.petstore.aspects.Validatable;
import org.petstore.entity.MCustomer;
import org.petstore.entity.MOrderDetail;
import org.petstore.util.BindableEntity;

@Sop(aspects={Artifact.class, Beanable.class, Editable.class, Validatable.class, Translatable.class})
public enum SopOrder implements BindableEntity {  
 
	@Soplet(
		textEN = "Address",
		textDE = "Anschrift",
		textFR = "",
		description = "The address of the customer",
		javaType = String.class,
		length = 255)
	address,

	@Soplet(
		textEN = "Phone number",
		textDE = "Telefonnummer", 
		description = "The phone number of the customer",
		javaType = String.class,
		length = 20,
		validator = SopValidators.phonePattern)
	phone,

	@Soplet( 
		textEN = "Order time",
		textDE = "Bestellzeitpunkt", 
		description = "Time when the order has been placed",
		javaType = Date.class,
		readOnly = true)
	orderTime,

	@ManyToOne(  //this annotation will be woven into the MOrder class
		targetEntity=MCustomer.class)
	@Soplet( 
		textEN = "Customer",
		textDE = "Kunde", 
		description = "The person which ordered the pet",
		readOnly = false,
		javaType = MCustomer.class,
		mandatory = true) 
	customer, 

	@Soplet(
		textEN = "Region",
		textDE = "Region", 
		description = "Classification for the distance to the customer",
		readOnly = false,
		javaType = SopRegion.class)  
	region,

	@OneToMany(  //this annotation will be woven into the MOrder class
		targetEntity = MOrderDetail.class,
		fetch=FetchType.EAGER)
	@Soplet(
		textEN = "Order details",
		textDE = "Bestellliste", 
		description = "The items which comprise an order",
		readOnly = false,
		javaType = List.class)
//		entity = SopEntityMaster.DUMMY, 
//		multiplicity = SopMultiplicity.oneToMany)
	details,
	
	@Soplet(
		textEN = "",
		textDE = "",
		description = "",
		readOnly = false,
		javaType = Double.class)
//		accessType = SopFieldAccess.abstractGetter,
//		viewPrivilege=SopPrivilege.viewEarnings)
	earnings;
	
	public String defaultValue() {
		return "";  //TODO
	}
}