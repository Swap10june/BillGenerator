package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

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
			
			

		default:
			break;
		}
	}

}
