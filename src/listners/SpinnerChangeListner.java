package listners;

import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exceptions.PopupDialogs;

import ui.TelcoBOM;
import util.Registry;
import util.SConstants;

public class SpinnerChangeListner implements ChangeListener
{

	Registry reg = SConstants.reg;
	@Override
	public void stateChanged(ChangeEvent e)
	{
		Map<String, Object> billGenerateUIComponentsMap = TelcoBOM.getComponentMap();
		
		JPanel panelTotalKM = (JPanel) billGenerateUIComponentsMap.get("panelTotalKM");
		JTextField textTotalKM=(JTextField) panelTotalKM.getComponent(2);
		textTotalKM.setEditable(false);
		
		JPanel panelStartKM = (JPanel) billGenerateUIComponentsMap.get("panelStartKM");
		JSpinner spinner  =(JSpinner) panelStartKM.getComponent(2);
		SpinnerModel textStartKM=spinner.getModel();
		
		
		JPanel panelEndKM = (JPanel) billGenerateUIComponentsMap.get("panelEndKM");
		JSpinner spinner1  = (JSpinner) panelEndKM.getComponent(2);
		SpinnerModel textEnd = spinner1.getModel();
		
		double totalKm = Double.parseDouble(textEnd.getValue().toString())-Double.parseDouble(textStartKM.getValue().toString());
		if(totalKm>=0 )
		{
			textTotalKM.setText(String.valueOf(totalKm));
			TelcoBOM.getComponentMap().put("totalKM", totalKm);
		}
		else
		{
			
			new PopupDialogs(reg.getValueFor("E_KM_EXCEPTION_STRING"),PopupDialogs.ERROR_MESSAGE);
			//textTotalKM.setText(String.valueOf(0));
			//textEnd.setValue(textEnd.getPreviousValue());
		}
				
	}

}
