package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import uit.billgen.handlers.CustomerButtonHandler;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class AddCustomer extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UITemplates templates = new UITemplates();
	private static Map<String, Object> addCustomerUIComponentMap;
	
	public AddCustomer(JDialog owner, String windowName)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,windowName);
		addCustomerUIComponentMap = new HashMap<String, Object>();
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(final JDialog owner)
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new GridLayout(2, 2));
		bodyPanel.setBounds(10, 50, 550, 150);
		
		JPanel enterCustomerName = templates.getLabelWithTextField("enterCustomerName", "Enter Customer Name", "Enter Customer Name here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(enterCustomerName);
		
		
		JPanel enterAddress = templates.getLabelWithTextField("enterAddress", "Enter Customer Address", "Enter Customer Add here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(enterAddress);
		
		
		JPanel enterVendorCode = templates.getLabelWithTextField("enterVendorCode", "Enter Vendor Code", "Enter Vendor Code here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(enterVendorCode);
		
		
		JPanel cDeptPanel = templates.getLabelWithTextField("cDeptPanel", "Enter Dept", "Enter Dept here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(cDeptPanel);
		
		
		JButton btnAddCustomer = new JButton(SConstants.ADD_BTN_STRING);
		btnAddCustomer.setBounds(150, 200, 100, 30);
		btnAddCustomer.addActionListener(new CustomerButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(350, 200, 100, 30);
		btnCancel.addActionListener(new CustomerButtonHandler(owner));
		owner.add(bodyPanel);
		owner.add(btnAddCustomer);
		owner.add(btnCancel);
		
	}
	public static Map<String, Object> getAddCustomerUIComponentMap() {
		return addCustomerUIComponentMap;
	}
	public void setAddCustomerUIComponentMap(
			Map<String, Object> addCustomerUIComponentMap) {
		AddCustomer.addCustomerUIComponentMap = addCustomerUIComponentMap;
	}
	
}
