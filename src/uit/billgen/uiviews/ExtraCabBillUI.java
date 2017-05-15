package uit.billgen.uiviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import uit.billgen.beans.CabObject;
import uit.billgen.beans.DutyType;
import uit.billgen.beans.Vehicle;
import uit.billgen.constants.SConstants;
import uit.billgen.datamodel.BillsDataModel;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.DutyTypeDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.db.Dao;
import uit.billgen.exceptions.BillGenException;
import uit.billgen.handlers.ExtraCabBillUIButtonHandler;
import uit.billgen.listners.TelcoBillComboItemListner;
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
	private DutyTypeDataModel m_dutyTypeModel;
	private DutyType m_selectedDutyType;
	public ExtraCabBillUI(JDialog owner, String talBillBtnString)
	{
		super(owner);
		billGenerateUIComponentsMap = new HashMap<String,Object>();
		this.m_dutyTypeModel = new DutyTypeDataModel();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,talBillBtnString);
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI( final JDialog owner)
	{
		Dimension btnDimension = new Dimension(30,30);
		// Header Panel
		JPanel panelBillHeader = new JPanel();
		panelBillHeader.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 70);
		panelBillHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillHeader.setLayout(new FlowLayout());
		
		String billNo = utility.getBillNumber();
		
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
		String[] dutyTypeArray;
		if(vehicleSelectionCombo.getSelectedItem()==null || vehicleSelectionCombo==null)
			dutyTypeArray = new String []{""};
		else
			dutyTypeArray = m_dutyTypeModel.getAllDutyTypStringsFor(customerSelectionCombo.getSelectedItem().toString(),vehicleSelectionCombo.getSelectedItem().toString());
		
		final JPanel panelDutyType = templates.getLabelWithComboWOListner("panelDutyType",SConstants.L_DUTY_TYPE, dutyTypeArray, billGenerateUIComponentsMap);
		@SuppressWarnings("unchecked")
		JComboBox<String> comboDutyType = (JComboBox<String>) panelDutyType.getComponent(2);
		comboDutyType.addItemListener(/*new TelcoBillComboItemListner(SConstants.COMBO_TAL_BILL_SELECT_DUTY_TYPE)*/new ItemListener() {
			
			

			@Override
			public void itemStateChanged(ItemEvent e)
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
					
					m_selectedDutyType = m_dutyTypeModel.getDutyType(dutyTypeID);
					lblTotalDistanceValue.setText(String.valueOf(m_selectedDutyType.getKm()));
					
					JPanel panelRate = (JPanel) ExtraCabBillUI.getComponentMap().get("panelRate");
					JLabel lblRate = (JLabel) panelRate.getComponent(2);
					lblRate.setText(String.valueOf(m_selectedDutyType.getPackageRate()));
					
					
					JPanel panelAmount = (JPanel) ExtraCabBillUI.getComponentMap().get("panelAmount");
					JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
					lblAMountValue.setText(String.valueOf(m_selectedDutyType.getPackageRate()));
					 
					int extraKM = (int) (totalKM-m_selectedDutyType.getKm());
					if(extraKM>0)
					{
					
						JPanel panelTotalExtraKM = (JPanel) ExtraCabBillUI.getComponentMap().get("panelTotalExtraKM");
						JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(2);
						
						
						lblExtraKM.setText(String.valueOf(extraKM));
					
						JPanel panelExtraKMRate = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraKMRate");
						JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(2);
						lblExtraRate.setText(String.valueOf(m_selectedDutyType.getExtraKmRate()));
						 
						
						
						JPanel panelExtraKMAmount = (JPanel) ExtraCabBillUI.getComponentMap().get("panelExtraKMAmount");
						JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
						lblExtraAMount.setText(String.valueOf(extraKM*m_selectedDutyType.getExtraKmRate()));
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
						
						double extraHour = hour-m_selectedDutyType.getHours();
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
							
							String vName = m_selectedDutyType.getVehicleType();
							String cName = m_selectedDutyType.getCustomerName();
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
			
			}
		});
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
		
		
		
		final JPanel panelExtraHourLabel = templates.getLabelWithEmptyLabel("panelExtraHourLabel","Total Time Travelled:", billGenerateUIComponentsMap);
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
						
				CabObject extraCab = new CabObject();
				extraCab.setBillType(SConstants.EXTRA_CAB_BILL_TYPE);
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
				extraCab.setAcStatus(String.valueOf(m_selectedDutyType.getAcNonAcType()));
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
				extraCab.setTotalWithoutTax(String.valueOf(billAmount));
				extraCab.setServiceTaxCarges(String.valueOf(taxOnBillAMount));
				extraCab.setTollCharges(utility.getStringValueFromPanelComponent(panelTollAmount, 2));
				extraCab.setGrandTotal(String.valueOf(finalAMount));
				if(panelGenerateExcelCheck.isSelected())
				{
					Utils.getUtilityInstance().generateBill(extraCab);
					
				}
				
				try
				{
					new BillsDataModel().addBillTransaction(extraCab);
				} catch (BillGenException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
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
