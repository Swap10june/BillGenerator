package listners;

import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exceptions.CustomException;
import ui.BillGenerateUI;
import util.Registry;
import util.SConstants;

public class SpinnerChangeListner implements ChangeListener
{

	Registry reg = SConstants.reg;
	@Override
	public void stateChanged(ChangeEvent e)
	{
		Map<String, Object> billGenerateUIComponentsMap = BillGenerateUI.getComponentMap();
		
		JPanel panelTotalKM = (JPanel) billGenerateUIComponentsMap.get("panelTotalKM");
		JTextField textTotalKM=(JTextField) panelTotalKM.getComponent(1);
		textTotalKM.setEditable(false);
		
		JPanel panelStartKM = (JPanel) billGenerateUIComponentsMap.get("panelStartKM");
		JSpinner spinner  =(JSpinner) panelStartKM.getComponent(1);
		SpinnerModel textStartKM=spinner.getModel();
		
		
		JPanel panelEndKM = (JPanel) billGenerateUIComponentsMap.get("panelEndKM");
		JSpinner spinner1  = (JSpinner) panelEndKM.getComponent(1);
		SpinnerModel textEnd = spinner1.getModel();
		
		int totalKm = Integer.parseInt(textEnd.getValue().toString())-Integer.parseInt(textStartKM.getValue().toString());
		if(totalKm>0 )
		{
			textTotalKM.setText(String.valueOf(totalKm));
			BillGenerateUI.getComponentMap().put("totalKM", totalKm);
		}
		else
		{
			
			new CustomException(reg.getValueFor("E_KM_EXCEPTION_STRING"));
			textTotalKM.setText(String.valueOf(0));
			//textEnd.setValue(textEnd.getPreviousValue());
		}
				
	}

}
