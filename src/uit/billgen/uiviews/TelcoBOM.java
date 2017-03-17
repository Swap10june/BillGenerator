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
import javax.swing.JPanel;

import uit.billgen.beans.BOM;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.DutyTypeDataModel;
import uit.billgen.datamodel.TelcoBillDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.handlers.TelcoBillUIButtonHandler;
import uit.billgen.listners.TelcoBillComboItemListner;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class TelcoBOM extends JDialog {


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
	public TelcoBOM(JDialog owner, String talBillBtnString)
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
		//8411989003
		String billNo = "TAL "+String.valueOf(no)+":"+String.valueOf(dateYear)+"-"+String.valueOf(dateYear+1);
		
		final JPanel panelBillNo = templates.getLabelWithValueLabel("panelBillNo",SConstants.L_BILL_NO,billNo,billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillNo);
		
		final JPanel panelBillDate = templates.getLabelWithValueLabel("panelBillDate",SConstants.L_TEL_BILL_DTAE, SConstants.TODAYS_DATE,billGenerateUIComponentsMap);
		panelBillHeader.add(panelBillDate);
		
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
		
		String [] vehicleList = new VehicleDataModel().getAllVehicleNames();
		final JPanel panelVehicleType = templates.getLabelWithComboWOListner("panelVehicleType",SConstants.L_VEHICLE_TYPE,vehicleList,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleType);
		@SuppressWarnings("rawtypes")
		JComboBox vehiCleType = (JComboBox) panelVehicleType.getComponent(2);
		vehiCleType.addItemListener(new TelcoBillComboItemListner(SConstants.COMBO_TAL_BILL_SELECT_VEHICLE_TYPE));
		
		
		String [] customerList = new CustomerDataModel().getAllCustomerNames();
		final JPanel panelTOCustomer = templates.getLabelWithComboWOListner("panelTOCustomer",SConstants.L_TO, customerList, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTOCustomer);
		
		@SuppressWarnings("rawtypes")
		JComboBox customerCombo = (JComboBox) panelTOCustomer.getComponent(2);
		customerCombo.addItemListener(new TelcoBillComboItemListner(SConstants.COMBO_TAL_BILL_SELECT_CUSTOMER));
		
		
		final JPanel panelDateOfTravels = templates.getLabelWithTextFieldDatePicker("panelDateOfTravels",SConstants.L_START_DATE,billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfTravels);
		
		final JPanel panelDateOfReturn = templates.getLabelWithTextFieldDatePicker("panelDateOfReturn",SConstants.L_RETRUN_DATE, billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfReturn);
		
		
		final JPanel panelVehicleNumber = templates.getLabelWithTextField("panelVehicleNumber",SConstants.L_VEHICLE_NO,"Enter Vehicle Number here",15, false,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleNumber);
		
		final JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber", SConstants.L_VENDOR_CODE,"Enter Vendor No here",15,false, billGenerateUIComponentsMap);
		panelLeftBody.add(panelVendorNumber);
		
		final JPanel panelEmployeeName = templates.getLabelWithTextField("panelEmployeeName",SConstants.L_EMP_NAME,"Enter Employee Name here",15,false, billGenerateUIComponentsMap);
		panelLeftBody.add(panelEmployeeName);
		
		final JPanel panelStartKM = templates.getLabelWithIntSpinner("panelStartKM",SConstants.L_START_KM,0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelStartKM);
		
		final JPanel panelEndKM = templates.getLabelWithIntSpinner("panelEndKM",SConstants.L_END_KM,0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelEndKM);
		
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
		panelMiddleBody.setLayout(new GridLayout(9,1));
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
		/*JTextField textTotalKm = (JTextField) panelTotalKM.getComponent(2);
		textTotalKm.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				panelMiddleBody.setVisible(true);
				
			}
		});*/
		
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
		
		final JPanel panelServiceTax = templates.getLabelWithTextField("panelServiceTax", "Service Tax", "Zero", 15,true, billGenerateUIComponentsMap);
		panelMiddleBody.add(panelServiceTax);
		
		JButton btnTotal = new JButton(SConstants.GET_TOTAL_BTN_STRING);
		btnTotal.setSize(btnDimension);
		btnTotal.setBounds(525, 510	, 100, 30);
		btnTotal.setEnabled(false);
		owner.add(btnTotal);
		billGenerateUIComponentsMap.put(SConstants.GET_TOTAL_BTN_STRING,btnTotal );
		btnTotal.addActionListener(new TelcoBillUIButtonHandler());
		
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
		
		final JPanel panelBtns = new JPanel();
		panelBtns.setBounds(780, 100, 200, 100);
		panelBtns.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBtns.setLayout(new GridLayout());
		
		final JPanel panelGenerateExcelCheck = templates.getLabelWithCheckBox("panelGenerateExcelCheck",SConstants.L_GENERATE_EXCEL_CHECK, billGenerateUIComponentsMap);
		final JCheckBox generateBillCheck = (JCheckBox) panelGenerateExcelCheck.getComponent(2);
		panelBtns.add(panelGenerateExcelCheck);
		
		
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
				billGenerateUIComponentsMap.remove(SConstants.TOTAL_KM_ATTR);
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
