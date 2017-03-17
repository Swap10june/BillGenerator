/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.billgen.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import uit.billgen.exceptions.PopupDialogs;

/**
 *
 * @author Swapnil
 */
public class DBConnection
{
    

    private static Connection m_connection=null;
    private DBConnection()
    {
        try 
        {
            
            String URL = SConstants.DBCONNECTION_URL;
            String USER = SConstants.DB_UN;
            String PASS = SConstants.DB_PW;
            Class.forName(SConstants.DB_ORACLE_CLASS_FORNAME);
            Driver myDriver = new oracle.jdbc.driver.OracleDriver();
            DriverManager.registerDriver( myDriver );
            m_connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException |ClassNotFoundException ex)
        {
            new PopupDialogs("Unable to Connect Database. Please Contact Database Administrator", PopupDialogs.ERROR_MESSAGE);
        }
	
    }
    public static Connection getConnectionInstance()
    {
        if(m_connection==null)
        {
           //System.out.println("New Db Connection");
           new DBConnection();
           return m_connection;
          
        }
        else
        {
         //System.out.println("Return existing connection");
            return m_connection;
        }
    }

}
