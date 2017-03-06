package listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CustomerDataModel;
import model.DutyTypeDataModel;
import model.VehicleDataModel;
import beans.Customer2;
import beans.DutyType;
import beans.Vehicle;
import ui.TelcoBOM;
import util.SConstants;

public class TelcoBillComboItemListner implements ItemListener
{

	private String comboId = null;
	public TelcoBillComboItemListner(String comboID)
	{
		this.setComboId(comboID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if(comboId.equalsIgnoreCase(SConstants.COMBO_TAL_BILL_SELECT_DUTY_TYPE))
		{
			double totalKM = 0.0;
			if(TelcoBOM.getComponentMap().containsKey("totalKM"))
			{
				totalKM = (double) TelcoBOM.getComponentMap().get("totalKM");
				JButton btnToatl = (JButton) TelcoBOM.getComponentMap().get(SConstants.GET_TOTAL_BTN_STRING);
				btnToatl.setEnabled(true);
				
				JPanel panelTotalPkgKm = (JPanel) TelcoBOM.getComponentMap().get("panelTotalPkgKm");
				JLabel lblTotalDistanceValue = (JLabel) panelTotalPkgKm.getComponent(2);
				
				String dutyTypeID = e.getItem().toString();
				
				DutyType dutyType = new DutyTypeDataModel().getDutyType(dutyTypeID);
				lblTotalDistanceValue.setText(String.valueOf(dutyType.getKm()));
				
				
				JPanel panelRate = (JPanel) TelcoBOM.getComponentMap().get("panelRate");
				JLabel lblRate = (JLabel) panelRate.getComponent(2);
				lblRate.setText(String.valueOf(dutyType.getPackageRate()));
				
				
				JPanel panelAmount = (JPanel) TelcoBOM.getComponentMap().get("panelAmount");
				JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
				lblAMountValue.setText(String.valueOf(dutyType.getPackageRate()));
				 
				 
				JPanel panelTotalExtraKM = (JPanel) TelcoBOM.getComponentMap().get("panelTotalExtraKM");
				JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(2);
				
				int extraKM = (int) (totalKM-dutyType.getKm());
				lblExtraKM.setText(String.valueOf(extraKM));
				 
				
				JPanel panelExtraKMRate = (JPanel) TelcoBOM.getComponentMap().get("panelExtraKMRate");
				JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(2);
				lblExtraRate.setText(String.valueOf(dutyType.getExtraKmRate()));
				 
				
				
				JPanel panelExtraKMAmount = (JPanel) TelcoBOM.getComponentMap().get("panelExtraKMAmount");
				JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
				lblExtraAMount.setText(String.valueOf(extraKM*dutyType.getExtraKmRate()));
			}
		}
		if(comboId.equalsIgnoreCase(SConstants.COMBO_TAL_BILL_SELECT_VEHICLE_TYPE))
		{
			JPanel panelTOCustomer = (JPanel) TelcoBOM.getComponentMap().get("panelTOCustomer");
			@SuppressWarnings("rawtypes")
			JComboBox customerSelectionCombo = (JComboBox) panelTOCustomer.getComponent(2);
			
			JPanel panelDutyType = (JPanel) TelcoBOM.getComponentMap().get("panelDutyType");
			@SuppressWarnings("rawtypes")
			JComboBox DutySelectionCombo = (JComboBox) panelDutyType.getComponent(2);
			
			JPanel panelVehicleType = (JPanel) TelcoBOM.getComponentMap().get("panelVehicleType");
			@SuppressWarnings("rawtypes")
			JComboBox vehicleSelectionCombo = (JComboBox) panelVehicleType.getComponent(2);
			String cName = customerSelectionCombo.getSelectedItem().toString();
			String vName = vehicleSelectionCombo.getSelectedItem().toString();
			
			String[] dutyTypeArray = new DutyTypeDataModel().getAllDutyTypStringsFor(cName,vName);
			DutySelectionCombo.removeAllItems();
			for (int i = 0; i < dutyTypeArray.length; i++)
			{
				DutySelectionCombo.addItem(dutyTypeArray[i].toString());
			}
			
			Vehicle vehicle = new VehicleDataModel().getVehicle(vName);
			JPanel panelVehicleNumber = (JPanel) TelcoBOM.getComponentMap().get("panelVehicleNumber");
			JTextField txtVehicleText = (JTextField) panelVehicleNumber.getComponent(2);
			txtVehicleText.setText(vehicle.getVehicleNumber());
		}
		
		if(comboId.equalsIgnoreCase(SConstants.COMBO_TAL_BILL_SELECT_CUSTOMER))
		{
			JPanel panelTOCustomer = (JPanel) TelcoBOM.getComponentMap().get("panelTOCustomer");
			@SuppressWarnings("rawtypes")
			JComboBox customerSelectionCombo = (JComboBox) panelTOCustomer.getComponent(2);
			
			JPanel panelDutyType = (JPanel) TelcoBOM.getComponentMap().get("panelDutyType");
			@SuppressWarnings("rawtypes")
			JComboBox DutySelectionCombo = (JComboBox) panelDutyType.getComponent(2);
			
			JPanel panelVehicleType = (JPanel) TelcoBOM.getComponentMap().get("panelVehicleType");
			@SuppressWarnings("rawtypes")
			JComboBox vehicleSelectionCombo = (JComboBox) panelVehicleType.getComponent(2);
			String cName = customerSelectionCombo.getSelectedItem().toString();
			String vName = vehicleSelectionCombo.getSelectedItem().toString();
			
			String[] dutyTypeArray = new DutyTypeDataModel().getAllDutyTypStringsFor(cName,vName);
			DutySelectionCombo.removeAllItems();
			for (int i = 0; i < dutyTypeArray.length; i++)
			{
				DutySelectionCombo.addItem(dutyTypeArray[i].toString());
			}
			Customer2 customer = new CustomerDataModel().getCustomer(cName);
			
			JPanel panelVendorNumber = (JPanel) TelcoBOM.getComponentMap().get("panelVendorNumber");
			JTextField textVCode =  (JTextField) panelVendorNumber.getComponent(2);
			textVCode.setText(customer.getcVendorCode().isEmpty()?"-":customer.getcVendorCode());
			
		}
	}

	/**
	 * @return the comboId
	 */
	public String getComboId() {
		return comboId;
	}

	/**
	 * @param comboId the comboId to set
	 */
	public void setComboId(String comboId) {
		this.comboId = comboId;
	}

}
