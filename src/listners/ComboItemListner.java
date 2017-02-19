package listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
			
			JPanel panelTotalDistance = (JPanel) BillGenerateUI.getComponentMap().get("panelTotalDistance");
			JLabel lblTotalDistanceValue = (JLabel) panelTotalDistance.getComponent(1);
			
			DutyType t = DutyType.map.get(e.getItem().toString());
			lblTotalDistanceValue.setText(String.valueOf(t.getKm()));
			
			
			JPanel panelRate = (JPanel) BillGenerateUI.getComponentMap().get("panelRate");
			JLabel lblRate = (JLabel) panelRate.getComponent(1);
			lblRate.setText(String.valueOf(t.getPackageRate()));
			
			
			JPanel panelAmount = (JPanel) BillGenerateUI.getComponentMap().get("panelAmount");
			JLabel lblAMountValue = (JLabel) panelAmount.getComponent(1);
			lblAMountValue.setText(String.valueOf(t.getPackageRate()));
			 
			 
			JPanel panelTotalExtraKM = (JPanel) BillGenerateUI.getComponentMap().get("panelTotalExtraKM");
			JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(1);
			int totalKM = (int) BillGenerateUI.getComponentMap().get("totalKM");
			int extraKM = totalKM-t.getKm();
			lblExtraKM.setText(String.valueOf(extraKM));
			 
			
			JPanel panelExtraKMRate = (JPanel) BillGenerateUI.getComponentMap().get("panelExtraKMRate");
			JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(1);
			lblExtraRate.setText(String.valueOf(10));
			 
			
			
			JPanel panelExtraKMAmount = (JPanel) BillGenerateUI.getComponentMap().get("panelExtraKMAmount");
			JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(1);
			lblExtraAMount.setText(String.valueOf(extraKM*10));
			 
			int finalAMount = Integer.parseInt(lblAMountValue.getText())+ Integer.parseInt(lblExtraAMount.getText());
			NumToWords w = new NumToWords(); 
			String inwords = w.convert(finalAMount);
			
			
			
			JPanel panelFinalAmount = (JPanel) BillGenerateUI.getComponentMap().get("panelFinalAmount");
			JLabel lblFianlAMount = (JLabel) panelFinalAmount.getComponent(1);
			lblFianlAMount.setText(String.valueOf(finalAMount));
			
			JPanel panelAmountInWords = (JPanel) BillGenerateUI.getComponentMap().get("panelAmountInWords");
			JLabel finalAmount = (JLabel) panelAmountInWords.getComponent(1);
			finalAmount.setText(inwords+" Only");
			 
		}
	}

}
