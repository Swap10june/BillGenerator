package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import exceptions.PopupDialogs;
import ui.Home;
import util.Registry;
import util.SConstants;
import util.Utils;

public class Login extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Registry reg = SConstants.reg;
	
	public Login(JDialog owner)
	{
		super(owner);
		Utils.getUtilityInstance().applyBasicSettingsOnWindow(owner,"Login");
		initUI(owner);
		owner.setVisible(true);
	}
	private void initUI(final JDialog owner)
	{
		// Image panel
		 JPanel imagePanel = new JPanel();
		 //Helper.setComponent(imagePanel, "imagePanel");
		 imagePanel.setLayout(new BorderLayout());
		 JLabel pic = new JLabel();
		 imagePanel.add(pic);
		 ImageIcon test = new ImageIcon("images/login.png"); 
		 pic.setIcon(test);
		 //imagePanel.setBackground(Color.CYAN);
		 imagePanel.setBounds(30, 100, 410, 350);
		 
		 //Label for user name
		 JLabel lblUserName = new JLabel();
		 //Helper.setComponent(lblUserName, "lblUserName");
		 lblUserName.setText("Enter Username");
		 lblUserName.setText(Utils.getUtilityInstance().htmlIfy("<p style='color:#1C66AE;'>Enter Username</p>"));
		 lblUserName.setFont(new Font("Courier", Font.BOLD,20));
		 lblUserName.setBounds(450, 100, 200, 30);
		 
		 //Text field for user name 
		 final JTextField  txtUserName = new JTextField(15);
		 //Helper.setComponent(txtUserName, "txtUserName");
         txtUserName.setText("swap");
		 txtUserName.setBounds(480, 140, 300, 30);
		 
		 //Label for user name image icon
		 JLabel lblUserNameIcon = new JLabel();
		 //Helper.setComponent(lblUserNameIcon, "lblUserNameIcon");
		 lblUserNameIcon.setIcon(new ImageIcon("images/user.png"));
		 lblUserNameIcon.setBounds(790, 140, 40, 30);
		 
		 //Label for password
		 JLabel lblUserPass = new JLabel();
		 //Helper.setComponent(lblUserPass, "lblUserPass");
		 lblUserPass.setText("Enter Password");
		 lblUserPass.setFont(new Font("Courier", Font.BOLD,20));
		 lblUserPass.setText(Utils.getUtilityInstance().htmlIfy("<p style='color:#1C66AE;'>Enter Password</p>"));
		 lblUserPass.setBounds(450, 200, 200, 30);
		 
		 
		 
		 //Text field for password
		 final JPasswordField txtUserPass = new JPasswordField(15);
		 //Helper.setComponent(txtUserPass, "txtUserPass");
         txtUserPass.setText("swap");
		 txtUserPass.setBounds(480, 240, 300, 30);
		 
		//Label for user password image icon
		 JLabel lblUserPassIcon = new JLabel();
		 //Helper.setComponent(lblUserPassIcon, "lblUserPassIcon");
		 lblUserPassIcon.setIcon(new ImageIcon("images/passwordKey.png"));
		 lblUserPassIcon.setBounds(790, 240, 40, 30);
		 
		 //Check box to show password
		 final JCheckBox passCheckBox = new JCheckBox("Show Password");
		 //Helper.setComponent(passCheckBox, "passCheckBox");
		 passCheckBox.setBounds(750, 280, 300, 30);
		 passCheckBox.addActionListener(new ActionListener()
                 {

                        @Override
                        public void actionPerformed(ActionEvent e)
                        {	
                            if(passCheckBox.isSelected())
                            {
                                txtUserPass.setEchoChar((char)0);
                            }else
                            {
                                txtUserPass.setEchoChar('*');
                            }
                        }
                    });			
		 
		 // submit button
		 JButton btnLogin = new JButton("Login");
		 //Helper.setComponent(btnLogin, "btnLogin");
		 btnLogin.setBounds(480, 350, 100, 30);
		 btnLogin.addActionListener(new ActionListener()
                 {

                    @SuppressWarnings("deprecation")
					@Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if(txtUserName.getText().equalsIgnoreCase(Utils.getUtilityInstance().ReadTag("name", "resource/users.xml")) 
                        		&&txtUserPass.getText().equalsIgnoreCase(Utils.getUtilityInstance().ReadTag("pw", "resource/users.xml")))
                        {
                            System.out.println("Login Successful :: "+txtUserName.getText());
                            new Home(new javax.swing.JDialog());
                        	owner.dispose();
                        }
                        else
							new PopupDialogs(reg.getValueFor("E_LOGIN_EXCEPTION_STRING"),PopupDialogs.ERROR_MESSAGE);
                        txtUserPass.setText("");
                    }
                    });
		 
		 // Cancel button
		 JButton btnCancel = new JButton("Cancel");
		 //Helper.setComponent(btnCancel, "btnCancel");
		 btnCancel.setBounds(630, 350, 100, 30);
		 btnCancel.addActionListener(new ActionListener()
                 {

                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            //Helper.writeIntoFile(new File(Data.LICENSE_FILE_PATH),"N");
                            System.exit(0);
                        }
                    });
		 
		 
		 JPanel loginBodyPanel = new JPanel();
	   	 loginBodyPanel.setLayout(null);
	   	 loginBodyPanel.setBounds(0, 30, 894, 510);
	   	 //loginBodyPanel.setBackground(Color.gray);
		 loginBodyPanel.add(imagePanel);
		 loginBodyPanel.add(lblUserName);
		 loginBodyPanel.add(txtUserName);
		 loginBodyPanel.add(lblUserNameIcon);
		 loginBodyPanel.add(lblUserPass);
		 loginBodyPanel.add(txtUserPass);
		 loginBodyPanel.add(lblUserPassIcon);
		 loginBodyPanel.add(passCheckBox);
		 loginBodyPanel.add(btnLogin);
		 loginBodyPanel.add(btnCancel);
		// loginBodyPanel.add(footerLabel);
         loginBodyPanel.setVisible(true);
         owner.add(loginBodyPanel);
	}
	public static void main(String[] args) 
	{
		try 
		{
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
		    {
		        if ("Nimbus".equals(info.getName()))
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		//Login loginFrame = new Login();
		new Login(new javax.swing.JDialog());
		//loginFrame.setVisible(true);
		//new Home(new javax.swing.JDialog());
	}
}