package ui;

import handlers.MonthlyBOMButtonHandler;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.TelcoBillDataModel;
import util.SConstants;
import util.Utils;

public class MonthlyBOM extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JDialog parent = null;

	private HashMap<String, Object> monthlyBillUIComponentsMap;
	
	private UITemplates templates = new UITemplates();

	public MonthlyBOM(JDialog owner, String monthlyBomBtnString)
	{
		
		super(owner);
		this.setParent(owner);
		setMonthlyBillUIComponentsMap(new HashMap<String,Object>());
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,monthlyBomBtnString);
		initUI(owner);
		owner.setVisible(true);
		
	}

	private void initUI(JDialog owner)
	{
		// Header Panel
		JPanel panelBillHeader = new JPanel();
		panelBillHeader.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 70);
		panelBillHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillHeader.setLayout(new FlowLayout());
		
		int no = new TelcoBillDataModel().getNoOfTagsUnderTag("Bill");
		@SuppressWarnings("deprecation")
		String str = ((new Date().toLocaleString().split(",")[1]).split(" ")[1]).substring(2);
		int dateYear = Integer.parseInt(str);
		@SuppressWarnings("deprecation")
		int month = new Date().getMonth();
		if(month>2)
		{
			dateYear++;
		}
		//8411989003
		String billNo = "ACC "+String.valueOf(no)+":"+String.valueOf(dateYear)+"-"+String.valueOf(dateYear+1);
		
		final JPanel panelBillNo = templates.getLabelWithValueLabel("panelBillNo",SConstants.L_BILL_NO,billNo,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelBillNo);
		
		final JPanel panelBillDate = templates.getLabelWithValueLabel("panelBillDate",SConstants.L_TEL_BILL_DTAE, SConstants.TODAYS_DATE,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelBillDate);
		
		final JPanel panelContactNumber = templates.getLabelWithValueLabel("panelContactNumber",SConstants.L_TEL_CONTACT_NO,SConstants.V_CLIENT_MOB1,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelContactNumber);
		
		final JPanel panelPanNumber = templates.getLabelWithValueLabel("panelPanNumber",SConstants.L_PAN_NO,SConstants.V_PAN_NO,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelPanNumber);
		
		final JPanel panelEmail = templates.getLabelWithValueLabel("panelEmail",SConstants.L_E_MAIl,SConstants.V_E_Mail,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelEmail);
		
		owner.add(panelBillHeader);
		
		JButton btnAddBillRow = new JButton(SConstants.ADD_BTN_STRING);
		btnAddBillRow.setBounds(10,100, 50, 30);
		owner.add(btnAddBillRow);

		JPanel RowsPanel = new JPanel();
		RowsPanel.setBounds(60, 100, 870, 500);
		RowsPanel.setBackground(Color.yellow);
		//RowsPanel.setLayout(new GridLayout(1,1));
		
		
		btnAddBillRow.addActionListener(new MonthlyBOMButtonHandler(owner,RowsPanel));
		owner.repaint();
		
		//new CreateMonthlyBillRow(jDialog);
	}

	/**
	 * @return the billGenerateUIComponentsMap
	 */
	public HashMap<String, Object> getMonthlyBillUIComponentsMap() {
		return monthlyBillUIComponentsMap;
	}

	/**
	 * @param billGenerateUIComponentsMap the billGenerateUIComponentsMap to set
	 */
	public void setMonthlyBillUIComponentsMap(
			HashMap<String, Object> billGenerateUIComponentsMap) {
		this.monthlyBillUIComponentsMap = billGenerateUIComponentsMap;
	}

	/**
	 * @return the parent
	 */
	public JDialog getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(JDialog parent) {
		this.parent = parent;
	}

}
