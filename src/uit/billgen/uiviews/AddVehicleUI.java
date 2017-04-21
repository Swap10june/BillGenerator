package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.handlers.VehicleButtonHandler;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class AddVehicleUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UITemplates templates = new UITemplates();
	private static Map<String, Object> addVehicleUIComponentMap;
	public AddVehicleUI(JDialog owner, String windowName)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_550X300(owner,windowName);
		addVehicleUIComponentMap = new HashMap<String, Object>();
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(final JDialog owner)
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new GridLayout(5, 1));
		bodyPanel.setBounds(10, 50, 400, 150);
		
		JPanel enterVehicleName = templates.getLabelWithTextField("enterVehicleName", " Enter Vehicle Name", "Enter Veh. Name here",SConstants.TEXT_COL_SIZE_15 , false, addVehicleUIComponentMap,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyPanel.add(enterVehicleName);
		
		
		JPanel enterVNumber = templates.getLabelWithTextField("enterVNumber", " Enter Vehicle No", "Enter Veh. No here", SConstants.TEXT_COL_SIZE_15, false, addVehicleUIComponentMap,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyPanel.add(enterVNumber);
		
		
		JPanel enterCustomerName = templates.getLabelWithComboWOListner("enterCustomerName", "Enter Customer Name", new CustomerDataModel().getAllCustomerNames(), addVehicleUIComponentMap,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyPanel.add(enterCustomerName);
		
		
		JPanel enterMonthlyRate = templates.getLabelWithTextField("enterMonthlyRate", "Enter Monthly Rate", "Enter Monthly Rate", SConstants.TEXT_COL_SIZE_15, true, addVehicleUIComponentMap,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyPanel.add(enterMonthlyRate);
		
		JPanel enterExKmRate = templates.getLabelWithTextField("enterExKmRate", "Enter Extra Km Rate", "Enter Extra Km Rate", SConstants.TEXT_COL_SIZE_15, true, addVehicleUIComponentMap,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyPanel.add(enterExKmRate);
		
		
		JButton btnLogin = new JButton(SConstants.ADD_BTN_STRING);
		btnLogin.setBounds(150, 220, 100, 30);
		btnLogin.addActionListener(new VehicleButtonHandler(owner));
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(350, 220, 100, 30);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				owner.dispose();
			}
		});
		owner.add(bodyPanel);
		owner.add(btnLogin);
		owner.add(btnCancel);
		
	}
	/**
	 * @return the addVehicleUIComponent
	 */
	public static Map<String, Object> getAddVehicleUIComponent() {
		return addVehicleUIComponentMap;
	}
	/**
	 * @param addVehicleUIComponent the addVehicleUIComponent to set
	 */
	public static void setAddVehicleUIComponent(Map<String, Object> addVehicleUIComponent) {
		AddVehicleUI.addVehicleUIComponentMap = addVehicleUIComponent;
	}

}
