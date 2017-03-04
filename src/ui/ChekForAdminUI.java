package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.PopupDialogs;
import util.Utils;

public class ChekForAdminUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDialog parent = null;
	private UITemplates templates = new UITemplates();
	Map<String, Object> checkForAdminUIComponent  ;
	public ChekForAdminUI(JDialog owner)
	{
		super(owner);
		this.setParent(owner);
		checkForAdminUIComponent =  new HashMap<String, Object>();
		Utils.getUtilityInstance().applyBasicSettingsOnWindow_Small(owner,"Check For Admin");
		initUI(owner);
		owner.setVisible(true);
		
	}
	private void initUI(JDialog owner) 
	{
		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new GridLayout(2, 2));
		bodyPanel.setBounds(10, 50, 500, 150);
		JPanel panelUserName = templates.getLabelWithTextField("panelUserName", "Enter UserId", "Enter User id Here", 10, false, checkForAdminUIComponent);
		bodyPanel.add(panelUserName);
		JTextField txtUserName = (JTextField) panelUserName.getComponent(2);
		txtUserName.setText("swap");
		
		JPanel panelUserPass = templates.getLabelWithTextField("panelUserName", "Enter Password", "Enter password Here", 10, false, checkForAdminUIComponent);
		bodyPanel.add(panelUserPass);
		JTextField txtUserPass = (JTextField) panelUserPass.getComponent(2);
		txtUserPass.setText("swap");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(150, 200, 100, 30);
		btnLogin.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(txtUserName.getText().equalsIgnoreCase(Utils.getUtilityInstance().ReadTag("admin", "resource/admin.xml")) 
                		&&txtUserPass.getText().equalsIgnoreCase(Utils.getUtilityInstance().ReadTag("pw", "resource/admin.xml")))
                {
                    System.out.println("Admin Login Successful :: "+txtUserName.getText());
                    new AdminHomeUI(new javax.swing.JDialog());
                	owner.dispose();
                }
                else
                	new PopupDialogs("Please Check Admin Credentials.", PopupDialogs.ERROR_MESSAGE);
                txtUserPass.setText("");
			}
		});
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(350, 200, 100, 30);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				owner.dispose();
			}
		});
		owner.add(bodyPanel);
		owner.add(btnLogin);
		owner.add(btnCancel);
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
