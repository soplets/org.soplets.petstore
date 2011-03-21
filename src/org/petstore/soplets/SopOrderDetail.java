package org.petstore.soplets;

import lombok.soplets.Beanable;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Beanable2;
import org.petstore.aspects.Translatable;

@Sop(aspects={Artifact.class, Translatable.class, Beanable.class, Beanable2.class})
public enum SopOrderDetail {

	@Soplet (
		textEN = "",
		textDE = "",
		description = "",
		readOnly = true,
		javaType = Double.class)
//		viewPrivilege=SopPrivilege.viewEarnings)
	earnings,

	@Soplet (
		textEN = "Order Text",
		textDE = "Bestellung",
		description = "The thing to be ordered",
		readOnly = true,
		javaType = SopArticle.class)
	article,

	@Soplet (
		textEN = "Quantity",
		textDE = "Menge",
		readOnly = true,
		description = "The ordered quantity of the product",
		javaType = Integer.class)
	quantity,

	@Soplet (
		textEN = "",
		textDE = "",
		description = "",
		readOnly = true,
		javaType = Double.class)
	pricePerUnit,

	@Soplet (
		textEN = "",
		textDE = "",
		description = "",
		readOnly = true,
		javaType = Double.class)
	subTotal,


}
