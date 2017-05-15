package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.constants.SConstants;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.handlers.DutyTypeButtonHandler;
import uit.billgen.util.Utils;

public class AddDutyTypeUI extends JDialog
{

	private static final long serialVersionUID = 1L;
	private String action;
	private UITemplates templates = new UITemplates();
	private CustomerDataModel m_customerModel;
	private VehicleDataModel m_vehicleModel;
	private String m_extraKmRate;
	private String m_selectedCustomerName;
	private String m_selectedVehicleName;
	private static Map<String, Object> addDutyTypeUIComponent ;
	public static Map<String, Object> getDutyTypeUIComponent() {
		return addDutyTypeUIComponent;
	}

	public void setDutyTypeUIComponent(Map<String, Object> dutyTypeUIComponent)
	{
		AddDutyTypeUI.addDutyTypeUIComponent = dutyTypeUIComponent;
	}

	public AddDutyTypeUI(JDialog owner, String action) 
	{
		super(owner);
		this.setAction(action);
		addDutyTypeUIComponent = new HashMap<String, Object>();
		this.m_customerModel = new CustomerDataModel();
		this.m_vehicleModel = new VehicleDataModel();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_500X400(owner,action);
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(10, 50, 400, 250);
		bodyLeftPanel.setLayout(new GridLayout(7, 1));
		JPanel enterHours = templates.getLabelWithTextField("enterHours", "Enter Hrs.", "Enter Hours Here", SConstants.ADD_DUTY_TYPE_TEXTFILED_COL_SIZE,true, addDutyTypeUIComponent,SConstants.ADD_DUTY_TYPE_LABEL_NAME_SIZE);
		bodyLeftPanel.add(enterHours);
		
		JPanel enterKmValue = templates.getLabelWithTextField("enterKmValue", "Enter Km.", "Enter Km Here",SConstants.ADD_DUTY_TYPE_TEXTFILED_COL_SIZE,true, addDutyTypeUIComponent,SConstants.ADD_DUTY_TYPE_LABEL_NAME_SIZE);
		bodyLeftPanel.add(enterKmValue);
		
		JPanel enterPkgRate = templates.getLabelWithTextField("enterPkgRate", "Enter Rate", "Enter Rate Here", SConstants.ADD_DUTY_TYPE_TEXTFILED_COL_SIZE, true,addDutyTypeUIComponent,SConstants.ADD_DUTY_TYPE_LABEL_NAME_SIZE);
		bodyLeftPanel.add(enterPkgRate);
		
		String [] customerList = Utils.getUtilityInstance().removeDuplicate(m_customerModel.getAllCustomerNames());
		JPanel panelCustomer = templates.getLabelWithCombo("panelCustomer", "Select Customer", SConstants.ID_CUSTOMER_SELECT_DUTY_TYPE_COMBO,customerList, addDutyTypeUIComponent,SConstants.ADD_DUTY_TYPE_LABEL_NAME_SIZE);
		bodyLeftPanel.add(panelCustomer);
		@SuppressWarnings("unchecked")
		JComboBox<String> selectedCustomerCombo = (JComboBox<String>) panelCustomer.getComponent(2);
		m_selectedCustomerName = selectedCustomerCombo.getSelectedItem().toString().trim();
		selectedCustomerCombo.addItemListener(new ItemListener() {
			
		

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				m_selectedCustomerName = e.getItem().toString();
				m_extraKmRate = m_vehicleModel.getAttributeExtraKmRate(m_selectedCustomerName,m_selectedVehicleName);
				JPanel enterExtraKmRate1 = (JPanel) addDutyTypeUIComponent.get("enterExtraKmRate");
				JTextField extraKRate = (JTextField) enterExtraKmRate1.getComponent(2);
				extraKRate.setText(m_extraKmRate);
				
			}
		});
		
		String [] vehicleList = Utils.getUtilityInstance().removeDuplicate(m_vehicleModel.getAllVehicleNames());
		JPanel panelVehicle = templates.getLabelWithCombo("panelVehicle", "Select Vehicle",SConstants.ID_VEHICLE_SELECT_DUTY_TYPE_COMBO, vehicleList, addDutyTypeUIComponent,SConstants.ADD_DUTY_TYPE_LABEL_NAME_SIZE);
		bodyLeftPanel.add(panelVehicle);
		@SuppressWarnings("unchecked")
		JComboBox<String> selectedVehicleCombo = (JComboBox<String>) panelVehicle.getComponent(2);
		m_selectedVehicleName = selectedVehicleCombo.getSelectedItem().toString().trim();
		selectedVehicleCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				m_selectedVehicleName = e.getItem().toString();
				m_extraKmRate = m_vehicleModel.getAttributeExtraKmRate(m_selectedCustomerName,m_selectedVehicleName);
				JPanel enterExtraKmRate1 = (JPanel) addDutyTypeUIComponent.get("enterExtraKmRate");
				JTextField extraKRate = (JTextField) enterExtraKmRate1.getComponent(2);
				extraKRate.setText(m_extraKmRate);
				
			}
		});
		
		JPanel enterExtraKmRate = templates.getLabelWithTextField("enterExtraKmRate", "Enter Ex.Km Rate", m_extraKmRate==null?"Enter Ex. Km Rate":m_extraKmRate, SConstants.ADD_DUTY_TYPE_TEXTFILED_COL_SIZE, true,addDutyTypeUIComponent,SConstants.ADD_DUTY_TYPE_LABEL_NAME_SIZE);
		bodyLeftPanel.add(enterExtraKmRate);
		
		
		
		String [] type = {"AC","Non-AC"};
		JPanel panelTypeACNonAC = templates.getLabelWithCombo("panelTypeACNonAC", "Type",SConstants.ID_TYPE_SELECT_DUTY_TYPE_COMBO,type, addDutyTypeUIComponent,SConstants.ADD_DUTY_TYPE_LABEL_NAME_SIZE);
		bodyLeftPanel.add(panelTypeACNonAC);
		
		JButton btnAddDutyType = new JButton(SConstants.ADD_BTN_STRING);
		btnAddDutyType.setBounds(100, 330, 150, 30);
		btnAddDutyType.addActionListener(new DutyTypeButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(300, 330, 150, 30);
		btnCancel.addActionListener(new DutyTypeButtonHandler(owner));
		
		owner.add(bodyLeftPanel);
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
