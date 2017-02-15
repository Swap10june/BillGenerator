package listners;

import java.util.Map;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import S_Util.Registry;
import exceptions.CustomException;
import ui.BillGenerateUI;

public class SpinnerChangeListner implements ChangeListener
{

	@Override
	public void stateChanged(ChangeEvent e)
	{
		Map<String, Object> billGenerateUIComponentsMap = BillGenerateUI.getComponentMap();
		JTextField textTotalKM = (JTextField) billGenerateUIComponentsMap.get("Total KM_textValue");
		textTotalKM.setEditable(false);
		SpinnerModel textStart = (SpinnerModel) billGenerateUIComponentsMap.get("Start KM_spinnermodel");
		SpinnerModel textEnd = (SpinnerModel) billGenerateUIComponentsMap.get("End KM_spinnermodel");
		int totalKm = Integer.parseInt(textStart.getValue().toString())-Integer.parseInt(textEnd.getValue().toString());
		if(totalKm>0)
			textTotalKM.setText(String.valueOf(totalKm));
		else
		{
			try
			{
				throw new CustomException(Registry.KM_EXCEPTION_STRING);
			} catch (CustomException e1)
			{
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			textTotalKM.setText(String.valueOf(0));
		}
				
	}

}
