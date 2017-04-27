package uit.billgen.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.uiviews.ExtraCabBillUI;
import uit.billgen.util.NumToWords;
import uit.billgen.util.SConstants;

public class ExtraCabBillUIButtonHandler implements ActionListener
{

	Map<String, Object> componentMap;
	public ExtraCabBillUIButtonHandler()
	{
		componentMap = ExtraCabBillUI.getComponentMap();
	}
	
	public ExtraCabBillUIButtonHandler(String valueFor, JDialog owner)
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getActionCommand().equalsIgnoreCase(SConstants.GET_TOTAL_BTN_STRING))
		{			
			JPanel panelAmount = (JPanel) componentMap.get("panelAmount");
			JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
			
			JPanel panelExtraKMAmount = (JPanel) componentMap.get("panelExtraKMAmount");
			JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
			
			JPanel panelTollAmount = (JPanel) componentMap.get("panelTollAmount");
			JTextField tollAmountText = (JTextField) panelTollAmount.getComponent(2);
			
			JPanel panelNightHaltAmount = (JPanel) componentMap.get("panelNightHaltAmount");
			JTextField nightHaltAmountText = (JTextField) panelNightHaltAmount.getComponent(2);
			
			JPanel panelServiceTax = (JPanel) componentMap.get("panelServiceTax");
			JTextField serviceTaxText = (JTextField) panelServiceTax.getComponent(2);
			
			JPanel panelExtraHourAmount = (JPanel) componentMap.get("panelExtraHourAmount");
			JLabel lblExtraHourAMount = (JLabel) panelExtraHourAmount.getComponent(2);
			double extraHourAmount = Double.parseDouble(lblExtraHourAMount.getText());
			
			double billAmount = extraHourAmount+Double.parseDouble(lblAMountValue.getText())+
					Double.parseDouble(lblExtraAMount.getText())+Double.parseDouble(nightHaltAmountText.getText().isEmpty()?"0":nightHaltAmountText.getText());
			double taxOnBillAMount = (billAmount*Double.parseDouble(serviceTaxText.getText().isEmpty()?"0":serviceTaxText.getText()))/100;
			double finalAMount = billAmount+taxOnBillAMount+Double.parseDouble(tollAmountText.getText().isEmpty()?"0":tollAmountText.getText());
					/*Double.parseDouble(lblAMountValue.getText())+
					Double.parseDouble(lblExtraAMount.getText())+
					Double.parseDouble(tollAmountText.getText().isEmpty()?"0":tollAmountText.getText())+
					Double.parseDouble(nightHaltAmountText.getText().isEmpty()?"0":nightHaltAmountText.getText())+
					Double.parseDouble(serviceTaxText.getText().isEmpty()?"0":serviceTaxText.getText());*/
			
			NumToWords w = new NumToWords(); 
			String inwords = w.convert((int)finalAMount);
			
			JPanel panelFinalAmount = (JPanel) componentMap.get("panelFinalAmount");
			JLabel lblFianlAMount = (JLabel) panelFinalAmount.getComponent(2);
			lblFianlAMount.setText(String.valueOf(finalAMount));
			
			JPanel panelAmountInWords = (JPanel) componentMap.get("panelAmountInWords");
			JLabel finalAmount = (JLabel) panelAmountInWords.getComponent(2);
			finalAmount.setText(inwords);
			
			JButton btnGenerate = (JButton) ExtraCabBillUI.getComponentMap().get("btnGenerate");
			btnGenerate.setEnabled(true);
		}
		
	}

}
