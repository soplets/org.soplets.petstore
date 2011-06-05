package org.petstore;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.petstore.Feature.DemoFeature;
import org.petstore.dao.DaoOrder;
import org.petstore.dao.DemoDataInit;
import org.petstore.entity.MOrder;
import org.petstore.entity.MOrderDetail;
import org.petstore.gui.component.Bindable;
import org.petstore.gui.component.BoundLabel;
import org.petstore.gui.component.BoundTableModel;
import org.petstore.soplets.SopArticle;
import org.petstore.soplets.SopOrder;
import org.petstore.soplets.SopOrderDetail;
import org.petstore.soplets.SopPrivilege;
import org.petstore.util.BindableEntity;
import org.petstore.util.EditorFactory;
import org.petstore.util.PetStoreUtil;
import org.petstore.util.RolesUtil;
import org.petstore.util.ValidationException;

public class PetStoreApplication extends JFrame {

	
	public static void main(String... args) {		
		DemoDataInit.init();
		new PetStoreApplication();
	}
	
	private static final long serialVersionUID = 1L;
	private List<Bindable<MOrder>> boundComponents = new ArrayList<Bindable<MOrder>>();
	private DaoOrder daoOrder = DaoOrder.getInstance();
	private JList lstOrders;
	private DefaultListModel mdlOrders;
	private JPanel pnlOrders;
	private BoundTableModel<MOrderDetail> tbmOrderDetails;
	
	public PetStoreApplication() {
		initGUI();
		loadOrders();
		setVisible(true);
	}

	/**
	 * Add a new (bound) editor field
	 */
	private Bindable addEditRow(SopOrder field, int row) throws InstantiationException, IllegalAccessException {
		Insets insets = new Insets(3, 3, 3, 3);
		int west = GridBagConstraints.WEST;
		
		BoundLabel lblAddress = new BoundLabel(field);
		pnlOrders.add(lblAddress, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, west, 0, insets, 3, 3));
				
