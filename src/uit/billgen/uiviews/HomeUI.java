package uit.billgen.uiviews;

import javax.swing.JButton;
import javax.swing.JDialog;

import uit.billgen.constants.SConstants;
import uit.billgen.handlers.HomeButtonHandler;
import uit.billgen.util.Utils;

public class HomeUI extends JDialog {

	

	private static final long serialVersionUID = 1L;

	public HomeUI(JDialog owner)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,"Home");
		initUI(owner);
		owner.setVisible(true);
	}

	private void initUI(JDialog owner)
	{
		JButton [] btnArray = new JButton[SConstants.HOME_BUTTONS_NAMES.length]; 
		for (int i = 0 , xPos= 10, yPos=60;i < btnArray.length; i++,xPos+=180)
		{
			if(i%5==0 && i!=0)
			{	
				yPos+=60;
				xPos= 10;
			}
			btnArray[i] = new JButton(SConstants.HOME_BUTTONS_NAMES[i]);
			btnArray[i].setBounds(xPos,yPos, 150, 30);
			owner.add(btnArray[i]);
			btnArray[i].addActionListener(new HomeButtonHandler(owner));
		}
	}

}
