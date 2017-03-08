package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import listners.ComboItemListner;
import listners.SpinnerChangeListner;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.prompt.PromptSupport;

import util.SConstants;
import util.Utils;

public class UITemplates 
{	
	@SuppressWarnings("deprecation")
	public JPanel getLabelWithValueLabel(String mapKey, String labelKey,Object value,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
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
			str =(String) value;
		}
		JLabel labelValue = new JLabel(str);
		/*JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

        panel.add(picker);
        panel.add(picker);*/
		panel.add(labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(labelValue);
		
		billGenerateUIComponent.put(mapKey, panel);
		//billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_value")), labelValue);
		
		return panel;
		
	}
	public JPanel getLabelWithEmptyLabel(String mapKey, String labelKey,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		JLabel labelValue = new JLabel();
		panel.add(labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(labelValue);
		
		billGenerateUIComponent.put(mapKey, panel);
		
		return panel;
		
	}
	
	public JPanel getLabelWithTextFieldDatePicker(String mapKey, String labelKey,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		
		JXDatePicker picker = new JXDatePicker();
		picker.setBackground(Color.magenta);
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd-MM-yyyy"));

        
		panel.add(labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(picker);
		billGenerateUIComponent.put(mapKey, panel);
		//billGenerateUIComponent.put(Utils.getComponentName(labelText,reg.getValueFor("L_Picker")), picker);
		return panel;
	}
	public JPanel getLabelWithTextField(String mapKey,String labelKey,String TextValue,int TextColumnSize,boolean IntegerStatus , Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		@SuppressWarnings("unused")
		String str = "";
		if(TextValue instanceof String)
		{
			str = (String) TextValue;
		}
		JTextField textValue = new JTextField(TextColumnSize);
		textValue.setHorizontalAlignment(JTextField.RIGHT);
		//textValue.setToolTipText(TextValue);
		//textValue.setText(labelKey);
		PromptSupport.setPrompt(TextValue, textValue);
		panel.add(labelkey);
		//billGenerateUIComponent.put(Utils.getComponentName(labelText,reg.getValueFor("L_key")), labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(textValue);
		//System.out.println(labelText);
		//System.out.println(Utils.getComponentName(labelText,reg.getValueFor("L_value")));
		//billGenerateUIComponent.put(Utils.getComponentName(labelText,reg.getValueFor("L_value")), textValue);
		billGenerateUIComponent.put(mapKey, panel);
		if(IntegerStatus)
		{
			textValue.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (! ((c >= '0') && (c <= '9') || (c == '.')||  (c == KeyEvent.VK_BACK_SPACE) ||  (c == KeyEvent.VK_DELETE))) 
		      {
		          //getToolkit().beep();
		    	  e.consume();
		      }
		    }
		  });
		}
		
