package uit.billgen.exceptions;

import javax.swing.JOptionPane;

public class PopupDialogs
{

	/** Used for error messages. */
    public static final int  ERROR_MESSAGE = 0;
    /** Used for information messages. */
    public static final int  INFORMATION_MESSAGE = 1;
    /** Used for warning messages. */
    public static final int  WARNING_MESSAGE = 2;
    /** Used for questions. */
    public static final int  QUESTION_MESSAGE = 3;
    /** No icon is used. */
    public static final int   PLAIN_MESSAGE = -1;

	public PopupDialogs(String string,int ERROR_MESSAGE)
	{
		
		JOptionPane.showMessageDialog(null,string,"Action",ERROR_MESSAGE);
	}

	
	
}
