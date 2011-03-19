package org.petstore.soplets;

import lombok.Soplet;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Beanable;
import org.petstore.aspects.Translatable;

import javax.persistence.*;

@Soplet(aspects={Artifact.class, Translatable.class, Beanable.class})
public enum SopOrderDetail {

	@Sop (
		textEN = "",
		textDE = "",
		description = "",
		readOnly = true,
		javaType = Double.class)
//		viewPrivilege=SopPrivilege.viewEarnings)
	earnings,

//	@Enumerated(EnumType.STRING)
	@Sop (
		textEN = "Order Text",
		textDE = "Bestellung",
		description = "The thing to be ordered",
		readOnly = true,
		javaType = SopArticle.class)
	article,

	@Sop (
		textEN = "Quantity",
		textDE = "Menge",
		readOnly = true,
		description = "The ordered quantity of the product",
		javaType = Integer.class)
	quantity,

	@Sop (
		textEN = "",
		textDE = "",
		description = "",
		readOnly = true,
		javaType = Double.class)
	pricePerUnit,

	@Sop (
		textEN = "",
		textDE = "",
		description = "",
		readOnly = true,
		javaType = Double.class)
	subTotal,


}
