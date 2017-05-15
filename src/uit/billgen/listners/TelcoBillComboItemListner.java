package uit.billgen.listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.beans.Customer;
import uit.billgen.beans.Vehicle;
import uit.billgen.constants.SConstants;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.DutyTypeDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.uiviews.ExtraCabBillUI;

public class TelcoBillComboItemListner implements ItemListener
{

	private String comboId = null;
	public TelcoBillComboItemListner(String comboID)
	{
		this.setComboId(comboID);
		new DutyTypeDataModel();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		/*if(comboId.equalsIgnoreCase(SConstants.COMBO_TAL_BILL_SELECT_DUTY_TYPE))
		{
			double totalKM = 0.0;
			if(ExtraCabBillUI.getComponentMap().containsKey(SConstants.TOTAL_KM_ATTR))
			{
				totalKM = (double) ExtraCabBillUI.getComponentMap().get(SConstants.TOTAL_KM_ATTR);
				JButton btnToatl = (JButton) ExtraCabBillUI.getComponentMap().get(SConstants.GET_TOTAL_BTN_STRING);
				btnToatl.setEnabled(true);
				
				JPanel panelTotalPkgKm = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTotalPkgKm");
				JLabel lblTotalDistanceValue = (JLabel) panelTotalPkgKm.getComponent(2);
				
				String dutyTypeID = e.getItem().toString();
				
				DutyType dutyType = m_dutyTypeModel.getDutyType(dutyTypeID);
				lblTotalDistanceValue.setText(String.valueOf(dutyType.getKm()));
				
				JPanel panelRate = (JPanel) ExtraCabBillUI.getComponentMap().get("panelRate");
				JLabel lblRate = (JLabel) panelRate.getComponent(2);
				lblRate.setText(String.valueOf(dutyType.getPackageRate()));
				
				
				JPanel panelAmount = (JPanel) ExtraCabBillUI.getComponentMap().get("panelAmount");
				JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
				lblAMountValue.setText(String.valueOf(dutyType.getPackageRate()));
				 
				int extraKM = (int) (totalKM-dutyType.getKm());
				if(extraKM>0)
				{
				
					JPanel panelTotalExtraKM = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTotalExtraKM");
					JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(2);
					
					
					lblExtraKM.setText(String.valueOf(extraKM));
				
					JPanel panelExtraKMRate = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraKMRate");
					JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(2);
					lblExtraRate.setText(String.valueOf(dutyType.getExtraKmRate()));
					 
					
					
					JPanel panelExtraKMAmount = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraKMAmount");
					JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
					lblExtraAMount.setText(String.valueOf(extraKM*dutyType.getExtraKmRate()));
				}
				else
				{
					JPanel panelTotalExtraKM = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTotalExtraKM");
					JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(2);
					
					
					lblExtraKM.setText(String.valueOf(0));
					 
					
					
					JPanel panelExtraKMRate = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraKMRate");
					JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(2);
					lblExtraRate.setText(String.valueOf(0));
					 
					
					
					JPanel panelExtraKMAmount = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraKMAmount");
					JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
					lblExtraAMount.setText(String.valueOf(0));
				}
				JPanel panelStartTime = (JPanel) ExtraCabBillUI.getComponentMap().get("panelStartTime");
				JSpinner spinnerStartTime= (JSpinner) panelStartTime.getComponent(2);
				
				String [] arr = spinnerStartTime.getValue().toString().split(" ");
				
				JPanel panelEndTime = (JPanel) ExtraCabBillUI.getComponentMap().get("panelEndTime");
				JSpinner spinnerEndTime= (JSpinner) panelEndTime.getComponent(2);
				String [] arr2 = spinnerEndTime.getValue().toString().split(" ");
				
				String time1 = arr[3];
				String time2 = arr2[3];

				try 
				{
					SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					Date date1 = format.parse(time1);
					Date date2 = format.parse(time2);
					long difference = date2.getTime() - date1.getTime();
					System.out.println(difference);
					int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(difference);

					if(minutes<0)minutes += 1440; 
					System.out.println(minutes);
					int hour = minutes/60;
					int finalmin = minutes%60;
					
					double extraHour = hour-dutyType.getHours();
					System.out.println("");
					if(extraHour>0)
					{
						if(finalmin>=15 &&finalmin<30)
							extraHour+=0.25;
						
						if(finalmin>=30 &&finalmin<45)
							extraHour+=0.50;
						
						if(finalmin>=45 &&finalmin<60)
							extraHour+=0.75;
						JPanel panelExtraHourLabel = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraHourLabel");
						JLabel lbl = (JLabel) panelExtraHourLabel.getComponent(2);
						lbl.setText(String.valueOf(hour)+":"+String.valueOf(finalmin));
						
						String vName = dutyType.getVehicleType();
						String cName = dutyType.getCustomerName();
						Vehicle vehicle = new VehicleDataModel().getVehicleFor(vName,cName);
						JPanel panelTotalExtraHour = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTotalExtraHour");
						JLabel lblExtraHour = (JLabel) panelTotalExtraHour.getComponent(2);
						
						
						lblExtraHour.setText(String.valueOf(extraHour));
					
						JPanel panelExtraHourRate = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraHourRate");
						JLabel lblExtraHourRate = (JLabel) panelExtraHourRate.getComponent(2);
						lblExtraHourRate.setText(String.valueOf(vehicle==null?0:vehicle.getExtraHourRate()));
						 
						
						
						JPanel panelExtraHourAmount = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraHourAmount");
						JLabel lblExtraAMount = (JLabel) panelExtraHourAmount.getComponent(2);
						lblExtraAMount.setText(String.valueOf(extraHour*(Double.parseDouble(vehicle==null?"0":vehicle.getExtraHourRate()))));
					}
					else
					{
						JPanel panelExtraHourLabel = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraHourLabel");
						JLabel lbl = (JLabel) panelExtraHourLabel.getComponent(2);
						lbl.setText(String.valueOf(hour)+":"+String.valueOf(finalmin));
						
						JPanel panelTotalExtraHour = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTotalExtraHour");
						JLabel lblExtraHour = (JLabel) panelTotalExtraHour.getComponent(2);
						
						
						lblExtraHour.setText(String.valueOf(0));
					
						JPanel panelExtraHourRate = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraHourRate");
						JLabel lblExtraHourRate = (JLabel) panelExtraHourRate.getComponent(2);
						lblExtraHourRate.setText(String.valueOf(0));
						 
						
						
						JPanel panelExtraHourAmount = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraHourAmount");
						JLabel lblExtraAMount = (JLabel) panelExtraHourAmount.getComponent(2);
						lblExtraAMount.setText(String.valueOf(0));
					
					}
				} catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
				
			}
		}*/
		
			
		if(comboId.equalsIgnoreCase(SConstants.COMBO_TAL_BILL_SELECT_VEHICLE_TYPE))
		{
			JPanel panelTOCustomer = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTOCustomer");
			@SuppressWarnings("rawtypes")
			JComboBox customerSelectionCombo = (JComboBox) panelTOCustomer.getComponent(2);
			
			JPanel panelDutyType = (JPanel) ExtraCabBillUI.getComponentMap().get("panelDutyType");
			@SuppressWarnings("rawtypes")
			JComboBox DutySelectionCombo = (JComboBox) panelDutyType.getComponent(2);
			
			JPanel panelVehicleType = (JPanel) ExtraCabBillUI.getComponentMap().get("panelVehicleType");
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
			JPanel panelVehicleNumber = (JPanel) ExtraCabBillUI.getComponentMap().get("panelVehicleNumber");
			JTextField txtVehicleText = (JTextField) panelVehicleNumber.getComponent(2);
			txtVehicleText.setText(vehicle.getVehicleNumber());
		}
		
		if(comboId.equalsIgnoreCase(SConstants.COMBO_TAL_BILL_SELECT_CUSTOMER))
		{
			JPanel panelTOCustomer = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTOCustomer");
			@SuppressWarnings("rawtypes")
			JComboBox customerSelectionCombo = (JComboBox) panelTOCustomer.getComponent(2);
			
			JPanel panelDutyType = (JPanel) ExtraCabBillUI.getComponentMap().get("panelDutyType");
			@SuppressWarnings("rawtypes")
			JComboBox DutySelectionCombo = (JComboBox) panelDutyType.getComponent(2);
			
			JPanel panelVehicleType = (JPanel) ExtraCabBillUI.getComponentMap().get("panelVehicleType");
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
			Customer customer = new CustomerDataModel().getCustomer(cName);
			
			JPanel panelVendorNumber = (JPanel) ExtraCabBillUI.getComponentMap().get("panelVendorNumber");
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
