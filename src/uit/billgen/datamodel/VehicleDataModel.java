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

import uit.billgen.beans.Vehicle;
import uit.billgen.util.SConstants;

public class VehicleDataModel {

	private Document doc;
	private String filePath = SConstants.VEHICLE_DATA_MODEL_FILE_PATH;
	public VehicleDataModel()
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
	public void addVehicle(Vehicle vehicle)
	{
		NodeList totalNumberOfVehiclesTags = doc.getElementsByTagName(SConstants.VEHICLES_TAG);
        Element vehicleTag = (Element) totalNumberOfVehiclesTags.item(0);
        Element newVehicleTag = doc.createElement(SConstants.VEHICLE_TAG);
        newVehicleTag.setAttribute(SConstants.VEHICLE_NAME_ATTR,vehicle.getVehicleName());
        newVehicleTag.setAttribute(SConstants.VEHICLE_NO_ATTR,vehicle.getVehicleNumber());
        newVehicleTag.setAttribute(SConstants.UID_ATTR,String.valueOf(vehicle.getUid()));
        newVehicleTag.setAttribute(SConstants.SUID_ATTR, vehicle.getStringUID());
        newVehicleTag.setAttribute(SConstants.CUSTOMER_NAME_ATTR,vehicle.getCustomerName());
        newVehicleTag.setAttribute(SConstants.MONTHLY_VEHICLE_RATE_ATTR,vehicle.getMonthlyRate());
        newVehicleTag.setAttribute(SConstants.EXTRA_KM_RATE,vehicle.getExtraKmRate());
        vehicleTag.appendChild(newVehicleTag);
       updateXML();
	}
	public String[] getAllVehicleNames()
	{

    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.VEHICLE_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute(SConstants.VEHICLE_NAME_ATTR));
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
	public Vehicle getVehicle(String suid)
	{
		Vehicle vehicle = null;
   	 try 
   	 {
   		 doc.getDocumentElement().normalize();
   		 NodeList nList = doc.getElementsByTagName(SConstants.VEHICLE_TAG);
   		 for (int temp = 0; temp < nList.getLength(); temp++)
   		 {
   			 Node nNode = nList.item(temp);

   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
   				{
   		            Element eElement = (Element) nNode;
   		            if(eElement.getAttribute(SConstants.SUID_ATTR).equalsIgnoreCase(suid))
   		            {
   		            	vehicle = new Vehicle
   		            			(
   		            					Integer.parseInt(eElement.getAttribute(SConstants.UID_ATTR)),
   		            					eElement.getAttribute(SConstants.VEHICLE_NAME_ATTR),
   		            					eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR),
   		            					eElement.getAttribute(SConstants.VEHICLE_NO_ATTR),
   		            					eElement.getAttribute(SConstants.MONTHLY_VEHICLE_RATE_ATTR),
   		            					eElement.getAttribute(SConstants.EXTRA_KM_RATE));
   		            }
   		           
   		        }
   		    }
   		    }
   			catch (Exception e)
   			{
   				e.printStackTrace();
   		    }
		return vehicle;
	}
	public void updateAttributeValue(Vehicle newVehicle)
	{
		NodeList vehicleTags = doc.getElementsByTagName(SConstants.VEHICLE_TAG);
        Element tag = null;
        //loop for each employee
        for(int i=0; i<vehicleTags.getLength();i++)
        {
            tag = (Element) vehicleTags.item(i);
            NamedNodeMap abc = tag.getAttributes();
            Node s = abc.getNamedItem(SConstants.UID_ATTR);
            if(s!=null &&s.getNodeValue().equalsIgnoreCase(String.valueOf(newVehicle.getUid())))
            {
            	/*for (Map.Entry<String, String> entry : values.entrySet())
            	{*/
            	    //System.out.println(entry.getKey() + "/" + entry.getValue());
            	    tag.setAttribute(SConstants.UID_ATTR,String.valueOf(newVehicle.getUid()));
            	    tag.setAttribute(SConstants.SUID_ATTR,String.valueOf(newVehicle.getStringUID()));
            	    tag.setAttribute(SConstants.CUSTOMER_NAME_ATTR,newVehicle.getCustomerName());
            	    tag.setAttribute(SConstants.VEHICLE_NAME_ATTR,newVehicle.getVehicleName());
            	    tag.setAttribute(SConstants.VEHICLE_NO_ATTR,newVehicle.getVehicleNumber());
            	    tag.setAttribute(SConstants.MONTHLY_VEHICLE_RATE_ATTR, newVehicle.getMonthlyRate());
            	    tag.setAttribute(SConstants.EXTRA_KM_RATE, newVehicle.getExtraKmRate());
            	//}
            	updateXML();
            }
            
        }
	}
	public List<Vehicle> getAllVehicles()
	{
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		
   	 try 
   	 {
   		 doc.getDocumentElement().normalize();
   		 NodeList nList = doc.getElementsByTagName(SConstants.VEHICLE_TAG);
   		 for (int temp = 0; temp < nList.getLength(); temp++)
   		 {
   			 Node nNode = nList.item(temp);

   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
   				{
   		            Element eElement = (Element) nNode;
   		            vehicleList.add(new Vehicle
   		            			(Integer.parseInt(eElement.getAttribute(SConstants.UID_ATTR)),
   		            					eElement.getAttribute(SConstants.VEHICLE_NAME_ATTR),
   		            					eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR),
   		            					eElement.getAttribute(SConstants.VEHICLE_NO_ATTR),
   		            					eElement.getAttribute(SConstants.MONTHLY_VEHICLE_RATE_ATTR),
   		            					eElement.getAttribute(SConstants.EXTRA_KM_RATE)));
   				}
   		           
   		 }
    }
	catch (Exception e)
	{
		e.printStackTrace();
    }
		return vehicleList;
	}
}
