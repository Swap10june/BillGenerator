package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;

import S_Util.Registry;
import S_Util.Utils;

public class BillGenerateUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> billGenerateUIComponent = new HashMap<String,Object>();
	
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
		
		JPanel panelBillNo = templates.getLabelWithLabel("Bill No","TAL 02/17-18",billGenerateUIComponent);
		//panelBillNo.setBounds(10, 5, 190, 20);
		panelBillHeader.add(panelBillNo);
		//panelBillNo.setBackground(Color.cyan);
		
		JPanel panelBillDate = templates.getLabelWithLabel("Bill Date",new Date(),billGenerateUIComponent);
		//panelBillDate.setBounds(250, 5, 250, 20);
		panelBillHeader.add(panelBillDate);
		
		JPanel panelContactNumber = templates.getLabelWithLabel("Contact No",Registry.CLIENT_MOB1,billGenerateUIComponent);
		//panelContactNumber.setBounds(510, 5, 200, 20);
		panelBillHeader.add(panelContactNumber);
		
		JPanel panelPanNumber = templates.getLabelWithLabel("PAN NO",Registry.CLIENT_PAN_ID,billGenerateUIComponent);
		//panelPanNumber.setBounds(710, 5, 150, 25);
		panelBillHeader.add(panelPanNumber);
		
		JPanel panelEmail = templates.getLabelWithLabel("E-Mail",Registry.CLIENT_EMAIL_ID,billGenerateUIComponent);
		//panelEmail.setBounds(860, 5, 240, 25);
		panelBillHeader.add(panelEmail);
		
		JPanel panelVehicleType = templates.getLabelWithCombo("Veh.Type",Registry.VEHICLE_TYPES,billGenerateUIComponent);
		//panelVehicleType.setBounds(670, 3, 200, 32);
		panelBillHeader.add(panelVehicleType);
		
		/*JLabel lblContactNumber = new JLabel("Contact No:");
		lblContactNumber.setBounds(600, 0, 100, 30);
		panelBillHeader.add(lblContactNumber);
		
		JLabel lblContactsNumber1 = new JLabel(Registry.CLIENT_MOB1);
		lblContactsNumber1.setBounds(700, 0, 100, 30);
		panelBillHeader.add(lblContactsNumber1);
		
		JLabel lblContactsNumber2 = new JLabel(Registry.CLIENT_MOB2);
		lblContactsNumber2.setBounds(700,20, 100, 30);
		panelBillHeader.add(lblContactsNumber2);
		
		/*JLabel lblPanNumber = new JLabel("PAN NO:");
		lblPanNumber.setBounds(10, 40, 50, 30);
		panelBillHeader.add(lblPanNumber);
		
		JLabel lblPanNumberValue = new JLabel(Registry.CLIENT_PAN_ID);
		lblPanNumberValue.setBounds(80, 40, 100, 30);
		panelBillHeader.add(lblPanNumberValue);*/
		
		
		
		/*JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(600, 40, 100, 30);
		panelBillHeader.add(lblEmail);
		
		JLabel lblEmailValue = new JLabel(Registry.CLIENT_EMAIL_ID);
		lblEmailValue.setBounds(700, 40, 200, 30);
		panelBillHeader.add(lblEmailValue);*/
		
		owner.add(panelBillHeader);
		
		// Body Panel
		JPanel panelBillBody = new JPanel();
		panelBillBody.setBounds(0, 73, Registry.MAIN_WINDOW_WIDTH-6, 471);
		panelBillBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody.setLayout(new FlowLayout());
		
		/*JLabel lblTO = new JLabel("TO,");
		lblTO.setBounds(5, 30, 30, 30);
		panelBillBody.add(lblTO);
		
		JComboBox<String> txtCustomer = new JComboBox<String>(Registry.CUSSTOMER_LIST);
		txtCustomer.setBounds(10, 52, 100, 30);
		panelBillBody.add(txtCustomer);*/
		
		JPanel panelCustomer = templates.getLabelWithCombo("TO",Registry.CUSSTOMER_LIST, billGenerateUIComponent);
		//panelCustomer.setBounds(5, 3, 120, 32);
		panelBillBody.add(panelCustomer);
		
		JPanel panelDateOfTravels = templates.getLabelWithTextField("Start Date",null,true,15, billGenerateUIComponent);
		//panelDateOfTravels.setBounds(125, 3, 270, 32);
		//panelDateOfTravels.setBackground(Color.cyan);
		panelBillBody.add(panelDateOfTravels);
		
		JPanel panelDateOfReturn = templates.getLabelWithTextField("Return Date",null,true,15, billGenerateUIComponent);
		//panelDateOfReturn.setBounds(395, 3, 270, 32);
		//panelDateOfReturn.setBackground(Color.yellow);
		panelBillBody.add(panelDateOfReturn);

		JPanel panelVehicleNumber = templates.getLabelWithTextField("Veh.Number","",false,15, billGenerateUIComponent);
		//panelVehicleNumber.setBounds(870, 3, 300, 32);
		panelBillBody.add(panelVehicleNumber);
		
		JPanel panelVendorNumber = templates.getLabelWithTextField("Vendor Code","",false,15, billGenerateUIComponent);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelVendorNumber);
		
		/*JLabel lblDateOfTravel = new JLabel("Date Of Travel:");
		lblDateOfTravel.setBounds(400, 30, 100, 30);
		panelBillBody.add(lblDateOfTravel);
		
		JLabel lblDateOfTravel = new JLabel("Date Of Return:");
		lblBillDate.setBounds(400, 30, 100, 30);
		panelBillBody.add(lblBillDate);
		JLabel lblBillDate = new JLabel("Bill Date:");
		lblBillDate.setBounds(400, 30, 100, 30);
		panelBillBody.add(lblBillDate);
		JLabel lblBillDate = new JLabel("Bill Date:");
		lblBillDate.setBounds(400, 30, 100, 30);
		panelBillBody.add(lblBillDate);
		JLabel lblBillDate = new JLabel("Bill Date:");
		lblBillDate.setBounds(400, 30, 100, 30);
		panelBillBody.add(lblBillDate);*/
		
		JPanel panelEmployeeName = templates.getLabelWithTextField("Emp. Name","",false,15, billGenerateUIComponent);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelEmployeeName);
		
		JPanel panelStartKM = templates.getLabelWithIntSpinner("Start KM",15,0,1000,1, billGenerateUIComponent);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelStartKM);
		
		JPanel panelEndKM = templates.getLabelWithIntSpinner("End KM",10,0,1000,1, billGenerateUIComponent);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelEndKM);
		
		JPanel panelStartTime = templates.getLabelWithTimeSpinner("Start Time", billGenerateUIComponent);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelStartTime);
		
		JPanel panelEndTime = templates.getLabelWithTimeSpinner("End Time", billGenerateUIComponent);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelBillBody.add(panelEndTime);
		
		JPanel panelTotalKM = templates.getLabelWithTextField("Total KM","",false,5, billGenerateUIComponent);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		JTextField textTotalKM = (JTextField) billGenerateUIComponent.get("Total KM_textValue");
		SpinnerModel textStart = (SpinnerModel) billGenerateUIComponent.get("Start KM_spinnermodel");
		SpinnerModel textEnd = (SpinnerModel) billGenerateUIComponent.get("End KM_spinnermodel");
		textTotalKM.setText(String.valueOf(Integer.parseInt(textStart.getValue().toString())-Integer.parseInt(textEnd.getValue().toString())));
		panelBillBody.add(panelTotalKM);
		owner.add(panelBillBody);
	}

	

}
