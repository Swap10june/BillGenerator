package listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.CustomException;
import beans.DutyType;
import ui.BillGenerateUI;
import util.NumToWords;
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
		if(comboFrom.equalsIgnoreCase(reg.getValueFor("ID_Duty_Type_combo")))
		{
			double totalKM = 0.0;
			if(!BillGenerateUI.getComponentMap().containsKey("totalKM"))
			{
				new CustomException("Please Select Km Value.",CustomException.WARNING);
				return;
			}
			totalKM = (double) BillGenerateUI.getComponentMap().get("totalKM");
			if(totalKM<=0)
			{
				new CustomException("Total Km Should be greater Than 0(Zero)");
				return;
			}
			JPanel panelTotalDistance = (JPanel) BillGenerateUI.getComponentMap().get("panelTotalDistance");
			JLabel lblTotalDistanceValue = (JLabel) panelTotalDistance.getComponent(2);
			
			DutyType t = DutyType.map.get(e.getItem().toString());
			lblTotalDistanceValue.setText(String.valueOf(t.getKm()));
			
			
			JPanel panelRate = (JPanel) BillGenerateUI.getComponentMap().get("panelRate");
			JLabel lblRate = (JLabel) panelRate.getComponent(2);
			lblRate.setText(String.valueOf(t.getPackageRate()));
			
			
			JPanel panelAmount = (JPanel) BillGenerateUI.getComponentMap().get("panelAmount");
			JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
			lblAMountValue.setText(String.valueOf(t.getPackageRate()));
			 
			 
			JPanel panelTotalExtraKM = (JPanel) BillGenerateUI.getComponentMap().get("panelTotalExtraKM");
			JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(2);
			
			double extraKM = totalKM-t.getKm();
			lblExtraKM.setText(String.valueOf(extraKM));
			 
			
			JPanel panelExtraKMRate = (JPanel) BillGenerateUI.getComponentMap().get("panelExtraKMRate");
			JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(2);
			lblExtraRate.setText(String.valueOf(10));
			 
			
			
			JPanel panelExtraKMAmount = (JPanel) BillGenerateUI.getComponentMap().get("panelExtraKMAmount");
			JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
			lblExtraAMount.setText(String.valueOf(extraKM*10));
			 
			
			 
		}
	}

}
