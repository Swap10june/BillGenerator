package uit.billgen.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;

import uit.billgen.beans.Customer2;
import uit.billgen.beans.DutyType;
import uit.billgen.beans.Vehicle;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.DutyTypeDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.exceptions.PopupDialogs;
import uit.billgen.uiviews.AddCustomer;
import uit.billgen.uiviews.AddDutyTypeUI;
import uit.billgen.uiviews.AddVehicleUI;
import uit.billgen.uiviews.EditCustomer;
import uit.billgen.uiviews.EditDutyType;
import uit.billgen.uiviews.EditVehicleUI;
import uit.billgen.util.Dao;
import uit.billgen.util.SConstants;

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
				new AddCustomer(new javax.swing.JDialog(),SConstants.ADD_CUSTOMER_BTN_STRING);
			}
			
			break;
			case SConstants.EDIT_CUSTOMER_BTN_STRING:
			{
				new EditCustomer(new javax.swing.JDialog(), SConstants.EDIT_CUSTOMER_BTN_STRING);
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
				new EditDutyType(new javax.swing.JDialog(),SConstants.EDIT_DUTY_TYPE_BTN_STRING);
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
				Dao daoObect = new Dao();
				//updating vehicle
				List<Vehicle> vehicleList = new VehicleDataModel().getAllVehicles();
				daoObect.updateOrInsertVehicleIntoDB(vehicleList);
				
				//updating customer
				List<Customer2> customerList = new CustomerDataModel().getAllCustomers();
				daoObect.updateOrInsertCustomerIntoDB(customerList);
				
				//updating dutyType
				List<DutyType> dutyTypeList = new DutyTypeDataModel().getAllDutyTypes();
				daoObect.updateOrInsertDutyTypesIntoDB(dutyTypeList);
				
				new PopupDialogs(SConstants.MSG_UPDATED_SUCCESSFULLY, PopupDialogs.INFORMATION_MESSAGE);
			}
			
			break;
		default:
			break;
		}
	}

}
