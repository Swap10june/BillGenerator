package model;
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

import beans.DutyType;

public class DutyTypeDataModel
{
	private Document doc;
	private String filePath = "resource/DutyTypeModel.xml";
	
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
    	NodeList totalNumberOfFamilysTags = doc.getElementsByTagName("DutyTypes");
        Element DutyTypesTag = (Element) totalNumberOfFamilysTags.item(0);
        Element familyElement = doc.createElement("DutyType");
        familyElement.setAttribute("uid",String.valueOf(dutyType.getUid()));
        familyElement.setAttribute("id",dutyType.getId());
        familyElement.setAttribute("hours",String.valueOf(dutyType.getHours()));
        familyElement.setAttribute("km",String.valueOf(dutyType.getKm()));
        familyElement.setAttribute("dutyTypeString",dutyType.getId());
        familyElement.setAttribute("cName",dutyType.getCustomerName());
        familyElement.setAttribute("vName",dutyType.getVehicleType());
        familyElement.setAttribute("Rate",String.valueOf(dutyType.getPackageRate()));
        familyElement.setAttribute("eKmRate",String.valueOf(dutyType.getExtraKmRate()));
        DutyTypesTag.appendChild(familyElement);
       updateXML();
	}
    public String[] getAllDutyTypStringsFor(String cName, String vName)
    {
    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName("DutyType");
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            if(eElement.getAttribute("cName").equalsIgnoreCase(cName) && eElement.getAttribute("vName").equalsIgnoreCase(vName))
    		            values.add(eElement.getAttribute("id"));
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
    public DutyType getDutyType(String id)
    {
    	DutyType dutyType = null;
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName("DutyType");
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            if(eElement.getAttribute("id").equalsIgnoreCase(id))
    		            {
    		            	dutyType = new DutyType(
    		            			
    		            			Integer.parseInt(eElement.getAttribute("uid")),
    		            			Integer.parseInt(eElement.getAttribute("hours")),
	            					Integer.parseInt(eElement.getAttribute("km")),
	            					Double.parseDouble(eElement.getAttribute("Rate")),
	            					Double.parseDouble(eElement.getAttribute("eKmRate")),
	            					eElement.getAttribute("cName"),
	            					eElement.getAttribute("vName"));
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
    		 NodeList nList = doc.getElementsByTagName("DutyType");
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute("id"));
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

	public void editDutyType(DutyType dutyType)
	{

    	NodeList totalNumberOfFamilysTags = doc.getElementsByTagName("DutyTypes");
        Element DutyTypesTag = (Element) totalNumberOfFamilysTags.item(0);
        Element familyElement = doc.createElement("DutyType");
        familyElement.setAttribute("id",dutyType.getId());
        familyElement.setAttribute("hours",String.valueOf(dutyType.getHours()));
        familyElement.setAttribute("km",String.valueOf(dutyType.getKm()));
        familyElement.setAttribute("dutyTypeString",dutyType.getId());
        familyElement.setAttribute("cName",dutyType.getCustomerName());
        familyElement.setAttribute("vName",dutyType.getVehicleType());
        familyElement.setAttribute("Rate",String.valueOf(dutyType.getPackageRate()));
        familyElement.setAttribute("eKmRate",String.valueOf(dutyType.getExtraKmRate()));
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

		NodeList DutyType = doc.getElementsByTagName("DutyType");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<DutyType.getLength();i++)
        {
            emp = (Element) DutyType.item(i);
            NamedNodeMap abc = emp.getAttributes();
            Node s = abc.getNamedItem("uid");
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
    		 NodeList nList = doc.getElementsByTagName("DutyType");
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            if(eElement.getAttribute("uid").equalsIgnoreCase(uid))
    		            {
    		            	dutyType = new DutyType(
    		            			Integer.parseInt(eElement.getAttribute("uid")),
    		            			Integer.parseInt(eElement.getAttribute("hours")),
	            					Integer.parseInt(eElement.getAttribute("km")),
	            					Double.parseDouble(eElement.getAttribute("Rate")),
	            					Double.parseDouble(eElement.getAttribute("eKmRate")),
	            					eElement.getAttribute("cName"),
	            					eElement.getAttribute("vName"));
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
		NodeList DutyType = doc.getElementsByTagName("DutyType");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<DutyType.getLength();i++)
        {
            emp = (Element) DutyType.item(i);
            NamedNodeMap abc = emp.getAttributes();
            Node s = abc.getNamedItem("uid");
            if(s!=null &&s.getNodeValue().equalsIgnoreCase(String.valueOf(newDutyType.getUid())))
            {
            	/*for (Map.Entry<String, String> entry : values.entrySet())
            	{*/
            	    //System.out.println(entry.getKey() + "/" + entry.getValue());
            	    emp.setAttribute("km",String.valueOf(newDutyType.getKm()));
            	    emp.setAttribute("Rate",String.valueOf(newDutyType.getPackageRate()));
            	    emp.setAttribute("eKmRate",String.valueOf(newDutyType.getExtraKmRate()));
            	    emp.setAttribute("cName",String.valueOf(newDutyType.getCustomerName()));
            	    emp.setAttribute("dutyTypeString",String.valueOf(newDutyType.getDutyTypeString()));
            	    emp.setAttribute("hours",String.valueOf(newDutyType.getHours()));
            	    emp.setAttribute("id",String.valueOf(newDutyType.getId()));
            	    emp.setAttribute("vName",String.valueOf(newDutyType.getVehicleType()));
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
    		 NodeList nList = doc.getElementsByTagName("DutyType");
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute("id"));
    		            //System.out.println("Staff id : " + eElement.getAttribute("id"));
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
   		 NodeList nList = doc.getElementsByTagName("DutyType");
   		 for (int temp = 0; temp < nList.getLength(); temp++)
   		 {
   			 Node nNode = nList.item(temp);

   		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
   				{
   		            Element eElement = (Element) nNode;
   		            dutyTypeList.add(new DutyType
   		            		(
   		            			Integer.parseInt(eElement.getAttribute("uid")),
   		            			Integer.parseInt(eElement.getAttribute("hours")),
	            					Integer.parseInt(eElement.getAttribute("km")),
	            					Double.parseDouble(eElement.getAttribute("Rate")),
	            					Double.parseDouble(eElement.getAttribute("eKmRate")),
	            					eElement.getAttribute("cName"),
	            					eElement.getAttribute("vName"))
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