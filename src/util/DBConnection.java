/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            String URL = "jdbc:oracle:thin:@localhost:1521:xe";
            String USER = "swap";
            String PASS = "swap";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Driver myDriver = new oracle.jdbc.driver.OracleDriver();
            DriverManager.registerDriver( myDriver );
            m_connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex)
        {
            System.out.println(ex.getCause());
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
	
    }
    public static Connection getConnectionInstance()
    {
        if(m_connection==null)
        {
           System.out.println("New Db Connection");
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
