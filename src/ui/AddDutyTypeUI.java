package ui;

import handlers.DutyTypeButtonHandler;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import util.Registry;
import util.SConstants;
import util.Utils;

public class AddDutyTypeUI extends JDialog
{

	/**
	 * 
	 */
	public static final String UI_ID = "AddDutyTypeUI"; 
	private static final long serialVersionUID = 1L;
	private String action;
	private UITemplates templates = new UITemplates();
	private static Map<String, Object> addDutyTypeUIComponent ;
	private Registry reg = SConstants.reg;
	
	
	public static Map<String, Object> getDutyTypeUIComponent() {
		return addDutyTypeUIComponent;
	}

	public void setDutyTypeUIComponent(Map<String, Object> dutyTypeUIComponent) {
		AddDutyTypeUI.addDutyTypeUIComponent = dutyTypeUIComponent;
	}

	public AddDutyTypeUI(JDialog owner, String action) 
	{
		super(owner);
		this.setAction(action);
		addDutyTypeUIComponent = new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,action);
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(10, 30, 280, 200);
		bodyLeftPanel.setLayout(new GridLayout(3, 1));
		//bodyLeftPanel.setBackground(Color.cyan);
		JPanel enterHours = templates.getLabelWithTextField("enterHours", "Enter Hrs.", "Enter Hours Here", 10,true, addDutyTypeUIComponent);
		bodyLeftPanel.add(enterHours);
		
		JPanel enterKmValue = templates.getLabelWithTextField("enterKmValue", "Enter Km.", "Enter Km Here",10,true, addDutyTypeUIComponent);
		bodyLeftPanel.add(enterKmValue);
		
		JPanel enterPkgRate = templates.getLabelWithTextField("enterPkgRate", "Enter Rate", "Enter Rate Here", 10, true,addDutyTypeUIComponent);
		bodyLeftPanel.add(enterPkgRate);
		
		JPanel bodyRightPanel = new JPanel();
		bodyRightPanel.setBounds(300, 30, 350, 200);
		bodyRightPanel.setLayout(new GridLayout(3, 1));
		

		JPanel enterExtraKmRate = templates.getLabelWithTextField("enterExtraKmRate", "Enter Ex.Km Rate", "Enter Ex Rate Here", 10, true,addDutyTypeUIComponent);
		bodyRightPanel.add(enterExtraKmRate);
		
		JPanel panelCustomer = templates.getLabelWithCombo("panelCustomer", "Select Customer", UI_ID+reg.getValueFor("ID_CUSTOMER_SELECT_DUTY_TYPE_COMBO"), SConstants.CUSSTOMER_LIST, addDutyTypeUIComponent);
		bodyRightPanel.add(panelCustomer);
		
		JPanel panelVehicle = templates.getLabelWithCombo("panelVehicle", "Select Vehicle	", UI_ID+reg.getValueFor("ID_VEHICLE_SELECT_DUTY_TYPE_COMBO"), SConstants.VEHICLE_TYPES, addDutyTypeUIComponent);
		bodyRightPanel.add(panelVehicle);
		
		JButton btnAddDutyType = new JButton(reg.getValueFor("ID_BTN_ADD_DUTY_TYPE"));
		btnAddDutyType.setBounds(150, 250, 150, 30);
		btnAddDutyType.addActionListener(new DutyTypeButtonHandler(owner));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(350, 250, 150, 30);
		btnCancel.addActionListener(new DutyTypeButtonHandler(owner));
		
		owner.add(bodyLeftPanel);
		owner.add(bodyRightPanel);
		
		owner.add(btnAddDutyType);
		owner.add(btnCancel);
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the templates
	 */
	public UITemplates getTemplates() {
		return templates;
	}

	/**
	 * @param templates the templates to set
	 */
	public void setTemplates(UITemplates templates) {
		this.templates = templates;
	}

}
