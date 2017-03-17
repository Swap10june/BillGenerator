package uit.billgen.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import uit.billgen.beans.BillRow;
import uit.billgen.datamodel.MonthlyBillDataModel;
import uit.billgen.exceptions.PopupDialogs;
import uit.billgen.uiviews.CreateMonthlyBillRow;
import uit.billgen.uiviews.MonthlyBOMUI;
import uit.billgen.uiviews.UITemplates;
import uit.billgen.util.SConstants;
import uit.billgen.util.Utils;

public class MonthlyBOMButtonHandler implements ActionListener {
 
	UITemplates templates = new UITemplates();
	private JTextField TotalAmount;
	private MonthlyBOMUI source =null;
	private String action = null;
	public MonthlyBOMButtonHandler(MonthlyBOMUI monthlyBOM, String action) 
	{
		this.source  = monthlyBOM;
		this.action = action;		
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(action.equalsIgnoreCase(SConstants.ADD_BTN_STRING))
		{
			//System.out.println("size:"+source.getBillRowList().size());
			if(source.getBillRowList().size()<5)
			{
				BillRow billRow = new BillRow();
				new CreateMonthlyBillRow(source.getBillrows(),billRow);	
				addLabels(billRow);
				source.getBillRowList().add(billRow);
				source.getChkExcel().setEnabled(true);
			}
			else
			{
				new PopupDialogs(SConstants.MSG_ONLY_5_ROWS_ALLOWED, PopupDialogs.ERROR_MESSAGE);
			}
		}
		if(action.equalsIgnoreCase(SConstants.GENEARTE_BILL_BTN_STRING))
		{
			source.getMonthlyBom().setBillRows(source.getBillRowList());
			if(source.getChkExcel().isSelected())
			{
				Utils.getUtilityInstance().generateMonthlyBill(source.getMonthlyBom());
			}
			int status = new MonthlyBillDataModel().addBillTransaction(source.getBillRowList());
			if(status==0)
			{
				new PopupDialogs(SConstants.MSG_ADDED_SUCCESSFULLY, PopupDialogs.INFORMATION_MESSAGE);
				source.getParent().dispose();
			}
			else
			{
				new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
			}
				
		}

	}

