package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ui.ChekForAdminUI;
import ui.TelcoBOM;
import util.SConstants;

public class HomeButtonHandler implements ActionListener {

	public HomeButtonHandler(JDialog owner)
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case SConstants.TAL_BILL_BTN_STRING:
			{
				new TelcoBOM(new javax.swing.JDialog(),SConstants.TAL_BILL_BTN_STRING);
			}
			
			break;
			case SConstants.ADMIN_BTN_STRING:
			{
				new ChekForAdminUI(new javax.swing.JDialog(),SConstants.ADMIN_BTN_STRING);
			}
			
			break;
			
			

		default:
			break;
		}
	}

}
