package ui;

import handlers.DutyTypeButtonHandler;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import ModelXml.DutyTypeDataModel;

import util.Registry;
import util.SConstants;
import util.Utils;

public class EditDutyTypeUI extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final String UI_ID = "AddDutyTypeUI"; 
	private static Map<String, Object> editDutyTypeUIComponent = new HashMap<String, Object>();
	private UITemplates templates = new UITemplates();
	private Registry reg = SConstants.reg;
	private static List<JPanel> list = new ArrayList<JPanel>();
	
	


	public EditDutyTypeUI(JDialog owner) 
	{
		super(owner);
		Utils.applyBasicSettingsOnWindow_Small(owner,"Edit Duty Type");
		initUI(owner);
		owner.setVisible(true);
	}
		
	
	
	private void initUI(JDialog owner) 
	{
		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(10, 30, 500, 150);
		bodyLeftPanel.setLayout(new GridLayout(2, 2));
		
		String [] dutyTypes = new DutyTypeDataModel().getAllDutyTypes();
		
		JPanel panelDutyType = templates.getLabelWithCombo("panelDutyType", "Select Duty Type", UI_ID+reg .getValueFor("ID_SELECT_DUTY_TYPE_COMBO"), dutyTypes, editDutyTypeUIComponent);
		bodyLeftPanel.add(panelDutyType);
		
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
		
		JPanel customer = templates.getLabelWithTextField("customer", "Select Customer", "Edit Customer",10,false, editDutyTypeUIComponent);
		bodyLeftPanel.add(customer);
		customer.setVisible(false);
		list.add(customer);
		
		JPanel vehicle = templates.getLabelWithTextField("vehicle", "Select Vehicle", "Edit type", 10, false,editDutyTypeUIComponent);
		bodyLeftPanel.add(vehicle);
		vehicle.setVisible(false);
		list.add(vehicle);
		
		JButton btnAddDutyType = new JButton(reg.getValueFor("BTN_STRING_EDIT_DUTY_TYPE"));
		btnAddDutyType.setBounds(150, 200, 150, 30);
		btnAddDutyType.addActionListener(new DutyTypeButtonHandler(owner));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(350, 200, 150, 30);
		btnCancel.addActionListener(new DutyTypeButtonHandler(owner));
		
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

	
	public static List<JPanel> getList() {
		return list;
	}



	

}
