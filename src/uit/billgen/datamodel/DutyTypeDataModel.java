package uit.billgen.datamodel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import uit.billgen.beans.DutyType;
import uit.billgen.constants.SConstants;

public class DutyTypeDataModel
{
	private Document doc;
	private String filePath = SConstants.FILE_DUTY_TYPE_MODEL;
	
	public DutyTypeDataModel()
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
    public void addDutyType(DutyType dutyType)
    {
    	NodeList totalNumberOfFamilysTags = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPES_TAG);
        Element DutyTypesTag = (Element) totalNumberOfFamilysTags.item(0);
        Element familyElement = doc.createElement(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
        familyElement.setAttribute(SConstants.UID_ATTR,String.valueOf(dutyType.getUid()));
        familyElement.setAttribute(SConstants.ID_ATTR,dutyType.getId());
        familyElement.setAttribute(SConstants.HOURS_ATTR,String.valueOf(dutyType.getHours()));
        familyElement.setAttribute(SConstants.KM_ATTR,String.valueOf(dutyType.getKm()));
        familyElement.setAttribute(SConstants.DUTY_TYPE_STRING_ATTR,dutyType.getId());
        familyElement.setAttribute(SConstants.CUSTOMER_NAME_ATTR,dutyType.getCustomerName());
        familyElement.setAttribute(SConstants.VEHICLE_NAME_ATTR,dutyType.getVehicleType());
        familyElement.setAttribute(SConstants.RATE_ATTR,String.valueOf(dutyType.getPackageRate()));
        familyElement.setAttribute(SConstants.EXTRA_KM_RATE,String.valueOf(dutyType.getExtraKmRate()));
        familyElement.setAttribute(SConstants.ACNONAC_ATTR,String.valueOf(dutyType.getAcNonAcType()));
        DutyTypesTag.appendChild(familyElement);
       updateXML();
	}
    public String[] getAllDutyTypStringsFor(String cName, String vName)
    {
    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            if(eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR).equalsIgnoreCase(cName) && eElement.getAttribute(SConstants.VEHICLE_NAME_ATTR).equalsIgnoreCase(vName))
    		            values.add(eElement.getAttribute(SConstants.DUTY_TYPE_STRING_ATTR));
    		            //System.out.println("Staff id : " + eElement.getAttribute(SConstants.ID_ATTR));
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return values.toArray(new String[values.size()]);
    }
    public DutyType getDutyType(String id)
    {
    	DutyType dutyType = null;
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            if(eElement.getAttribute(SConstants.ID_ATTR).equalsIgnoreCase(id))
    		            {
    		            	dutyType = new DutyType(
    		            			
    		            			Integer.parseInt(eElement.getAttribute(SConstants.UID_ATTR)),
    		            			Integer.parseInt(eElement.getAttribute(SConstants.HOURS_ATTR)),
	            					Integer.parseInt(eElement.getAttribute(SConstants.KM_ATTR)),
	            					Double.parseDouble(eElement.getAttribute(SConstants.RATE_ATTR)),
	            					Double.parseDouble(eElement.getAttribute(SConstants.EXTRA_KM_RATE)),
	            					eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR),
	            					eElement.getAttribute(SConstants.VEHICLE_NAME_ATTR),
	            					eElement.getAttribute(SConstants.ACNONAC_ATTR));
    		            }
    		           
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return dutyType;
    }
	public String[] getAllDutyTypeStrings()
	{

    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute(SConstants.ID_ATTR));
    		            //System.out.println("Staff id : " + eElement.getAttribute(SConstants.ID_ATTR));
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return values.toArray(new String[values.size()]);
    
	}

	public void editDutyType(DutyType dutyType)
	{

    	NodeList totalNumberOfFamilysTags = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPES_TAG);
        Element DutyTypesTag = (Element) totalNumberOfFamilysTags.item(0);
        Element familyElement = doc.createElement(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
        familyElement.setAttribute(SConstants.ID_ATTR,dutyType.getId());
        familyElement.setAttribute(SConstants.HOURS_ATTR,String.valueOf(dutyType.getHours()));
        familyElement.setAttribute(SConstants.KM_ATTR,String.valueOf(dutyType.getKm()));
        familyElement.setAttribute(SConstants.DUTY_TYPE_STRING_ATTR,dutyType.getId());
        familyElement.setAttribute(SConstants.CUSTOMER_NAME_ATTR,dutyType.getCustomerName());
        familyElement.setAttribute(SConstants.VEHICLE_NAME_ATTR,dutyType.getVehicleType());
        familyElement.setAttribute(SConstants.RATE_ATTR,String.valueOf(dutyType.getPackageRate()));
        familyElement.setAttribute(SConstants.EXTRA_KM_RATE,String.valueOf(dutyType.getExtraKmRate()));
        familyElement.setAttribute(SConstants.ACNONAC_ATTR,String.valueOf(dutyType.getAcNonAcType()));
        DutyTypesTag.appendChild(familyElement);
        
        updateXML();
	
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
			System.out.println(" Duty Type XML file updated successfully");
			
		} catch (TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateAttributeValue(String uid,Map<String,String> values)
	{

		NodeList DutyType = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
        Element emp = null;
        //loop for each employee
        for(int i=0; i<DutyType.getLength();i++)
        {
            emp = (Element) DutyType.item(i);
            NamedNodeMap abc = emp.getAttributes();
            Node s = abc.getNamedItem(SConstants.UID_ATTR);
            if(s.getNodeValue().equalsIgnoreCase(uid))
            {
            	for (Map.Entry<String, String> entry : values.entrySet())
            	{
            	    //System.out.println(entry.getKey() + "/" + entry.getValue());
            	    emp.setAttribute(entry.getKey(),entry.getValue());
            	}
            	updateXML();
            }
            
        }
    }
	public DutyType getDutyTypeByUID(String uid)
    {
    	DutyType dutyType = null;
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            if(eElement.getAttribute(SConstants.UID_ATTR).equalsIgnoreCase(uid))
    		            {
    		            	dutyType = new DutyType(
    		            			Integer.parseInt(eElement.getAttribute(SConstants.UID_ATTR)),
    		            			Integer.parseInt(eElement.getAttribute(SConstants.HOURS_ATTR)),
	            					Integer.parseInt(eElement.getAttribute(SConstants.KM_ATTR)),
	            					Double.parseDouble(eElement.getAttribute(SConstants.RATE_ATTR)),
	            					Double.parseDouble(eElement.getAttribute(SConstants.EXTRA_KM_RATE)),
	            					eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR),
	            					eElement.getAttribute(SConstants.VEHICLE_NAME_ATTR),
	            					eElement.getAttribute(SConstants.ACNONAC_ATTR));
    		            }
    		           
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return dutyType;
    }
	public void updateAttributeValue(DutyType newDutyType)
	{
		NodeList DutyType = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
        Element emp = null;
        //loop for each employee
        for(int i=0; i<DutyType.getLength();i++)
        {
            emp = (Element) DutyType.item(i);
            NamedNodeMap abc = emp.getAttributes();
            Node s = abc.getNamedItem(SConstants.UID_ATTR);
            if(s!=null &&s.getNodeValue().equalsIgnoreCase(String.valueOf(newDutyType.getUid())))
            {
            	/*for (Map.Entry<String, String> entry : values.entrySet())
            	{*/
            	    //System.out.println(entry.getKey() + "/" + entry.getValue());
            	    emp.setAttribute(SConstants.KM_ATTR,String.valueOf(newDutyType.getKm()));
            	    emp.setAttribute(SConstants.RATE_ATTR,String.valueOf(newDutyType.getPackageRate()));
            	    emp.setAttribute(SConstants.EXTRA_KM_RATE,String.valueOf(newDutyType.getExtraKmRate()));
            	    emp.setAttribute(SConstants.CUSTOMER_NAME_ATTR,String.valueOf(newDutyType.getCustomerName()));
            	    emp.setAttribute(SConstants.DUTY_TYPE_STRING_ATTR,String.valueOf(newDutyType.getDutyTypeString()));
            	    emp.setAttribute(SConstants.HOURS_ATTR,String.valueOf(newDutyType.getHours()));
            	    emp.setAttribute(SConstants.ID_ATTR,String.valueOf(newDutyType.getId()));
            	    emp.setAttribute(SConstants.VEHICLE_NAME_ATTR,String.valueOf(newDutyType.getVehicleType()));
            	    emp.setAttribute(SConstants.ACNONAC_ATTR,String.valueOf(newDutyType.getAcNonAcType()));
            	//}
            	updateXML();
            }
            
        }
	}
	public int noOfDutyTypes()
	{

    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute(SConstants.ID_ATTR));
    		            //System.out.println("Staff id : " + eElement.getAttribute(SConstants.ID_ATTR));
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return values.size();
    
	}
	public List<DutyType> getAllDutyTypes()
	{
		List<DutyType> dutyTypeList = new ArrayList<DutyType>();
   	 try 
   	 {
   		 doc.getDocumentElement().normalize();
   		 NodeList nList = doc.getElementsByTagName(SConstants.DUTY_TYPE_DATA_MODEL_DUTYTYPE_TAG);
   		 for (int temp = 0; temp < nList.getLength(); temp++)
   		 {
   			 Node nNode = nList.item(temp);

   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
   				{
   		            Element eElement = (Element) nNode;
   		            dutyTypeList.add(new DutyType
   		            		(
   		            			Integer.parseInt(eElement.getAttribute(SConstants.UID_ATTR)),
   		            			Integer.parseInt(eElement.getAttribute(SConstants.HOURS_ATTR)),
	            					Integer.parseInt(eElement.getAttribute(SConstants.KM_ATTR)),
	            					Double.parseDouble(eElement.getAttribute(SConstants.RATE_ATTR)),
	            					Double.parseDouble(eElement.getAttribute(SConstants.EXTRA_KM_RATE)),
	            					eElement.getAttribute(SConstants.CUSTOMER_NAME_ATTR),
	            					eElement.getAttribute(SConstants.VEHICLE_NAME_ATTR),
	            					eElement.getAttribute(SConstants.ACNONAC_ATTR))
   		            		);
   		         }
   		           
   		 }
   	 }
	catch (Exception e)
	{
		e.printStackTrace();
    }
		return dutyTypeList;
	}
}