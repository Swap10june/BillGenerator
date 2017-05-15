package uit.billgen.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import uit.billgen.constants.SConstants;
import uit.billgen.exceptions.PopupDialogs;
 
/**
 *
 * @author swapnil
 */
public class SQliteConnection
{
	
	 private static Connection connection = null;
    /**
     * Connect to a database
     *
     * @param fileName the database file name
     */
    public static Connection createNewDatabase(String dbFilename)
    {
    	try
        {
           Class.forName("org.sqlite.JDBC");
           String dbURL = SConstants.DB_URL+dbFilename;
           connection = DriverManager.getConnection(dbURL);
            if (connection != null)
            {
                System.out.println("Connected to the database");
                return connection;
                //DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                /*System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());*/
                
            }
 
        } catch (SQLException | ClassNotFoundException e)
        {
        	try
        	{
        		if(connection!=null)
        			connection.close();
        		else
        			new PopupDialogs("DB Connection Failed", PopupDialogs.ERROR_MESSAGE);
			} catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            //System.out.println(e.getMessage());
        }
		return connection;
    }
    
   
    private SQliteConnection()
    {
    	
    }
    public static Connection getSQliteConnection(String dbFileName)
    {
    	if(connection!=null)
		 return connection;
    	else
    	{
    		return createNewDatabase(dbFileName);
    	}
    	
    }
}