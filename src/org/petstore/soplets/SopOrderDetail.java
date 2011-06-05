package org.petstore.soplets;

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
		textEN = "Order Text",
		textDE = "Bestellung",
		description = "The thing to be ordered",
		javaType = SopArticle.class)
	article,

	@Soplet (
		textEN = "Quantity",
		textDE = "Menge",
		description = "The ordered quantity of the product",
		javaType = Long.class)
	quantity,

	@Soplet (
		textEN = "Unit price",
		textDE = "Preis",
		description = "",
		readOnly = true,
		javaType = Double.class)
	pricePerUnit,

	@Soplet (
		textEN = "Sub-total",
		textDE = "Subtotal",
		description = "",
		readOnly = true,
		javaType = Double.class)
	subTotal;


	public String defaultValue() {
		return "";  //TODO
	}
	
	@Override
	public String toString() {
		return textEN();
	}
}
