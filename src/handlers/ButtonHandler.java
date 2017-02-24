package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javafx.scene.control.ComboBox;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import beans.DutyType;
import ui.BillGenerateUI;
import util.NumToWords;
import util.Registry;
import util.SConstants;

public class ButtonHandler implements ActionListener
{

	Registry reg = SConstants.reg;
	Map<String, Object> componentMap;
	public ButtonHandler()
	{
		componentMap = BillGenerateUI.getComponentMap();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getActionCommand().equalsIgnoreCase(reg.getValueFor("V_TOTAL_BTN_STRING")))
		{
			JPanel panelDutyType = (JPanel) componentMap.get("panelDutyType");
			@SuppressWarnings("unchecked")
			JComboBox<String> comboDutyType = (JComboBox<String>) panelDutyType.getComponent(2);
			DutyType t = DutyType.map.get(comboDutyType.getSelectedItem().toString());
			
			JPanel panelAmount = (JPanel) componentMap.get("panelAmount");
			JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
			//lblAMountValue.setText(String.valueOf(t.getPackageRate()));
			
			JPanel panelExtraKMAmount = (JPanel) componentMap.get("panelExtraKMAmount");
			JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
			//lblExtraAMount.setText(String.valueOf(extraKM*10));
			
			JPanel panelTollAmount = (JPanel) componentMap.get("panelTollAmount");
			JTextField tollAmountText = (JTextField) panelTollAmount.getComponent(2);
			
			JPanel panelNightHaltAmount = (JPanel) componentMap.get("panelTollAmount");
			JTextField nightHaltAmountText = (JTextField) panelNightHaltAmount.getComponent(2);
			//lblFianlAMount.setText(String.valueOf(finalAMount));
			
			double finalAMount = 
					Double.parseDouble(lblAMountValue.getText())+
					Double.parseDouble(lblExtraAMount.getText())+
					Double.parseDouble(tollAmountText.getText().isEmpty()?"0":tollAmountText.getText())+
					Double.parseDouble(nightHaltAmountText.getText().isEmpty()?"0":nightHaltAmountText.getText());
			NumToWords w = new NumToWords(); 
			String inwords = w.convert((int)finalAMount);
			
			JPanel panelFinalAmount = (JPanel) componentMap.get("panelFinalAmount");
			JLabel lblFianlAMount = (JLabel) panelFinalAmount.getComponent(2);
			lblFianlAMount.setText(String.valueOf(finalAMount));
			
			JPanel panelAmountInWords = (JPanel) componentMap.get("panelAmountInWords");
			JLabel finalAmount = (JLabel) panelAmountInWords.getComponent(2);
			finalAmount.setText(inwords+" Only");
		}
	}

}
