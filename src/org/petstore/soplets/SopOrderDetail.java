package org.petstore.soplets;

import java.lang.annotation.Annotation;

import lombok.soplets.Beanable;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Editable;
import org.petstore.aspects.Translatable;
import org.petstore.aspects.Validatable;
import org.petstore.util.BindableEntity;

@Sop(aspects={Artifact.class, Translatable.class, Beanable.class, Validatable.class, Editable.class})
public enum SopOrderDetail implements BindableEntity {

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
		javaType = Long.class)
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
	subTotal;

	public String defaultValue() {
		return "";  //TODO
	}
}
