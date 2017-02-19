package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import beans.BOM;

public class Utils 
{
	private static Utils s_utils=null;
	
	private static final String HTML = "<html>";
    private static final String HTML_END = "</html>";
    
    private Utils()
    {
    	
    }
    public static Utils getUtilityInstance()
    {
        if(s_utils==null)
        {
           return new Utils();
          
        }
        else
        {
         //System.out.println("Return existing connection");
            return s_utils;
        }
    }
	public void applyBasicSettingsOnWindow(JDialog owner, String title) 
	{
		owner.setLayout(null);
	    //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    //int x = (int) ((dimension.getWidth() - owner.getWidth()) / 4);
	    //int y = (int) ((dimension.getHeight() - owner.getHeight()) / 5);
	    owner.setLocation(80,10);
	    owner.setSize(new Dimension(SConstants.MAIN_WINDOW_WIDTH, SConstants.MAIN_WINDOW_HEIGHT));
	    owner.setModal(true);
	    owner.setResizable(false);
	    owner.setTitle(title);
	    owner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    JLabel headerLabel = new JLabel(SConstants.PRPJECT_HEADING,SwingConstants.CENTER);
	    headerLabel.setBounds(0, 0,SConstants.MAIN_WINDOW_WIDTH-6, 30);
	    headerLabel.setFont(SConstants.FONT_COURRIER_BOLD_18);
	    Border border = BorderFactory.createLineBorder(Color.blue);
	    headerLabel.setBorder(border);
	    owner.add(headerLabel);
	    
	    JLabel footerLabel = new JLabel(SConstants.MY_COMPANY_NAME,SwingConstants.CENTER);
	    footerLabel.setBounds(0, SConstants.MAIN_WINDOW_HEIGHT-60, SConstants.MAIN_WINDOW_WIDTH, 30);
	    footerLabel.setFont(SConstants.FONT_COURRIER_BOLD_18);
	    footerLabel.setBorder(border);
	    owner.add(footerLabel);
	}

	public static void applyBasicSettingsOnWindow_Small(JDialog owner, String string)
	{
		owner.setLayout(null);
		//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	   // int x = (int) ((dimension.getWidth() - owner.getWidth()) / 10);
	    //int y = (int) ((dimension.getHeight() - owner.getHeight()) / 10);
	    owner.setLocation(300,100);
	    owner.setSize(new Dimension(600, 502));
	    owner.setModal(true);
	    owner.setResizable(false);
	    owner.setTitle(string);
	    owner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    JLabel headerLabel = new JLabel(SConstants.PRPJECT_HEADING,SwingConstants.CENTER);
	    headerLabel.setBounds(0, 0, 594, 30);
        //set font for JLabel
	    headerLabel.setFont(SConstants.FONT_COURRIER_BOLD_18);
	    Border border = BorderFactory.createLineBorder(Color.blue);
	    headerLabel.setBorder(border);
	    owner.add(headerLabel);
	    
	    JLabel footerLabel = new JLabel(SConstants.MY_COMPANY_NAME,SwingConstants.CENTER);
	    footerLabel.setBounds(0, 442, 594, 30);
	    footerLabel.setFont(SConstants.FONT_COURRIER_BOLD_18);
	    footerLabel.setBorder(border);
	    owner.add(footerLabel);
	}
    public  ResultSet querySELECT(String Query) throws ClassNotFoundException, SQLException
    {
	  
        System.out.println("Select query fired: "+Query);
        ResultSet set=null;
        Statement statement = DBConnection.getConnectionInstance().createStatement();
        set=statement.executeQuery(Query.toUpperCase());
        return set;     
	
    }
    public void setComponenet(JComponent component, Map<String,JComponent> componenetMap)
	{
		component.setName(component.toString());
		componenetMap.put(component.getName(), component);
	}
	
	public ArrayList<String> getValueListForColumnName(String tableName,String colmnName)
	{
		try
		{
			ArrayList<String> list = new ArrayList<String>();
			ResultSet set = querySELECT("select "+colmnName+" from "+tableName);
			while(set.next())
			{
				list.add(set.getString(colmnName));
			}
			return list;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getFileExtension(File file)
	{
	    String name = file.getName();
	    try
	    {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e)
	    {
	        return "";
	    }
	}
	
	
	public String htmlIfy(String s)
    {
        return HTML.concat(s).concat(HTML_END);
    }
	public String ReadTag(String tagName, String file) {

	    try 
	    {

		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		
		doc.getDocumentElement().normalize();

		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("user");

		//System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++)
	        {

			Node nNode = nList.item(temp);

			//System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE)
	        {

				Element eElement = (Element) nNode;
				return eElement.getElementsByTagName(tagName).item(0).getTextContent();
			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	        return null;
	  }
	public static String getComponentName(String key, String value)
	{
		return key+"_"+value;
	}
	public void generateBill(BOM bom)
	{
		System.out.println(bom.getBillDate());
		System.out.println(bom.getBillNumber());
	}
}
