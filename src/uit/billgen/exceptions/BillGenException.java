package uit.billgen.exceptions;

import javax.swing.JOptionPane;

public class BillGenException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BillGenException(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}

}
