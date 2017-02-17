package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import beans.DutyType;
import S_Util.Registry;
import S_Util.Utils;

public class BillGenerateUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String,Object> billGenerateUIComponentsMap = new HashMap<String,Object>();
	private int totalKM = 0;
	
	public static Map<String, Object> getComponentMap()
	{
		return billGenerateUIComponentsMap;
	}
	private UITemplates templates = new UITemplates();
	public BillGenerateUI(JDialog owner)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,"Bill Generation");
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(JDialog owner)
	{
		
		// Header Panel
		JPanel panelBillHeader = new JPanel();
		panelBillHeader.setBounds(0, 30, Registry.MAIN_WINDOW_WIDTH-6, 43);
		//panelBillHeader.setBackground(Color.cyan);
		panelBillHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillHeader.setLayout(new FlowLayout());
		
		JPanel panelBillNo = templates.getLabelWithLabel("Bill No","TAL 02/17-18",billGenerateUIComponentsMap);
		//panelBillNo.setBounds(10, 5, 190, 20);
		panelBillHeader.add(panelBillNo);
		//panelBillNo.setBackground(Color.cyan);
		
		JPanel panelBillDate = templates.getLabelWithLabel("Bill Date",new Date(),billGenerateUIComponentsMap);
		//panelBillDate.setBounds(250, 5, 250, 20);
		panelBillHeader.add(panelBillDate);
		
		JPanel panelContactNumber = templates.getLabelWithLabel("Contact No",Registry.CLIENT_MOB1,billGenerateUIComponentsMap);
		//panelContactNumber.setBounds(510, 5, 200, 20);
		panelBillHeader.add(panelContactNumber);
		
		JPanel panelPanNumber = templates.getLabelWithLabel("PAN NO",Registry.CLIENT_PAN_ID,billGenerateUIComponentsMap);
		//panelPanNumber.setBounds(710, 5, 150, 25);
		panelBillHeader.add(panelPanNumber);
		
		JPanel panelEmail = templates.getLabelWithLabel("E-Mail",Registry.CLIENT_EMAIL_ID,billGenerateUIComponentsMap);
		//panelEmail.setBounds(860, 5, 240, 25);
		panelBillHeader.add(panelEmail);
		
		JPanel panelVehicleType = templates.getLabelWithCombo("Veh.Type",Registry.VEHICLE_TYPE_COMBO_ID, Registry.VEHICLE_TYPES,billGenerateUIComponentsMap);
		//panelVehicleType.setBounds(670, 3, 200, 32);
		panelBillHeader.add(panelVehicleType);
		
		owner.add(panelBillHeader);
		
		// Body Panel
		JPanel panelBillBody = new JPanel();
		panelBillBody.setBounds(0, 73, Registry.MAIN_WINDOW_WIDTH-6, 45);
		panelBillBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody.setLayout(new FlowLayout());
		
		
		JPanel panelCustomer = templates.getLabelWithCombo("TO",null, Registry.CUSSTOMER_LIST, billGenerateUIComponentsMap);
		//panelCustomer.setBounds(5, 3, 120, 32);
		panelBillBody.add(panelCustomer);
		
		JPanel panelDateOfTravels = templates.getLabelWithTextFieldDatePicker("Start Date",billGenerateUIComponentsMap);
		//panelDateOfTravels.setBounds(125, 3, 270, 32);
		//panelDateOfTravels.setBackground(Color.cyan);
		panelBillBody.add(panelDateOfTravels);
		
		JPanel panelDateOfReturn = templates.getLabelWithTextFieldDatePicker("Return Date",billGenerateUIComponentsMap);
		//panelDateOfReturn.setBounds(395, 3, 270, 32);
		//panelDateOfReturn.setBackground(Color.yellow);
		panelBillBody.add(panelDateOfReturn);

		JPanel panelVehicleNumber = templates.getLabelWithTextField("Veh.Number","",8, billGenerateUIComponentsMap);
		//panelVehicleNumber.setBounds(870, 3, 300, 32);
		panelBillBody.add(panelVehicleNumber);
		
		JPanel panelVendorNumber = templates.getLabelWithTextField("Vendor Code","",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelVendorNumber);
		
		owner.add(panelBillBody);
		
		// Body Panel
		JPanel panelBillBody1 = new JPanel();
		panelBillBody1.setBounds(820, 115, 400, 180);
		panelBillBody1.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody1.setLayout(new FlowLayout());
		
		
		JPanel panelEmployeeName = templates.getLabelWithTextField("Emp. Name","",15, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelEmployeeName);
		
		JPanel panelStartKM = templates.getLabelWithIntSpinner("Start KM",15,0,1000,1, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelStartKM);
		
		JPanel panelEndKM = templates.getLabelWithIntSpinner("End KM",10,0,1000,1, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelEndKM);
		
		JPanel panelStartTime = templates.getLabelWithTimeSpinner("Start Time", billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelStartTime);
		
		JPanel panelEndTime = templates.getLabelWithTimeSpinner("End Time", billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelEndTime);
		
		JPanel panelTotalKM = templates.getLabelWithTextField("Total KM","",3, billGenerateUIComponentsMap);
		panelBillBody1.add(panelTotalKM);
		
		owner.add(panelBillBody1);
		
		// Body Panel
		JPanel panelBillBody2 = new JPanel();
		panelBillBody2.setBounds(10, 300, 1170, 50);
		panelBillBody2.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody2.setBackground(Color.cyan);
		panelBillBody2.setLayout(new FlowLayout());
		
		
		DutyType dutyType = new DutyType(4, 40,520);
		dutyType.setDutyTypeString();
		//billGenerateUIComponentsMap.put("dutyType", dutyType);
		DutyType dutyType1 = new DutyType(8, 80,1040);
		dutyType1.setDutyTypeString();
		//billGenerateUIComponentsMap.put("dutyType1", dutyType1);
		String[] dutyTypeArray = {dutyType.getDutyTypeString(),dutyType1.getDutyTypeString()};
		
		JPanel panelDutyType = templates.getLabelWithCombo("Duty Type", Registry.DUTYTYPE_COMBO_ID, dutyTypeArray, billGenerateUIComponentsMap);
		panelBillBody2.add(panelDutyType);
		
		JPanel panelTotalDistance = templates.getLabelWithLabel("Total KM", "", billGenerateUIComponentsMap);
		panelBillBody2.add(panelTotalDistance);
		
		JPanel panelRate = templates.getLabelWithLabel("Rate", "", billGenerateUIComponentsMap);
		panelBillBody2.add(panelRate);
		
		JPanel panelAmount = templates.getLabelWithLabel("Amount", "", billGenerateUIComponentsMap);
		panelBillBody2.add(panelAmount);
		
		
		owner.add(panelBillBody2);
		
		JPanel panelBillBody3 = new JPanel();
		panelBillBody3.setBounds(10, 360, 1170, 50);
		panelBillBody3.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody3.setBackground(Color.lightGray);
		panelBillBody3.setLayout(new FlowLayout());
		
		/*JPanel panelExtraKM = templates.getLabelWithLabel("Extra KM", "", billGenerateUIComponentsMap);
		panelBillBody3.add(panelExtraKM);*/
		
		JPanel panelTotalExtraKM = templates.getLabelWithLabel("Total Extra KM", "", billGenerateUIComponentsMap);
		panelBillBody3.add(panelTotalExtraKM);
		
		JPanel panelExtraKMRate = templates.getLabelWithLabel("Extra KM Rate", "", billGenerateUIComponentsMap);
		panelBillBody3.add(panelExtraKMRate);
		
		JPanel panelExtraKMAmount = templates.getLabelWithLabel("Extra Amount", "", billGenerateUIComponentsMap);
		panelBillBody3.add(panelExtraKMAmount);
		owner.add(panelBillBody3);
		
		
	}
}
