package uit.billgen.datamodel;

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

import uit.billgen.beans.Customer2;
import uit.billgen.util.SConstants;

public class CustomerDataModel {

	private Document doc;
	private String filePath = SConstants.CUSTOMER_DATA_MODEL_FILE_PATH;
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
		NodeList customersTags = doc.getElementsByTagName(SConstants.CUSTOMER_DATA_MODEL_CUSTOMERS_TAG);
        Element vehicleTag = (Element) customersTags.item(0);
        Element newCustomerTag = doc.createElement(SConstants.CUSTOMER_DATA_MODEL_CUSTOMER_TAG);
        newCustomerTag.setAttribute(SConstants.UID_ATTR,String.valueOf(customer.getUid()));
        newCustomerTag.setAttribute(SConstants.SUID_ATTR, customer.getSuid());
        newCustomerTag.setAttribute(SConstants.CUSTOMER_NAME_ATTR,customer.getcName());
        newCustomerTag.setAttribute(SConstants.CUSTOMER_ADD_ATTR,customer.getcAddress());
        newCustomerTag.setAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_CODE_ATTR,customer.getcVendorCode());
        newCustomerTag.setAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_DEPT_ATTR,customer.getCustomerDepartmentName());
        vehicleTag.appendChild(newCustomerTag);
       updateXML();
	}
	public String[] getAllCustomerNames()
	{

    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.CUSTOMER_DATA_MODEL_CUSTOMER_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute(SConstants.SUID_ATTR));
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
   		 NodeList nList = doc.getElementsByTagName(SConstants.CUSTOMER_DATA_MODEL_CUSTOMER_TAG);
   		 for (int temp = 0; temp < nList.getLength(); temp++)
   		 {
   			 Node nNode = nList.item(temp);

   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
   				{
   		            Element eElement = (Element) nNode;
   		            if(eElement.getAttribute(SConstants.SUID_ATTR).equalsIgnoreCase(suid))
   		            {
   		            	customer2 = new Customer2
   		            			(
   		            					Integer.parseInt(eElement.getAttribute(SConstants.UID_ATTR)), 
   		            					eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR), 
   		            					eElement.getAttribute(SConstants.CUSTOMER_ADD_ATTR),
   		            					eElement.getAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_CODE_ATTR),
   		            					eElement.getAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_DEPT_ATTR)
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
	public void updateAttributeValue(Customer2 newCustomer)
	{
		NodeList customerTags = doc.getElementsByTagName(SConstants.CUSTOMER_DATA_MODEL_CUSTOMER_TAG);
        Element tag = null;
        //loop for each employee
        for(int i=0; i<customerTags.getLength();i++)
        {
            tag = (Element) customerTags.item(i);
            NamedNodeMap abc = tag.getAttributes();
            Node s = abc.getNamedItem(SConstants.UID_ATTR);
            if(s!=null &&s.getNodeValue().equalsIgnoreCase(String.valueOf(newCustomer.getUid())))
            {
            	/*for (Map.Entry<String, String> entry : values.entrySet())
            	{*/
            	    //System.out.println(entry.getKey() + "/" + entry.getValue());
            	    tag.setAttribute(SConstants.UID_ATTR,String.valueOf(newCustomer.getUid()));
            	    tag.setAttribute(SConstants.SUID_ATTR,String.valueOf(newCustomer.getSuid()));
            	    tag.setAttribute(SConstants.CUSTOMER_NAME_ATTR,newCustomer.getcName());
            	    tag.setAttribute(SConstants.CUSTOMER_ADD_ATTR,newCustomer.getcAddress());
            	    tag.setAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_CODE_ATTR,newCustomer.getcVendorCode());
            	    tag.setAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_DEPT_ATTR,newCustomer.getCustomerDepartmentName());
            	//}
            	updateXML();
            }
            
        }
	}
	public List<Customer2> getAllCustomers()
	{

		List<Customer2> cList = new ArrayList<Customer2>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
       		 NodeList nList = doc.getElementsByTagName(SConstants.CUSTOMER_DATA_MODEL_CUSTOMER_TAG);
       		 for (int temp = 0; temp < nList.getLength(); temp++)
       		 {
       			 Node nNode = nList.item(temp);

       		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
       				{
       		            Element eElement = (Element) nNode;
       		            cList.add(new Customer2 (
       		            					Integer.parseInt(eElement.getAttribute(SConstants.UID_ATTR)),
       		            					eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR),
       		            					eElement.getAttribute(SConstants.CUSTOMER_ADD_ATTR),
       		            					eElement.getAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_CODE_ATTR),
       		            					eElement.getAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_DEPT_ATTR)
       		            			));
       		        }
       		           
       		 }
       	}
		catch (Exception e)
		{
			e.printStackTrace();
	    }
		return cList;
    
	}
	public ArrayList<String> getAllCustomersList()
	{
		ArrayList<String> cList = new ArrayList<String>();
   	 try 
   	 {
   		 doc.getDocumentElement().normalize();
      		 NodeList nList = doc.getElementsByTagName(SConstants.CUSTOMER_DATA_MODEL_CUSTOMER_TAG);
      		 for (int temp = 0; temp < nList.getLength(); temp++)
      		 {
      			 Node nNode = nList.item(temp);

      		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
      				{
      		            Element eElement = (Element) nNode;
      		            cList.add(eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR));
      		        }
      		           
      		 }
      	}
		catch (Exception e)
		{
			e.printStackTrace();
	    }
		return cList;
	}
}
