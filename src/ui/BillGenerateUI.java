package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import util.Registry;
import util.SConstants;
import util.Utils;
import beans.DutyType;

public class BillGenerateUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String,Object> billGenerateUIComponentsMap = new HashMap<String,Object>();
	private Registry reg = SConstants.reg;
	
	public static Map<String, Object> getComponentMap()
	{
		return billGenerateUIComponentsMap;
	}
	private UITemplates templates = new UITemplates();
	public BillGenerateUI(JDialog owner)
	{
		super(owner);
		billGenerateUIComponentsMap.put("reg", reg);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,reg.getValueFor("TEL_BILL_GEN_UI_NAME"));
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(JDialog owner)
	{
		
		// Header Panel
		JPanel panelBillHeader = new JPanel();
		panelBillHeader.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 43);
		//panelBillHeader.setBackground(Color.cyan);
		panelBillHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillHeader.setLayout(new FlowLayout());
		
		JPanel panelBillNo = templates.getLabelWithLabel("panelBillNo",reg.getValueFor("L_BILL_NO"),reg.getValueFor("TEL_BILL_SERIAL_TEXT_GEN"),billGenerateUIComponentsMap);
		//panelBillNo.setBounds(10, 5, 190, 20);
		panelBillHeader.add(panelBillNo);
		//panelBillNo.setBackground(Color.cyan);
		
		JPanel panelBillDate = templates.getLabelWithLabel("panelBillDate",reg.getValueFor("L_TEL_BILL_DTAE"), SConstants.TODAYS_DATE,billGenerateUIComponentsMap);
		//panelBillDate.setBounds(250, 5, 250, 20);
		panelBillHeader.add(panelBillDate);
		
		JPanel panelContactNumber = templates.getLabelWithLabel("panelContactNumber",reg.getValueFor("L_TEL_CONTACT_NO"),reg.getValueFor("V_CLIENT_MOB1"),billGenerateUIComponentsMap);
		//panelContactNumber.setBounds(510, 5, 200, 20);
		panelBillHeader.add(panelContactNumber);
		
		JPanel panelPanNumber = templates.getLabelWithLabel("panelPanNumber",reg.getValueFor("L_PAN_NO"),reg.getValueFor("V_PAN_NO"),billGenerateUIComponentsMap);
		//panelPanNumber.setBounds(710, 5, 150, 25);
		panelBillHeader.add(panelPanNumber);
		
		JPanel panelEmail = templates.getLabelWithLabel("panelEmail",reg.getValueFor("L_E_Mail"),reg.getValueFor("V_E_Mail"),billGenerateUIComponentsMap);
		//panelEmail.setBounds(860, 5, 240, 25);
		panelBillHeader.add(panelEmail);
		
		JPanel panelVehicleType = templates.getLabelWithCombo("panelVehicleType",reg.getValueFor("L_Veh_Type"),reg.getValueFor("ID_Vehicle_Type_combo"), SConstants.VEHICLE_TYPES,billGenerateUIComponentsMap);
		//panelVehicleType.setBounds(670, 3, 200, 32);
		panelBillHeader.add(panelVehicleType);
		
		owner.add(panelBillHeader);
		
		// Body Panel
		JPanel panelBillBody = new JPanel();
		panelBillBody.setBounds(0, 73, SConstants.MAIN_WINDOW_WIDTH-6, 45);
		panelBillBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody.setLayout(new FlowLayout());
		
		
		JPanel panelTOCustomer = templates.getLabelWithCombo("panelTOCustomer",reg.getValueFor("L_TO"),reg.getValueFor("ID_TO_CUSTOMER_Combo"), SConstants.CUSSTOMER_LIST, billGenerateUIComponentsMap);
		//panelCustomer.setBounds(5, 3, 120, 32);
		panelBillBody.add(panelTOCustomer);
		
		JPanel panelDateOfTravels = templates.getLabelWithTextFieldDatePicker("panelDateOfTravels",reg.getValueFor("L_Start_Date"),billGenerateUIComponentsMap);
		//panelDateOfTravels.setBounds(125, 3, 270, 32);
		//panelDateOfTravels.setBackground(Color.cyan);
		panelBillBody.add(panelDateOfTravels);
		
		JPanel panelDateOfReturn = templates.getLabelWithTextFieldDatePicker("panelDateOfReturn",reg.getValueFor("L_Return_Date"), billGenerateUIComponentsMap);
		//panelDateOfReturn.setBounds(395, 3, 270, 32);
		//panelDateOfReturn.setBackground(Color.yellow);
		panelBillBody.add(panelDateOfReturn);

		JPanel panelVehicleNumber = templates.getLabelWithTextField("panelVehicleNumber",reg.getValueFor("L_Veh_Number"),"Enter Veh Number",8, billGenerateUIComponentsMap);
		//panelVehicleNumber.setBounds(870, 3, 300, 32);
		panelBillBody.add(panelVehicleNumber);
		
		JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber",reg.getValueFor("L_Vendor_Code"),"Enter Vendor No",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelVendorNumber);
		
		owner.add(panelBillBody);
		
		// Body Panel
		JPanel panelBillBody1 = new JPanel();
		panelBillBody1.setBounds(820, 115, 400, 180);
		panelBillBody1.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody1.setLayout(new FlowLayout());
		
		
		JPanel panelEmployeeName = templates.getLabelWithTextField("panelEmployeeName",reg.getValueFor("L_Emp_Name"),"",15, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelEmployeeName);
		
		JPanel panelStartKM = templates.getLabelWithIntSpinner("panelStartKM",reg.getValueFor("L_Start_KM"),15,0,10000,1, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelStartKM);
		
		JPanel panelEndKM = templates.getLabelWithIntSpinner("panelEndKM",reg.getValueFor("L_End_KM"),10,0,10000,1, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelEndKM);
		
		JPanel panelStartTime = templates.getLabelWithTimeSpinner("panelStartTime",reg.getValueFor("L_Start_Time"), billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelStartTime);
		
		JPanel panelEndTime = templates.getLabelWithTimeSpinner("panelEndTime", reg.getValueFor("L_End_Time"), billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody1.add(panelEndTime);
		
		JPanel panelTotalKM = templates.getLabelWithTextField("panelTotalKM",reg.getValueFor("L_Total_KM"),"",3, billGenerateUIComponentsMap);
		panelBillBody1.add(panelTotalKM);
		
		owner.add(panelBillBody1);
		
		// Body Panel
		JPanel panelDutyTypeRow = new JPanel();
		panelDutyTypeRow.setBounds(10, 300, 1170, 50);
		panelDutyTypeRow.setBorder(BorderFactory.createLineBorder(Color.black));
		panelDutyTypeRow.setBackground(Color.cyan);
		panelDutyTypeRow.setLayout(new FlowLayout());
		
		
		DutyType dutyType = new DutyType(4, 40,520);
		dutyType.setDutyTypeString();
		//billGenerateUIComponentsMap.put("dutyType", dutyType);
		DutyType dutyType1 = new DutyType(8, 80,1040);
		dutyType1.setDutyTypeString();
		//billGenerateUIComponentsMap.put("dutyType1", dutyType1);
		String[] dutyTypeArray = {dutyType.getDutyTypeString(),dutyType1.getDutyTypeString()};
		
		JPanel panelDutyType = templates.getLabelWithCombo("panelDutyType",reg.getValueFor("L_Duty_Type"),reg.getValueFor("ID_Duty_Type_combo"), dutyTypeArray, billGenerateUIComponentsMap);
		panelDutyTypeRow.add(panelDutyType);
		
		JPanel panelTotalDistance = templates.getLabelWithLabel("panelTotalDistance",reg.getValueFor("L_Total_Distance"), "", billGenerateUIComponentsMap);
		panelDutyTypeRow.add(panelTotalDistance);
		
		JPanel panelRate = templates.getLabelWithLabel("panelRate",reg.getValueFor("L_Rate"), "", billGenerateUIComponentsMap);
		panelDutyTypeRow.add(panelRate);
		
		JPanel panelAmount = templates.getLabelWithLabel("panelAmount",reg.getValueFor("L_Amount"), "", billGenerateUIComponentsMap);
		panelDutyTypeRow.add(panelAmount);
		
		
		owner.add(panelDutyTypeRow);
		
		JPanel panelExtraKMRow = new JPanel();
		panelExtraKMRow.setBounds(10, 360, 1170, 50);
		panelExtraKMRow.setBorder(BorderFactory.createLineBorder(Color.black));
		panelExtraKMRow.setBackground(Color.lightGray);
		panelExtraKMRow.setLayout(new FlowLayout());
		
		JPanel panelExtraKM = templates.getLabelWithLabel("panelExtraKM",reg.getValueFor("L_Extra_KM"), "", billGenerateUIComponentsMap);
		panelExtraKMRow.add(panelExtraKM);
		
		JPanel panelTotalExtraKM = templates.getLabelWithLabel("panelTotalExtraKM",reg.getValueFor("L_Total_Extra_KM"), "", billGenerateUIComponentsMap);
		panelExtraKMRow.add(panelTotalExtraKM);
		
		JPanel panelExtraKMRate = templates.getLabelWithLabel("panelExtraKMRate",reg.getValueFor("L_Extra_KM_Rate"), "", billGenerateUIComponentsMap);
		panelExtraKMRow.add(panelExtraKMRate);
		
		JPanel panelExtraKMAmount = templates.getLabelWithLabel("panelExtraKMAmount", reg.getValueFor("L_Extra_Amount"),"", billGenerateUIComponentsMap);
		panelExtraKMRow.add(panelExtraKMAmount);
		owner.add(panelExtraKMRow);
		
		JPanel panelTollCharges = new JPanel();
		panelTollCharges.setBounds(10, 420, 1170, 50);
		panelTollCharges.setBorder(BorderFactory.createLineBorder(Color.black));
		panelTollCharges.setBackground(Color.lightGray);
		panelTollCharges.setLayout(new FlowLayout());
		
		JPanel panelTollAmount = templates.getLabelWithTextField("panelTollAmount",  reg.getValueFor("L_TOLL_AMOUNT"), "0", 3, billGenerateUIComponentsMap);
		panelTollCharges.add(panelTollAmount);
		
		owner.add(panelTollCharges);
		
		
		JPanel panelNightHalt = new JPanel();
		panelNightHalt.setBounds(10, 480, 1170, 50);
		panelNightHalt.setBorder(BorderFactory.createLineBorder(Color.black));
		panelNightHalt.setBackground(Color.lightGray);
		panelNightHalt.setLayout(new FlowLayout());
		
		JPanel panelNightHaltAmount = templates.getLabelWithTextField("panelNightHaltAmount",  reg.getValueFor("L_NIGHT_HALT_AMOUNT"), "0", 3, billGenerateUIComponentsMap);
		panelNightHalt.add(panelNightHaltAmount);
		
		owner.add(panelNightHalt);
		
		JPanel panelGross = new JPanel();
		panelGross.setBounds(10, 540, 1170, 50);
		panelGross.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGross.setBackground(Color.lightGray);
		panelGross.setLayout(new FlowLayout());
		
		JPanel panelFinalAmount = templates.getLabelWithLabel("panelFinalAmount",reg.getValueFor("L_GROSS_AMOUNT"), "", billGenerateUIComponentsMap);
		panelGross.add(panelFinalAmount);
		
		JPanel panelAmountInWords = templates.getLabelWithLabel("panelAmountInWords",reg.getValueFor("L_AMOUNTS_IN_WORDS"), "", billGenerateUIComponentsMap);
		panelGross.add(panelAmountInWords);
		
		
		owner.add(panelGross);
		
	}
}
