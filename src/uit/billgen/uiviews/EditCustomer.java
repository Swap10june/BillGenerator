package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.beans.Customer2;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.handlers.CustomerButtonHandler;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class EditCustomer extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Map<String, Object> editCustomerUIComponent ;
	
	private UITemplates templates = new UITemplates();
	private static CustomerDataModel model = null;
	private static List<Object> list = new ArrayList<Object>();
	
	public EditCustomer(JDialog owner, String windowName) 
	{
		super(owner);
		editCustomerUIComponent = new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,windowName);
		initUI(owner);
		owner.setVisible(true);
	}
	
	private void initUI(final JDialog owner) 
	{
		
		setModel(new CustomerDataModel());
		System.out.println("");
		String [] customerList = getModel().getAllCustomerNames();
		
		JPanel topPanel  = new JPanel();
		topPanel.setBounds(50, 30, owner.getWidth(), 30);
		topPanel.setLayout(new GridLayout(1,2));
		
		JPanel panelSelectCustomer = templates.getLabelWithComboWOListner("panelSelectCustomer", "Select Customer", customerList, editCustomerUIComponent,SConstants.UI_LABEL_NAME_SIZE_25);
		topPanel.add(panelSelectCustomer);
		owner.add(topPanel);
		@SuppressWarnings("unchecked")
		final JComboBox<String> comboEditCustomer= (JComboBox<String>) panelSelectCustomer.getComponent(2);
		comboEditCustomer.addItemListener(new ItemListener()
		{
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				
				JPanel panelCombo = (JPanel) editCustomerUIComponent.get("panelSelectCustomer");
				@SuppressWarnings("rawtypes")
				JComboBox comboSelectCustomer = (JComboBox) panelCombo.getComponent(2);
				Customer2 customer = getModel().getCustomer(comboSelectCustomer.getSelectedItem().toString());
				for (int i = 0; i < list.size(); i++)
				{
					if(list.get(i) instanceof JPanel)
						((JPanel) list.get(i)).setVisible(true);
					if(list.get(i) instanceof JButton)
						((JButton) list.get(i)).setEnabled(true);
				}
				JPanel enterVehicleName = (JPanel) editCustomerUIComponent.get("enterCustomerName");
				JTextField vName = (JTextField) enterVehicleName.getComponent(2);
				vName.setText(customer.getcName());
				
				JPanel enterAddress = (JPanel) editCustomerUIComponent.get("enterAddress");
				JTextField vNo = (JTextField) enterAddress.getComponent(2);
				vNo.setText(customer.getcAddress());
				
				JPanel enterVendorCode = (JPanel) editCustomerUIComponent.get("enterVendorCode");
				JTextField cname = (JTextField) enterVendorCode.getComponent(2);
				cname.setText(customer.getcVendorCode());
				
				JPanel cDeptPanel = (JPanel) editCustomerUIComponent.get("cDeptPanel");
				JTextField cDept = (JTextField) cDeptPanel.getComponent(2);
				cDept.setText(customer.getCustomerDepartmentName());
				
				
			}
		});
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBounds(0, 60, owner.getWidth(), 150);
		bodyPanel.setLayout(new GridLayout(4, 1));
		
		JPanel enterCustomerName = templates.getLabelWithTextField("enterCustomerName", "Enter Customer Name", "Enter Customer Name here", SConstants.TEXT_COL_SIZE_15, false, editCustomerUIComponent,SConstants.UI_LABEL_NAME_SIZE_25);
		bodyPanel.add(enterCustomerName);
		enterCustomerName.setVisible(false);
		list.add(enterCustomerName);
		
		JPanel enterAddress = templates.getLabelWithTextField("enterAddress", "Enter Customer Address", "Enter Customer Add here", SConstants.TEXT_COL_SIZE_15, false, editCustomerUIComponent,SConstants.UI_LABEL_NAME_SIZE_25);
		bodyPanel.add(enterAddress);
		enterAddress.setVisible(false);
		list.add(enterAddress);
		
		JPanel enterVendorCode = templates.getLabelWithTextField("enterVendorCode", "Enter Vendor Code", "Enter Vendor Code here",  SConstants.TEXT_COL_SIZE_15, false, editCustomerUIComponent,SConstants.UI_LABEL_NAME_SIZE_25);
		bodyPanel.add(enterVendorCode);
		enterVendorCode.setVisible(false);
		list.add(enterVendorCode);
		
		JPanel cDeptPanel = templates.getLabelWithTextField("cDeptPanel", "Enter Dept", "Enter Dept here",  SConstants.TEXT_COL_SIZE_15, false, editCustomerUIComponent,SConstants.UI_LABEL_NAME_SIZE_25);
		bodyPanel.add(cDeptPanel);
		cDeptPanel.setVisible(false);
		list.add(cDeptPanel);
		
		JButton btnEdit = new JButton(SConstants.EDIT_BTN_STRING);
		btnEdit.setBounds(100, 220, 150, 30);
		btnEdit.setEnabled(false);
		list.add(btnEdit);
		btnEdit.addActionListener(new CustomerButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(300, 220, 150, 30);
		btnCancel.setEnabled(false);
		list.add(btnCancel);
		btnCancel.addActionListener(new CustomerButtonHandler(owner));
		
		owner.add(bodyPanel);
		owner.add(btnEdit);
		owner.add(btnCancel);
	}

	public static List<Object> getList() {
		return list;
	}

	/**
	 * @return the model
	 */
	public static CustomerDataModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(CustomerDataModel model) {
		EditCustomer.model = model;
	}
	public static Map<String, Object> getEditCustomerUIComponent() {
		return editCustomerUIComponent;
	}



	public static void setEditCustomerUIComponent(
			Map<String, Object> editCustomerUIComponent) {
		EditCustomer.editCustomerUIComponent = editCustomerUIComponent;
	}

}
