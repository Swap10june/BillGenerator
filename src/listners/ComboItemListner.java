package listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboItemListner implements ItemListener{

	public ComboItemListner(String string)
	{
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		/*if(comboFrom.equalsIgnoreCase(TelcoBOM.UI_ID+reg.getValueFor("ID_DUTY_TYPE_COMBO")))
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
			 
			
			 
		}*/
		
				
	}

}
