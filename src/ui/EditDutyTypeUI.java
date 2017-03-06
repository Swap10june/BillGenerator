package ui;

import handlers.DutyTypeButtonHandler;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listners.DutyTypeComboListner;
import model.CustomerDataModel;
import model.DutyTypeDataModel;
import util.SConstants;
import util.Utils;

public class EditDutyTypeUI extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Object> editDutyTypeUIComponent ;
	private UITemplates templates = new UITemplates();
	private static List<Object> list = new ArrayList<Object>();
	
	


	public EditDutyTypeUI(JDialog owner, String winName) 
	{
		super(owner);
		editDutyTypeUIComponent = new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,winName);
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(JDialog owner) 
	{
		
		JPanel topPanel  = new JPanel();
		topPanel.setBounds(150, 30, 400, 100);
		topPanel.setLayout(new GridLayout(2,2));
		
		String [] dutyTypes = new DutyTypeDataModel().getAllDutyTypes();
		JPanel panelDutyType = templates.getLabelWithComboWOListner("panelDutyType", "Select Duty Type", dutyTypes, editDutyTypeUIComponent);
		topPanel.add(panelDutyType);
		@SuppressWarnings("unchecked")
		JComboBox<String> comboSelectDutyType = (JComboBox<String>) panelDutyType.getComponent(2);
		comboSelectDutyType.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
		comboSelectDutyType.addItemListener(new DutyTypeComboListner(SConstants.COMBO_EDIT_DUTY_TYPE_SELCT_DUTY_TYPE));
		
 		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(10, 120, 400, 150);
		bodyLeftPanel.setLayout(new GridLayout(2,4));
		
		
		JPanel enterHours = templates .getLabelWithTextField("enterHours", "Enter Hrs.", "Edit Hours Here", 10,true, editDutyTypeUIComponent);
		bodyLeftPanel.add(enterHours);
		enterHours.setVisible(false);
		list.add(enterHours);
		
		JPanel enterKmValue = templates.getLabelWithTextField("enterKmValue", "Enter Km.", "Edit Km Here",10,true, editDutyTypeUIComponent);
		bodyLeftPanel.add(enterKmValue);
		enterKmValue.setVisible(false);
		list.add(enterKmValue);
		
		JPanel enterPkgRate = templates.getLabelWithTextField("enterPkgRate", "Enter Rate", "Edit Rate Here", 10, true,editDutyTypeUIComponent);
		bodyLeftPanel.add(enterPkgRate);
		enterPkgRate.setVisible(false);
		list.add(enterPkgRate);
		
		JPanel enterExtraKmRate = templates.getLabelWithTextField("enterExtraKmRate", "Enter Extra Km Rate", "Enter Ex Rate Here", 10, true,editDutyTypeUIComponent);
		bodyLeftPanel.add(enterExtraKmRate);
		enterExtraKmRate.setVisible(false);
		list.add(enterExtraKmRate);
		
		JPanel customer = templates.getLabelWithTextField("customer", "Select Customer", "Edit Customer",10,false, editDutyTypeUIComponent);
		bodyLeftPanel.add(customer);
		customer.setVisible(false);
		list.add(customer);
		JTextField text = (JTextField) customer.getComponent(2);
		
		Utils.getUtilityInstance().applyIntelisense(text,owner,new CustomerDataModel().getAllCustomers());
		
		
		JPanel vehicle = templates.getLabelWithTextField("vehicle", "Select Vehicle", "Edit type", 10, false,editDutyTypeUIComponent);
		bodyLeftPanel.add(vehicle);
		vehicle.setVisible(false);
		list.add(vehicle);
		
		JButton btnAddDutyType = new JButton(SConstants.EDIT_BTN_STRING);
		btnAddDutyType.setBounds(150, 300, 150, 30);
		btnAddDutyType.setEnabled(false);
		list.add(btnAddDutyType);
		btnAddDutyType.addActionListener(new DutyTypeButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(350, 300, 150, 30);
		btnCancel.setEnabled(false);
		list.add(btnCancel);
		btnCancel.addActionListener(new DutyTypeButtonHandler(owner));
		
		owner.add(topPanel);
		owner.add(bodyLeftPanel);
		owner.add(btnAddDutyType);
		owner.add(btnCancel);
	}











	/**
	 * @return the editDutyTypeUIComponent
	 */
	public static Map<String, Object> getEditDutyTypeUIComponent() {
		return editDutyTypeUIComponent;
	}
	/**
	 * @param editDutyTypeUIComponent the editDutyTypeUIComponent to set
	 */
	public static void setEditDutyTypeUIComponent(
			Map<String, Object> editDutyTypeUIComponent) {
		EditDutyTypeUI.editDutyTypeUIComponent = editDutyTypeUIComponent;
	}

	
	public static List<Object> getList() {
		return list;
	}



	

}
