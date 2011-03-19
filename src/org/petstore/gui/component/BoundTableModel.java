package org.petstore.gui.component;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.petstore.Feature;
import org.petstore.Feature.DemoFeature;
import org.petstore.soplets.SopOrderDetail;
import org.petstore.util.BindableEntity;

@DemoFeature(Feature.binding)
public class BoundTableModel<T> extends DefaultTableModel {
	
	public void addColumn(BindableEntity field) {		
		// TODO
	}

	@Deprecated
	public void addColumn(SopOrderDetail field) {		
	}

	public TableColumnModel getColumnModel() {
		// TODO
		return null;
	}

	public void setData(List<T> details) {
		// TODO		
	}

}