		@DemoFeature(Feature.binding)
		Bindable component = EditorFactory.getEditor(field);
		pnlOrders.add((JComponent)component, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0, west, GridBagConstraints.BOTH, insets, 3, 3));
		
		boundComponents.add(component);
		return component;
	}

	/**
	 * Create a new order
	 */
	private void btnNewOrderClicked() {
		MOrder order = new MOrder();
		order.setOrderTime(Calendar.getInstance().getTime());
		order.setDetails(new Vector<MOrderDetail>());
		daoOrder.saveOrder(order);
		mdlOrders.addElement(order);
		lstOrders.setSelectedIndex(mdlOrders.getSize() - 1); 
	}
	
	/**
	 * Create a new order detail entry
	 */
	private void btnNewEntryClicked() {
		MOrderDetail detail = new MOrderDetail();
		detail.setQuantity(1L); 
		tbmOrderDetails.fireTableDataChanged();
		getSelectedOrder().getDetails().add(detail);
	}

	/**
	 * Create the the order details table at the right bottom
	 * side of the dialog
	 */
	@DemoFeature(Feature.binding)
	private JScrollPane createOrderDetailsTable() {
		tbmOrderDetails = new BoundTableModel<MOrderDetail>() {
			@Override
			public void setValueAt(Object o, int row, int col) {
				super.setValueAt(o, row, col);
				if (col < 2) {
					//price per unit and sub-total is read-only and updated automatically
					//when the article of the quantity changes
					SopArticle article = (SopArticle)getValueAt(row, 0);
					super.setValueAt(article.price(), row, 2);
					super.setValueAt(article.price() * (Long)getValueAt(row, 1), row, 3);
					fireTableCellUpdated(row, 2); //price per unit
					fireTableCellUpdated(row, 3); //sub-total
				}
			}
		};
		tbmOrderDetails.addColumn(SopOrderDetail.article);
		tbmOrderDetails.addColumn(SopOrderDetail.quantity);
		tbmOrderDetails.addColumn(SopOrderDetail.pricePerUnit);
		tbmOrderDetails.addColumn(SopOrderDetail.subTotal);		
		JTable table = new JTable(tbmOrderDetails, tbmOrderDetails.getColumnModel());
		table.setEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	/**
	 * Initialize the list of orders at the left side of the dialog
	 */
	private JList createOrdersList() {		
		mdlOrders = new DefaultListModel();
		lstOrders = new JList(mdlOrders);
		lstOrders.setPreferredSize(new Dimension(200, 1));
		lstOrders.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				loadValues();
			}
		});
		return lstOrders;
	}
	
	/**
	 * Create the editor panel on the right side of the dialog
	 */
	private JPanel createOrdersPanel() {		
		Insets insets = new Insets(3, 3, 3, 3);
		int west = GridBagConstraints.WEST;
		
		pnlOrders = new JPanel();
		pnlOrders.setLayout(new GridBagLayout());
		
		int row = 0;
		try {
			addEditRow(SopOrder.customer, row++);
			addEditRow(SopOrder.address, row++);
			addEditRow(SopOrder.phone, row++);
			addEditRow(SopOrder.region, row++);
			addEditRow(SopOrder.orderTime, row++);  //read-only
			if (RolesUtil.hasPrivilege(SopPrivilege.viewEarnings)) {
				addEditRow(SopOrder.earnings, row++);  //read-only
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//detail section
		JScrollPane scrollPane = createOrderDetailsTable();
		pnlOrders.add(scrollPane, new GridBagConstraints(0, row++, GridBagConstraints.REMAINDER, 1, 1.0, 1.0, west, GridBagConstraints.BOTH, insets, 3, 3));

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewOrderClicked();
			}
		});
		pnlOrders.add(btnNew, new GridBagConstraints(0, row, 1, 1, 0, 0, west, 0, insets, 3, 3));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveValues();
			}
		});
		pnlOrders.add(btnSave, new GridBagConstraints(1, row, 1, 1, 0, 0, west, 0, insets, 3, 3));
		
		JButton btnNewEntry = new JButton("New Entry");
		btnNewEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewEntryClicked();
			}
		});
		pnlOrders.add(btnNewEntry, new GridBagConstraints(2, row++, 1, 1, 0, 0, west, 0, insets, 3, 3));

		return pnlOrders;
	}

	/**
	 * Get the currently selected order business object
	 */
	private MOrder getSelectedOrder() {
		int selectedPos = lstOrders.getSelectedIndex();
		if (selectedPos < 0) return null;
		return (MOrder)mdlOrders.getElementAt(selectedPos);		
	}

	/**
	 * Build the GUI
	 */
	private void initGUI() {
		setBounds(200, 200, 700, 500);
		setTitle("Soplets Pet Shop Demo");
		setLayout(new BorderLayout(6, 6));
		add(createOrdersList(), BorderLayout.WEST);
		add(createOrdersPanel(), BorderLayout.CENTER);
	}

	/**
	 * Load all orders from the database, and populate the
	 * list with these values
	 */
	private void loadOrders() {
		List<MOrder> orders = daoOrder.loadOrders();
		for (MOrder order : orders) {
			mdlOrders.addElement(order);
		}
	}

	/**
	 * Read the values of one order object, and display it on the GUI
	 */
	private void loadValues() {
		MOrder order = getSelectedOrder();
		if (order == null) return;
		for (Bindable<MOrder> bindable : boundComponents) {
			bindable.load(order);
		}		
		tbmOrderDetails.setData(order.getDetails());
		tbmOrderDetails.fireTableDataChanged();
	}

	/**
	 * Validate the input, and store the values to the database
	 */
	private void saveValues() {
		int selectedPos = lstOrders.getSelectedIndex(); 
		if (selectedPos == -1) return;
		MOrder order = (MOrder)mdlOrders.getElementAt(selectedPos);
		try {
			for (Bindable<MOrder> bindable : boundComponents) {
				BindableEntity boundField = bindable.getBoundField();
				if (!boundField.readOnly()) {
					bindable.save(order);
				}
			}
		} catch (ValidationException ve) {
			PetStoreUtil.showWarning(ve);
			return;
		}
		daoOrder.saveOrder(order);
	}
}
