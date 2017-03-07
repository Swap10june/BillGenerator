package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.PopupDialogs;
import beans.Vehicle;
import model.VehicleDataModel;
import ui.AddVehicleUI;
import ui.EditVehicleUI;
import util.SConstants;

public class VehicleButtonHandler implements ActionListener {

	private JDialog parent = null;
	public VehicleButtonHandler(JDialog owner)
	{
		this.parent = owner;
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equalsIgnoreCase(SConstants.ADD_BTN_STRING))
		{
			Map<String, Object> compMap = AddVehicleUI.getAddVehicleUIComponent();
			
			JPanel enterVehicleName =  (JPanel) compMap.get("enterVehicleName");
			final JTextField txtVName = (JTextField) enterVehicleName.getComponent(2);
			
			JPanel enterVNumber =  (JPanel) compMap.get("enterVNumber");
			final JTextField txtVNo= (JTextField) enterVNumber.getComponent(2);
			
			JPanel enterCustomerName =  (JPanel) compMap.get("enterCustomerName");
			final JTextField txtCName = (JTextField) enterCustomerName.getComponent(2);
			
			if(!txtVName.getText().isEmpty()|| !txtCName.getText().isEmpty() || !txtVNo.getText().isEmpty())
			{
				VehicleDataModel model = new VehicleDataModel();
				int uid = model.getAllVehicleNames().length;
				Vehicle vehicle = new Vehicle((uid+1),txtVName.getText(), txtCName.getText(),txtVNo.getText());
				// TODO: add to db
				model.addVehicle(vehicle);
				new PopupDialogs("Vehicle Added Successfully",PopupDialogs.PLAIN_MESSAGE);
				parent .dispose();
			}
			else
			{
				new PopupDialogs("Please fill all the fields", PopupDialogs.ERROR_MESSAGE);
			}
		}
	
		if(event.getActionCommand().equalsIgnoreCase(SConstants.EDIT_BTN_STRING))
		{
			Map<String, Object> compoMap = EditVehicleUI.getEditDutyTypeUIComponent();
			
			JPanel panelSelectVehicle = (JPanel) compoMap.get("panelSelectVehicle");
			@SuppressWarnings("unchecked")
			JComboBox<String> comboEditVehicle  = (JComboBox<String>) panelSelectVehicle.getComponent(2);
			
			Vehicle oldVehicle = EditVehicleUI.getModel().getVehicle(comboEditVehicle.getSelectedItem().toString());
			
			JPanel enterVehicleName = (JPanel) compoMap.get("enterVehicleName");
			JTextField vName = (JTextField) enterVehicleName.getComponent(2);
			String vNameValue = vName.getText();
			
			JPanel enterVNumber = (JPanel) compoMap.get("enterVNumber");
			JTextField vNo = (JTextField) enterVNumber.getComponent(2);
			String vNoValue = vNo.getText();
			
			JPanel enterCustomerName = (JPanel) compoMap.get("enterCustomerName");
			JTextField cName = (JTextField) enterCustomerName.getComponent(2);
			String cNameValue = cName.getText();
			
			
			Vehicle newVehicle = new Vehicle(oldVehicle.getUid(), vNameValue, cNameValue,vNoValue);
			EditVehicleUI.getModel().updateAttributeValue(newVehicle);
			new PopupDialogs("Vehicle Updated Successfully", PopupDialogs.PLAIN_MESSAGE);
			parent.dispose();
		}
		if(event.getActionCommand().equalsIgnoreCase(SConstants.CANCEL_BTN_STRING))
		{
			parent.dispose();
		}
	}

}
