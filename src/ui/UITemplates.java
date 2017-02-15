package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javafx.scene.layout.Border;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import listners.SpinnerChangeListner;

import org.jdesktop.swingx.JXDatePicker;

import S_Util.Registry;

public class UITemplates 
{
	public UITemplates()
	{
		// TODO Auto-generated constructor stub
	}
	public JPanel getLabelWithLabel(String key, Object value,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(key +":");
		labelkey.setFont(Registry.FONT_COURRIER_BOLD_10);
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
		
		billGenerateUIComponent.put(getComponentName(key, "labelkey"), labelkey);
		billGenerateUIComponent.put(getComponentName(key,"labelValue"), labelValue);
		
		return panel;
		
	}
	public JPanel getLabelWithTextFieldDatePicker(String labelText,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelText +":");
		labelkey.setFont(Registry.FONT_COURRIER_BOLD_10);
		
		JXDatePicker picker = new JXDatePicker();
		picker.setBackground(Color.magenta);
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

        
		panel.add(labelkey);
		billGenerateUIComponent.put(getComponentName(labelText,"labelkey"), labelkey);
		panel.add(picker);
		billGenerateUIComponent.put(getComponentName(labelText,"picker"), picker);
		return panel;
	}
	public JPanel getLabelWithTextField(String labelText,String TextValue,int TextColumnSize,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(labelText +":");
		labelkey.setFont(Registry.FONT_COURRIER_BOLD_10);
		String str = "";
		if(TextValue instanceof String)
		{
			str = (String) TextValue;
		}
		JTextField textValue = new JTextField(TextColumnSize);
		textValue.setText(str);
		panel.add(labelkey);
		billGenerateUIComponent.put(getComponentName(labelText,"labelkey"), labelkey);
		panel.add(textValue);
		billGenerateUIComponent.put(getComponentName(labelText,"textValue"), textValue);
		
		return panel;
	}
	public JPanel getLabelWithCombo(String string, String[] vehicleTypes,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(string +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(Registry.FONT_COURRIER_BOLD_10);
		JComboBox<String> textValue = new JComboBox<String>(vehicleTypes);
		panel.add(labelkey);
		panel.add(textValue);
		billGenerateUIComponent.put(getComponentName(string, "labelkey"), labelkey);
		billGenerateUIComponent.put(getComponentName(string, "textValue"), textValue);
		
		return panel;
	}
	public JPanel getLabelWithTimeSpinner(String key,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(key +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(Registry.FONT_COURRIER_BOLD_10);
		
		JSpinner spinner = new JSpinner();
		SpinnerDateModel spinnermodel = new SpinnerDateModel();
		spinnermodel.setCalendarField(Calendar.MINUTE);
		spinner .setModel(spinnermodel);
		spinner .setEditor(new JSpinner.DateEditor(spinner , "hh:mm:ss"));
	    panel.add(labelkey);
	    billGenerateUIComponent.put(getComponentName(key, "labelkey"), labelkey);
	    panel.add(spinner);
	    billGenerateUIComponent.put(getComponentName(key, "spinner"), spinner);
	    billGenerateUIComponent.put(getComponentName(key, "spinnermodel"), spinnermodel);
	    return panel;
	}
	public JPanel getLabelWithIntSpinner(String key,int value,int min,int max,int step,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelkey = new JLabel(key +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(Registry.FONT_COURRIER_BOLD_10);
		
		JSpinner spinner = new JSpinner();
		SpinnerModel spinnermodel = new SpinnerNumberModel(value,min,max,step);
		//spinnermodel.setCalendarField(Calendar.MINUTE);
		spinner .setModel(spinnermodel);
		spinner.addChangeListener(new SpinnerChangeListner());
		
	    panel.add(labelkey);
	    billGenerateUIComponent.put(getComponentName(key, "labelkey"), labelkey);
	    panel.add(spinner);
	    billGenerateUIComponent.put(getComponentName(key, "spinner"), spinner);
	    billGenerateUIComponent.put(getComponentName(key, "spinnermodel"), spinnermodel);
	    return panel;
	}
	private String getComponentName(String key, String value)
	{
		return key+"_"+value;
	}
}
