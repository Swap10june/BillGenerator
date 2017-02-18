package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import listners.ComboItemListner;
import listners.SpinnerChangeListner;

import org.jdesktop.swingx.JXDatePicker;

import util.Registry;
import util.SConstants;
import util.Utils;

public class UITemplates 
{

	Registry reg = SConstants.reg;
	public JPanel getLabelWithLabel(String mapKey, String labelKey,Object value,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		String str = "";
		if(value instanceof  Date)
		{
			Date date = (Date) value;
			str = date.toLocaleString();
		}
		else if(value instanceof String)
		{
			str = "<HTML><U>"+(String) value+"</U></HTML>";
		}
		JLabel labelValue = new JLabel(str);
		/*JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        panel.add(picker);
        panel.add(picker);*/
		panel.add(labelkey);
		panel.add(labelValue);
		
		billGenerateUIComponent.put(mapKey, panel);
		//billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_value")), labelValue);
		
		return panel;
		
	}
	public JPanel getLabelWithTextFieldDatePicker(String mapKey, String labelKey,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		
		JXDatePicker picker = new JXDatePicker();
		picker.setBackground(Color.magenta);
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

        
		panel.add(labelkey);
		
		panel.add(picker);
		billGenerateUIComponent.put(mapKey, panel);
		//billGenerateUIComponent.put(Utils.getComponentName(labelText,reg.getValueFor("L_Picker")), picker);
		return panel;
	}
	public JPanel getLabelWithTextField(String mapKey,String labelKey,String TextValue,int TextColumnSize,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		String str = "";
		if(TextValue instanceof String)
		{
			str = (String) TextValue;
		}
		JTextField textValue = new JTextField(TextColumnSize);
		textValue.setText(TextValue);
		panel.add(labelkey);
		//billGenerateUIComponent.put(Utils.getComponentName(labelText,reg.getValueFor("L_key")), labelkey);
		panel.add(textValue);
		//System.out.println(labelText);
		//System.out.println(Utils.getComponentName(labelText,reg.getValueFor("L_value")));
		//billGenerateUIComponent.put(Utils.getComponentName(labelText,reg.getValueFor("L_value")), textValue);
		billGenerateUIComponent.put(mapKey, panel);
		
		return panel;
	}
	public JPanel getLabelWithCombo(String mapKey,String labelKey,String comboID, String[] vehicleTypes,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelKey +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		JComboBox<String> textValue = new JComboBox<String>(vehicleTypes);
		textValue.addItemListener(new ComboItemListner(comboID));
		panel.add(labelkey);
		panel.add(textValue);
		//billGenerateUIComponent.put(Utils.getComponentName(string, reg.getValueFor("L_key")), labelkey);
		//billGenerateUIComponent.put(Utils.getComponentName(string, reg.getValueFor("L_value")), textValue);
		billGenerateUIComponent.put(mapKey, panel);
		return panel;
	}
	public JPanel getLabelWithTimeSpinner(String mapKey,String labelKey,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelKey +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		
		JSpinner spinner = new JSpinner();
		SpinnerDateModel spinnermodel = new SpinnerDateModel();
		spinnermodel.setCalendarField(Calendar.MINUTE);
		spinner .setModel(spinnermodel);
		spinner .setEditor(new JSpinner.DateEditor(spinner , "hh:mm:ss"));
		
	    panel.add(labelkey);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_key")), labelkey);
	    panel.add(spinner);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_spinner")), spinner);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_spinnermodel")), spinnermodel);
	    billGenerateUIComponent.put(mapKey,panel );
	    return panel;
	}
	public JPanel getLabelWithIntSpinner(String mapKey,String labelKey,int value,int min,int max,int step,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelKey +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		
		JSpinner spinner = new JSpinner();
		SpinnerModel spinnermodel = new SpinnerNumberModel(value,min,max,step);
		//spinnermodel.setCalendarField(Calendar.MINUTE);
		spinner .setModel(spinnermodel);
		spinner.addChangeListener(new SpinnerChangeListner());
		
	    panel.add(labelkey);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_key")), labelkey);
	    panel.add(spinner);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_spinner")), spinner);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_spinnermodel")), spinnermodel);
	    billGenerateUIComponent.put(mapKey,panel );
	    return panel;
	}
	
}
