package ModelXml;
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
        DutyTypesTag.appendChild(familyElement);
        
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
	public String[] getAllDutyTypes()
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
        DutyTypesTag.appendChild(familyElement);
        
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
	public void updateAttributeValue(String uid,String attr, String value)
	{
		NodeList DutyType = doc.getElementsByTagName("DutyType");
        Element emp = null;
        //loop for each employee
        for(int i=0; i<DutyType.getLength();i++)
        {
            emp = (Element) DutyType.item(i);
            String gender = emp.getElementsByTagName("gender").item(0).getFirstChild().getNodeValue();
            if(gender.equalsIgnoreCase("male")){
                //prefix id attribute with M
                emp.setAttribute("id", "M"+emp.getAttribute("id"));
            }else{
                //prefix id attribute with F
                emp.setAttribute("id", "F"+emp.getAttribute("id"));
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
}