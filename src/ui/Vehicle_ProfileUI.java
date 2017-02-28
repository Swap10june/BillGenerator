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

public class Vehicle_ProfileUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String,Object> billGenerateUIComponentsMap = new HashMap<String,Object>();
	private String action;
	private Registry reg = SConstants.reg;
	
	public static Map<String, Object> getComponentMap()
	{
		return billGenerateUIComponentsMap;
	}
	private UITemplates templates = new UITemplates();
	public Vehicle_ProfileUI(JDialog owner,String action)
	{
		super(owner);
		this.action=action;
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,"Admin");
		initUI(owner);
		owner.setVisible(true);
		
	}
	private void initUI(JDialog owner)
	{

		JPanel panelVehicleProfileBody = new JPanel();
		panelVehicleProfileBody.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 43);
		//panelVehicleProfileBody.setBackground(Color.cyan);
		panelVehicleProfileBody.setBorder(BorderFactory.createLineBorder(Color.black));
		panelVehicleProfileBody.setLayout(new FlowLayout());
		


		JPanel panelTOCustomer = templates.getLabelWithCombo("panelTOCustomer",reg.getValueFor("C_Vendor_NAME"),reg.getValueFor("ID_TO_CUSTOMER_Combo"), SConstants.CUSSTOMER_LIST, billGenerateUIComponentsMap);
		//panelCustomer.setBounds(5, 3, 120, 32);
		panelVehicleProfileBody.add(panelTOCustomer);
			
		owner.add(panelVehicleProfileBody);
		
		JPanel panelDutyType = templates.getLabelWithTextField("panelDutyType",reg.getValueFor("L_Duty_Type"),"Enter Duty Type",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelVehicleProfileBody.add(panelDutyType);
		
		owner.add(panelVehicleProfileBody);
		
		JPanel panelVehicleType = templates.getLabelWithTextField("panelVehicleType",reg.getValueFor("L_Veh_Type"),"Enter Vehicle Type",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelVehicleProfileBody.add(panelVehicleType);
		
		JPanel panelPackgRate = templates.getLabelWithTextField("panelPackgRate",reg.getValueFor("L_PKG_Rate"),"Enter Package Rate",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelVehicleProfileBody.add(panelPackgRate);
		
		owner.add(panelVehicleProfileBody);
		
		JPanel panelExtraRate = templates.getLabelWithTextField("panelExtraRate",reg.getValueFor("L_Extra_Rate"),"Extra Rate",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelVehicleProfileBody.add(panelExtraRate);
		
		JPanel panelVendorcode = templates.getLabelWithTextField("panelVendorcode",reg.getValueFor("L_Vendor_Code"),"Enter Vendor No",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelVehicleProfileBody.add(panelVendorcode);
		
		owner.add(panelVehicleProfileBody);
		
		JPanel panelACnoAC = templates.getLabelWithTextField("panelACnoAC",reg.getValueFor("C_VENDOR_ADDESSS"),"AC non AC (Y/N)",8, billGenerateUIComponentsMap);
		//panelVendorNumber.setBounds(550, 170, 400, 35);
		panelVehicleProfileBody.add(panelACnoAC);
		
		owner.add(panelVehicleProfileBody);
		if(action.equals("Add")){
			//query to add
		}else{
			// query to update
			
		}		
		
	
	
				
	}
}