	private void addLabels(BillRow billRow)
	{
		@SuppressWarnings("unchecked")
		Map<String, Object> billRowComponents = (Map<String, Object>) source.getBillrows().get("components"+String.valueOf(MonthlyBOMUI.counter));
		if(billRowComponents!=null && billRowComponents.size()>0)
		{
			Map<String, JPanel> map = source.getRowsMap();
			JPanel pan = map.get("pan"+String.valueOf(MonthlyBOMUI.counter));
			pan.setVisible(true);
			List<JLabel> listLabels = new ArrayList<JLabel>();
			for (int i = 0; i <pan.getComponentCount(); i++)
			{
				if(pan.getComponent(i) instanceof JLabel)
					listLabels.add((JLabel)pan.getComponent(i));
			}
			
			if(billRowComponents.containsKey("fromDatePanel"))
			{
				JXDatePicker fromDate = (JXDatePicker) ((JPanel)billRowComponents.get("fromDatePanel")).getComponent(2);
				listLabels.get(0).setText("From:");
				listLabels.get(0).setFont(SConstants.FONT_COURRIER_BOLD_13);
				@SuppressWarnings("deprecation")
				String fromDateString =fromDate.getDate().toLocaleString();
				listLabels.get(1).setText(fromDateString);
				billRow.setFromDate(fromDateString);
			}
			if(billRowComponents.containsKey("toDatePanel"))
			{
				JXDatePicker toDate = (JXDatePicker) ((JPanel)billRowComponents.get("toDatePanel")).getComponent(2);
				listLabels.get(2).setText("To:");
				listLabels.get(2).setFont(SConstants.FONT_COURRIER_BOLD_13);
				@SuppressWarnings("deprecation")
				String toDateString = toDate.getDate().toLocaleString();
				listLabels.get(3).setText(toDateString);
				billRow.setToDate(toDateString);
			}
			if(billRowComponents.containsKey("vehicleDesc"))
			{
				JTextArea desc = (JTextArea) ((JPanel)billRowComponents.get("vehicleDesc")).getComponent(2);
				listLabels.get(4).setText("Vehicle Desc:");
				listLabels.get(4).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(5).setText(desc.getText());
				billRow.setVehicleDesc(desc.getText().isEmpty()?"":desc.getText());
			}
			if(billRowComponents.containsKey("monthlyKm"))
			{
				JSpinner monthlyKm = (JSpinner) ((JPanel)billRowComponents.get("monthlyKm")).getComponent(2);
				listLabels.get(6).setText("Monthly Km:");
				listLabels.get(6).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(7).setText(monthlyKm.getModel().getValue().toString());
				int km = (int)Double.parseDouble(monthlyKm.getModel().getValue().toString());
				billRow.setMonthlyKm(km);
			}
			if(billRowComponents.containsKey("extraKm"))
			{
				JSpinner extraKm = (JSpinner) ((JPanel)billRowComponents.get("extraKm")).getComponent(2);
				listLabels.get(8).setText("Extra Km:");
				listLabels.get(8).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(9).setText(extraKm.getModel().getValue().toString());
				int extraKmValue = (int)Double.parseDouble(extraKm.getModel().getValue().toString());
				billRow.setExtraKm(extraKmValue);
			}
			if(billRowComponents.containsKey("vehicle"))
			{
				@SuppressWarnings("unchecked")
				JComboBox<String> vehicle = (JComboBox<String>) ((JPanel)billRowComponents.get("vehicle")).getComponent(2);
				listLabels.get(10).setText("Vehicle:");
				listLabels.get(10).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(11).setText(vehicle.getSelectedItem().toString());
				billRow.setVehicle(vehicle.getSelectedItem().toString());
			}
			if(billRowComponents.containsKey("rate"))
			{
				JTextField rate = (JTextField) ((JPanel)billRowComponents.get("rate")).getComponent(2);
				listLabels.get(12).setText("Rate:");
				listLabels.get(12).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(13).setText(rate.getText().toString());
				billRow.setRate(Double.parseDouble(rate.getText().toString().isEmpty()?"0":rate.getText().toString()));
				
			}
			if(billRowComponents.containsKey("amount"))
			{
				JTextField amount = (JTextField) ((JPanel)billRowComponents.get("amount")).getComponent(2);
				listLabels.get(14).setText("Amount:");
				listLabels.get(14).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(15).setText(amount.getText());
				billRow.setAmount(Double.parseDouble(amount.getText().isEmpty()?"0":amount.getText()));
			}
			if(billRowComponents.containsKey("ExtraKmRate"))
			{
				JTextField ExtraKmRate = (JTextField) ((JPanel)billRowComponents.get("ExtraKmRate")).getComponent(2);
				listLabels.get(16).setText("Extra Km Rate:");
				listLabels.get(16).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(17).setText(ExtraKmRate.getText());
				billRow.setExtraKmRate(Double.parseDouble(ExtraKmRate.getText().isEmpty()?"0":ExtraKmRate.getText()));
			}
			if(billRowComponents.containsKey("ExKmAmount"))
			{
				JTextField ExKmAmount = (JTextField) ((JPanel)billRowComponents.get("ExKmAmount")).getComponent(2);
				listLabels.get(18).setText("Extra Km Amount:");
				listLabels.get(18).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(19).setText(ExKmAmount.getText());
				billRow.setExtraKmAmpount(Double.parseDouble(listLabels.get(19).getText().isEmpty()?"0":listLabels.get(19).getText()));
			}
			if(billRowComponents.containsKey("TotalAmount"))
			{
				TotalAmount = (JTextField) ((JPanel)billRowComponents.get("TotalAmount")).getComponent(2);
				listLabels.get(20).setText("Total Amount:");
				listLabels.get(20).setFont(SConstants.FONT_COURRIER_BOLD_13);
				listLabels.get(21).setText(TotalAmount.getText());
				MonthlyBOMUI.totalAmount = MonthlyBOMUI.totalAmount+Double.parseDouble(TotalAmount.getText().isEmpty()?"0":TotalAmount.getText());
				billRow.setTotalAmount(Double.parseDouble(TotalAmount.getText().isEmpty()?"0":TotalAmount.getText()));
			}
			MonthlyBOMUI.counter++;
			//billRowComponents.clear();
			source.updateTotalAmountValues();
			
		}
	}

	
}
