package org.petstore.soplets;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Privilegable;
import org.petstore.dao.DaoOrder;
import org.petstore.entity.MOrder;

@Sop(aspects={Artifact.class, Privilegable.class})
public enum SopAction {
	
	@Soplet(
		description="Reloads the current set of orders in the main window",
		roles=SopRole.ANY)
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
	
	@Soplet(
		description="Delete an existing order (requires chief privileges!)",
		roles=SopRole.chief)
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
