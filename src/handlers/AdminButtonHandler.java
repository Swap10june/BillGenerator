package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JPanel;

import ui.BillGenerateUI;
import ui.Customer_ProfileUI;
import ui.Vehicle_ProfileUI;
import util.SConstants;

public class AdminButtonHandler implements ActionListener {

	public AdminButtonHandler(JDialog owner)
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case SConstants.ADD_CUST_WIN_NAME:
			{
				new Customer_ProfileUI(new javax.swing.JDialog(),"Add");
			}
			
			break;
			case SConstants.EDIT_CUST_WIN_NAME:
			{
				new Customer_ProfileUI(new javax.swing.JDialog(),"Edit");
			}
			
			break;
			case SConstants.ADD_VEHICLE_WIN_NAME:
			{
				new Vehicle_ProfileUI(new javax.swing.JDialog(),"Add");
			}
			
			break;
			case SConstants.EDIT_VEHICLE_WIN_NAME:
			{
				new Vehicle_ProfileUI(new javax.swing.JDialog(),"Edit");
			}
			
			break;
			case SConstants.SEARCH_BUTTON:
			{
				 Map<String, Object> panelVendorName = Customer_ProfileUI.getComponentMap();
				new Customer_ProfileUI(new javax.swing.JDialog(),"Search");
			}
			
			break;
			

		default:
			break;
		}
	}

}
