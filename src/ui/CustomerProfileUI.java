package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import handlers.AdminButtonHandler;
import util.Registry;
import util.SConstants;
import util.Utils;

public class CustomerProfileUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String,Object> CustomerProfileUIComponentsMap = new HashMap<String,Object>();
	private Registry reg = SConstants.reg;
	private String action;
	Connection conn = null;
	public static Map<String, Object> getComponentMap()
	{
		return CustomerProfileUIComponentsMap;
	}
	private UITemplates templates = new UITemplates();
	public CustomerProfileUI(JDialog owner,String action)
	{
		super(owner);
		this.action=action;
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,"Admin");
		initUI(owner);
		owner.setVisible(true);
		
		
	}
	private void initUI(JDialog owner)
	{
		JPanel panelCustProfileBody = new JPanel();
		panelCustProfileBody.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 100);
		//panelCustProfileBody.setBackground(Color.cyan);
		panelCustProfileBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelCustProfileBody.setLayout(new FlowLayout());
		
		if(action.equals("Add"))
		{
			JPanel panelVendorName = templates.getLabelWithTextField("panelVendorName",reg.getValueFor("C_Vendor_NAME"),"Enter Vendor Name",8,false, CustomerProfileUIComponentsMap);
	
			//panelVendorNumber.setBounds(550, 170, 400, 35);
			panelCustProfileBody.add(panelVendorName);
			
			owner.add(panelCustProfileBody);
			
			JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber",reg.getValueFor("L_Vendor_Code"),"Enter Vendor No",8,false, CustomerProfileUIComponentsMap);
			//panelVendorNumber.setBounds(550, 170, 400, 35);
			panelCustProfileBody.add(panelVendorNumber);
			
			owner.add(panelCustProfileBody);
			
			JPanel panelVendorAddress = templates.getLabelWithTextField("panelVendorAddress",reg.getValueFor("C_VENDOR_ADDESSS"),"Enter Vendor Address",8,false, CustomerProfileUIComponentsMap);
			//panelVendorNumber.setBounds(550, 170, 400, 35);
			panelCustProfileBody.add(panelVendorAddress);
				
		}
		else
		{
			
			JPanel panelTOCustomer = templates.getLabelWithCombo("panelTOCustomer",reg.getValueFor("C_Vendor_NAME"),reg.getValueFor("ID_TO_CUSTOMER_Combo"), SConstants.CUSSTOMER_LIST, CustomerProfileUIComponentsMap);
			//panelCustomer.setBounds(5, 3, 120, 32);
			panelCustProfileBody.add(panelTOCustomer);
			
			JPanel panelVendorName = templates.getLabelWithTextField("panelVendorName",reg.getValueFor("C_Vendor_NAME"),"Enter Vendor Name",8,false, CustomerProfileUIComponentsMap);
			//panelVendorNumber.setBounds(550, 170, 400, 35);
			panelCustProfileBody.add(panelVendorName);
			
			JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber",reg.getValueFor("L_Vendor_Code"),"Enter Vendor No",8,false, CustomerProfileUIComponentsMap);
			//panelVendorNumber.setBounds(550, 170, 400, 35);
			panelCustProfileBody.add(panelVendorNumber);
			
			JPanel panelVendorAddress = templates.getLabelWithTextField("panelVendorAddress",reg.getValueFor("C_VENDOR_ADDESSS"),"Enter Vendor Address",8,false, CustomerProfileUIComponentsMap);
			//panelVendorNumber.setBounds(550, 170, 400, 35);
			panelCustProfileBody.add(panelVendorAddress);
			
			owner.add(panelCustProfileBody);
			
			JButton button_search = new JButton();
			button_search.setText(SConstants.CUST_EDIT_BUTTON);
			button_search.addActionListener(new AdminButtonHandler(owner));
			panelCustProfileBody.add(button_search);
			
		}
		owner.add(panelCustProfileBody);
	}
}
