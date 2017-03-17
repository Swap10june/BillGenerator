package uit.billgen.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.beans.Vehicle;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.exceptions.PopupDialogs;
import uit.billgen.uiviews.AddVehicleUI;
import uit.billgen.uiviews.EditVehicleUI;
import uit.billgen.util.Dao;
import uit.billgen.util.SConstants;

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
			@SuppressWarnings("unchecked")
			final JComboBox<String> txtCName = (JComboBox<String>) enterCustomerName.getComponent(2);
			
			JPanel enterMonthlyRate =  (JPanel) compMap.get("enterMonthlyRate");
			final JTextField txtMRate = (JTextField) enterMonthlyRate.getComponent(2);
			
			
			JPanel enterExKmRate =  (JPanel) compMap.get("enterExKmRate");
			final JTextField txtExKmRate = (JTextField) enterExKmRate.getComponent(2);
			
			if(!txtVName.getText().isEmpty()|| !txtCName.getSelectedItem().toString().isEmpty() || !txtVNo.getText().isEmpty() ||!txtMRate.getText().isEmpty() ||!txtExKmRate.getText().isEmpty())
			{
				VehicleDataModel model = new VehicleDataModel();
				int uid = model.getAllVehicleNames().length;
				Vehicle vehicle = new Vehicle((uid+1),txtVName.getText(), txtCName.getSelectedItem().toString(),txtVNo.getText(),txtMRate.getText(),txtExKmRate.getText());
				// TODO: add to db
				model.addVehicle(vehicle);
				new PopupDialogs(SConstants.MSG_ADDED_SUCCESSFULLY,PopupDialogs.PLAIN_MESSAGE);
				parent .dispose();
			}
			else
			{
				new PopupDialogs(SConstants.MSG_PLZ_FILL_ALL_THE_FIELDS, PopupDialogs.ERROR_MESSAGE);
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
			@SuppressWarnings("unchecked")
			JComboBox<String> cName = (JComboBox<String>) enterCustomerName.getComponent(2);
			String cNameValue = cName.getSelectedItem().toString();
			
			JPanel enterMonthlyRate = (JPanel) compoMap.get("enterMonthlyRate");
			JTextField vMRateText = (JTextField) enterMonthlyRate.getComponent(2);
			String mRate = vMRateText.getText();
			
			JPanel enterExKmRate =  (JPanel) compoMap.get("enterExKmRate");
			final JTextField txtExKmRate = (JTextField) enterExKmRate.getComponent(2);
			
			Vehicle newVehicle = new Vehicle(oldVehicle.getUid(), vNameValue, cNameValue,vNoValue,mRate,txtExKmRate.getText());
			EditVehicleUI.getModel().updateAttributeValue(newVehicle);
			new Dao().editVehicle(newVehicle);
			new PopupDialogs(SConstants.MSG_UPDATED_SUCCESSFULLY, PopupDialogs.PLAIN_MESSAGE);
			parent.dispose();
		}
		if(event.getActionCommand().equalsIgnoreCase(SConstants.CANCEL_BTN_STRING))
		{
			parent.dispose();
		}
	}

}
