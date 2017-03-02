package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.AddDutyTypeUI;
import ui.EditDutyTypeUI;
import util.Registry;
import util.SConstants;
import ModelXml.DutyTypeDataModel;
import beans.DutyType;
import exceptions.PopupDialogs;

public class DutyTypeButtonHandler implements ActionListener {

	private Registry reg = SConstants.reg;
	private JDialog parent = null;
	public DutyTypeButtonHandler(JDialog owner)
	{
		this.parent = owner;
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equalsIgnoreCase(reg.getValueFor("ID_BTN_ADD_DUTY_TYPE")))
		{
			Map<String, Object> dutyTypeComponentMap = AddDutyTypeUI.getDutyTypeUIComponent();
			
			JPanel enterHours = (JPanel) dutyTypeComponentMap.get("enterHours");
			JTextField lblHoursValue = (JTextField) enterHours.getComponent(2);
			String hoursValue = lblHoursValue.getText();
			
			JPanel enterKmValue = (JPanel) dutyTypeComponentMap.get("enterKmValue");
			JTextField lblKMValue = (JTextField) enterKmValue.getComponent(2);
			String kmValue = lblKMValue.getText();
			
			JPanel enterPkgRate = (JPanel) dutyTypeComponentMap.get("enterPkgRate");
			JTextField lblPkgValue = (JTextField) enterPkgRate.getComponent(2);
			String pkgValue = lblPkgValue.getText();
			
			JPanel panelCustomer = (JPanel) dutyTypeComponentMap.get("panelCustomer");
			@SuppressWarnings("rawtypes")
			JComboBox comboCustomer = (JComboBox) panelCustomer.getComponent(2);
			String cName = comboCustomer.getSelectedItem().toString();
			
			JPanel panelVehicle = (JPanel) dutyTypeComponentMap.get("panelVehicle");
			@SuppressWarnings("rawtypes")
			JComboBox comboVehicle = (JComboBox) panelVehicle.getComponent(2);
			String vName = comboVehicle.getSelectedItem().toString();
			if(hoursValue.isEmpty() ||  cName.isEmpty() || kmValue.isEmpty() || pkgValue.isEmpty() || vName.isEmpty())
			{
				new PopupDialogs("Please Fill All the fields...",PopupDialogs.ERROR_MESSAGE);
			}
			else
			{
				int uid = 0 + (int)(Math.random() * 1000); 
				DutyType dutyType = new DutyType(uid,Integer.parseInt(hoursValue), Integer.parseInt(kmValue), Double.parseDouble(pkgValue), cName, vName);
				//new Dao().addDutyType(dutyType);
				new DutyTypeDataModel().addDutyType(dutyType);
				new PopupDialogs("Duty Type Added Successfully",PopupDialogs.PLAIN_MESSAGE);
				parent .dispose();
			}
			
			
			
		}
		if(event.getActionCommand().equalsIgnoreCase(reg.getValueFor("BTN_STRING_EDIT_DUTY_TYPE")))
		{
			Map<String, Object> map = EditDutyTypeUI.getEditDutyTypeUIComponent();
			JPanel panelCombo = (JPanel) map.get("panelDutyType");
			@SuppressWarnings("rawtypes")
			JComboBox comboDutyType = (JComboBox) panelCombo.getComponent(2);
			DutyType dutyType = new DutyTypeDataModel().getDutyType(comboDutyType.getSelectedItem().toString());
			
			DutyTypeDataModel objDutyTypeDataModel = new DutyTypeDataModel();
			DutyType oldDutyType = objDutyTypeDataModel.getDutyTypeByUID(String.valueOf(dutyType.getUid()));
			objDutyTypeDataModel.updateAttributeValue(String.valueOf(oldDutyType.getUid()), "km", "11111");
		}
		if(event.getActionCommand().equalsIgnoreCase("Cancel"))
		{
			parent.dispose();
		}
	}

}