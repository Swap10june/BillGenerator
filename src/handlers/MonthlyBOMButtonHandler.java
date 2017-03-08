package handlers;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;

import ui.CreateMonthlyBillRow;
import util.SConstants;

public class MonthlyBOMButtonHandler implements ActionListener {

	private JPanel parentPanel = null;
	private JDialog parentFrame = null;
	private static Map<String,Object> rows= new HashMap<String,Object>(); 
	public MonthlyBOMButtonHandler(JDialog owner, JPanel rowsPanel) 
	{
		this.parentPanel  = rowsPanel;
		this.parentFrame = owner;
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getActionCommand().equalsIgnoreCase(SConstants.ADD_BTN_STRING))
		{
			//parentPanel.setLayout(new GridLayout(11,1));
			JPanel panel = new CreateMonthlyBillRow().getBillRowPanel();
			panel.setSize(parentPanel.getSize());
			panel.setBackground(Color.cyan);
			parentPanel.add(panel);
			
			
			parentFrame.add(parentPanel);
			parentFrame.setVisible(true);
		}

	}

	/**
	 * @return the rows
	 */
	public static Map<String,Object> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public static void setRows(Map<String,Object> rows) {
		MonthlyBOMButtonHandler.rows = rows;
	}

}
