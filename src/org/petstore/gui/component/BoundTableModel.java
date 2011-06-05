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
import org.petstore.util.PetStoreUtil;

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
		return !fields.get(col).readOnly();
	}
	
	public void addColumn(BindableEntity field) {
		TableColumn column = new TableColumn();
		column.setHeaderValue(field.toString());
		column.setPreferredWidth(Number.class.isAssignableFrom(field.javaType()) ? 70 : 250);
		column.setModelIndex(columnModel.getColumnCount());
		if (Enum.class.isAssignableFrom(field.javaType())) {
			//we assume a Soplet list
			BoundComboBox<?> cbo = new BoundComboBox(field.javaType());
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

	@Override
    public Class getColumnClass(int columnIndex) {
		return fields.get(columnIndex).javaType();        
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
		return PetStoreUtil.extractValue(entity, fieldDef);	
	}
	
	@Override
	public void setValueAt(Object o, int row, int col) {
		BindableEntity fieldDef = fields.get(col);
		T entity = data.get(row);
		PetStoreUtil.injectValue(entity, fieldDef, o);
	}
}
