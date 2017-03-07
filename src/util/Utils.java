package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdesktop.swingx.JXDatePicker;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import exceptions.PopupDialogs;
import beans.BOM;

public class Utils 
{
	private static Utils s_utils=null;
	
	private static final String HTML = "<html>";
    private static final String HTML_END = "</html>";

	private ArrayList<String> customersList = null;;
    
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
	    owner.setLocation(150,10);
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

	public void applyBasicSettingsOnWindow_Small(JDialog owner, String string)
	{
		owner.setLayout(null);
		//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	   // int x = (int) ((dimension.getWidth() - owner.getWidth()) / 10);
	    //int y = (int) ((dimension.getHeight() - owner.getHeight()) / 10);
	    owner.setLocation(300,150);
	    owner.setSize(new Dimension(700, 400));
	    owner.setModal(true);
	    owner.setResizable(false);
	    owner.setTitle(string);
	    owner.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    JLabel headerLabel = new JLabel(SConstants.PRPJECT_HEADING,SwingConstants.CENTER);
	    headerLabel.setBounds(0, 0, 694, 30);
        //set font for JLabel
	    headerLabel.setFont(SConstants.FONT_COURRIER_BOLD_18);
	    Border border = BorderFactory.createLineBorder(Color.blue);
	    headerLabel.setBorder(border);
	    owner.add(headerLabel);
	    
	    JLabel footerLabel = new JLabel(SConstants.MY_COMPANY_NAME,SwingConstants.CENTER);
	    footerLabel.setBounds(0, 442, 694, 30);
	    footerLabel.setFont(SConstants.FONT_COURRIER_BOLD_18);
	    footerLabel.setBorder(border);
	    owner.add(footerLabel);
	}
    
    public void setComponenet(JComponent component, Map<String,JComponent> componenetMap)
	{
		component.setName(component.toString());
		componenetMap.put(component.getName(), component);
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
		String str = bom.getBillNumber();
		str= str.replace(" ", "");
		str = str.replace(":", "");
		str= str.replace("-", "");
		File excel = new File("C:\\Bills\\"+str+".xls");
		
		try 
		{
			if(!excel.exists())
				excel.createNewFile();
			new CreateExlFile().CreateBOMExcel(excel,bom);
		} catch (IOException e)
		{
			new PopupDialogs("File Not Found",PopupDialogs.ERROR_MESSAGE);
		}
		
	}
	@SuppressWarnings("deprecation")
	public String getStringValueFromPanelComponent(JPanel Panel,int ComponentPosition)
	{
		if(Panel.getComponent(ComponentPosition)!=null && Panel.getComponent(ComponentPosition) instanceof JLabel)
		{
			JLabel lbl= (JLabel) Panel.getComponent(2);
			return lbl.getText();
		}
		if(Panel.getComponent(ComponentPosition)!=null && Panel.getComponent(ComponentPosition) instanceof JTextField)
		{
			JTextField lbl= (JTextField) Panel.getComponent(2);
			return lbl.getText().isEmpty()?"0.0":lbl.getText();
		}
		if(Panel.getComponent(ComponentPosition)!=null && Panel.getComponent(ComponentPosition) instanceof JComboBox)
		{
			@SuppressWarnings("rawtypes")
			JComboBox lbl= (JComboBox) Panel.getComponent(2);
			return lbl.getSelectedItem().toString().isEmpty()?"":lbl.getSelectedItem().toString();
		}
		if(Panel.getComponent(ComponentPosition)!=null && Panel.getComponent(ComponentPosition) instanceof JSpinner)
		{
			JSpinner lbl= (JSpinner) Panel.getComponent(2);
			if(lbl.getEditor() instanceof JSpinner.DateEditor)
			{
				String [] arr = lbl.getValue().toString().split(" ");
				return arr[3];
			}
				
			return lbl.getValue().toString();
		}
		if(Panel.getComponent(ComponentPosition)!=null && Panel.getComponent(ComponentPosition) instanceof JXDatePicker)
		{
			JXDatePicker lbl= (JXDatePicker) Panel.getComponent(2);
			return lbl.getDate().toLocaleString();
		}
		return "";
		
	}
	public String getStringOfCharacters(String string,int chars)
	{
		int charSize = string.length();
		if(string.length()<chars)
		{
			while(charSize!=chars)
			{
				string=" "+string;
				charSize++;
			}
		}
		return string;
		
	}
	public void applyIntelisense(JTextField text, JDialog owner, final String[] allCustomers)
	{
		@SuppressWarnings("unused")
		AutoSuggestor autoSuggestor = new AutoSuggestor(text, owner, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f)
		{
		    protected boolean wordTyped(String typedWord) {

		        //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
		        ArrayList<String> words = new ArrayList<>();

		       for (String string : allCustomers)
		       {
		    	   words.add(string);
		       }
		       
				if(customersList==null)
		        {
			        customersList  = new ArrayList<String>(new LinkedHashSet<String>(words));
		        }
		        
		        setDictionary(customersList);
		        //addToDictionary("bye");//adds a single word

		        return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
		    }
		};
	}
}
