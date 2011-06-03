package org.petstore.gui.component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.petstore.Feature;
import org.petstore.Feature.DemoFeature;
import org.petstore.util.BindableEntity;
import org.petstore.util.PizzaUtil;

@DemoFeature(Feature.binding)
public class BoundTableModel<T> extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultTableColumnModel columnModel;
	private List<BindableEntity> fields = new Vector<BindableEntity>();
	private List<T> data;
	
	public BoundTableModel() {
		columnModel = new DefaultTableColumnModel();
	}
	
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	public void addColumn(BindableEntity field, Object[] values) {
		TableColumn column = new TableColumn();
		column.setHeaderValue(field.toString());
		column.setWidth(100);
		column.setModelIndex(columnModel.getColumnCount());
		if (values != null) {
			final BoundComboBox<?> cbo = new BoundComboBox();
			for (Object entry : values) {
				cbo.addItem(entry);
			}
			cbo.bind(field);
			column.setCellEditor(new DefaultCellEditor(cbo));
		} else if (Long.class.isAssignableFrom(field.javaType())) {
			TableCellEditor numberCellEditor = new DefaultCellEditor(new JFormattedTextField(NumberFormat.getIntegerInstance())) {
				@Override
				public Object getCellEditorValue() {
					try {
						((JFormattedTextField)editorComponent).commitEdit();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Object o = ((JFormattedTextField)editorComponent).getValue();
					return o;
				}
			};
			column.setCellEditor(numberCellEditor);
		}
		
		columnModel.addColumn(column);
		fields.add(field);
	}

	public TableColumnModel getColumnModel() {
		return columnModel;
	}

	public void setData(List<T> data) {
		this.data = data;		
	}
	
	@Override
	public int getColumnCount() {
		return columnModel.getColumnCount();
	}

	@Override
	public int getRowCount() {
		if (data == null) return 0;
		return data.size();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		BindableEntity fieldDef = fields.get(col);
		T entity = data.get(row);
		return PizzaUtil.extractValue(entity, fieldDef);	
	}
	
	@Override
	public void setValueAt(Object o, int row, int col) {
		BindableEntity fieldDef = fields.get(col);
		T entity = data.get(row);
		PizzaUtil.injectValue(entity, fieldDef, o);
	}
}
