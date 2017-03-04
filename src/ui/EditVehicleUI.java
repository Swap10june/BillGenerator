package ui;

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

import exceptions.PopupDialogs;
import beans.Vehicle;
import model.VehicleDataModel;
import util.Utils;

public class EditVehicleUI extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final String UI_ID = "EditVehicleUI"; 
	private static Map<String, Object> editVehicleUIComponent ;
	private UITemplates templates = new UITemplates();
	private VehicleDataModel model = null;
	private static List<Object> list = new ArrayList<Object>();
	
	


	public EditVehicleUI(JDialog owner, String windowName) 
	{
		super(owner);
		editVehicleUIComponent = new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,windowName);
		initUI(owner);
		owner.setVisible(true);
	}
		
	
	
	private void initUI(JDialog owner) 
	{
		JPanel bodyLeftPanel = new JPanel();
		bodyLeftPanel.setBounds(10, 30, 500, 200);
		bodyLeftPanel.setLayout(new GridLayout(3, 2));
		setModel(new VehicleDataModel());
		String [] vehicleList = getModel().getAllVehicles();
		
		JPanel panelSelectVehicle = templates.getLabelWithComboWOListner("panelSelectVehicle", "Select Vehicle","VehicleEditCombo", vehicleList, editVehicleUIComponent);
		bodyLeftPanel.add(panelSelectVehicle);
		@SuppressWarnings("unchecked")
		JComboBox<String> comboEditVehicle= (JComboBox<String>) panelSelectVehicle.getComponent(2);
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
				
				JPanel enterCustomerName = (JPanel) editVehicleUIComponent.get("enterCustomerName");
				JTextField cname = (JTextField) enterCustomerName.getComponent(2);
				cname.setText(vehicle.getCustomerName());
				
				
			}
		});
		
		JPanel enterVehicleName = templates .getLabelWithTextField("enterVehicleName", "Enter Vehicle Name", "Edit Name Here", 10,false, editVehicleUIComponent);
		bodyLeftPanel.add(enterVehicleName);
		enterVehicleName.setVisible(false);
		list.add(enterVehicleName);
		
		JPanel enterCustomerName = templates.getLabelWithTextField("enterCustomerName", "Enter Customer Name", "Edit Name Here",10,false, editVehicleUIComponent);
		bodyLeftPanel.add(enterCustomerName);
		enterCustomerName.setVisible(false);
		list.add(enterCustomerName);
		
		
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(150, 300, 150, 30);
		btnEdit.setEnabled(false);
		list.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				
				Vehicle oldVehicle = getModel().getVehicle(comboEditVehicle.getSelectedItem().toString());
				
				JPanel enterVehicleName = (JPanel) getEditDutyTypeUIComponent().get("enterVehicleName");
				JTextField vName = (JTextField) enterVehicleName.getComponent(2);
				String vNameValue = vName.getText();
				
				JPanel enterCustomerName = (JPanel) getEditDutyTypeUIComponent().get("enterCustomerName");
				JTextField cName = (JTextField) enterCustomerName.getComponent(2);
				String cNameValue = cName.getText();
				
				
				Vehicle newVehicle = new Vehicle(oldVehicle.getUid(), vNameValue, cNameValue);
				getModel().updateAttributeValue(newVehicle);
				new PopupDialogs("Updated Successfully", PopupDialogs.PLAIN_MESSAGE);
				owner.dispose();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
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
	public VehicleDataModel getModel() {
		return model;
	}



	/**
	 * @param model the model to set
	 */
	public void setModel(VehicleDataModel model) {
		this.model = model;
	}



	

}
