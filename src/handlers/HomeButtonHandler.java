package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ui.Admin_homeUI;
import ui.BillGenerateUI;
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
			case SConstants.BILL_GEN_WIN_NAME:
			{
				new BillGenerateUI(new javax.swing.JDialog());
			}
			
			break;
			case SConstants.ADMIN_WIN_NAME:
			{
				new Admin_homeUI(new javax.swing.JDialog());
			}
			
			break;
			
			

		default:
			break;
		}
	}

}
