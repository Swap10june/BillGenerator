package ui;

import handlers.VehicleButtonHandler;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import util.SConstants;
import util.Utils;

public class AddVehicleUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String UI_ID = "AddVehicleUI"; 
	private UITemplates templates = new UITemplates();
	private static Map<String, Object> addVehicleUIComponentMap;
	//private static Map<String, Object> addVehicleUIComponent = new HashMap<String, Object>();
	public AddVehicleUI(JDialog owner, String windowName)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,windowName);
		addVehicleUIComponentMap = new HashMap<String, Object>();
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(final JDialog owner)
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new GridLayout(2, 2));
		bodyPanel.setBounds(10, 50, 500, 150);
		
		JPanel enterVehicleName = templates.getLabelWithTextField("enterVehicleName", " Enter Vehicle Name", "Enter Veh. Name here", 10, false, addVehicleUIComponentMap);
		bodyPanel.add(enterVehicleName);
		
		
		JPanel enterVNumber = templates.getLabelWithTextField("enterVNumber", " Enter Vehicle No", "Enter Veh. No here", 10, false, addVehicleUIComponentMap);
		bodyPanel.add(enterVNumber);
		
		
		JPanel enterCustomerName = templates.getLabelWithTextField("enterCustomerName", "Enter Customer Name", "Enter Customer Name here", 10, false, addVehicleUIComponentMap);
		bodyPanel.add(enterCustomerName);
		
		
		JPanel enterMonthlyRate = templates.getLabelWithTextField("enterMonthlyRate", "Enter Monthly Rate", "Enter Monthly Rate", 10, true, addVehicleUIComponentMap);
		bodyPanel.add(enterMonthlyRate);
		
		JPanel enterExKmRate = templates.getLabelWithTextField("enterExKmRate", "Enter Extra Km Rate", "Enter Extra Km Rate", 0, true, addVehicleUIComponentMap);
		bodyPanel.add(enterExKmRate);
		
		
		JButton btnLogin = new JButton(SConstants.ADD_BTN_STRING);
		btnLogin.setBounds(150, 200, 100, 30);
		btnLogin.addActionListener(new VehicleButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(350, 200, 100, 30);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				owner.dispose();
			}
		});
		owner.add(bodyPanel);
		owner.add(btnLogin);
		owner.add(btnCancel);
		
	}
	/**
	 * @return the addVehicleUIComponent
	 */
	public static Map<String, Object> getAddVehicleUIComponent() {
		return addVehicleUIComponentMap;
	}
	/**
	 * @param addVehicleUIComponent the addVehicleUIComponent to set
	 */
	public static void setAddVehicleUIComponent(Map<String, Object> addVehicleUIComponent) {
		AddVehicleUI.addVehicleUIComponentMap = addVehicleUIComponent;
	}

}
