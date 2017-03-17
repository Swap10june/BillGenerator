package uit.billgen.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.beans.Customer2;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.exceptions.PopupDialogs;
import uit.billgen.uiviews.AddCustomer;
import uit.billgen.uiviews.EditCustomer;
import uit.billgen.util.Dao;
import uit.billgen.util.SConstants;

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
			Map<String, Object> compMap = AddCustomer.getAddCustomerUIComponentMap();
			
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
				int uid = model.getAllCustomerNames().length;
				Customer2 customer = new Customer2((uid+1),txtCName.getText(), txtCAdd.getText(),txtCVCode.getText(),txtCDept.getText());
				// TODO: add to db
				model.addCustomer(customer);
				new PopupDialogs(SConstants.MSG_ADDED_SUCCESSFULLY,PopupDialogs.PLAIN_MESSAGE);
				parent .dispose();
			}
			else
			{
				new PopupDialogs(SConstants.MSG_PLZ_FILL_ALL_THE_FIELDS, PopupDialogs.ERROR_MESSAGE);
			}
		}
		if(event.getActionCommand().equalsIgnoreCase(SConstants.EDIT_BTN_STRING))
		{
			Map<String, Object> compoMap = EditCustomer.getEditCustomerUIComponent();
			
			JPanel panelSelectCustomer = (JPanel) compoMap.get("panelSelectCustomer");
			@SuppressWarnings("unchecked")
			JComboBox<String> comboEditCustomer  = (JComboBox<String>) panelSelectCustomer.getComponent(2);
			
			Customer2 oldCustomer = EditCustomer.getModel().getCustomer(comboEditCustomer.getSelectedItem().toString());
			
			JPanel enterCustomerName = (JPanel) compoMap.get("enterCustomerName");
			JTextField cNameText = (JTextField) enterCustomerName.getComponent(2);
			String cName = cNameText.getText();
			
			
			JPanel enterAddress = (JPanel) compoMap.get("enterAddress");
			JTextField cAddText = (JTextField) enterAddress.getComponent(2);
			String cAdd = cAddText.getText();
			
			JPanel enterVendorCode = (JPanel) compoMap.get("enterVendorCode");
			JTextField cVCodeText = (JTextField) enterVendorCode.getComponent(2);
			String cVCode = cVCodeText.getText();
			
			JPanel cDeptPanel = (JPanel) compoMap.get("cDeptPanel");
			JTextField cDeptText = (JTextField) cDeptPanel.getComponent(2);
			String cDept = cDeptText.getText();
		
			Customer2 customer = new Customer2(oldCustomer.getUid(), cName, cAdd, cVCode, cDept);
			EditCustomer.getModel().updateAttributeValue(customer);
			new Dao().editCustomer(customer);
			new PopupDialogs(SConstants.MSG_UPDATED_SUCCESSFULLY, PopupDialogs.PLAIN_MESSAGE);
			parent.dispose();
		}
		if(event.getActionCommand().equalsIgnoreCase(SConstants.CANCEL_BTN_STRING))
		{
			parent.dispose();
		}
	
	}

}
