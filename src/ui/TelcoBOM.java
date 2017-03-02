package ui;

import handlers.TelcoBillUIButtonHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import util.Registry;
import util.SConstants;
import ModelXml.DutyTypeDataModel;
import ModelXml.TelcoBillDataModel;
import util.Utils;
import beans.BOM;

public class TelcoBOM extends JDialog {

	/**
	 * 
	 */
	public static final String UI_ID = "TelcoBOM"; 
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
	public TelcoBOM(JDialog owner)
	{
		super(owner);
		billGenerateUIComponentsMap.put("reg", reg);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,reg.getValueFor("TEL_BILL_GEN_UI_NAME"));
		initUI(owner);
		owner.setVisible(true);
	}
	@SuppressWarnings("deprecation")
	private void initUI( final JDialog owner)
	{
		Dimension btnDimension = new Dimension(30,30);
		// Header Panel
		JPanel panelBillHeader = new JPanel();
		panelBillHeader.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 70);
		panelBillHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillHeader.setLayout(new FlowLayout());
		
		int no = new TelcoBillDataModel().getNoOfTagsUnderTag("Bill");
		String str = ((new Date().toLocaleString().split(",")[1]).split(" ")[1]).substring(2);
		int dateYear = Integer.parseInt(str);
		if(new Date().getMonth()>2)
		{
			dateYear++;
		}
		//8411989003
		String billNo = "TAL "+String.valueOf(no)+":"+String.valueOf(dateYear)+"-"+String.valueOf(dateYear+1);
		
		final JPanel panelBillNo = templates.getLabelWithLabel("panelBillNo",reg.getValueFor("L_BILL_NO"),billNo,billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillNo);
		
		final JPanel panelBillDate = templates.getLabelWithLabel("panelBillDate",reg.getValueFor("L_TEL_BILL_DTAE"), SConstants.TODAYS_DATE,billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillDate);
		
		final JPanel panelContactNumber = templates.getLabelWithLabel("panelContactNumber",reg.getValueFor("L_TEL_CONTACT_NO"),reg.getValueFor("V_CLIENT_MOB1"),billGenerateUIComponentsMap);
		panelBillHeader.add(panelContactNumber);
		
		final JPanel panelPanNumber = templates.getLabelWithLabel("panelPanNumber",reg.getValueFor("L_PAN_NO"),reg.getValueFor("V_PAN_NO"),billGenerateUIComponentsMap);
		panelBillHeader.add(panelPanNumber);
		
		final JPanel panelEmail = templates.getLabelWithLabel("panelEmail",reg.getValueFor("L_E_Mail"),reg.getValueFor("V_E_Mail"),billGenerateUIComponentsMap);
		panelBillHeader.add(panelEmail);
		
		owner.add(panelBillHeader);
		
		
		// Body Panel
		final JPanel panelLeftBody = new JPanel();
		panelLeftBody.setBounds(0, 100, 350, 543);
		panelLeftBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelLeftBody.setLayout(new GridLayout(14, 1));
		
		final JPanel panelVehicleType = templates.getLabelWithCombo("panelVehicleType",reg.getValueFor("L_Veh_Type"),UI_ID+reg.getValueFor("ID_Vehicle_TYPE_COMBO"), SConstants.VEHICLE_TYPES,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleType);
		
		final JPanel panelTOCustomer = templates.getLabelWithCombo("panelTOCustomer",reg.getValueFor("L_TO"),UI_ID+reg.getValueFor("ID_TO_CUSTOMER_COMBO"), SConstants.CUSSTOMER_LIST, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTOCustomer);
		
		final JPanel panelDateOfTravels = templates.getLabelWithTextFieldDatePicker("panelDateOfTravels",reg.getValueFor("L_Start_Date"),billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfTravels);
		
		final JPanel panelDateOfReturn = templates.getLabelWithTextFieldDatePicker("panelDateOfReturn",reg.getValueFor("L_Return_Date"), billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfReturn);
		
		
		final JPanel panelVehicleNumber = templates.getLabelWithTextField("panelVehicleNumber",reg.getValueFor("L_Veh_Number"),"Enter Vehicle Number here",15, false,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleNumber);
		
		final JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber", reg.getValueFor("L_Vendor_Code"),"Enter Vendor No here",15,false, billGenerateUIComponentsMap);
		panelLeftBody.add(panelVendorNumber);
		
		final JPanel panelEmployeeName = templates.getLabelWithTextField("panelEmployeeName",reg.getValueFor("L_Emp_Name"),"Enter Employee Name here",15,false, billGenerateUIComponentsMap);
		panelLeftBody.add(panelEmployeeName);
		
		final JPanel panelStartKM = templates.getLabelWithIntSpinner("panelStartKM",reg.getValueFor("L_Start_KM"),0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelStartKM);
		
		final JPanel panelEndKM = templates.getLabelWithIntSpinner("panelEndKM",reg.getValueFor("L_End_KM"),0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelEndKM);
		
		final JPanel panelStartTime = templates.getLabelWithTimeSpinner("panelStartTime",reg.getValueFor("L_Start_Time"), billGenerateUIComponentsMap);
		panelLeftBody.add(panelStartTime);
		
		final JPanel panelEndTime = templates.getLabelWithTimeSpinner("panelEndTime", reg.getValueFor("L_End_Time"), billGenerateUIComponentsMap);
		panelLeftBody.add(panelEndTime);
		
		final JPanel panelTotalKM = templates.getLabelWithTextField("panelTotalKM",reg.getValueFor("L_Total_KM"),"0",11,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTotalKM);
		
		final JPanel panelTollAmount = templates.getLabelWithTextField("panelTollAmount",reg.getValueFor("L_TOLL_AMOUNT"), "0.0", 10,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTollAmount);
		

		final JPanel panelNightHaltAmount = templates.getLabelWithTextField("panelNightHaltAmount",  reg.getValueFor("L_NIGHT_HALT_AMOUNT"), "0.0", 10,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelNightHaltAmount);
		
		owner.add(panelLeftBody);
		
		// Body Panel
		JPanel panelMiddleBody = new JPanel();
		panelMiddleBody.setBounds(370, 100, 400, 400);
		panelMiddleBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelMiddleBody.setLayout(new GridLayout(9,1));
		@SuppressWarnings("rawtypes")
		JComboBox customerSelectionCombo = (JComboBox) panelTOCustomer.getComponent(2);
		
		@SuppressWarnings("rawtypes")
		JComboBox vehicleSelectionCombo = (JComboBox) panelVehicleType.getComponent(2);
		
		String[] dutyTypeArray = new DutyTypeDataModel().getAllDutyTypStringsFor(customerSelectionCombo.getSelectedItem().toString(),vehicleSelectionCombo.getSelectedItem().toString());
		
		final JPanel panelDutyType = templates.getLabelWithCombo("panelDutyType",reg.getValueFor("L_Duty_Type"),UI_ID+reg.getValueFor("ID_DUTY_TYPE_COMBO"), dutyTypeArray, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelDutyType);
		
		final JPanel panelTotalPkgKm = templates.getLabelWithLabel("panelTotalPkgKm",reg.getValueFor("L_Total_PKG_KM"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelTotalPkgKm);
		
		final JPanel panelRate = templates.getLabelWithLabel("panelRate",reg.getValueFor("L_Rate"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelRate);
		
		final JPanel panelAmount = templates.getLabelWithLabel("panelAmount",reg.getValueFor("L_Amount"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelAmount);
		
		final JPanel panelExtraKM = templates.getLabelWithLabel("panelExtraKM",reg.getValueFor("L_Extra_KM"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKM);
		
		final JPanel panelTotalExtraKM = templates.getLabelWithLabel("panelTotalExtraKM",reg.getValueFor("L_Total_Extra_KM"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelTotalExtraKM);
		
		final JPanel panelExtraKMRate = templates.getLabelWithLabel("panelExtraKMRate",reg.getValueFor("L_Extra_KM_Rate"), "", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKMRate);
		
		final JPanel panelExtraKMAmount = templates.getLabelWithLabel("panelExtraKMAmount", reg.getValueFor("L_Extra_Amount"),"", billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKMAmount);
		
		final JPanel panelServiceTax = templates.getLabelWithTextField("panelServiceTax", "Service Tax", "Zero", 15,true, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelServiceTax);
		
		JButton btnTotal = new JButton(reg.getValueFor("V_TOTAL_BTN_STRING"));
		btnTotal.setSize(btnDimension);
		btnTotal.setBounds(525, 510	, 100, 30);
		btnTotal.setEnabled(false);
		owner.add(btnTotal);
		billGenerateUIComponentsMap.put("btnTotal",btnTotal );
		btnTotal.addActionListener(new TelcoBillUIButtonHandler());
		
		owner.add(panelMiddleBody);
		
		JPanel panelGross = new JPanel();
		panelGross.setBounds(370, 550, 400, 92);
		panelGross.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGross.setBackground(Color.lightGray);
		panelGross.setLayout(new GridLayout(2,1));
		
		final JPanel panelFinalAmount = templates.getLabelWithLabel("panelFinalAmount",reg.getValueFor("L_GROSS_AMOUNT"), "0.0", billGenerateUIComponentsMap);
		panelGross.add(panelFinalAmount);
		
		final JPanel panelAmountInWords = templates.getLabelWithLabel("panelAmountInWords",reg.getValueFor("L_AMOUNTS_IN_WORDS"), "0.0", billGenerateUIComponentsMap);
		panelGross.add(panelAmountInWords);
		
		
		owner.add(panelGross);
		
		final JPanel panelBtns = new JPanel();
		panelBtns.setBounds(780, 100, 200, 100);
		panelBtns.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBtns.setLayout(new GridLayout());
		
		final JPanel panelGenerateExcelCheck = templates.getLabelWithCheckBox("panelGenerateExcelCheck",reg.getValueFor("L_GENERATE_EXCEL_CHECK"), billGenerateUIComponentsMap);
		final JCheckBox generateBillCheck = (JCheckBox) panelGenerateExcelCheck.getComponent(2);
		panelBtns.add(panelGenerateExcelCheck);
		
		
		JButton btnGenerate = new JButton("Bill");
		btnGenerate.setBounds(800, 300, 100, 30);
		btnGenerate.setEnabled(false);
		owner.add(btnGenerate);
		billGenerateUIComponentsMap.put("btnGenerate",btnGenerate );
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
				bom.setTotalKM(utility.getStringValueFromPanelComponent(panelTotalKM, 2));
				bom.setDutyType(utility.getStringValueFromPanelComponent(panelDutyType, 2));
				bom.setPackageKM(utility.getStringValueFromPanelComponent(panelTotalPkgKm, 2));
				bom.setPackageRate(utility.getStringValueFromPanelComponent(panelRate, 2));
				bom.setPakageAmount(utility.getStringValueFromPanelComponent(panelAmount, 2));
				bom.setExtraKM(utility.getStringValueFromPanelComponent(panelTotalExtraKM, 2));
				bom.setExtraRate(utility.getStringValueFromPanelComponent(panelExtraKMRate, 2));
				bom.setExtraTotalAmount(utility.getStringValueFromPanelComponent(panelExtraKMAmount, 2));
				bom.setTollCharges(utility.getStringValueFromPanelComponent(panelTollAmount, 2));
				bom.setNightHaltRate(utility.getStringValueFromPanelComponent(panelNightHaltAmount, 2));
				bom.setGrandTotal(utility.getStringValueFromPanelComponent(panelFinalAmount, 2));
				bom.setServiceTaxCarges(utility.getStringValueFromPanelComponent(panelServiceTax, 2));
				bom.setTotalWithoutTax(String.valueOf(Double.parseDouble(utility.getStringValueFromPanelComponent(panelFinalAmount, 2))-Double.parseDouble(utility.getStringValueFromPanelComponent(panelServiceTax, 2))));
				if(generateBillCheck.isSelected())
				{
					Utils.getUtilityInstance().generateBill(bom);
					
				}
				//new Dao().addBill(bom);
				new TelcoBillDataModel().addBillTransaction(bom);
				owner.dispose();
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
	public String getLblPanNoValue()
	{
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
