package handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import S_Util.Registry;
import ui.BillGenerateUI;

public class HomeButtonHandler implements ActionListener {

	private JDialog parent;
	
	public HomeButtonHandler(JDialog owner)
	{
		this.parent = owner;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case Registry.BILL_GEN_WIN_NAME:
			{
				new BillGenerateUI(new javax.swing.JDialog());
			}
			
			break;
			
			

		default:
			break;
		}
	}

}