		return panel;
	}
	public JPanel getLabelWithCombo(String mapKey,String labelKey,String comboID, String[] vehicleTypes,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		JComboBox<String> textValue = new JComboBox<String>(vehicleTypes);
		textValue.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXX");
		textValue.addItemListener(new ComboItemListner(comboID));
		panel.add(labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(textValue);
		//billGenerateUIComponent.put(Utils.getComponentName(string, reg.getValueFor("L_key")), labelkey);
		//billGenerateUIComponent.put(Utils.getComponentName(string, reg.getValueFor("L_value")), textValue);
		billGenerateUIComponent.put(mapKey, panel);
		return panel;
	}
	public JPanel getLabelWithTimeSpinner(String mapKey,String labelKey,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		
	     //formatting time to have AM/PM text using 'a' format
	     //String strDateFormat = "HH:mm:ss a";
	     String strDateFormat = "hh:mm a";
		JSpinner spinner = new JSpinner();
		SpinnerDateModel spinnermodel = new SpinnerDateModel();
		spinnermodel.setCalendarField(Calendar.AM_PM);
		spinner .setModel(spinnermodel);
		spinner .setEditor(new JSpinner.DateEditor(spinner , strDateFormat));
	    panel.add(labelkey);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_key")), labelkey);
	    panel.add(Box.createHorizontalStrut(10));
	    panel.add(spinner);
	    //panel.add(Box.createHorizontalStrut(70));
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_spinner")), spinner);
	    //billGenerateUIComponent.put(Utils.getComponentName(key, reg.getValueFor("L_spinnermodel")), spinnermodel);
	    billGenerateUIComponent.put(mapKey,panel );
	    return panel;
	}
	public JPanel getLabelWithIntSpinner(String mapKey,String labelKey,double value,double min,double max,double step,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		
		JSpinner spinner = new JSpinner();
		SpinnerModel spinnermodel = new SpinnerNumberModel(value,min,max,step);
		//spinnermodel.setCalendarField(Calendar.MINUTE);
		spinner .setModel(spinnermodel);
		if(!mapKey.equalsIgnoreCase("panelStartKM"))
			spinner.addChangeListener(new SpinnerChangeListner());
		
	    panel.add(labelkey);
	    panel.add(Box.createHorizontalStrut(10));
	    panel.add(spinner);
	    billGenerateUIComponent.put(mapKey,panel );
	    return panel;
	}
	public JPanel getLabelWithCheckBox(String mapKey, String valueFor,
			Map<String, Object> billGenerateUIComponentsMap)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		valueFor = Utils.getUtilityInstance().getStringOfCharacters(valueFor,13);
		JLabel labelkey = new JLabel(valueFor +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		JCheckBox textValue = new JCheckBox();
		panel.add(labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(textValue);
		billGenerateUIComponentsMap.put(mapKey, panel);
		return panel;
	}
	public JPanel getLabelWithComboWOListner(String mapKey, String labelKey, String[] dutyTypeArray,Map<String, Object> billGenerateUIComponentsMap)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		JComboBox<String> textValue = new JComboBox<String>(dutyTypeArray);
		textValue.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXX");
		//textValue.addItemListener(new TelcoBillComboItemListner(comboID,true));
		panel.add(labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(textValue);
		billGenerateUIComponentsMap.put(mapKey, panel);
		return panel;
	}
	public JPanel getLabelWithTextArea(String mapKey,String labelKey,String TextValue,boolean IntegerStatus , Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		@SuppressWarnings("unused")
		String str = "";
		if(TextValue instanceof String)
		{
			str = (String) TextValue;
		}
		JTextArea textValue = new JTextArea();
		PromptSupport.setPrompt(TextValue, textValue);
		panel.add(labelkey);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(textValue);
		billGenerateUIComponent.put(mapKey, panel);
		if(IntegerStatus)
		{
			textValue.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		       // getToolkit().beep();
		        e.consume();
		      }
		    }
		  });
		}
		
		return panel;
	}
	public JPanel getLabelWithIntSpinnerWOListner(String mapKey,String labelKey,double value,double min,double max,double step,Map<String,Object> billGenerateUIComponent)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelKey = Utils.getUtilityInstance().getStringOfCharacters(labelKey,13);
		JLabel labelkey = new JLabel(labelKey +":");
		//labelkey.setBorder(Registry.BORDER_BLUE_1);
		labelkey.setFont(SConstants.FONT_COURRIER_BOLD_13);
		
		JSpinner spinner = new JSpinner();
		SpinnerModel spinnermodel = new SpinnerNumberModel(value,min,max,step);
		//spinnermodel.setCalendarField(Calendar.MINUTE);
		spinner .setModel(spinnermodel);
		/*if(!mapKey.equalsIgnoreCase("panelStartKM"))
			spinner.addChangeListener(new SpinnerChangeListner());*/
		
	    panel.add(labelkey);
	    panel.add(Box.createHorizontalStrut(10));
	    panel.add(spinner);
	    billGenerateUIComponent.put(mapKey,panel );
	    return panel;
	}
	public Component getLabelWithValueLabelWOMap(String key,String value) 
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel(key));
		panel.add(new JLabel(value));
		return panel;
	}
	
}
