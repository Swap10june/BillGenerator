package uit.billgen.uiviews;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.beans.ExtraCabObject;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.DutyTypeDataModel;
import uit.billgen.datamodel.TelcoBillDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.handlers.ExtraCabBillUIButtonHandler;
import uit.billgen.listners.TelcoBillComboItemListner;
import uit.billgen.util.Dao;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class ExtraCabBillUI extends JDialog {


	private static final long serialVersionUID = 1L;
	private static Map<String,Object> billGenerateUIComponentsMap;
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
	public ExtraCabBillUI(JDialog owner, String talBillBtnString)
	{
		super(owner);
		billGenerateUIComponentsMap = new HashMap<String,Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,talBillBtnString);
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
		
		int no = new TelcoBillDataModel().getNoOfTags(SConstants.BILL_TAG);
		String str = ((new Date().toLocaleString().split(",")[1]).split(" ")[1]).substring(2);
		int dateYear = Integer.parseInt(str);
		if(new Date().getMonth()>2)
		{
			dateYear++;
		}
		dateYear-=1;
		//8411989003
		String billNo = String.valueOf(no)+":"+String.valueOf(dateYear)+"-"+String.valueOf(dateYear+1);
		
		final JPanel panelBillNo = templates.getLabelWithValueLabel("panelBillNo",SConstants.L_BILL_NO,billNo,billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillNo);
		
		
		final JPanel panelBillDate = templates.getLabelWithTextFieldDatePicker("panelBillDate","Bill Date",billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillDate);
		
		
		/*String dateString = Utils.getUtilityInstance().getDateString(SConstants.TODAYS_DATE);
		final JPanel panelBillDate = templates.getLabelWithTextField("panelBillDate", SConstants.L_TEL_BILL_DTAE, dateString, SConstants.TEXT_COL_SIZE_10, false, billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillDate);*/
		
		final JPanel panelContactNumber = templates.getLabelWithValueLabel("panelContactNumber",SConstants.L_TEL_CONTACT_NO,SConstants.V_CLIENT_MOB1,billGenerateUIComponentsMap);
		panelBillHeader.add(panelContactNumber);
		
		final JPanel panelPanNumber = templates.getLabelWithValueLabel("panelPanNumber",SConstants.L_PAN_NO,SConstants.V_PAN_NO,billGenerateUIComponentsMap);
		panelBillHeader.add(panelPanNumber);
		
		final JPanel panelEmail = templates.getLabelWithValueLabel("panelEmail",SConstants.L_E_MAIl,SConstants.V_E_Mail,billGenerateUIComponentsMap);
		panelBillHeader.add(panelEmail);
		
		owner.add(panelBillHeader);
		
		
		// Body Panel
		final JPanel panelLeftBody = new JPanel();
		panelLeftBody.setBounds(0, 100, 350, 543);
		panelLeftBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelLeftBody.setLayout(new GridLayout(14, 1));
		
		String [] vehicleList = Utils.getUtilityInstance().removeDuplicate(new VehicleDataModel().getAllVehicleNames());
		final JPanel panelVehicleType = templates.getLabelWithComboWOListner("panelVehicleType",SConstants.L_VEHICLE_TYPE,vehicleList,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleType);
		@SuppressWarnings("rawtypes")
		JComboBox vehiCleType = (JComboBox) panelVehicleType.getComponent(2);
		vehiCleType.addItemListener(new TelcoBillComboItemListner(SConstants.COMBO_TAL_BILL_SELECT_VEHICLE_TYPE));
		
		
		String [] customerList = Utils.getUtilityInstance().removeDuplicate(new CustomerDataModel().getAllCustomerNames());
		final JPanel panelTOCustomer = templates.getLabelWithComboWOListner("panelTOCustomer",SConstants.L_TO, customerList, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTOCustomer);
		
		@SuppressWarnings("rawtypes")
		JComboBox customerCombo = (JComboBox) panelTOCustomer.getComponent(2);
		customerCombo.addItemListener(new TelcoBillComboItemListner(SConstants.COMBO_TAL_BILL_SELECT_CUSTOMER));
		
		
		final JPanel panelDateOfTravels = templates.getLabelWithTextFieldDatePicker("panelDateOfTravels",SConstants.L_START_DATE,billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfTravels);
		
		final JPanel panelDateOfReturn = templates.getLabelWithTextFieldDatePicker("panelDateOfReturn",SConstants.L_RETRUN_DATE, billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfReturn);
		
		
		final JPanel panelVehicleNumber = templates.getLabelWithTextField("panelVehicleNumber",SConstants.L_VEHICLE_NO,SConstants.NA_STRING,15, false,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleNumber);
		
		final JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber", SConstants.L_VENDOR_CODE,SConstants.NA_STRING,15,false, billGenerateUIComponentsMap);
		panelLeftBody.add(panelVendorNumber);
		
		final JPanel panelEmployeeName = templates.getLabelWithTextField("panelEmployeeName",SConstants.L_EMP_NAME,SConstants.NA_STRING,15,false, billGenerateUIComponentsMap);
		panelLeftBody.add(panelEmployeeName);
		

		final JPanel panelEndKM = templates.getLabelWithIntSpinner("panelEndKM",SConstants.L_END_KM,0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelEndKM);
		
		final JPanel panelStartKM = templates.getLabelWithIntSpinner("panelStartKM",SConstants.L_START_KM,0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelStartKM);
		
		final JPanel panelStartTime = templates.getLabelWithTimeSpinner("panelStartTime",SConstants.L_START_TIME, billGenerateUIComponentsMap);
		panelLeftBody.add(panelStartTime);
		
		final JPanel panelEndTime = templates.getLabelWithTimeSpinner("panelEndTime", SConstants.L_END_TIME, billGenerateUIComponentsMap);
		panelLeftBody.add(panelEndTime);
		
		final JPanel panelTotalKM = templates.getLabelWithTextField("panelTotalKM",SConstants.L_TOTAL_KM,"0",11,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTotalKM);
		
		
		final JPanel panelTollAmount = templates.getLabelWithTextField("panelTollAmount",SConstants.L_TOLL_AMOUNT, "0.0", 10,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTollAmount);
		

		final JPanel panelNightHaltAmount = templates.getLabelWithTextField("panelNightHaltAmount",  SConstants.L_NIGHT_HALT_AMOUNT, "0.0", 10,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelNightHaltAmount);
		
		owner.add(panelLeftBody);
		
		// Body Panel
		final JPanel panelMiddleBody = new JPanel();
		panelMiddleBody.setBounds(370, 100, 400, 400);
		panelMiddleBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelMiddleBody.setLayout(new GridLayout(13,1));
		//panelMiddleBody.setVisible(false);
		@SuppressWarnings("rawtypes")
		JComboBox customerSelectionCombo = (JComboBox) panelTOCustomer.getComponent(2);
		
		@SuppressWarnings("rawtypes")
		JComboBox vehicleSelectionCombo = (JComboBox) panelVehicleType.getComponent(2);
		
		String[] dutyTypeArray = new DutyTypeDataModel().getAllDutyTypStringsFor(customerSelectionCombo.getSelectedItem().toString(),vehicleSelectionCombo.getSelectedItem().toString());
		
		final JPanel panelDutyType = templates.getLabelWithComboWOListner("panelDutyType",SConstants.L_DUTY_TYPE, dutyTypeArray, billGenerateUIComponentsMap);
		@SuppressWarnings("unchecked")
		JComboBox<String> comboDutyType = (JComboBox<String>) panelDutyType.getComponent(2);
		comboDutyType.addItemListener(new TelcoBillComboItemListner(SConstants.COMBO_TAL_BILL_SELECT_DUTY_TYPE));
		comboDutyType.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		panelMiddleBody.add(panelDutyType);
				
		final JPanel panelTotalPkgKm = templates.getLabelWithEmptyLabel("panelTotalPkgKm",SConstants.L_TOTAL_PKG_KM, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelTotalPkgKm);
		
		final JPanel panelRate = templates.getLabelWithEmptyLabel("panelRate",SConstants.L_RATE, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelRate);
		
		final JPanel panelAmount = templates.getLabelWithEmptyLabel("panelAmount",SConstants.L_AMOUNT, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelAmount);
		
		final JPanel panelExtraKM = templates.getLabelWithEmptyLabel("panelExtraKM",SConstants.L_EXTRA_KM, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKM);
		
		final JPanel panelTotalExtraKM = templates.getLabelWithEmptyLabel("panelTotalExtraKM",SConstants.L_TOTAL_EXTRA_KM, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelTotalExtraKM);
		
		final JPanel panelExtraKMRate = templates.getLabelWithEmptyLabel("panelExtraKMRate",SConstants.L_EXTRA_KM_RATE, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKMRate);
		
		final JPanel panelExtraKMAmount = templates.getLabelWithEmptyLabel("panelExtraKMAmount", SConstants.L_EXTRA_AMOUNT,billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraKMAmount);
		
		
		
		final JPanel panelExtraHourLabel = templates.getLabelWithEmptyLabel("panelExtraHourLabel",SConstants.L_EXTRA_HOUR, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraHourLabel);
		
		final JPanel panelTotalExtraHour = templates.getLabelWithEmptyLabel("panelTotalExtraHour",SConstants.L_TOTAL_EXTRA_HOUR, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelTotalExtraHour);
		
		final JPanel panelExtraHourRate = templates.getLabelWithEmptyLabel("panelExtraHourRate",SConstants.L_EXTRA_HOUR_RATE, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraHourRate);
		
		final JPanel panelExtraHourAmount = templates.getLabelWithEmptyLabel("panelExtraHourAmount", SConstants.L_EXTRA_AMOUNT,billGenerateUIComponentsMap);
		panelMiddleBody.add(panelExtraHourAmount);
		
		
		
		
		final JPanel panelServiceTax = templates.getLabelWithTextField("panelServiceTax", "Service Tax(%)", "Zero", 15,true, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelServiceTax);
		
		JButton btnTotal = new JButton(SConstants.GET_TOTAL_BTN_STRING);
		btnTotal.setSize(btnDimension);
		btnTotal.setBounds(525, 510	, 100, 30);
		btnTotal.setEnabled(false);
		owner.add(btnTotal);
		billGenerateUIComponentsMap.put(SConstants.GET_TOTAL_BTN_STRING,btnTotal );
		btnTotal.addActionListener(new ExtraCabBillUIButtonHandler());
		
		owner.add(panelMiddleBody);
		
		JPanel panelGross = new JPanel();
		panelGross.setBounds(370, 550, 400, 92);
		panelGross.setBorder(BorderFactory.createLineBorder(Color.black));
		panelGross.setBackground(Color.lightGray);
		panelGross.setLayout(new GridLayout(2,1));
		
		final JPanel panelFinalAmount = templates.getLabelWithValueLabel("panelFinalAmount",SConstants.L_GROSS_AMOUNT, "0.0", billGenerateUIComponentsMap);
		panelGross.add(panelFinalAmount);
		
		final JPanel panelAmountInWords = templates.getLabelWithValueLabel("panelAmountInWords",SConstants.L_AMOUNTS_IN_WORDS, "0.0", billGenerateUIComponentsMap);
		panelGross.add(panelAmountInWords);
		
		
		owner.add(panelGross);
		
		final JCheckBox panelGenerateExcelCheck = new JCheckBox("Excel");
		panelGenerateExcelCheck.setBounds(800, 250, 100, 30);
		//panelGenerateExcelCheck.setEnabled(false);
		owner.add(panelGenerateExcelCheck);
		
		JButton btnGenerate = new JButton(SConstants.GENEARTE_BILL_BTN_STRING);
		btnGenerate.setBounds(800, 300, 100, 30);
		btnGenerate.setEnabled(false);
		owner.add(btnGenerate);
		billGenerateUIComponentsMap.put("btnGenerate",btnGenerate );
		btnGenerate.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JPanel panelAmount = (JPanel) billGenerateUIComponentsMap.get("panelAmount");
				JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
				
				JPanel panelExtraKMAmount = (JPanel) billGenerateUIComponentsMap.get("panelExtraKMAmount");
				JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
				
				JPanel panelTollAmount = (JPanel) billGenerateUIComponentsMap.get("panelTollAmount");
				JTextField tollAmountText = (JTextField) panelTollAmount.getComponent(2);
				
				JPanel panelNightHaltAmount = (JPanel) billGenerateUIComponentsMap.get("panelNightHaltAmount");
				JTextField nightHaltAmountText = (JTextField) panelNightHaltAmount.getComponent(2);
				
				JPanel panelServiceTax = (JPanel) billGenerateUIComponentsMap.get("panelServiceTax");
				JTextField serviceTaxText = (JTextField) panelServiceTax.getComponent(2);
				
				
				JPanel panelExtraHourAmount = (JPanel) billGenerateUIComponentsMap.get("panelExtraHourAmount");
				JLabel lblExtraHourAMount = (JLabel) panelExtraHourAmount.getComponent(2);
				double extraHourAmount = Double.parseDouble(lblExtraHourAMount.getText());
				
				
				double billAmount = extraHourAmount+Double.parseDouble(lblAMountValue.getText())+
						Double.parseDouble(lblExtraAMount.getText())+Double.parseDouble(nightHaltAmountText.getText().isEmpty()?"0":nightHaltAmountText.getText());
				double taxOnBillAMount = (billAmount*Double.parseDouble(serviceTaxText.getText().isEmpty()?"0":serviceTaxText.getText()))/100;
				double finalAMount = billAmount+taxOnBillAMount+Double.parseDouble(tollAmountText.getText().isEmpty()?"0":tollAmountText.getText());
						
				ExtraCabObject extraCab = new ExtraCabObject();
				
				extraCab.setBillNumber(utility.getStringValueFromPanelComponent(panelBillNo, 2));
				extraCab.setBillDate(utility.getStringValueFromPanelComponent(panelBillDate, 2));
				extraCab.setContactNumber(utility.getStringValueFromPanelComponent(panelContactNumber, 2));
				extraCab.setEmail(utility.getStringValueFromPanelComponent(panelEmail,2));
				extraCab.setCustomerName(utility.getStringValueFromPanelComponent(panelTOCustomer, 2).isEmpty()?"N/A":utility.getStringValueFromPanelComponent(panelTOCustomer, 2));
				extraCab.setDateOfTravels(utility.getStringValueFromPanelComponent(panelDateOfTravels,2));
				extraCab.setDateOfreturn(utility.getStringValueFromPanelComponent(panelDateOfReturn,2));
				extraCab.setTypeOfVehicle(utility.getStringValueFromPanelComponent(panelVehicleType, 2));
				extraCab.setVehicleNumber(utility.getStringValueFromPanelComponent(panelVehicleNumber, 2).isEmpty()?"N/A":utility.getStringValueFromPanelComponent(panelVehicleNumber, 2));
				extraCab.setVendorCode(utility.getStringValueFromPanelComponent(panelVendorNumber, 2).isEmpty()?"N/A":utility.getStringValueFromPanelComponent(panelVendorNumber, 2));
				extraCab.setEmployeeNameUsedVehicle(utility.getStringValueFromPanelComponent(panelEmployeeName, 2).isEmpty()?"N/A":utility.getStringValueFromPanelComponent(panelEmployeeName, 2));
				extraCab.setStartKM(utility.getStringValueFromPanelComponent(panelStartKM, 2));
				extraCab.setEndKM(utility.getStringValueFromPanelComponent(panelEndKM, 2));
				//String time = utility.getStringValueFromPanelComponent(panelStartTime, 2);
				extraCab.setStartTime(utility.getStringValueFromPanelComponent(panelStartTime, 2));
				extraCab.setEndTime(utility.getStringValueFromPanelComponent(panelEndTime, 2));
				extraCab.setTotalKM(utility.getStringValueFromPanelComponent(panelTotalKM, 2));
				extraCab.setDutyType(utility.getStringValueFromPanelComponent(panelDutyType, 2));
				extraCab.setPackageType(utility.getStringValueFromPanelComponent(panelDutyType, 2));
				extraCab.setPackageKM(utility.getStringValueFromPanelComponent(panelTotalPkgKm, 2));
				extraCab.setPackageRate(utility.getStringValueFromPanelComponent(panelRate, 2));
				extraCab.setPakageAmount(utility.getStringValueFromPanelComponent(panelAmount, 2));
				extraCab.setExtraKM(utility.getStringValueFromPanelComponent(panelTotalExtraKM, 2));
				extraCab.setExtraKMRate(utility.getStringValueFromPanelComponent(panelExtraKMRate, 2));
				extraCab.setExtraTotalAmount(utility.getStringValueFromPanelComponent(panelExtraKMAmount, 2));
				extraCab.setExtraTimeHours(utility.getStringValueFromPanelComponent(panelTotalExtraHour, 2));
				extraCab.setExtraTimeHoursRate(utility.getStringValueFromPanelComponent(panelExtraHourRate, 2));
				extraCab.setExtraHourAmount(String.valueOf(extraHourAmount));
				extraCab.setNightHaltRate(utility.getStringValueFromPanelComponent(panelNightHaltAmount, 2));
				extraCab.setTotalWithoutTax(/*String.valueOf(Double.parseDouble(
						utility.getStringValueFromPanelComponent(panelFinalAmount, 2))
						-Double.parseDouble(utility.getStringValueFromPanelComponent(panelServiceTax, 2)))*/
						String.valueOf(billAmount));
				extraCab.setServiceTaxCarges(/*utility.getStringValueFromPanelComponent(panelServiceTax, 2)*/String.valueOf(taxOnBillAMount));
				extraCab.setTollCharges(utility.getStringValueFromPanelComponent(panelTollAmount, 2));
				extraCab.setGrandTotal(/*utility.getStringValueFromPanelComponent(panelFinalAmount, 2)*/String.valueOf(finalAMount));
				if(panelGenerateExcelCheck.isSelected())
				{
					Utils.getUtilityInstance().generateBill(extraCab);
					
				}
				//new Dao().addBill(bom);
				new TelcoBillDataModel().addBillTransaction(extraCab);
				billGenerateUIComponentsMap.remove(SConstants.TOTAL_KM_ATTR);
				new Dao().addBOM(extraCab);
				//BOM boma = new Dao().getBOM("date",new java.sql.Date(new java.util.Date().getTime()));
				owner.dispose();
			}
		});
		
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
