package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;

import exceptions.PopupDialogs;
import model.CustomerDataModel;
import model.DutyTypeDataModel;
import model.VehicleDataModel;
import beans.Customer2;
import beans.DutyType;
import beans.Vehicle;
import ui.AddCustomerUI;
import ui.AddVehicleUI;
import ui.AddDutyTypeUI;
import ui.EditDutyTypeUI;
import ui.EditVehicleUI;
import util.DBUtils;
import util.SConstants;

public class AdminButtonHandler implements ActionListener {

	public AdminButtonHandler(JDialog owner)
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case SConstants.ADD_CUSTOMER_BTN_STRING:
			{
				new AddCustomerUI(new javax.swing.JDialog(),SConstants.ADD_CUSTOMER_BTN_STRING);
			}
			
			break;
			case SConstants.EDIT_CUSTOMER_BTN_STRING:
			{
				//new CustomerProfileUI(new javax.swing.JDialog(),"Edit");
			}
			
			break;
			case SConstants.ADD_VEHICLE_BTN_STRING:
			{
				new AddVehicleUI(new javax.swing.JDialog(),SConstants.ADD_VEHICLE_BTN_STRING);
			}
			
			break;
			case SConstants.EDIT_VEHICLE_BTN_STRING:
			{
				new EditVehicleUI(new javax.swing.JDialog(),SConstants.EDIT_VEHICLE_BTN_STRING);
			}
			
			break;
			case SConstants.ADD_DUTY_TYPE_BTN_STRING:
			{
				new AddDutyTypeUI(new javax.swing.JDialog(),SConstants.ADD_DUTY_TYPE_BTN_STRING);
			}
			
			break;
			case SConstants.EDIT_DUTY_TYPE_BTN_STRING:
			{
				new EditDutyTypeUI(new javax.swing.JDialog(),SConstants.EDIT_DUTY_TYPE_BTN_STRING);
			}
			
			break;
			/*case SConstants.CUST_EDIT_BUTTON:
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
			
			break;*/
			
			case SConstants.UPDATE_BTN_STRING:
			{
				//updating vehicle
				List<Vehicle> vehicleList = new VehicleDataModel().getAllVehicles();
				new DBUtils().updateOrInsertVehicleIntoDB(vehicleList);
				
				//updating customer
				List<Customer2> customerList = new CustomerDataModel().getAllCustomers();
				new DBUtils().updateOrInsertCustomerIntoDB(customerList);
				
				//updating dutyType
				List<DutyType> dutyTypeList = new DutyTypeDataModel().getAllDutyTypes();
				new DBUtils().updateOrInsertDutyTypesIntoDB(dutyTypeList);
				
				new PopupDialogs("Updated Successfully", PopupDialogs.INFORMATION_MESSAGE);
			}
			
			break;
		default:
			break;
		}
	}

}
