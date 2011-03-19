package org.petstore.gui;

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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.petstore.Feature;
import org.petstore.Feature.DemoFeature;
import org.petstore.dao.DaoCustomer;
import org.petstore.dao.DaoOrder;
import org.petstore.entity.MCustomer;
import org.petstore.entity.MOrder;
import org.petstore.entity.MOrderDetail;
import org.petstore.gui.component.Bindable;
import org.petstore.gui.component.BoundComboBox;
import org.petstore.gui.component.BoundLabel;
import org.petstore.gui.component.BoundTableModel;
import org.petstore.gui.component.BoundTextField;
import org.petstore.soplets.SopOrder;
import org.petstore.soplets.SopOrderDetail;
import org.petstore.soplets.SopPrivileges;
import org.petstore.soplets.SopValidators;
import org.petstore.util.BindableEntity;
import org.petstore.util.PizzaUtil;
import org.petstore.util.RolesUtil;
import org.petstore.util.ValidationException;

public class FrmMain extends JFrame {
	
	public static void main(String... args) {
		FrmMain frm = new FrmMain();
		frm.setVisible(true);
	}
	
	private DefaultListModel mdlOrders;
	private List<Bindable<MOrder>> boundComponents = new ArrayList<Bindable<MOrder>>();
	private JList lstOrders;
	private DaoOrder daoOrder = DaoOrder.getInstance();
	private DaoCustomer daoCustomer = DaoCustomer.getInstance();
	private BoundTableModel<MOrderDetail> tbmOrderDetails;
	private JMenuBar menuBar;
	private JPanel pnlOrders;
	
	public FrmMain() {
		initGUI();
		loadOrders();
	}
	
	private void initGUI() {
		setBounds(200, 200, 700, 500);
		setTitle("My Pet Shop");
		setLayout(new BorderLayout(6, 6));
		createMenu();
		add(createOrdersList(), BorderLayout.WEST);
		add(createOrdersPanel(), BorderLayout.CENTER);
	}

	private void createMenu() {
		JMenu mnuMain = new JMenu();
		
	}
	
	private JPanel createOrdersPanel() {		
		Insets insets = new Insets(3, 3, 3, 3);
		int west = GridBagConstraints.WEST;
		
		pnlOrders = new JPanel();
		pnlOrders.setLayout(new GridBagLayout());
		
		int row = 0;
		try {
			BoundComboBox<MOrder> cboCustomer = (BoundComboBox<MOrder>)
				addEditRow(SopOrder.customer, row++, BoundComboBox.class);
			for (MCustomer customer : daoCustomer.loadCustomer()) {
				cboCustomer.addItem(customer);
			}
			addEditRow(SopOrder.address, row++, BoundTextField.class);
			addEditRow(SopOrder.phone, row++, BoundTextField.class);
			addEditRow(SopOrder.region, row++, BoundComboBox.class);
			addEditRow(SopOrder.orderTime, row++, BoundTextField.class);  //read-only
			if (RolesUtil.hasPrivilege(SopPrivileges.viewEarnings)) {
				addEditRow(SopOrder.earnings, row++, BoundTextField.class);  //read-only
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//detail section
		JTable table = createOrderDetailsTable();
		pnlOrders.add(table, new GridBagConstraints(0, row++, GridBagConstraints.REMAINDER, 1, 1.0, 1.0, west, GridBagConstraints.BOTH, insets, 3, 3));

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewClicked();
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
		pnlOrders.add(btnSave, new GridBagConstraints(1, row++, 1, 1, 0, 0, west, 0, insets, 3, 3));
		return pnlOrders;
	}

	private void btnNewClicked() {
		MOrder order = new MOrder();
		order.setOrderTime(Calendar.getInstance().getTime());
		daoOrder.saveOrder(order);
		mdlOrders.addElement(order);
		lstOrders.setSelectedIndex(mdlOrders.getSize() - 1); 
	}
	
	private Bindable addEditRow(SopOrder field, int row, Class componentClass) throws InstantiationException, IllegalAccessException {
		Insets insets = new Insets(3, 3, 3, 3);
		int west = GridBagConstraints.WEST;
		
		BoundLabel lblAddress = new BoundLabel(field);
		pnlOrders.add(lblAddress, new GridBagConstraints(0, row, 1, 1, 0.0, 0.0, west, 0, insets, 3, 3));
		
		Bindable component = (Bindable)componentClass.newInstance();
		component.bind(field);
		pnlOrders.add((JComponent)component, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0, west, GridBagConstraints.BOTH, insets, 3, 3));
		
		boundComponents.add(component);
		return component;
	}
	
	private JTable createOrderDetailsTable() {
		@DemoFeature(Feature.binding) Object dummyFeature;  //XXX needed for inline annotation
		tbmOrderDetails = new BoundTableModel<MOrderDetail>();
		tbmOrderDetails.addColumn(SopOrderDetail.article);
		tbmOrderDetails.addColumn(SopOrderDetail.pricePerUnit);
		tbmOrderDetails.addColumn(SopOrderDetail.quantity);
		tbmOrderDetails.addColumn(SopOrderDetail.subTotal);		
		JTable tblOrderDetails = new JTable(tbmOrderDetails, tbmOrderDetails.getColumnModel());		
		return tblOrderDetails;
	}

	private void loadOrders() {
		List<MOrder> orders = daoOrder.loadOrders();
		for (MOrder order : orders) {
			mdlOrders.addElement(order);
		}
	}

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
			PizzaUtil.showWarning(ve);
			return;
		}
		daoOrder.saveOrder(order);
	}

	private void loadValues() {
		int selectedPos = lstOrders.getSelectedIndex(); 
		MOrder order = (MOrder)mdlOrders.getElementAt(selectedPos);
		for (Bindable<MOrder> bindable : boundComponents) {
			bindable.load(order);
		}		
	}

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
}
