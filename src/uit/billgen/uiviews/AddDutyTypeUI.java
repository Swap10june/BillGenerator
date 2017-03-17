package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.handlers.DutyTypeButtonHandler;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class AddDutyTypeUI extends JDialog
{

	private static final long serialVersionUID = 1L;
	private String action;
	private UITemplates templates = new UITemplates();
	private static Map<String, Object> addDutyTypeUIComponent ;
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
		bodyLeftPanel.setBounds(10, 30, 300, 200);
		bodyLeftPanel.setLayout(new GridLayout(4, 1));
		//bodyLeftPanel.setBackground(Color.cyan);
		JPanel enterHours = templates.getLabelWithTextField("enterHours", "Enter Hrs.", "Enter Hours Here", 10,true, addDutyTypeUIComponent);
		bodyLeftPanel.add(enterHours);
		
		JPanel enterKmValue = templates.getLabelWithTextField("enterKmValue", "Enter Km.", "Enter Km Here",10,true, addDutyTypeUIComponent);
		bodyLeftPanel.add(enterKmValue);
		
		JPanel enterPkgRate = templates.getLabelWithTextField("enterPkgRate", "Enter Rate", "Enter Rate Here", 10, true,addDutyTypeUIComponent);
		bodyLeftPanel.add(enterPkgRate);
		
		JPanel enterExtraKmRate = templates.getLabelWithTextField("enterExtraKmRate", "Enter Ex.Km Rate", "Enter Ex Rate Here", 10, true,addDutyTypeUIComponent);
		bodyLeftPanel.add(enterExtraKmRate);
		
		JPanel bodyRightPanel = new JPanel();
		bodyRightPanel.setBounds(320, 30, 300, 200);
		bodyRightPanel.setLayout(new GridLayout(2, 1));
		//bodyRightPanel.setBackground(Color.MAGENTA);
		
		
		String [] customerList = new CustomerDataModel().getAllCustomerNames();
		JPanel panelCustomer = templates.getLabelWithCombo("panelCustomer", "Select Customer", SConstants.ID_CUSTOMER_SELECT_DUTY_TYPE_COMBO,customerList, addDutyTypeUIComponent);
		bodyRightPanel.add(panelCustomer);
		
		
		String [] vehicleList = new VehicleDataModel().getAllVehicleNames();
		JPanel panelVehicle = templates.getLabelWithCombo("panelVehicle", "Select Vehicle	",SConstants.ID_VEHICLE_SELECT_DUTY_TYPE_COMBO, vehicleList, addDutyTypeUIComponent);
		bodyRightPanel.add(panelVehicle);
		
		JButton btnAddDutyType = new JButton(SConstants.ADD_BTN_STRING);
		btnAddDutyType.setBounds(150, 250, 150, 30);
		btnAddDutyType.addActionListener(new DutyTypeButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
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
