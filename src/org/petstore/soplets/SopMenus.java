package org.petstore.soplets;

import org.petstore.aspects.Menuable;
import org.petstore.aspects.Translatable;

@lombok.Soplet(aspects={Translatable.class, Menuable.class})
public enum SopMenus {
	
	NULL,
	
	@Sop(
		textEN = "Reload orders",
		textDE = "Bestellung laden")
	orders,
	 
	@Sop(
		parent = SopMenus.orders,
		action = SopActions.reloadOrders, 
		textEN = "Reload orders",
		textDE = "Bestellung laden")
	reloadOrders,
	 
	@Sop(
		parent = SopMenus.orders,
		action = SopActions.deleteOrder, 
		textEN = "Delete order",
		textDE = "Bestellung löschen")
	deleteOrder,
	
	aboutDialog;
	

}
