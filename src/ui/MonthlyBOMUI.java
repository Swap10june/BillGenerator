package ui;

import handlers.MonthlyBOMButtonHandler;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import beans.BillRow;
import beans.MonthlyBOM;
import model.MonthlyBillDataModel;
import util.NumToWords;
import util.SConstants;
import util.Utils;

public class MonthlyBOMUI extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JDialog parent = null;

	private HashMap<String, Object> monthlyBillUIComponentsMap;
	
	private UITemplates templates = new UITemplates();

	private JPanel finalPanel;

	private Map<String,Object> billrows = null;

	private Map<String,JPanel> rowsMap = null;
	private List<BillRow> billRowList = null;

	public static int counter = 1;

	public static double totalAmount=0;
	private JLabel mothlyTotalAmount = new JLabel("0");
	private JLabel mothlyTotalAmountInWords = new JLabel("Zero");
	
	private JMenuItem menuItem = new JMenuItem("Remove");
    private JPopupMenu menuPopup = new JPopupMenu();

	private JButton btnGenerateBill;

	private JCheckBox chkExcel = null;
	private MonthlyBOM monthlyBom = null;
	public JCheckBox getChkExcel() {
		return chkExcel;
	}
	public MonthlyBOMUI(JDialog owner, String monthlyBomBtnString)
	{
		
		super(owner);
		this.setParent(owner);
		counter=1;
		billRowList = new ArrayList<BillRow>();
		billrows = new HashMap<String,Object>();
		totalAmount = 0;
		setMonthlyBillUIComponentsMap(new HashMap<String,Object>());
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,monthlyBomBtnString);
		monthlyBom = new MonthlyBOM();
		initUI(owner);
		menuPopup.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JMenuItem source = (JMenuItem) e.getSource();
				JPopupMenu abc = (JPopupMenu) source.getParent();
				JPanel root = (JPanel)abc.getInvoker();
				JPanel panMain = (JPanel)root.getParent();
				panMain.remove(root);
				
				for (int i = 0; i < billrows.size(); i++)
				{
					if(root.getName().equalsIgnoreCase(String.valueOf(i)))
						billrows.remove("components"+String.valueOf(i+1));
				}
				panMain.repaint();
				JLabel lbl = (JLabel) root.getComponent(21);
				totalAmount = totalAmount-Double.parseDouble(lbl.getText().isEmpty()?"0":lbl.getText());
				updateTotalAmountValues();
			}
		});
		owner.setVisible(true);
		
	}
	public void updateTotalAmountValues()
	{
		double finalValue = totalAmount;
		if(totalAmount>0)
		{
			btnGenerateBill.setEnabled(true);
		}
		getMothlyTotalAmount().setFont(SConstants.FONT_COURRIER_BOLD_18);
		getMothlyTotalAmount().setText(String.valueOf(finalValue));
		NumToWords numToWords = new NumToWords();
		String inwords = numToWords.convert((int)finalValue);
		getMothlyTotalAmountInWords().setFont(SConstants.FONT_COURRIER_BOLD_18);
		getMothlyTotalAmountInWords().setText(inwords);
		getFinalPanel().setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		// Header Panel
		JPanel panelBillHeader = new JPanel();
		panelBillHeader.setBounds(0, 30, SConstants.MAIN_WINDOW_WIDTH-6, 70);
		panelBillHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBillHeader.setLayout(new FlowLayout());
		
		int no = new MonthlyBillDataModel().getNoOfTagsUnderTag("MBills");
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
		monthlyBom.setBillNumber(((JLabel)panelBillNo.getComponent(2)).getText().isEmpty()?"-NA-":((JLabel)panelBillNo.getComponent(2)).getText());
		
		final JPanel panelBillDate = templates.getLabelWithValueLabel("panelBillDate",SConstants.L_TEL_BILL_DTAE, SConstants.TODAYS_DATE,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelBillDate);
		monthlyBom.setBillDate(((JLabel)panelBillDate.getComponent(2)).getText().isEmpty()?"-NA-":((JLabel)panelBillDate.getComponent(2)).getText());
		
		final JPanel panelContactNumber = templates.getLabelWithValueLabel("panelContactNumber",SConstants.L_TEL_CONTACT_NO,SConstants.V_CLIENT_MOB1,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelContactNumber);
		monthlyBom.setContactNumber(((JLabel)panelContactNumber.getComponent(2)).getText().isEmpty()?"-NA-":((JLabel)panelContactNumber.getComponent(2)).getText());
		
		final JPanel panelPanNumber = templates.getLabelWithValueLabel("panelPanNumber",SConstants.L_PAN_NO,SConstants.V_PAN_NO,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelPanNumber);
		
		
		final JPanel panelEmail = templates.getLabelWithValueLabel("panelEmail",SConstants.L_E_MAIl,SConstants.V_E_Mail,monthlyBillUIComponentsMap);
		panelBillHeader.add(panelEmail);
		monthlyBom.setEmail(((JLabel)panelEmail.getComponent(2)).getText().isEmpty()?"-NA-":((JLabel)panelEmail.getComponent(2)).getText());
		
		owner.add(panelBillHeader);
		
		JButton btnAddBillRow = new JButton();
		btnAddBillRow.setIcon(new ImageIcon("images/addDocument.png"));
		btnAddBillRow.addActionListener(new MonthlyBOMButtonHandler(this,SConstants.ADD_BTN_STRING));
		btnAddBillRow.setBounds(10,100, 50, 50);
		owner.add(btnAddBillRow);
		
		

		JPanel RowsPanel = new JPanel();
		RowsPanel.setBounds(60, 100, 870, 450);
		//RowsPanel.setBackground(Color.BLUE);
		RowsPanel.setLayout(new GridLayout(5,1));
		JScrollPane scroll = new JScrollPane(RowsPanel);
		//scroll.setBounds(860, 60, 10, 500);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		rowsMap = new HashMap<String, JPanel>();
		for (int i = 0; i < 5; i++)
		{
			JPanel pan = new JPanel();
			//pan.setSize(870, 250);
			pan.setLayout(new GridLayout(7,4));
			//pan.setBackground(Color.cyan);
			pan.setBorder(BorderFactory.createLineBorder(Color.blue));
			for (int j = 0; j < 22; j++)
			{
				pan.add(new JLabel());
				pan.setComponentPopupMenu(menuPopup);
				pan.setName(String.valueOf(i));
			}
			RowsPanel.add(pan);
			pan.setVisible(false);
			rowsMap.put("pan"+String.valueOf(i+1), pan);
		}
		owner.add(RowsPanel);
		//parent.add(scroll);
		
		finalPanel = new JPanel();
		finalPanel.setBounds(60, 560, 870, 80);
		//finalPanel.setBackground(Color.yellow);
		finalPanel.setLayout(new GridLayout(4,1));
		finalPanel.setVisible(false);
		finalPanel.add(new JLabel("Total Amount:"));
		finalPanel.add(mothlyTotalAmount);
		finalPanel.add(new JLabel("Total Amount In Words:"));
		finalPanel.add(mothlyTotalAmountInWords);
		owner.add(finalPanel);
		
		chkExcel = new JCheckBox();
		chkExcel.setBounds(10, 150, 50, 50);
		chkExcel.setEnabled(false);
		owner.add(chkExcel);
		
		
		JButton btnRemoveRow = new JButton();
		btnRemoveRow.setIcon(new ImageIcon("images/delete.png"));
		btnRemoveRow.setBounds(10, 200, 50, 50);
		btnRemoveRow.setEnabled(false);
		owner.add(btnRemoveRow);
		//btnRemoveRow.addActionListener(new MonthlyBOMButtonHandler(this,SConstants.REMOVE_BTN_STRING));
		//owner.dispose();
		

		btnGenerateBill = new JButton(SConstants.GENEARTE_BILL_BTN_STRING);
		btnGenerateBill.setBounds(10, 250, 50, 50);
		btnGenerateBill.setEnabled(false);
		owner.add(btnGenerateBill);
		btnGenerateBill.addActionListener(new MonthlyBOMButtonHandler(this,SConstants.GENEARTE_BILL_BTN_STRING));
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

	/**
	 * @return the mothlyTotalAmount
	 */
	public JLabel getMothlyTotalAmount() {
		return mothlyTotalAmount;
	}

	/**
	 * @param mothlyTotalAmount the mothlyTotalAmount to set
	 */
	public void setMothlyTotalAmount(JLabel mothlyTotalAmount) {
		this.mothlyTotalAmount = mothlyTotalAmount;
	}

	/**
	 * @return the mothlyTotalAmountInWords
	 */
	public JLabel getMothlyTotalAmountInWords() {
		return mothlyTotalAmountInWords;
	}

	/**
	 * @param mothlyTotalAmountInWords the mothlyTotalAmountInWords to set
	 */
	public void setMothlyTotalAmountInWords(JLabel mothlyTotalAmountInWords) {
		this.mothlyTotalAmountInWords = mothlyTotalAmountInWords;
	}

	/**
	 * @return the finalPanel
	 */
	public JPanel getFinalPanel() {
		return finalPanel;
	}

	/**
	 * @param finalPanel the finalPanel to set
	 */
	public void setFinalPanel(JPanel finalPanel) {
		this.finalPanel = finalPanel;
	}

	/**
	 * @return the billrows
	 */
	public Map<String,Object> getBillrows() {
		return billrows;
	}

	/**
	 * @param billrows the billrows to set
	 */
	public void setBillrows(Map<String,Object> billrows) {
		this.billrows = billrows;
	}
	/**
	 * @return the rowsMap
	 */
	public Map<String, JPanel> getRowsMap() {
		return rowsMap;
	}

	/**
	 * @param rowsMap the rowsMap to set
	 */
	public void setRowsMap(Map<String, JPanel> rowsMap) {
		this.rowsMap = rowsMap;
	}
	/**
	 * @return the billRowList
	 */
	public List<BillRow> getBillRowList() {
		return billRowList;
	}
	/**
	 * @param billRowList the billRowList to set
	 */
	public void setBillRowList(List<BillRow> billRowList) {
		this.billRowList = billRowList;
	}
	/**
	 * @return the monthlyBom
	 */
	public MonthlyBOM getMonthlyBom() {
		return monthlyBom;
	}
	/**
	 * @param monthlyBom the monthlyBom to set
	 */
	public void setMonthlyBom(MonthlyBOM monthlyBom) {
		this.monthlyBom = monthlyBom;
	}
	
}
