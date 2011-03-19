package org.petstore.soplets;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import lombok.Soplet;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Privilegable;
import org.petstore.dao.DaoOrder;
import org.petstore.entity.MOrder;

@Soplet(aspects={Artifact.class, Privilegable.class})
public enum SopActions {
	
	@Sop(
		description="Reloads the current set of orders in the main window",
		roles=SopRoles.ANY)
	reloadOrders {
		@Override
		public void actionPerformed(ActionEvent e) {
			DaoOrder dao = DaoOrder.getInstance();
			DefaultListModel mdlOrders = (DefaultListModel)e.getSource();  //TODO...
			List<MOrder> orders = dao.loadOrders();
			mdlOrders.setSize(0);
			for (MOrder order : orders) {
				mdlOrders.addElement(order);
			}
			ListDataEvent lde = new ListDataEvent(mdlOrders, ListDataEvent.CONTENTS_CHANGED, 0, mdlOrders.getSize() - 1);
			for (ListDataListener ldl : mdlOrders.getListDataListeners()) {
				ldl.contentsChanged(lde);
			}			
		}
	},
	
	@Sop(
		description="Delete an existing order (requires chief privileges!)",
		roles=SopRoles.chief)
	deleteOrder {
		@Override
		public void actionPerformed(ActionEvent e) {
			MOrder order = (MOrder)e.getSource();
			DaoOrder.getInstance().delete(order);
		}
	},
	
	NULL;
	
	public void actionPerformed(ActionEvent e){};
}
