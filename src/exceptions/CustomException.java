package exceptions;

import javax.swing.JOptionPane;

public class CustomException
{

	public static final String WARNING = null;

	public CustomException(String string)
	{
		
		JOptionPane.showMessageDialog(null,string,"Error",JOptionPane.ERROR_MESSAGE);
	}

	public CustomException(String string, String warning2)
	{
		
		JOptionPane.showMessageDialog(null,string,"Message",JOptionPane.WARNING_MESSAGE);
	}
	
}
