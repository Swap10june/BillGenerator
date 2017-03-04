package listners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ModelXml.DutyTypeDataModel;
import beans.DutyType;

import ui.TelcoBOM;

public class TelcoBillComboItemListner implements ItemListener
{

	private String comboId = null;
	private boolean status = false;
	public TelcoBillComboItemListner(String comboID, boolean status)
	{
		this.setComboId(comboID);
		this.setStatus(status);
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		double totalKM = 0.0;
		if(TelcoBOM.getComponentMap().containsKey("totalKM"))
		{
			totalKM = (double) TelcoBOM.getComponentMap().get("totalKM");
			JButton btnToatl = (JButton) TelcoBOM.getComponentMap().get("btnTotal");
			btnToatl.setEnabled(true);
			
			JPanel panelTotalPkgKm = (JPanel) TelcoBOM.getComponentMap().get("panelTotalPkgKm");
			JLabel lblTotalDistanceValue = (JLabel) panelTotalPkgKm.getComponent(2);
			
			String dutyTypeID = e.getItem().toString();
			
			DutyType dutyType = new DutyTypeDataModel().getDutyType(dutyTypeID);
			lblTotalDistanceValue.setText(String.valueOf(dutyType.getKm()));
			
			
			JPanel panelRate = (JPanel) TelcoBOM.getComponentMap().get("panelRate");
			JLabel lblRate = (JLabel) panelRate.getComponent(2);
			lblRate.setText(String.valueOf(dutyType.getPackageRate()));
			
			
			JPanel panelAmount = (JPanel) TelcoBOM.getComponentMap().get("panelAmount");
			JLabel lblAMountValue = (JLabel) panelAmount.getComponent(2);
			lblAMountValue.setText(String.valueOf(dutyType.getPackageRate()));
			 
			 
			JPanel panelTotalExtraKM = (JPanel) TelcoBOM.getComponentMap().get("panelTotalExtraKM");
			JLabel lblExtraKM = (JLabel) panelTotalExtraKM.getComponent(2);
			
			int extraKM = (int) (totalKM-dutyType.getKm());
			lblExtraKM.setText(String.valueOf(extraKM));
			 
			
			JPanel panelExtraKMRate = (JPanel) TelcoBOM.getComponentMap().get("panelExtraKMRate");
			JLabel lblExtraRate = (JLabel) panelExtraKMRate.getComponent(2);
			lblExtraRate.setText(String.valueOf(dutyType.getExtraKmRate()));
			 
			
			
			JPanel panelExtraKMAmount = (JPanel) TelcoBOM.getComponentMap().get("panelExtraKMAmount");
			JLabel lblExtraAMount = (JLabel) panelExtraKMAmount.getComponent(2);
			lblExtraAMount.setText(String.valueOf(extraKM*dutyType.getExtraKmRate()));

		}
	}

	/**
	 * @return the comboId
	 */
	public String getComboId() {
		return comboId;
	}

	/**
	 * @param comboId the comboId to set
	 */
	public void setComboId(String comboId) {
		this.comboId = comboId;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

}
