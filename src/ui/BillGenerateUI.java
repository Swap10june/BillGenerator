package ui;

import handlers.ButtonHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import util.Dao;
import util.Registry;
import util.SConstants;
import util.Utils;
import beans.BOM;
import beans.DutyType;

public class BillGenerateUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String,Object> billGenerateUIComponentsMap = new HashMap<String,Object>();
	private Registry reg = SConstants.reg;
	
	Utils utility = Utils.getUtilityInstance();
	
	// Bill Component
	private String lblBillNoValue = null;
	private String lblBillDateValue = null;
	private String lblMobNoValue = null;
	
	private String lblPanNoValue = null;
	private String lblEmailValue = null;
	
	
	
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
		Dimension btnDimension = new Dimension(30,30);
		// Header Panel
		JPanel panelBillHeader = new JPanel();
		panelBillHeader.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 70);
		//panelBillHeader.setBackground(Color.cyan);
		panelBillHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillHeader.setLayout(new FlowLayout());
		
		JPanel panelBillNo = templates.getLabelWithLabel("panelBillNo",reg.getValueFor("L_BILL_NO"),reg.getValueFor("V_TEL_BILL_SERIAL_TEXT_GEN"),billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillNo);
		//panelBillNo.setBackground(Color.cyan);
		
		JPanel panelBillDate = templates.getLabelWithLabel("panelBillDate",reg.getValueFor("L_TEL_BILL_DTAE"), SConstants.TODAYS_DATE,billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillDate);
		
		JPanel panelContactNumber = templates.getLabelWithLabel("panelContactNumber",reg.getValueFor("L_TEL_CONTACT_NO"),reg.getValueFor("V_CLIENT_MOB1"),billGenerateUIComponentsMap);
		panelBillHeader.add(panelContactNumber);
		
		JPanel panelPanNumber = templates.getLabelWithLabel("panelPanNumber",reg.getValueFor("L_PAN_NO"),reg.getValueFor("V_PAN_NO"),billGenerateUIComponentsMap);
		//panelPanNumber.setBounds(710, 5, 150, 25);
		panelBillHeader.add(panelPanNumber);
		
		JPanel panelEmail = templates.getLabelWithLabel("panelEmail",reg.getValueFor("L_E_Mail"),reg.getValueFor("V_E_Mail"),billGenerateUIComponentsMap);
		//panelEmail.setBounds(860, 5, 240, 25);
		panelBillHeader.add(panelEmail);
		
		owner.add(panelBillHeader);
		
		
		// Body Panel
		JPanel panelLeftBody = new JPanel();
		panelLeftBody.setBounds(0, 100, 350, 545);
		panelLeftBody.setBorder(BorderFactory.createLineBorder(Color.black));
		//panelLeftBody.setLayout(new FlowLayout());
		panelLeftBody.setLayout(new GridLayout(14, 1));
		
		JPanel panelVehicleType = templates.getLabelWithCombo("panelVehicleType",reg.getValueFor("L_Veh_Type"),reg.getValueFor("ID_Vehicle_Type_combo"), SConstants.VEHICLE_TYPES,billGenerateUIComponentsMap);
		//panelVehicleType.setBounds(670, 3, 200, 32);
		panelLeftBody.add(panelVehicleType);
		
		JPanel panelTOCustomer = templates.getLabelWithCombo("panelTOCustomer",reg.getValueFor("L_TO"),reg.getValueFor("ID_TO_CUSTOMER_Combo"), SConstants.CUSSTOMER_LIST, billGenerateUIComponentsMap);
		//panelCustomer.setBounds(5, 3, 120, 32);
		panelLeftBody.add(panelTOCustomer);
		
		JPanel panelDateOfTravels = templates.getLabelWithTextFieldDatePicker("panelDateOfTravels",reg.getValueFor("L_Start_Date"),billGenerateUIComponentsMap);
		//panelDateOfTravels.setBounds(125, 3, 270, 32);
		//panelDateOfTravels.setBackground(Color.cyan);
		panelLeftBody.add(panelDateOfTravels);
		
		JPanel panelDateOfReturn = templates.getLabelWithTextFieldDatePicker("panelDateOfReturn",reg.getValueFor("L_Return_Date"), billGenerateUIComponentsMap);
		//panelDateOfReturn.setBounds(395, 3, 270, 32);
		//panelDateOfReturn.setBackground(Color.yellow);
		panelLeftBody.add(panelDateOfReturn);
		
		
		JPanel panelVehicleNumber = templates.getLabelWithTextField("panelVehicleNumber",reg.getValueFor("L_Veh_Number"),"Enter Vehicle Number here",15, billGenerateUIComponentsMap);
		//panelVehicleNumber.setBounds(870, 3, 300, 32);
		panelLeftBody.add(panelVehicleNumber);
		
		JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber", reg.getValueFor("L_Vendor_Code"),"Enter Vendor No here",15, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelLeftBody.add(panelVendorNumber);
		
		JPanel panelEmployeeName = templates.getLabelWithTextField("panelEmployeeName",reg.getValueFor("L_Emp_Name"),"Enter Employee Name here",15, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelLeftBody.add(panelEmployeeName);
		
		JPanel panelStartKM = templates.getLabelWithIntSpinner("panelStartKM",reg.getValueFor("L_Start_KM"),0,0,100000000,1, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelLeftBody.add(panelStartKM);
		
		JPanel panelEndKM = templates.getLabelWithIntSpinner("panelEndKM",reg.getValueFor("L_End_KM"),0,0,100000000,1, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelLeftBody.add(panelEndKM);
		
		JPanel panelStartTime = templates.getLabelWithTimeSpinner("panelStartTime",reg.getValueFor("L_Start_Time"), billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelLeftBody.add(panelStartTime);
		
		JPanel panelEndTime = templates.getLabelWithTimeSpinner("panelEndTime", reg.getValueFor("L_End_Time"), billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelLeftBody.add(panelEndTime);
		
		JPanel panelTotalKM = templates.getLabelWithTextField("panelTotalKM",reg.getValueFor("L_Total_KM"),"0",11, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTotalKM);
		
		JPanel panelTollAmount = templates.getLabelWithTextField("panelTollAmount",reg.getValueFor("L_TOLL_AMOUNT"), "0", 10, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTollAmount);
		

		JPanel panelNightHaltAmount = templates.getLabelWithTextField("panelNightHaltAmount",  reg.getValueFor("L_NIGHT_HALT_AMOUNT"), "0", 10, billGenerateUIComponentsMap);
		panelLeftBody.add(panelNightHaltAmount);
		
		owner.add(panelLeftBody);
		
		// Body Panel
		JPanel panelMiddleBody = new JPanel();
		panelMiddleBody.setBounds(370, 100, 400, 420);
		panelMiddleBody.setBorder(BorderFactory.createLineBorder(Color.black));
		//panelMiddleBody.setBackground(Color.cyan);
		panelMiddleBody.setLayout(new GridLayout(9,1));
		
		
		DutyType dutyType = new DutyType(4, 40,520);
		dutyType.setDutyTypeString();
		//billGenerateUIComponentsMap.put("dutyType", dutyType);
		DutyType dutyType1 = new DutyType(8, 80,1040);
		dutyType1.setDutyTypeString();
		//billGenerateUIComponentsMap.put("dutyType1", dutyType1);
		String[] dutyTypeArray = {dutyType.getDutyTypeString(),dutyType1.getDutyTypeString()};
		
		JPanel panelDutyType = templates.getLabelWithCombo("panelDutyType",reg.getValueFor("L_Duty_Type"),reg.getValueFor("ID_Duty_Type_combo"), dutyTypeArray, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelDutyType);
		
		JPanel panelTotalDistance = templates.getLabelWithLabel("panelTotalDistance",reg.getValueFor("L_Total_Distance"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelTotalDistance);
		
		JPanel panelRate = templates.getLabelWithLabel("panelRate",reg.getValueFor("L_Rate"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelRate);
		
		JPanel panelAmount = templates.getLabelWithLabel("panelAmount",reg.getValueFor("L_Amount"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelAmount);
		
		JPanel panelExtraKM = templates.getLabelWithLabel("panelExtraKM",reg.getValueFor("L_Extra_KM"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKM);
		
		JPanel panelTotalExtraKM = templates.getLabelWithLabel("panelTotalExtraKM",reg.getValueFor("L_Total_Extra_KM"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelTotalExtraKM);
		
		JPanel panelExtraKMRate = templates.getLabelWithLabel("panelExtraKMRate",reg.getValueFor("L_Extra_KM_Rate"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKMRate);
		
		JPanel panelExtraKMAmount = templates.getLabelWithLabel("panelExtraKMAmount", reg.getValueFor("L_Extra_Amount"),"", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKMAmount);
		
		JButton btnTotal = new JButton(reg.getValueFor("V_TOTAL_BTN_STRING"));
		btnTotal.setSize(btnDimension);
		panelMiddleBody.add(btnTotal);
		btnTotal.addActionListener(new ButtonHandler());
		
		owner.add(panelMiddleBody);
		
		JPanel panelGross = new JPanel();
		panelGross.setBounds(370, 550, 400, 90);
		panelGross.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGross.setBackground(Color.lightGray);
		panelGross.setLayout(new GridLayout(2,1));
		
		JPanel panelFinalAmount = templates.getLabelWithLabel("panelFinalAmount",reg.getValueFor("L_GROSS_AMOUNT"), "", billGenerateUIComponentsMap);
		panelGross.add(panelFinalAmount);
		
		JPanel panelAmountInWords = templates.getLabelWithLabel("panelAmountInWords",reg.getValueFor("L_AMOUNTS_IN_WORDS"), "", billGenerateUIComponentsMap);
		panelGross.add(panelAmountInWords);
		
		
		owner.add(panelGross);
		
		JPanel panelBtns = new JPanel();
		panelBtns.setBounds(780, 100, 200, 400);
		panelBtns.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBtns.setBackground(Color.lightGray);
		panelBtns.setLayout(new GridLayout(9,1));
		
		JPanel panelGenerateExcelCheck = templates.getLabelWithCheckBox("panelGenerateExcelCheck",reg.getValueFor("L_GENERATE_EXCEL_CHECK"), billGenerateUIComponentsMap);
		panelBtns.add(panelGenerateExcelCheck);
		
		JCheckBox generateBillCheck = (JCheckBox) panelGenerateExcelCheck.getComponent(2);
		JButton btnGenerate = new JButton("Bill");
		btnGenerate.setPreferredSize(new Dimension(32,0));
		panelBtns.add(btnGenerate);	
		btnGenerate.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				BOM bom = new BOM();
				
				bom.setBillNumber(utility.getStringValueFromPanelComponent(panelBillNo, 2));
				bom.setBillDate(utility.getStringValueFromPanelComponent(panelBillDate, 2));
				bom.setContactNumber(utility.getStringValueFromPanelComponent(panelContactNumber, 2));
				bom.setEmail(utility.getStringValueFromPanelComponent(panelEmail,2));
				bom.setCustomerName(utility.getStringValueFromPanelComponent(panelTOCustomer, 2));
				bom.setDateOfTravels(utility.getStringValueFromPanelComponent(panelDateOfTravels,2));
				bom.setDateOfreturn(utility.getStringValueFromPanelComponent(panelDateOfReturn,2));
				bom.setTypeOfVehicle(utility.getStringValueFromPanelComponent(panelVehicleType, 2));
				bom.setVehicleNumber(utility.getStringValueFromPanelComponent(panelVehicleNumber, 2));
				bom.setVendorCode(utility.getStringValueFromPanelComponent(panelVendorNumber, 2));
				bom.setEmployeeNameUsedVehicle(utility.getStringValueFromPanelComponent(panelEmployeeName, 2));
				bom.setStartKM(utility.getStringValueFromPanelComponent(panelStartKM, 2));
				bom.setEndKM(utility.getStringValueFromPanelComponent(panelEndKM, 2));
				bom.setStartTime(utility.getStringValueFromPanelComponent(panelStartTime, 2));
				bom.setEndTime(utility.getStringValueFromPanelComponent(panelEndTime, 2));
				bom.setTotalKM(utility.getStringValueFromPanelComponent(panelTotalDistance, 2));
				bom.setDutyType(utility.getStringValueFromPanelComponent(panelDutyType, 2));
				bom.setPackageKM(utility.getStringValueFromPanelComponent(panelTotalKM, 2));
				bom.setPackageRate(utility.getStringValueFromPanelComponent(panelRate, 2));
				bom.setPakageAmount(utility.getStringValueFromPanelComponent(panelAmount, 2));
				bom.setExtraKM(utility.getStringValueFromPanelComponent(panelTotalExtraKM, 2));
				bom.setExtraRate(utility.getStringValueFromPanelComponent(panelExtraKMRate, 2));
				bom.setExtraTotalAmount(utility.getStringValueFromPanelComponent(panelExtraKMAmount, 2));
				bom.setTollCharges(utility.getStringValueFromPanelComponent(panelTollAmount, 2));
				bom.setNightHaltRate(utility.getStringValueFromPanelComponent(panelNightHaltAmount, 2));
				bom.setGrandTotal(utility.getStringValueFromPanelComponent(panelFinalAmount, 2));
				
				if(generateBillCheck.isSelected())
				{
					Utils.getUtilityInstance().generateBill(bom);
					owner.dispose();
				}
				
			}
		});
		
		owner.add(panelBtns);
	}
	
	
	public String getLblBillNoValue() {
		return lblBillNoValue;
	}
	public void setLblBillNoValue(String lblBillNoValue) {
		this.lblBillNoValue = lblBillNoValue;
	}
	/**
	 * @return the lblMobNoValue
	 */
	public String getLblMobNoValue() {
		return lblMobNoValue;
	}
	/**
	 * @param lblMobNoValue the lblMobNoValue to set
	 */
	public void setLblMobNoValue(String lblMobNoValue) {
		this.lblMobNoValue = lblMobNoValue;
	}
	/**
	 * @return the lblPanNoValue
	 */
	public String getLblPanNoValue() {
		return lblPanNoValue;
	}
	/**
	 * @param lblPanNoValue the lblPanNoValue to set
	 */
	public void setLblPanNoValue(String lblPanNoValue) {
		this.lblPanNoValue = lblPanNoValue;
	}
	/**
	 * @return the lblEmailValue
	 */
	public String getLblEmailValue() {
		return lblEmailValue;
	}
	/**
	 * @param lblEmailValue the lblEmailValue to set
	 */
	public void setLblEmailValue(String lblEmailValue) {
		this.lblEmailValue = lblEmailValue;
	}
	/**
	 * @return the lblBillDateValue
	 */
	public String getLblBillDateValue() {
		return lblBillDateValue;
	}
	/**
	 * @param lblBillDateValue the lblBillDateValue to set
	 */
	public void setLblBillDateValue(String lblBillDateValue) {
		this.lblBillDateValue = lblBillDateValue;
	}
}
