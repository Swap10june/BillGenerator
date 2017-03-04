package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.VehicleDataModel;
import beans.Vehicle;
import exceptions.PopupDialogs;
import util.Utils;

public class AddVehicleUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String UI_ID = "AddVehicleUI"; 
	private UITemplates templates = new UITemplates();
	private Map<String, Object> addVehicleUIComponentMap;
	private static Map<String, Object> addVehicleUIComponent = new HashMap<String, Object>();
	public AddVehicleUI(JDialog owner, String windowName)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,windowName);
		addVehicleUIComponentMap = new HashMap<String, Object>();
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(JDialog owner)
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new GridLayout(2, 2));
		bodyPanel.setBounds(10, 50, 500, 150);
		
		JPanel enterVehicleName = templates.getLabelWithTextField("enterVehicleName", " Enter Vehicle Name", "Enter Veh. Name here", 10, false, addVehicleUIComponentMap);
		bodyPanel.add(enterVehicleName);
		JTextField txtVName = (JTextField) enterVehicleName.getComponent(2);
		
		JPanel enterCustomerName = templates.getLabelWithTextField("enterCustomerName", "Enter Customer Name", "Enter Customer Name here", 10, false, addVehicleUIComponentMap);
		bodyPanel.add(enterCustomerName);
		JTextField txtCName = (JTextField) enterCustomerName.getComponent(2);
		
		JButton btnLogin = new JButton("Add");
		btnLogin.setBounds(150, 200, 100, 30);
		btnLogin.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(!txtVName.getText().isEmpty()|| !txtCName.getText().isEmpty())
				{
					VehicleDataModel model = new VehicleDataModel();
					int uid = model.getAllVehicles().length;
					Vehicle vehicle = new Vehicle((uid+1),txtVName.getText(), txtCName.getText());
					// TODO: add to db
					model.addVehicle(vehicle);
					new PopupDialogs("Vehicle Added Successfully",PopupDialogs.PLAIN_MESSAGE);
					owner .dispose();
				}
				else
				{
					new PopupDialogs("Please fill all the fields", PopupDialogs.ERROR_MESSAGE);
				}
			}
		});
		
		
		JButton btnCancel = new JButton("Cancel");
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
		return addVehicleUIComponent;
	}
	/**
	 * @param addVehicleUIComponent the addVehicleUIComponent to set
	 */
	public static void setAddVehicleUIComponent(Map<String, Object> addVehicleUIComponent) {
		AddVehicleUI.addVehicleUIComponent = addVehicleUIComponent;
	}

}
