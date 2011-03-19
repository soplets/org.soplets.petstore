package org.petstore.soplets;

import java.awt.List;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Soplet;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Beanable;
import org.petstore.aspects.Translatable;
import org.petstore.aspects.Validatable;
import org.petstore.entity.MCustomer;
import org.petstore.entity.MOrderDetail;
import org.petstore.util.BindableEntity;

@Soplet(aspects={Artifact.class, Beanable.class, Validatable.class, Translatable.class})
public enum SopOrder implements BindableEntity, Translatable {  
  
	@ManyToOne(
		targetEntity=MCustomer.class)
	@Sop( 
		textEN = "Customer",
		textDE = "Kunde", 
		description = "The person which ordered the pizza",
		readOnly = false,
		javaType = MCustomer.class,
		mandatory = true) 
	customer, 
 
	@Sop( 
		textEN = "Order time",
		textDE = "Bestellzeitpunkt", 
		description = "Time when the order has been placed",
		javaType = Date.class,
		readOnly = true)
	orderTime,

	@Sop(
		textEN = "Address",
		textDE = "Anschrift", 
		description = "The address of the customer",
		javaType = String.class,
		readOnly = false,
		length = 255)
	address,

	@Sop(
		textEN = "Phone number",
		textDE = "Telefonnummer", 
		description = "The phone number of the customer",
		javaType = String.class,
		readOnly = false,
		length = 20,
		validator = SopValidators.phonePattern)
	phone,

	@Sop(
		textEN = "Region",
		textDE = "Region", 
		description = "Classification for the distance to the customer",
		readOnly = false,
		javaType = String.class) //List.class)
//		list = SopListMasterImpl.sopRegion)  
	region,

	@OneToMany(
		targetEntity = MOrderDetail.class)
	@Sop(
		textEN = "Order details",
		textDE = "Bestellliste", 
		description = "The items which comprise an order",
		readOnly = false,
		javaType = List.class)
//		entity = SopEntityMaster.DUMMY, 
//		multiplicity = SopMultiplicity.oneToMany)
	details,
	
	@Sop(
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

	public Class annotationType() {
		return null;
	}
}