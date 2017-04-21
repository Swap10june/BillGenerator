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
				if(new CustomerDataModel().getAllCustomerNames().length == 0)
				{
					new PopupDialogs("Sorry..!No Customer added to Edit, Please Add Customer First",PopupDialogs.INFORMATION_MESSAGE  );
				}
				else
					{
						new EditCustomer(new javax.swing.JDialog(), SConstants.EDIT_CUSTOMER_BTN_STRING);
					}
			}
			
			break;
			case SConstants.ADD_VEHICLE_BTN_STRING:
			{
				new AddVehicleUI(new javax.swing.JDialog(),SConstants.ADD_VEHICLE_BTN_STRING);
			}
			
			break;
			case SConstants.EDIT_VEHICLE_BTN_STRING:
			{
				if(new VehicleDataModel().getAllVehicleNames().length == 0)
				{
					new PopupDialogs("Sorry..!No Vehicle added to Edit, Please Add Vehicle First",PopupDialogs.INFORMATION_MESSAGE  );
				}
				else
				{
					new EditVehicleUI(new javax.swing.JDialog(),SConstants.EDIT_VEHICLE_BTN_STRING);
				}
			}
			
			break;
			case SConstants.ADD_DUTY_TYPE_BTN_STRING:
			{
				new AddDutyTypeUI(new javax.swing.JDialog(),SConstants.ADD_DUTY_TYPE_BTN_STRING);
			}
			
			break;
			case SConstants.EDIT_DUTY_TYPE_BTN_STRING:
			{
				if(new DutyTypeDataModel().getAllDutyTypeStrings().length == 0)
				{
					new PopupDialogs("Sorry..!No DutyType added to Edit, Please Add DutyType First",PopupDialogs.INFORMATION_MESSAGE  );
				}
				else
				{
					new EditDutyType(new javax.swing.JDialog(),SConstants.EDIT_DUTY_TYPE_BTN_STRING);
				}
			}
			
			break;
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
