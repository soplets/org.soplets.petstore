package org.petstore.soplets;

import lombok.Soplet;
import lombok.soplets.Sop;

import org.petstore.aspects.Menuable;
import org.petstore.aspects.Translatable;

@Sop(aspects={Translatable.class, Menuable.class})
public enum SopMenu {
	
	NULL,
	
	@Soplet(
		textEN = "Reload orders",
		textDE = "Bestellung laden")
	orders,
	 
	@Soplet(
		parent = SopMenu.orders,
		action = SopActions.reloadOrders, 
		textEN = "Reload orders",
		textDE = "Bestellung laden")
	reloadOrders,
	 
	@Soplet(
		parent = SopMenu.orders,
		action = SopActions.deleteOrder, 
		textEN = "Delete order",
		textDE = "Bestellung löschen")
	deleteOrder,
	
	aboutDialog;

}
