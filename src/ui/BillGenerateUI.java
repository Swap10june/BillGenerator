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
	private static Map<String,Object> billGenerateUIComponentsMap = new HashMap<String,Object>();
	
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
		
		JPanel panelVehicleType = templates.getLabelWithCombo("Veh.Type",Registry.VEHICLE_TYPES,billGenerateUIComponentsMap);
		//panelVehicleType.setBounds(670, 3, 200, 32);
		panelBillHeader.add(panelVehicleType);
		
		owner.add(panelBillHeader);
		
		// Body Panel
		JPanel panelBillBody = new JPanel();
		panelBillBody.setBounds(0, 73, Registry.MAIN_WINDOW_WIDTH-6, 45);
		panelBillBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillBody.setLayout(new FlowLayout());
		
		
		JPanel panelCustomer = templates.getLabelWithCombo("TO",Registry.CUSSTOMER_LIST, billGenerateUIComponentsMap);
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
		
	}
}
