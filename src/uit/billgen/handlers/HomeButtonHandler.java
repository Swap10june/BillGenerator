package uit.billgen.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import uit.billgen.constants.SConstants;
import uit.billgen.exceptions.PopupDialogs;
import uit.billgen.uiviews.ChekForAdminUI;
import uit.billgen.uiviews.ExtraCabBillUI;
import uit.billgen.uiviews.MonthlyCabBillUI;

public class HomeButtonHandler implements ActionListener {

	public HomeButtonHandler(JDialog owner)
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch (arg0.getActionCommand())
		{
			case SConstants.EXTRA_CAB_BILL_BTN_STRING:
			{
				new ExtraCabBillUI(new javax.swing.JDialog(),SConstants.EXTRA_CAB_BILL_BTN_STRING);
			}
			
			break;
			case SConstants.ADMIN_BTN_STRING:
			{
				new ChekForAdminUI(new javax.swing.JDialog(),SConstants.ADMIN_BTN_STRING);
			}
			
			break;
			case SConstants.MONTHLY_CAB_BILL_BTN_STRING:
			{
				//new MonthlyCabUI(new javax.swing.JDialog(),SConstants.MONTHLY_CAB_BILL_BTN_STRING);
				new MonthlyCabBillUI(new javax.swing.JDialog(),SConstants.MONTHLY_CAB_BILL_BTN_STRING);
			}
			
			break;
			case SConstants.HISTORY_BTN_STRING:
			{
				//new SearchUI(new javax.swing.JDialog(),SConstants.SEARCH_STRING);
				JOptionPane.showMessageDialog(null, "Not yet completed.");
			}
			
			break;
			case SConstants.UPDATE_BTN_STRING:
			{
				/*//updating dutyType
				List<CabObject> dutyTypeList = new BillsDataModel().getAllTransactions();
				new Dao().update*/
				
				new PopupDialogs(SConstants.MSG_UPDATED_SUCCESSFULLY, PopupDialogs.INFORMATION_MESSAGE);
			}
			
			break;
			
			

		default:
			break;
		}
	}

}
