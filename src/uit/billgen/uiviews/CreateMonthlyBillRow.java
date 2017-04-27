package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;

import org.jdesktop.swingx.JXDatePicker;

import uit.billgen.beans.BillRow;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class CreateMonthlyBillRow extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UITemplates templates= new UITemplates();
	private Map<String, Object> billRowComponents;
	private BillRow billrow;
	public CreateMonthlyBillRow(final Map<String, Object> map, BillRow billRow)
	{
		this.billrow = billRow;
		billRowComponents = new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Row(this,"Bill Row");
		this.setLayout(null);
		
		JPanel totalBody = new JPanel();
		totalBody.setLayout(new GridLayout(15,1));
		totalBody.setBounds(0, 30, 500, 450);
		
				
		totalBody.add(templates.getLabelWithTextFieldDatePicker("fromDatePanel", Utils.getUtilityInstance().getStringOfCharacters( "From",16), billRowComponents));
		totalBody.add(templates.getLabelWithTextFieldDatePicker("toDatePanel", Utils.getUtilityInstance().getStringOfCharacters( "To",16), billRowComponents));
		totalBody.add(templates.getLabelWithTextArea("vehicleDesc", Utils.getUtilityInstance().getStringOfCharacters( "Enter Description",16), "Enter Description here", false, billRowComponents));
		totalBody.add(templates.getLabelWithIntSpinnerWOListner("monthlyKm", Utils.getUtilityInstance().getStringOfCharacters( "Enter Monthly Km",16),10, 0, 999999999, 1, billRowComponents));
		totalBody.add(templates.getLabelWithIntSpinnerWOListner("extraKm", Utils.getUtilityInstance().getStringOfCharacters( "Enter Extra Km",16),0, 0, 999999999, 1, billRowComponents));
		totalBody.add(new JPanel());
		totalBody.add(templates.getLabelWithComboWOListner("vehicle", Utils.getUtilityInstance().getStringOfCharacters("Select Vehicle",16), new VehicleDataModel().getAllVehicleNames(), billRowComponents));	
		totalBody.add(new JPanel());
		
		@SuppressWarnings("unchecked")
		final JComboBox<String> vehicleCombo = (JComboBox<String>) ((JPanel)billRowComponents.get("vehicle")).getComponent(2);
		//double rate = Double.parseDouble((new VehicleDataModel().getVehicle(vehicleCombo.getSelectedItem().toString())).getMonthlyRate());
		vehicleCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				Calendar startCalendar = new GregorianCalendar();
				startCalendar.setTime(((JXDatePicker)((JPanel)billRowComponents.get("fromDatePanel")).getComponent(2)).getDate());
				Calendar endCalendar = new GregorianCalendar();
				endCalendar.setTime(((JXDatePicker)((JPanel)billRowComponents.get("toDatePanel")).getComponent(2)).getDate());

				int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
				int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
				double rate = Double.parseDouble((new VehicleDataModel().getVehicle(vehicleCombo.getSelectedItem().toString())).getMonthlyRate());
				
				JTextField textRate = (JTextField) ((JPanel)billRowComponents.get("rate")).getComponent(2);
				textRate.setText(String.valueOf(rate));
				
				//JSpinner spinner  =(JSpinner) ((JPanel) billRowComponents.get("monthlyKm")).getComponent(2);
				//SpinnerModel txtMonthlyKm=spinner.getModel();
				//int km = (int)Double.parseDouble(txtMonthlyKm.getValue().toString());
				
				JTextField textAmount = (JTextField) ((JPanel)billRowComponents.get("amount")).getComponent(2);
				textAmount.setText(String.valueOf(rate*diffMonth));
				
				
				//Extra km 
				double exKmRate = Double.parseDouble((new VehicleDataModel().getVehicle(vehicleCombo.getSelectedItem().toString())).getExtraKmRate());
				JTextField textExRate = (JTextField) ((JPanel)billRowComponents.get("ExtraKmRate")).getComponent(2);
				textExRate.setText(String.valueOf(exKmRate));
				
				
				JSpinner spinner1  =(JSpinner) ((JPanel) billRowComponents.get("extraKm")).getComponent(2);
				SpinnerModel txtExtraKm=spinner1.getModel();
				int extraKm = (int)Double.parseDouble(txtExtraKm.getValue().toString());
				JTextField textExtraAmount = (JTextField) ((JPanel)billRowComponents.get("ExKmAmount")).getComponent(2);
				textExtraAmount.setText(String.valueOf(extraKm*exKmRate));
				
				
				JTextField TotalAmount = (JTextField) ((JPanel)billRowComponents.get("TotalAmount")).getComponent(2);
				TotalAmount.setText(String.valueOf(
						Double.parseDouble(textExtraAmount.getText().isEmpty()?"0":textExtraAmount.getText())+
						Double.parseDouble(textAmount.getText().isEmpty()?"0":textAmount.getText())));
			}
		});
		
		totalBody.add(templates.getLabelWithTextField("rate", Utils.getUtilityInstance().getStringOfCharacters("Rate",16),String.valueOf(0), 15, true, billRowComponents));
		totalBody.add(templates.getLabelWithTextField("amount",Utils.getUtilityInstance().getStringOfCharacters( "Amount",16),String.valueOf(0), 15, true, billRowComponents));
		totalBody.add(new JPanel());
		
		
		totalBody.add(templates.getLabelWithTextField("ExtraKmRate",Utils.getUtilityInstance().getStringOfCharacters( "Ex.Km Rate",16),String.valueOf(0), 15, true, billRowComponents));
		totalBody.add(templates.getLabelWithTextField("ExKmAmount",Utils.getUtilityInstance().getStringOfCharacters( "Extra Km Amount",16),String.valueOf(0), 15, true, billRowComponents));
		totalBody.add(new JPanel());
		JPanel finalAmount = templates.getLabelWithTextField("TotalAmount", Utils.getUtilityInstance().getStringOfCharacters("Total",16),String.valueOf(0), 15, true, billRowComponents);
		//finalAmount.setBackground(Color.MAGENTA);
		
		totalBody.add(finalAmount);
		JButton btnAdd = new JButton(SConstants.ADD_BTN_STRING);
		btnAdd.setBounds(200, 490, 100, 30);
		btnAdd.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				map.put("components"+String.valueOf(MonthlyCabUI.counter), billRowComponents);
				billrow.setId("components"+String.valueOf(MonthlyCabUI.counter));
				dispose();
			}
		});
		
		this.add(totalBody);
		this.add(btnAdd);
		setVisible(true);
		
		
	}
}
