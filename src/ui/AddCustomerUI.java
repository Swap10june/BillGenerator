package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CustomerDataModel;
import beans.Customer2;
import exceptions.PopupDialogs;
import util.Utils;

public class AddCustomerUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String UI_ID = "AddCustomerUI"; 
	private UITemplates templates = new UITemplates();
	private Map<String, Object> addCustomerUIComponentMap;
	public AddCustomerUI(JDialog owner, String windowName)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,windowName);
		addCustomerUIComponentMap = new HashMap<String, Object>();
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(JDialog owner)
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new GridLayout(2, 2));
		bodyPanel.setBounds(10, 50, 500, 150);
		
		JPanel enterCustomerName = templates.getLabelWithTextField("enterCustomerName", "Enter Customer Name", "Enter Customer Name here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(enterCustomerName);
		JTextField txtCName = (JTextField) enterCustomerName.getComponent(2);
		
		JPanel enterAddress = templates.getLabelWithTextField("enterAddress", "Enter Customer Address", "Enter Customer Add here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(enterAddress);
		JTextField txtCAdd = (JTextField) enterAddress.getComponent(2);
		
		JPanel enterVendorCode = templates.getLabelWithTextField("enterVendorCode", "Enter Vendor Code", "Enter Vendor Code here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(enterVendorCode);
		JTextField txtCVCode = (JTextField) enterVendorCode.getComponent(2);
		
		JPanel cDeptPanel = templates.getLabelWithTextField("cDeptPanel", "Enter Dept", "Enter Dept here", 10, false, addCustomerUIComponentMap);
		bodyPanel.add(cDeptPanel);
		JTextField txtCDept = (JTextField) cDeptPanel.getComponent(2);
		
		JButton btnLogin = new JButton("Add");
		btnLogin.setBounds(150, 200, 100, 30);
		btnLogin.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(!txtCName.getText().isEmpty()|| !txtCAdd.getText().isEmpty() ||!txtCVCode.getText().isEmpty() || !txtCDept.getText().isEmpty())
				{
					CustomerDataModel model = new CustomerDataModel();
					int uid = model.getAllCustomers().length;
					Customer2 customer = new Customer2((uid+1),txtCName.getText(), txtCAdd.getText(),txtCVCode.getText(),txtCDept.getText());
					// TODO: add to db
					model.addCustomer(customer);
					new PopupDialogs("Customer Added Successfully",PopupDialogs.PLAIN_MESSAGE);
					owner .dispose();
				}
				else
				{
					new PopupDialogs("Please fill all the fields", PopupDialogs.ERROR_MESSAGE);
				}
			}
		});
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(350, 200, 100, 30);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				owner.dispose();
			}
		});
		owner.add(bodyPanel);
		owner.add(btnLogin);
		owner.add(btnCancel);
		
	}
	
}
