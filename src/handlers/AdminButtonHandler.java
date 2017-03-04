package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;

import beans.Customer1;
import ui.AddCustomerUI;
import ui.AddVehicleUI;
import ui.CustomerProfileUI;
import ui.AddDutyTypeUI;
import ui.EditDutyTypeUI;
import ui.EditVehicleUI;
import util.Dao;
import util.SConstants;
import util.Utils;

public class AdminButtonHandler implements ActionListener {

	public AdminButtonHandler(JDialog owner)
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case SConstants.ADD_CUST_WIN_NAME:
			{
				new AddCustomerUI(new javax.swing.JDialog(),"Add New Customer");
			}
			
			break;
			case SConstants.EDIT_CUST_WIN_NAME:
			{
				new CustomerProfileUI(new javax.swing.JDialog(),"Edit");
			}
			
			break;
			case SConstants.ADD_VEHICLE_WIN_NAME:
			{
				new AddVehicleUI(new javax.swing.JDialog(),"Add New Vehicle");
			}
			
			break;
			case SConstants.EDIT_VEHICLE_WIN_NAME:
			{
				new EditVehicleUI(new javax.swing.JDialog(),"Edit Vehicle");
			}
			
			break;
			case SConstants.ADD_DUTY_TYPE:
			{
				new AddDutyTypeUI(new javax.swing.JDialog(),"Add");
			}
			
			break;
			case SConstants.EDIT_DUTY_TYPE:
			{
				new EditDutyTypeUI(new javax.swing.JDialog());
			}
			
			break;
			case SConstants.CUST_EDIT_BUTTON:
			{
				Utils util = Utils.getUtilityInstance();
				Map<String, Object> componentMap = CustomerProfileUI.getComponentMap();
				String vendorName = util.getStringValueFromPanelComponent((JPanel)componentMap.get("panelVendorName"), 1);
				String vendorNumber = util.getStringValueFromPanelComponent((JPanel)componentMap.get("panelVendorNumber"), 1);
				String vendorAddress = util.getStringValueFromPanelComponent((JPanel)componentMap.get("panelVendorAddress"), 1);
				String oldVendorName = util.getStringValueFromPanelComponent((JPanel)componentMap.get("panelTOCustomer"), 1);
				Dao dao = new Dao();
				Customer1 customerBean = new Customer1(vendorName,vendorAddress,vendorNumber,oldVendorName);
				dao.editCustomer(customerBean);
				
			}
			
			break;
			

		default:
			break;
		}
	}

}
