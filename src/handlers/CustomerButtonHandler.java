package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import beans.Customer2;
import model.CustomerDataModel;
import exceptions.PopupDialogs;
import ui.AddCustomerUI;
import util.SConstants;

public class CustomerButtonHandler implements ActionListener {

	private JDialog parent = null;
	public CustomerButtonHandler(JDialog owner) 
	{
		this.parent = owner;
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{

		if(event.getActionCommand().equalsIgnoreCase(SConstants.ADD_BTN_STRING))
		{
			Map<String, Object> compMap = AddCustomerUI.getAddCustomerUIComponentMap();
			
			JPanel enterCustomerName =  (JPanel) compMap.get("enterCustomerName");
			final JTextField txtCName = (JTextField) enterCustomerName.getComponent(2);
			
			JPanel enterAddress =  (JPanel) compMap.get("enterAddress");
			final JTextField txtCAdd = (JTextField) enterAddress.getComponent(2);
			
			JPanel enterVendorCode =  (JPanel) compMap.get("enterVendorCode");
			final JTextField txtCVCode = (JTextField) enterVendorCode.getComponent(2);
			
			JPanel cDeptPanel =  (JPanel) compMap.get("cDeptPanel");
			final JTextField txtCDept = (JTextField) cDeptPanel.getComponent(2);
			if(!txtCName.getText().isEmpty()|| !txtCAdd.getText().isEmpty() ||!txtCVCode.getText().isEmpty() || !txtCDept.getText().isEmpty())
			{
				CustomerDataModel model = new CustomerDataModel();
				int uid = model.getAllCustomers().length;
				Customer2 customer = new Customer2((uid+1),txtCName.getText(), txtCAdd.getText(),txtCVCode.getText(),txtCDept.getText());
				// TODO: add to db
				model.addCustomer(customer);
				new PopupDialogs("Customer Added Successfully",PopupDialogs.PLAIN_MESSAGE);
				parent .dispose();
			}
			else
			{
				new PopupDialogs("Please fill all the fields", PopupDialogs.ERROR_MESSAGE);
			}
		}
		
		if(event.getActionCommand().equalsIgnoreCase(SConstants.CANCEL_BTN_STRING))
		{
			parent.dispose();
		}
	
	}

}
