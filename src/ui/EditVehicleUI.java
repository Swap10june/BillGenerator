package ui;

import handlers.VehicleButtonHandler;

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

import beans.Vehicle;
import model.CustomerDataModel;
import model.VehicleDataModel;
import util.SConstants;
import util.Utils;

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
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,windowName);
		initUI(owner);
		owner.setVisible(true);
	}
		
	
	
	private void initUI(final JDialog owner) 
	{
		setModel(new VehicleDataModel());
		JPanel topPanel  = new JPanel();
		topPanel.setBounds(150, 30, 600, 100);
		topPanel.setLayout(new GridLayout(2,2));
		
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
				
				
			}
		});
		
		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(10, 120, 600, 150);
		bodyLeftPanel.setLayout(new GridLayout(2, 4));
		
		
		JPanel enterVehicleName = templates .getLabelWithTextField("enterVehicleName", "Enter Vehicle Name", "Edit Name Here", 10,false, editVehicleUIComponent);
		bodyLeftPanel.add(enterVehicleName);
		enterVehicleName.setVisible(false);
		list.add(enterVehicleName);
		
		JPanel enterVNumber = templates .getLabelWithTextField("enterVNumber", "Enter Vehicle No", "Edit No Here", 10,false, editVehicleUIComponent);
		bodyLeftPanel.add(enterVNumber);
		enterVNumber.setVisible(false);
		list.add(enterVNumber);
		
		/*JPanel enterCustomerName = templates.getLabelWithTextField("enterCustomerName", "Enter Customer Name", "Edit Name Here",10,false, editVehicleUIComponent);
		bodyLeftPanel.add(enterCustomerName);
		enterCustomerName.setVisible(false);
		list.add(enterCustomerName);*/
		String [] customers = new CustomerDataModel().getAllCustomerNames();
		JPanel enterCustomerName  = templates.getLabelWithComboWOListner("enterCustomerName", "Select Customer", customers, editVehicleUIComponent);
		bodyLeftPanel.add(enterCustomerName);
		enterCustomerName.setVisible(false);
		list.add(enterCustomerName);
		
		
		
		JButton btnEdit = new JButton(SConstants.EDIT_BTN_STRING);
		btnEdit.setBounds(150, 300, 150, 30);
		btnEdit.setEnabled(false);
		list.add(btnEdit);
		btnEdit.addActionListener(new VehicleButtonHandler(owner));
				/*new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				
				Vehicle oldVehicle = getModel().getVehicle(comboEditVehicle.getSelectedItem().toString());
				
				JPanel enterVehicleName = (JPanel) getEditDutyTypeUIComponent().get("enterVehicleName");
				JTextField vName = (JTextField) enterVehicleName.getComponent(2);
				String vNameValue = vName.getText();
				
				JPanel enterVNumber = (JPanel) getEditDutyTypeUIComponent().get("enterVNumber");
				JTextField vNo = (JTextField) enterVNumber.getComponent(2);
				String vNoValue = vNo.getText();
				
				JPanel enterCustomerName = (JPanel) getEditDutyTypeUIComponent().get("enterCustomerName");
				JTextField cName = (JTextField) enterCustomerName.getComponent(2);
				String cNameValue = cName.getText();
				
				
				Vehicle newVehicle = new Vehicle(oldVehicle.getUid(), vNameValue, cNameValue,vNoValue);
				getModel().updateAttributeValue(newVehicle);
				new PopupDialogs("Updated Successfully", PopupDialogs.PLAIN_MESSAGE);
				owner.dispose();
			}
		});*/
		
		JButton btnCancel = new JButton(SConstants.CANCEL_BTN_STRING);
		btnCancel.setBounds(350, 300, 150, 30);
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
