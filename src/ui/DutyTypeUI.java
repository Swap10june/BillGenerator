package ui;

import handlers.ButtonHandler;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import util.Registry;
import util.SConstants;
import util.Utils;

public class DutyTypeUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String action;
	private UITemplates templates = new UITemplates();
	private static Map<String, Object> dutyTypeUIComponent = new HashMap<String, Object>();
	private Registry reg = SConstants.reg;
	
	
	public static Map<String, Object> getDutyTypeUIComponent() {
		return dutyTypeUIComponent;
	}

	public void setDutyTypeUIComponent(Map<String, Object> dutyTypeUIComponent) {
		DutyTypeUI.dutyTypeUIComponent = dutyTypeUIComponent;
	}

	public DutyTypeUI(JDialog owner, String action) 
	{
		super(owner);
		this.setAction(action);
		Utils.getUtilityInstance();
		Utils.applyBasicSettingsOnWindow_Small(owner,action);
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBounds(10, 30, 400, 150);
		bodyPanel.setLayout(new GridLayout(3, 1));
		JPanel enterHours = templates.getLabelWithTextField("enterHours", "Enter Hrs.", "Enter Hours Here", 10, dutyTypeUIComponent);
		bodyPanel.add(enterHours);
		
		JPanel enterKmValue = templates.getLabelWithTextField("enterKmValue", "Enter Km.", "Enter Km Here",10, dutyTypeUIComponent);
		bodyPanel.add(enterKmValue);
		
		JPanel enterPkgRate = templates.getLabelWithTextField("enterPkgRate", "Enter Rate", "Enter Rate Here", 10, dutyTypeUIComponent);
		bodyPanel.add(enterPkgRate);
		
		JButton btnAddDutyType = new JButton(reg.getValueFor("ID_BTN_ADD_DUTY_TYPE"));
		btnAddDutyType.setBounds(100, 350, 200, 30);
		btnAddDutyType.addActionListener(new ButtonHandler(reg.getValueFor("ID_BTN_ADD_DUTY_TYPE"),owner));
		owner.add(btnAddDutyType);
		owner.add(bodyPanel);
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
