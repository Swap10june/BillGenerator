package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import uit.billgen.constants.SConstants;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.DutyTypeDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.handlers.DutyTypeButtonHandler;
import uit.billgen.listners.DutyTypeComboListner;
import uit.billgen.util.Utils;

public class EditDutyTypeUI extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Object> m_editDutyTypeUIComponent ;
	private UITemplates m_templates = null;
	private DutyTypeDataModel m_dutyTypeModel;
	private static List<Object> m_list = null;
	
	public EditDutyTypeUI(JDialog owner, String winName) 
	{
		super(owner);
		EditDutyTypeUI.m_editDutyTypeUIComponent = new HashMap<String, Object>();
		this.m_dutyTypeModel = new DutyTypeDataModel();
		this.m_templates = new UITemplates();
		EditDutyTypeUI.m_list =  new ArrayList<Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_500X400(owner,winName);
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(JDialog owner) 
	{
		
		JPanel topPanel  = new JPanel();
		topPanel.setBounds(50, 30, owner.getWidth(), 30);
		topPanel.setLayout(new GridLayout(1,2));
		
		String [] dutyTypes = m_dutyTypeModel.getAllDutyTypeStrings()==null?new String[]{""}:new DutyTypeDataModel().getAllDutyTypeStrings();
		JPanel panelDutyType = m_templates.getLabelWithComboWOListner("panelDutyType", "Select Duty Type", dutyTypes, m_editDutyTypeUIComponent);
		topPanel.add(panelDutyType);
		@SuppressWarnings("unchecked")
		JComboBox<String> comboSelectDutyType = (JComboBox<String>) panelDutyType.getComponent(2);
		comboSelectDutyType.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
		comboSelectDutyType.addItemListener(new DutyTypeComboListner(SConstants.COMBO_EDIT_DUTY_TYPE_SELCT_DUTY_TYPE));
		
 		JPanel bodyMiddlePanel = new JPanel();
		bodyMiddlePanel.setBounds(0, 60, owner.getWidth(), 200);
		bodyMiddlePanel.setLayout(new GridLayout(7,1));
		
		
		JPanel enterHours = m_templates .getLabelWithTextField("enterHours", "Enter Hrs.", "Edit Hours Here", SConstants.TEXT_COL_SIZE_15,true, m_editDutyTypeUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyMiddlePanel.add(enterHours);
		enterHours.setVisible(false);
		m_list.add(enterHours);
		
		JPanel enterKmValue = m_templates.getLabelWithTextField("enterKmValue", "Enter Km.", "Edit Km Here",SConstants.TEXT_COL_SIZE_15,true, m_editDutyTypeUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyMiddlePanel.add(enterKmValue);
		enterKmValue.setVisible(false);
		m_list.add(enterKmValue);
		
		JPanel enterPkgRate = m_templates.getLabelWithTextField("enterPkgRate", "Enter Rate", "Edit Rate Here", SConstants.TEXT_COL_SIZE_15, true,m_editDutyTypeUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyMiddlePanel.add(enterPkgRate);
		enterPkgRate.setVisible(false);
		m_list.add(enterPkgRate);
		
		JPanel enterExtraKmRate = m_templates.getLabelWithTextField("enterExtraKmRate", "Enter Extra Km Rate", "Enter Ex Rate Here", SConstants.TEXT_COL_SIZE_15, true,m_editDutyTypeUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyMiddlePanel.add(enterExtraKmRate);
		enterExtraKmRate.setVisible(false);
		m_list.add(enterExtraKmRate);
		
		JPanel customer = m_templates.getLabelWithComboWOListner("customer", "Select Customer", new CustomerDataModel().getAllCustomerNames(), m_editDutyTypeUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyMiddlePanel.add(customer);
		customer.setVisible(false);
		m_list.add(customer);
		
		JPanel vehicle = m_templates.getLabelWithComboWOListner("vehicle", "Select Vehicle", new VehicleDataModel().getAllVehicleNames(),m_editDutyTypeUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyMiddlePanel.add(vehicle);
		vehicle.setVisible(false);
		m_list.add(vehicle);
		
		String [] type = {"AC","Non-AC"};
		JPanel panelTypeACNonAC = m_templates.getLabelWithComboWOListner("panelTypeACNonAC", "Select Type", type,m_editDutyTypeUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyMiddlePanel.add(panelTypeACNonAC);
		panelTypeACNonAC.setVisible(false);
		m_list.add(panelTypeACNonAC);
		
		JButton btnAddDutyType = new JButton(SConstants.EDIT_BTN_STRING);
		btnAddDutyType.setBounds(100, 300, 150, 30);
		btnAddDutyType.setEnabled(false);
		m_list.add(btnAddDutyType);
		btnAddDutyType.addActionListener(new DutyTypeButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(300, 300, 150, 30);
		btnCancel.setEnabled(false);
		m_list.add(btnCancel);
		btnCancel.addActionListener(new DutyTypeButtonHandler(owner));
		
		owner.add(topPanel);
		owner.add(bodyMiddlePanel);
		owner.add(btnAddDutyType);
		owner.add(btnCancel);
	}

	/**
	 * @return the editDutyTypeUIComponent
	 */
	public static Map<String, Object> getEditDutyTypeUIComponent() {
		return m_editDutyTypeUIComponent;
	}
	/**
	 * @param editDutyTypeUIComponent the editDutyTypeUIComponent to set
	 */
	public static void setEditDutyTypeUIComponent(
			Map<String, Object> editDutyTypeUIComponent) {
		EditDutyTypeUI.m_editDutyTypeUIComponent = editDutyTypeUIComponent;
	}

	
	public static List<Object> getList() {
		return m_list;
	}



	

}
