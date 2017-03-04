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

import model.DutyTypeDataModel;
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
	private static Map<String, Object> editDutyTypeUIComponent ;
	private UITemplates templates = new UITemplates();
	private Registry reg = SConstants.reg;
	private static List<Object> list = new ArrayList<Object>();
	
	


	public EditDutyTypeUI(JDialog owner) 
	{
		super(owner);
		editDutyTypeUIComponent = new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,"Edit Duty Type");
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(JDialog owner) 
	{
		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(10, 30, 500, 200);
		bodyLeftPanel.setLayout(new GridLayout(3, 2));
		
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
		
		JPanel enterExtraKmRate = templates.getLabelWithTextField("enterExtraKmRate", "Enter Extra Km Rate", "Enter Ex Rate Here", 10, true,editDutyTypeUIComponent);
		bodyLeftPanel.add(enterExtraKmRate);
		enterExtraKmRate.setVisible(false);
		list.add(enterExtraKmRate);
		
		JPanel customer = templates.getLabelWithTextField("customer", "Select Customer", "Edit Customer",10,false, editDutyTypeUIComponent);
		bodyLeftPanel.add(customer);
		customer.setVisible(false);
		list.add(customer);
		
		JPanel vehicle = templates.getLabelWithTextField("vehicle", "Select Vehicle", "Edit type", 10, false,editDutyTypeUIComponent);
		bodyLeftPanel.add(vehicle);
		vehicle.setVisible(false);
		list.add(vehicle);
		
		JButton btnAddDutyType = new JButton(reg.getValueFor("BTN_STRING_EDIT_DUTY_TYPE"));
		btnAddDutyType.setBounds(150, 300, 150, 30);
		btnAddDutyType.setEnabled(false);
		list.add(btnAddDutyType);
		btnAddDutyType.addActionListener(new DutyTypeButtonHandler(owner));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(350, 300, 150, 30);
		btnCancel.setEnabled(false);
		list.add(btnCancel);
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

	
	public static List<Object> getList() {
		return list;
	}



	

}
