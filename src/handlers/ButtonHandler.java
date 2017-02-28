package handlers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import beans.DutyType;
import ui.BillGenerateUI;
import ui.DutyTypeUI;
import ModelXml.DutyTypeDataModel;
import util.NumToWords;
import util.Registry;
import util.SConstants;

public class ButtonHandler implements ActionListener
{

	Registry reg = SConstants.reg;
	Map<String, Object> componentMap;
	private Window parent = null;
	public ButtonHandler()
	{
		componentMap = BillGenerateUI.getComponentMap();
	}
	
	public ButtonHandler(String valueFor, JDialog owner)
	{
		this.parent = owner;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getActionCommand().equalsIgnoreCase(reg.getValueFor("V_TOTAL_BTN_STRING")))
		{			
			JPanel panelAmount = (JPanel) componentMap.get("panelAmount");
			JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
			
			JPanel panelExtraKMAmount = (JPanel) componentMap.get("panelExtraKMAmount");
			JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
			
			JPanel panelTollAmount = (JPanel) componentMap.get("panelTollAmount");
			JTextField tollAmountText = (JTextField) panelTollAmount.getComponent(2);
			
			JPanel panelNightHaltAmount = (JPanel) componentMap.get("panelTollAmount");
			JTextField nightHaltAmountText = (JTextField) panelNightHaltAmount.getComponent(2);
			
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
			finalAmount.setText(inwords+" Rupees Only");
			
			JButton btnGenerate = (JButton) BillGenerateUI.getComponentMap().get("btnGenerate");
			btnGenerate.setEnabled(true);
		}
		if(arg0.getActionCommand().equalsIgnoreCase(reg.getValueFor("ID_BTN_ADD_DUTY_TYPE")))
		{
			Map<String, Object> dutyTypeComponentMap = DutyTypeUI.getDutyTypeUIComponent();
			
			JPanel enterHours = (JPanel) dutyTypeComponentMap.get("enterHours");
			JTextField lblHoursValue = (JTextField) enterHours.getComponent(2);
			String hoursValue = lblHoursValue.getText();
			
			JPanel enterKmValue = (JPanel) dutyTypeComponentMap.get("enterKmValue");
			JTextField lblKMValue = (JTextField) enterKmValue.getComponent(2);
			String kmValue = lblKMValue.getText();
			
			JPanel enterPkgRate = (JPanel) dutyTypeComponentMap.get("enterPkgRate");
			JTextField lblPkgValue = (JTextField) enterPkgRate.getComponent(2);
			String pkgValue = lblPkgValue.getText();
			
			DutyType dutyType = new DutyType(Integer.parseInt(hoursValue), Integer.parseInt(kmValue), Integer.parseInt(pkgValue));
			//new Dao().addDutyType(dutyType);
			new DutyTypeDataModel().addDutyType(dutyType);
			
			parent .dispose();
		}
		if(arg0.getActionCommand().equalsIgnoreCase(reg.getValueFor("ID_BTN_Edit_DUTY_TYPE")))
		{
			
		}
	}

}
