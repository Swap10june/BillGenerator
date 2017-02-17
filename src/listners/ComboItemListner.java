package listners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;

import beans.DutyType;
import ui.BillGenerateUI;
import S_Util.Registry;

public class ComboItemListner implements ItemListener{

	private String comboFrom = null;
	public ComboItemListner(String string)
	{
		this.comboFrom = string;
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if(comboFrom.equalsIgnoreCase(Registry.DUTYTYPE_COMBO_ID))
		{
			JLabel lblTotalDistance = (JLabel) BillGenerateUI.getComponentMap().get("Total KM_labelValue");
			//DutyType t = (DutyType) BillGenerateUI.getComponentMap().get("dutyType1");;
			DutyType t = DutyType.map.get(e.getItem().toString());
			lblTotalDistance.setText(String.valueOf(t.getKm()));
			
			JLabel lblRate = (JLabel) BillGenerateUI.getComponentMap().get("Rate_labelValue");
			 lblRate.setText(String.valueOf(t.getPackageRate()));
			 
			 JLabel lblAMount = (JLabel) BillGenerateUI.getComponentMap().get("Amount_labelValue");
			 lblAMount.setText(String.valueOf(t.getPackageRate()));
			 
			 
			 // extra 
			 JLabel lblExtraKM = (JLabel) BillGenerateUI.getComponentMap().get("Total Extra KM_labelValue");
			 int totalKM = (int) BillGenerateUI.getComponentMap().get("totalKM");
			 int extraKM = totalKM-t.getKm();
			 lblExtraKM.setText(String.valueOf(extraKM));
			 
			 JLabel lblExtraRate = (JLabel) BillGenerateUI.getComponentMap().get("Extra KM Rate_labelValue");
			 lblExtraRate.setText(String.valueOf(10));
			 
			 JLabel lblExtraAMount = (JLabel) BillGenerateUI.getComponentMap().get("Extra Amount_labelValue");
			 lblExtraAMount.setText(String.valueOf(extraKM*10));
			 
		}
	}

}
