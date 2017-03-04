package listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DutyTypeDataModel;
import exceptions.PopupDialogs;
import beans.DutyType;
import ui.EditDutyTypeUI;
import ui.TelcoBOM;
import util.Registry;
import util.SConstants;

public class ComboItemListner implements ItemListener{

	private String comboFrom = null;
	private Registry reg = SConstants.reg;
	public ComboItemListner(String string)
	{
		this.comboFrom = string;
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if(comboFrom.equalsIgnoreCase(TelcoBOM.UI_ID+reg.getValueFor("ID_DUTY_TYPE_COMBO")))
		{
			double totalKM = 0.0;
			if(!TelcoBOM.getComponentMap().containsKey("totalKM"))
			{
				new PopupDialogs("Please Select Km Value.",PopupDialogs.WARNING_MESSAGE);
				return;
			}
			totalKM = (double) TelcoBOM.getComponentMap().get("totalKM");
			if(totalKM<=0)
			{
				new PopupDialogs("Total Km Should be greater Than 0(Zero)",PopupDialogs.WARNING_MESSAGE);
				return;
			}
			JButton btnToatl = (JButton) TelcoBOM.getComponentMap().get("btnTotal");
			btnToatl.setEnabled(true);
			
			JPanel panelTotalPkgKm = (JPanel) TelcoBOM.getComponentMap().get("panelTotalPkgKm");
			JLabel lblTotalDistanceValue = (JLabel) panelTotalPkgKm.getComponent(2);
			
			String dutyTypeID = e.getItem().toString();
			
			DutyType t = new DutyTypeDataModel().getDutyType(dutyTypeID);
			lblTotalDistanceValue.setText(String.valueOf(t.getKm()));
			
			
			JPanel panelRate = (JPanel) TelcoBOM.getComponentMap().get("panelRate");
			JLabel lblRate = (JLabel) panelRate.getComponent(2);
			lblRate.setText(String.valueOf(t.getPackageRate()));
			
			
			JPanel panelAmount = (JPanel) TelcoBOM.getComponentMap().get("panelAmount");
			JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
			lblAMountValue.setText(String.valueOf(t.getPackageRate()));
			 
			 
			JPanel panelTotalExtraKM = (JPanel) TelcoBOM.getComponentMap().get("panelTotalExtraKM");
			JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(2);
			
			double extraKM = totalKM-t.getKm();
			lblExtraKM.setText(String.valueOf(extraKM));
			 
			
			JPanel panelExtraKMRate = (JPanel) TelcoBOM.getComponentMap().get("panelExtraKMRate");
			JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(2);
			lblExtraRate.setText(String.valueOf(10));
			 
			
			
			JPanel panelExtraKMAmount = (JPanel) TelcoBOM.getComponentMap().get("panelExtraKMAmount");
			JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
			lblExtraAMount.setText(String.valueOf(extraKM*10));
			 
			
			 
		}
		if(comboFrom.equalsIgnoreCase(EditDutyTypeUI.UI_ID+reg .getValueFor("ID_SELECT_DUTY_TYPE_COMBO")))
		{
			Map<String, Object> map = EditDutyTypeUI.getEditDutyTypeUIComponent();
			JPanel panelCombo = (JPanel) map.get("panelDutyType");
			@SuppressWarnings("rawtypes")
			JComboBox comboDutyType = (JComboBox) panelCombo.getComponent(2);
			List<Object> pnelList = EditDutyTypeUI.getList();
			DutyType dutyType = new DutyTypeDataModel().getDutyType(comboDutyType.getSelectedItem().toString());
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
			JTextField textCustomer = (JTextField) customer.getComponent(2);
			textCustomer.setText(String.valueOf(dutyType.getCustomerName()));
			
			JPanel vehicle = (JPanel) map.get("vehicle");
			JTextField textVehicle = (JTextField) vehicle.getComponent(2);
			textVehicle.setText(String.valueOf(dutyType.getVehicleType()));
		}
				
	}

}
