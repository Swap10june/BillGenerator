package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.Customer2;
import beans.Vehicle;

public class CustomerDataModel {

	private Document doc;
	private String filePath = "resource/CustomerModel.xml";
	public CustomerDataModel()
	{
		try
	      {
	    	    File xmlFile = new File(filePath);
	    	    if(!xmlFile.exists())
	    	    	xmlFile.createNewFile();
	    	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	    DocumentBuilder dBuilder;
		    	dBuilder = dbFactory.newDocumentBuilder();
		        doc = dBuilder.parse(xmlFile);
		        doc.getDocumentElement().normalize();
	      }
	      catch (IOException | ParserConfigurationException | SAXException e)
	      {
	    	  // TODO Auto-generated catch block
	    	  e.printStackTrace();
	      } 
	}
	public void addCustomer(Customer2 customer)
	{
		NodeList customersTags = doc.getElementsByTagName("Customers");
        Element vehicleTag = (Element) customersTags.item(0);
        Element newCustomerTag = doc.createElement("Customer");
        newCustomerTag.setAttribute("uid",String.valueOf(customer.getUid()));
        newCustomerTag.setAttribute("suid", customer.getSuid());
        newCustomerTag.setAttribute("cName",customer.getcName());
        newCustomerTag.setAttribute("cAdd",customer.getcAddress());
        newCustomerTag.setAttribute("cVCode",customer.getcVendorCode());
        newCustomerTag.setAttribute("cDept",customer.getCustomerDepartmentName());
        vehicleTag.appendChild(newCustomerTag);
       updateXML();
	}
	public String[] getAllCustomers()
	{

    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName("Customer");
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute("suid"));
    		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return values.toArray(new String[values.size()]);
    
	}
	private void updateXML()
	{
		try
        {
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			System.out.println(" Vehicle XML file updated successfully");
			
		} catch (TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Customer2 getCustomer(String suid)
	{
		Customer2 customer2 = null;
   	 try 
   	 {
   		 doc.getDocumentElement().normalize();
   		 NodeList nList = doc.getElementsByTagName("Customer");
   		 for (int temp = 0; temp < nList.getLength(); temp++)
   		 {
   			 Node nNode = nList.item(temp);

   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
   				{
   		            Element eElement = (Element) nNode;
   		            if(eElement.getAttribute("suid").equalsIgnoreCase(suid))
   		            {
   		            	customer2 = new Customer2
   		            			(
   		            					Integer.parseInt(eElement.getAttribute("uid")), 
   		            					eElement.getAttribute("cName"), 
   		            					eElement.getAttribute("cAdd"),
   		            					eElement.getAttribute("cVCode"),
   		            					eElement.getAttribute("cDept")
   		            			);
   		            }
   		           
   		        }
   		    }
   		    }
   			catch (Exception e)
   			{
   				e.printStackTrace();
   		    }
		return customer2;
	}
	public void updateAttributeValue(Vehicle newVehicle)
	{
		NodeList vehicleTags = doc.getElementsByTagName("Vehicle");
        Element tag = null;
        //loop for each employee
        for(int i=0; i<vehicleTags.getLength();i++)
        {
            tag = (Element) vehicleTags.item(i);
            NamedNodeMap abc = tag.getAttributes();
            Node s = abc.getNamedItem("uid");
            if(s!=null &&s.getNodeValue().equalsIgnoreCase(String.valueOf(newVehicle.getUid())))
            {
            	/*for (Map.Entry<String, String> entry : values.entrySet())
            	{*/
            	    //System.out.println(entry.getKey() + "/" + entry.getValue());
            	    tag.setAttribute("uid",String.valueOf(newVehicle.getUid()));
            	    tag.setAttribute("sUid",String.valueOf(newVehicle.getStringUID()));
            	    tag.setAttribute("cName",newVehicle.getCustomerName());
            	    tag.setAttribute("vName",newVehicle.getVehicleName());
            	//}
            	updateXML();
            }
            
        }
	}
}
