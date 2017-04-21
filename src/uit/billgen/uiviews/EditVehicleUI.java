package uit.billgen.uiviews;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uit.billgen.beans.Vehicle;
import uit.billgen.datamodel.CustomerDataModel;
import uit.billgen.datamodel.VehicleDataModel;
import uit.billgen.handlers.VehicleButtonHandler;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class EditVehicleUI extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Object> editVehicleUIComponent ;
	private UITemplates templates = new UITemplates();
	private static VehicleDataModel model = null;
	private static List<Object> list = new ArrayList<Object>();
	
	public EditVehicleUI(JDialog owner, String windowName) 
	{
		super(owner);
		editVehicleUIComponent = new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_500X400(owner,windowName);
		initUI(owner);
		owner.setVisible(true);
	}
		
	
	
	private void initUI(final JDialog owner) 
	{
		setModel(new VehicleDataModel());
		JPanel topPanel  = new JPanel();
		topPanel.setBounds(50, 30, owner.getWidth(), 30);
		topPanel.setLayout(new GridLayout(1,2));
		
		String [] vehicleList = getModel().getAllVehicleNames();
		JPanel panelSelectVehicle = templates.getLabelWithComboWOListner("panelSelectVehicle", "Select Vehicle", vehicleList, editVehicleUIComponent);
		topPanel.add(panelSelectVehicle);
		owner.add(topPanel);
		@SuppressWarnings("unchecked")
		final JComboBox<String> comboEditVehicle= (JComboBox<String>) panelSelectVehicle.getComponent(2);
		comboEditVehicle.addItemListener(new ItemListener()
		{
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				
				JPanel panelCombo = (JPanel) editVehicleUIComponent.get("panelSelectVehicle");
				@SuppressWarnings("rawtypes")
				JComboBox comboSelectVehicle = (JComboBox) panelCombo.getComponent(2);
				Vehicle vehicle = getModel().getVehicle(comboSelectVehicle.getSelectedItem().toString());
				for (int i = 0; i < list.size(); i++)
				{
					if(list.get(i) instanceof JPanel)
						((JPanel) list.get(i)).setVisible(true);
					if(list.get(i) instanceof JButton)
						((JButton) list.get(i)).setEnabled(true);
				}
				JPanel enterVehicleName = (JPanel) editVehicleUIComponent.get("enterVehicleName");
				JTextField vName = (JTextField) enterVehicleName.getComponent(2);
				vName.setText(vehicle.getVehicleName());
				
				JPanel enterVNumber = (JPanel) editVehicleUIComponent.get("enterVNumber");
				JTextField vNo = (JTextField) enterVNumber.getComponent(2);
				vNo.setText(vehicle.getVehicleNumber());
				
				JPanel enterCustomerName = (JPanel) editVehicleUIComponent.get("enterCustomerName");
				@SuppressWarnings("unchecked")
				JComboBox<String> cname = (JComboBox<String>) enterCustomerName.getComponent(2);
				cname.setSelectedItem(vehicle.getCustomerName().toString());
				
				JPanel enterMonthlyRate = (JPanel) editVehicleUIComponent.get("enterMonthlyRate");
				JTextField vMRate = (JTextField) enterMonthlyRate.getComponent(2);
				vMRate.setText(vehicle.getMonthlyRate());
				
				JPanel enterExKmRate = (JPanel) editVehicleUIComponent.get("enterExKmRate");
				JTextField vExKmRate = (JTextField) enterExKmRate.getComponent(2);
				vExKmRate.setText(vehicle.getExtraKmRate());
				
				
			}
		});
		
		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(0, 60, owner.getWidth(), 200);
		bodyLeftPanel.setLayout(new GridLayout(5, 1));
		
		
		JPanel enterVehicleName = templates .getLabelWithTextField("enterVehicleName", "Enter Vehicle Name", "Edit Name Here", SConstants.TEXT_COL_SIZE_15,false, editVehicleUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyLeftPanel.add(enterVehicleName);
		enterVehicleName.setVisible(false);
		list.add(enterVehicleName);
		
		JPanel enterVNumber = templates .getLabelWithTextField("enterVNumber", "Enter Vehicle No", "Edit No Here", SConstants.TEXT_COL_SIZE_15,false, editVehicleUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyLeftPanel.add(enterVNumber);
		enterVNumber.setVisible(false);
		list.add(enterVNumber);
		
		String [] customers = new CustomerDataModel().getAllCustomerNames();
		JPanel enterCustomerName  = templates.getLabelWithComboWOListner("enterCustomerName", "Select Customer", customers, editVehicleUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyLeftPanel.add(enterCustomerName);
		enterCustomerName.setVisible(false);
		list.add(enterCustomerName);
		
		JPanel enterMonthlyRate = templates.getLabelWithTextField("enterMonthlyRate", "Enter Monthly Rate", "Enter Monthly Rate",  SConstants.TEXT_COL_SIZE_15, true, editVehicleUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyLeftPanel.add(enterMonthlyRate);
		enterMonthlyRate.setVisible(false);
		list.add(enterMonthlyRate);
		
		JPanel enterExKmRate = templates.getLabelWithTextField("enterExKmRate", "Enter Ex Km Rate", "Enter Ex Km Rate",  SConstants.TEXT_COL_SIZE_15, true, editVehicleUIComponent,SConstants.UI_LABEL_NAME_SIZE_20);
		bodyLeftPanel.add(enterExKmRate);
		enterExKmRate.setVisible(false);
		list.add(enterExKmRate);
		
		
		
		JButton btnEdit = new JButton(SConstants.EDIT_BTN_STRING);
		btnEdit.setBounds(100, 300, 150, 30);
		btnEdit.setEnabled(false);
		list.add(btnEdit);
		btnEdit.addActionListener(new VehicleButtonHandler(owner));
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(300, 300, 150, 30);
		btnCancel.setEnabled(false);
		list.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				owner.dispose();
			}
		});
		
		owner.add(bodyLeftPanel);
		owner.add(btnEdit);
		owner.add(btnCancel);
	}

	/**
	 * @return the editDutyTypeUIComponent
	 */
	public static Map<String, Object> getEditDutyTypeUIComponent() {
		return editVehicleUIComponent;
	}
	/**
	 * @param editDutyTypeUIComponent the editDutyTypeUIComponent to set
	 */
	public static void setEditDutyTypeUIComponent(
			Map<String, Object> editDutyTypeUIComponent) {
		EditVehicleUI.editVehicleUIComponent = editDutyTypeUIComponent;
	}

	
	public static List<Object> getList() {
		return list;
	}

	/**
	 * @return the model
	 */
	public static VehicleDataModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(VehicleDataModel model) {
		EditVehicleUI.model = model;
	}



	

}
