package uit.billgen.listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.beans.DutyType;
import uit.billgen.constants.SConstants;
import uit.billgen.datamodel.DutyTypeDataModel;
import uit.billgen.uiviews.EditDutyTypeUI;

public class DutyTypeComboListner implements ItemListener
{
	private String comboId = null;
	private DutyTypeDataModel m_dutyTypeModel;

	public DutyTypeComboListner(String comboEditDutyTypeSelctDutyType)
	{
		this.comboId = comboEditDutyTypeSelctDutyType;
		this.m_dutyTypeModel = new DutyTypeDataModel();
	}

	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if(comboId.equalsIgnoreCase(SConstants.COMBO_EDIT_DUTY_TYPE_SELCT_DUTY_TYPE))
		{
			Map<String, Object> map = EditDutyTypeUI.getEditDutyTypeUIComponent();
			JPanel panelCombo = (JPanel) map.get("panelDutyType");
			@SuppressWarnings("rawtypes")
			JComboBox comboDutyType = (JComboBox) panelCombo.getComponent(2);
			List<Object> pnelList = EditDutyTypeUI.getList();
			DutyType dutyType = m_dutyTypeModel.getDutyType(comboDutyType.getSelectedItem().toString());
			for (int i = 0; i < pnelList.size(); i++)
			{
				if(pnelList.get(i) instanceof JPanel)
					((JPanel) pnelList.get(i)).setVisible(true);
				if(pnelList.get(i) instanceof JButton)
					((JButton) pnelList.get(i)).setEnabled(true);
			}
			JPanel enterHours = (JPanel) map.get("enterHours");
			JTextField textHours = (JTextField) enterHours.getComponent(2);
			textHours.setText(String.valueOf(dutyType.getHours()));
			
			JPanel enterKmValue = (JPanel) map.get("enterKmValue");
			JTextField textKm = (JTextField) enterKmValue.getComponent(2);
			textKm.setText(String.valueOf(dutyType.getKm()));
			
			JPanel enterPkgRate = (JPanel) map.get("enterPkgRate");
			JTextField textPkg = (JTextField) enterPkgRate.getComponent(2);
			textPkg.setText(String.valueOf(dutyType.getPackageRate()));
			
			JPanel enterExtraRate = (JPanel) map.get("enterExtraKmRate");
			JTextField textExtraKmRate = (JTextField) enterExtraRate.getComponent(2);
			textExtraKmRate.setText(String.valueOf(dutyType.getExtraKmRate()));
			
			JPanel customer = (JPanel) map.get("customer");
			@SuppressWarnings("unchecked")
			JComboBox<String> textCustomer = (JComboBox<String>) customer.getComponent(2);
			textCustomer.setSelectedItem(String.valueOf(dutyType.getCustomerName()));
			
			JPanel vehicle = (JPanel) map.get("vehicle");
			@SuppressWarnings("unchecked")
			JComboBox<String> textVehicle = (JComboBox<String>) vehicle.getComponent(2);
			textVehicle.setSelectedItem(String.valueOf(dutyType.getVehicleType()));
		}
	}

}
