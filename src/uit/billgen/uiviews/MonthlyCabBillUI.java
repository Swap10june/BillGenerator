package uit.billgen.uiviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uit.billgen.beans.CabObject;
import uit.billgen.beans.Customer;
import uit.billgen.beans.Vehicle;
import uit.billgen.constants.SConstants;
import uit.billgen.datamodel.BillsDataModel;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.db.Dao;
import uit.billgen.exceptions.BillGenException;
import uit.billgen.util.NumToWords;
import uit.billgen.util.Utils;

public class MonthlyCabBillUI extends JDialog
{


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
	private VehicleDataModel m_vehicleModel  = new VehicleDataModel();
	private Vehicle m_selectedVehicle = null;
	private CustomerDataModel m_customerModel = new CustomerDataModel();
	private Customer m_selectedCustomer;
	private JPanel m_panelMiddleBody;
	private JPanel panelExtraKM;
	private JPanel panelExtraTimeHour;
	private JButton m_btnTotal;
	private JButton m_btnGenerate;
	public MonthlyCabBillUI(JDialog owner, String talBillBtnString)
	{
		super(owner);
		billGenerateUIComponentsMap = new HashMap<String,Object>();
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
		panelLeftBody.setLayout(new GridLayout(11, 1));
		
		String [] vehicleList = Utils.getUtilityInstance().removeDuplicate(new VehicleDataModel().getAllVehicleNames());
		final JPanel panelVehicleType = templates.getLabelWithComboWOListner("panelVehicleType",SConstants.L_VEHICLE_TYPE,vehicleList,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleType);
		@SuppressWarnings("rawtypes")
		JComboBox vehiCleType = (JComboBox) panelVehicleType.getComponent(2);
		m_selectedVehicle  = m_vehicleModel  .getVehicle(vehiCleType.getSelectedItem().toString().trim());
		vehiCleType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				String vehicle = e.getItem().toString();
				m_selectedVehicle = m_vehicleModel.getVehicle(vehicle);
				JPanel panelVehicleNumber = (JPanel) billGenerateUIComponentsMap.get("panelVehicleNumber");
				JTextField vehicleNumberLabel = (JTextField) panelVehicleNumber.getComponent(2);
				vehicleNumberLabel.setText(m_selectedVehicle.getVehicleNumber()==null?SConstants.NA_STRING:m_selectedVehicle.getVehicleNumber());
				populateMiddlePanelValues();
			}
		});
		
		
		String [] customerList = Utils.getUtilityInstance().removeDuplicate(m_customerModel.getAllCustomerNames());
		final JPanel panelTOCustomer = templates.getLabelWithComboWOListner("panelTOCustomer",SConstants.L_TO, customerList, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTOCustomer);
		
		@SuppressWarnings("rawtypes")
		JComboBox customerCombo = (JComboBox) panelTOCustomer.getComponent(2);
		m_selectedCustomer  = m_customerModel.getCustomer(customerCombo.getSelectedItem().toString().trim());
		customerCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				String customer = e.getItem().toString();
				m_selectedCustomer = m_customerModel.getCustomer(customer);
				JPanel panelVendorNumber = (JPanel) billGenerateUIComponentsMap.get("panelVendorNumber");
				JTextField panelVendorNumberLabel = (JTextField) panelVendorNumber.getComponent(2);
				panelVendorNumberLabel.setText(m_selectedCustomer.getcVendorCode()==null?SConstants.NA_STRING:m_selectedCustomer.getcVendorCode());
				populateMiddlePanelValues();
			}
		});
		
		final JPanel panelDateOfTravels = templates.getLabelWithTextFieldDatePicker("panelDateOfTravels",SConstants.L_START_DATE,billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfTravels);
		
		final JPanel panelDateOfReturn = templates.getLabelWithTextFieldDatePicker("panelDateOfReturn",SConstants.L_RETRUN_DATE, billGenerateUIComponentsMap);
		panelLeftBody.add(panelDateOfReturn);
		
		
		final JPanel panelVehicleNumber = templates.getLabelWithTextField("panelVehicleNumber",SConstants.L_VEHICLE_NO,m_selectedVehicle.getVehicleNumber()==null?SConstants.NA_STRING:m_selectedVehicle.getVehicleNumber(),15, false,billGenerateUIComponentsMap);
		panelLeftBody.add(panelVehicleNumber);
		
		final JPanel panelVendorNumber = templates.getLabelWithTextField("panelVendorNumber", SConstants.L_VENDOR_CODE,m_selectedCustomer.getcVendorCode()==null?SConstants.NA_STRING:m_selectedCustomer.getcVendorCode(),15,false, billGenerateUIComponentsMap);
		panelLeftBody.add(panelVendorNumber);
		
		panelExtraKM = templates.getLabelWithIntSpinnerWOListner("panelExtraKM","Extra KM",0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelExtraKM);
		JSpinner extraKmSpinner = (JSpinner) panelExtraKM.getComponent(2);
		extraKmSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e)
			{
				populateMiddlePanelValues();
			}
		});
		
		
		panelExtraTimeHour = templates.getLabelWithIntSpinnerWOListner("panelExtraTimeHour","Extra Hour",0,0,100000000,1, billGenerateUIComponentsMap);
		panelLeftBody.add(panelExtraTimeHour);
		JSpinner extraHourSpinner = (JSpinner) panelExtraTimeHour.getComponent(2);
		extraHourSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e)
			{
				populateMiddlePanelValues();
			}
		});
		
		final JPanel panelTollAmount = templates.getLabelWithTextField("panelTollAmount",SConstants.L_TOLL_AMOUNT, "0.0", 10,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelTollAmount);
		
		final JPanel panelNightHaltAmount = templates.getLabelWithTextField("panelNightHaltAmount",SConstants.L_NIGHT_HALT_AMOUNT, "0.0", 10,true, billGenerateUIComponentsMap);
		panelLeftBody.add(panelNightHaltAmount);
		
		final JButton getDetailsBtn = new JButton("Get Details");
		panelLeftBody.add(getDetailsBtn);
		getDetailsBtn.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				populateMiddlePanelValues();
			}

			
		});
		owner.add(panelLeftBody);
		
		// middle Body Panel
		m_panelMiddleBody = new JPanel();
		m_panelMiddleBody.setBounds(370, 100, 400, 400);
		m_panelMiddleBody.setBorder(BorderFactory.createLineBorder(Color.black));
		m_panelMiddleBody.setLayout(new GridLayout(12,1));
		m_panelMiddleBody.setVisible(false);
				
		final JPanel panelTotalPkgKm = templates.getLabelWithEmptyLabel("panelTotalPkgKm",SConstants.L_TOTAL_PKG_KM, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelTotalPkgKm);
		
		final JPanel panelRate = templates.getLabelWithEmptyLabel("panelRate",SConstants.L_RATE, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelRate);
		
		final JPanel panelAmount = templates.getLabelWithEmptyLabel("panelAmount",SConstants.L_AMOUNT, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelAmount);
		
		final JPanel panelExtraKMLabel = templates.getLabelWithEmptyLabel("panelExtraKMLabel",SConstants.L_EXTRA_KM, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelExtraKMLabel);
		
		final JPanel panelTotalExtraKM = templates.getLabelWithEmptyLabel("panelTotalExtraKM",SConstants.L_TOTAL_EXTRA_KM, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelTotalExtraKM);
		
		final JPanel panelExtraKMRate = templates.getLabelWithEmptyLabel("panelExtraKMRate",SConstants.L_EXTRA_KM_RATE, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelExtraKMRate);
		
		final JPanel panelExtraKMAmount = templates.getLabelWithEmptyLabel("panelExtraKMAmount", SConstants.L_EXTRA_AMOUNT,billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelExtraKMAmount);
		
		
		
		final JPanel panelExtraHourLabel = templates.getLabelWithEmptyLabel("panelExtraHourLabel",SConstants.L_EXTRA_HOUR, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelExtraHourLabel);
		
		final JPanel panelTotalExtraHour = templates.getLabelWithEmptyLabel("panelTotalExtraHour",SConstants.L_TOTAL_EXTRA_HOUR, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelTotalExtraHour);
		
		final JPanel panelExtraHourRate = templates.getLabelWithEmptyLabel("panelExtraHourRate",SConstants.L_EXTRA_HOUR_RATE, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelExtraHourRate);
		
		final JPanel panelExtraHourAmount = templates.getLabelWithEmptyLabel("panelExtraHourAmount", SConstants.L_EXTRA_AMOUNT,billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelExtraHourAmount);
		
		final JPanel panelServiceTax = templates.getLabelWithTextField("panelServiceTax", "Service Tax(%)", "Zero", 15,true, billGenerateUIComponentsMap);
		m_panelMiddleBody.add(panelServiceTax);
		
		m_btnTotal = new JButton(SConstants.GET_TOTAL_BTN_STRING);
		m_btnTotal.setSize(btnDimension);
		m_btnTotal.setBounds(525, 510	, 100, 30);
		m_btnTotal.setEnabled(false);
		owner.add(m_btnTotal);
		m_btnTotal.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				m_btnGenerate.setEnabled(true);
				
				double billAmount = 
						Double.parseDouble(utility.getStringValueFromPanelComponent(panelAmount, 2).isEmpty()?"0":utility.getStringValueFromPanelComponent(panelAmount, 2))
						+Double.parseDouble(utility.getStringValueFromPanelComponent(panelExtraKMAmount, 2).isEmpty()?"0":utility.getStringValueFromPanelComponent(panelExtraKMAmount, 2))
						+Double.parseDouble(utility.getStringValueFromPanelComponent(panelExtraHourAmount, 2).isEmpty()?"0":utility.getStringValueFromPanelComponent(panelExtraHourAmount, 2))
						+Double.parseDouble(utility.getStringValueFromPanelComponent(panelNightHaltAmount, 2).isEmpty()?"0":utility.getStringValueFromPanelComponent(panelNightHaltAmount, 2));
				double taxOnBillAMount = 
						(
								billAmount
								*
								Double.parseDouble(utility.getStringValueFromPanelComponent(panelServiceTax, 2).isEmpty()?"0":utility.getStringValueFromPanelComponent(panelServiceTax, 2))
						)/100;
				
				double finalAMount = 
								billAmount
								+taxOnBillAMount
								+Double.parseDouble(utility.getStringValueFromPanelComponent(panelTollAmount, 2).isEmpty()?"0":utility.getStringValueFromPanelComponent(panelTollAmount, 2));
				NumToWords w = new NumToWords(); 
				String inwords = w.convert((int)finalAMount);
				
				JPanel panelFinalAmount = (JPanel) billGenerateUIComponentsMap.get("panelFinalAmount");
				JLabel lblFianlAMount = (JLabel) panelFinalAmount.getComponent(2);
				lblFianlAMount.setText(String.valueOf(finalAMount));
				
				JPanel panelAmountInWords = (JPanel) billGenerateUIComponentsMap.get("panelAmountInWords");
				JLabel finalAmount = (JLabel) panelAmountInWords.getComponent(2);
				finalAmount.setText(inwords);
			}
		});
		
		owner.add(m_panelMiddleBody);
		
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
		
		m_btnGenerate = new JButton(SConstants.GENEARTE_BILL_BTN_STRING);
		m_btnGenerate.setBounds(800, 300, 100, 30);
		m_btnGenerate.setEnabled(false);
		owner.add(m_btnGenerate);
		billGenerateUIComponentsMap.put("btnGenerate",m_btnGenerate );
		m_btnGenerate.addActionListener(new ActionListener()
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
				
				extraCab.setBillType(SConstants.MONTHLY_CAB_BILL_TYPE);
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
				//extraCab.setEmployeeNameUsedVehicle(utility.getStringValueFromPanelComponent(panelEmployeeName, 2).isEmpty()?"N/A":utility.getStringValueFromPanelComponent(panelEmployeeName, 2));
				//extraCab.setStartKM(utility.getStringValueFromPanelComponent(panelStartKM, 2));
				//extraCab.setEndKM(utility.getStringValueFromPanelComponent(panelEndKM, 2));
				//String time = utility.getStringValueFromPanelComponent(panelStartTime, 2);
				//extraCab.setStartTime(utility.getStringValueFromPanelComponent(panelStartTime, 2));
				//extraCab.setEndTime(utility.getStringValueFromPanelComponent(panelEndTime, 2));
				//extraCab.setTotalKM(utility.getStringValueFromPanelComponent(panelTotalKM, 2));
				//extraCab.setDutyType(utility.getStringValueFromPanelComponent(panelDutyType, 2));
				//extraCab.setPackageType(utility.getStringValueFromPanelComponent(panelDutyType, 2));
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
				//billGenerateUIComponentsMap.remove(SConstants.TOTAL_KM_ATTR);
				new Dao().addBOM(extraCab);
				//BOM boma = new Dao().getBOM("date",new java.sql.Date(new java.util.Date().getTime()));
				owner.dispose();
			}
		});
		
	}
	private void populateMiddlePanelValues()
	{
		m_panelMiddleBody.setVisible(true);
		m_btnTotal.setEnabled(true);
		JPanel panelTotalPkgKmSet = (JPanel) billGenerateUIComponentsMap.get("panelTotalPkgKm");
		JLabel lblPkgKm = (JLabel) panelTotalPkgKmSet.getComponent(2);
		lblPkgKm.setText(String.valueOf(m_selectedVehicle.getMonthlyPkgKm()));
		
		JPanel panelRateSet = (JPanel) billGenerateUIComponentsMap.get("panelRate");
		JLabel lblPkgRate = (JLabel) panelRateSet.getComponent(2);
		lblPkgRate.setText(String.valueOf(m_selectedVehicle.getMonthlyRate()));
		
		JPanel panelPkgAmountSet = (JPanel) billGenerateUIComponentsMap.get("panelAmount");
		JLabel lblPkgAmount = (JLabel) panelPkgAmountSet.getComponent(2);
		lblPkgAmount.setText(String.valueOf(m_selectedVehicle.getMonthlyRate()));
		
		
		String strExtraKmTraveeld = utility.getStringValueFromPanelComponent(panelExtraKM, 2);
		JPanel panelExtraKMSet = (JPanel) billGenerateUIComponentsMap.get("panelTotalExtraKM");
		JLabel lblextraKM = (JLabel) panelExtraKMSet.getComponent(2);
		lblextraKM.setText(strExtraKmTraveeld);
		
		JPanel panelExtraKMRateSet = (JPanel) billGenerateUIComponentsMap.get("panelExtraKMRate");
		JLabel lblextraKMRate = (JLabel) panelExtraKMRateSet.getComponent(2);
		lblextraKMRate.setText(String.valueOf(m_selectedVehicle.getExtraKmRate()));
		
		double extraKmTravelld = Double.parseDouble(strExtraKmTraveeld);
		double rate = Double.parseDouble(m_selectedVehicle.getExtraKmRate());
		
		JPanel panelExtraKMAmountSet = (JPanel) billGenerateUIComponentsMap.get("panelExtraKMAmount");
		JLabel lblextraKMAmount = (JLabel) panelExtraKMAmountSet.getComponent(2);
		lblextraKMAmount.setText(String.valueOf(rate*extraKmTravelld));
		
		
		
		
		String strExtraHoueUsed = utility.getStringValueFromPanelComponent(panelExtraTimeHour, 2);
		JPanel panelExtraHourSet = (JPanel) billGenerateUIComponentsMap.get("panelTotalExtraHour");
		JLabel lblextraHour = (JLabel) panelExtraHourSet.getComponent(2);
		lblextraHour.setText(strExtraHoueUsed);
		
		JPanel panelExtraHourRateSet = (JPanel) billGenerateUIComponentsMap.get("panelExtraHourRate");
		JLabel lblextraHourRate = (JLabel) panelExtraHourRateSet.getComponent(2);
		lblextraHourRate.setText(String.valueOf(m_selectedVehicle.getExtraHourRate()));
		
		double extraHourTravelld = Double.parseDouble(strExtraHoueUsed);
		double rateHour = Double.parseDouble(m_selectedVehicle.getExtraHourRate());
		
		JPanel panelExtraHourAmountSet = (JPanel) billGenerateUIComponentsMap.get("panelExtraHourAmount");
		JLabel lblextrHourAmount = (JLabel) panelExtraHourAmountSet.getComponent(2);
		lblextrHourAmount.setText(String.valueOf(rateHour*extraHourTravelld));
		
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
