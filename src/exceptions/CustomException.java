package exceptions;

import javax.swing.JOptionPane;

public class CustomException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(String string)
	{
		super(string);
		JOptionPane.showMessageDialog(null,string,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
}
