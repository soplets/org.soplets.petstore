package org.petstore.soplets;

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
		action = SopAction.reloadOrders, 
		textEN = "Reload orders",
		textDE = "Bestellung laden")
	reloadOrders,
	 
	@Soplet(
		parent = SopMenu.orders,
		action = SopAction.deleteOrder, 
		textEN = "Delete order",
		textDE = "Bestellung löschen")
	deleteOrder,
	
	aboutDialog;

}
