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
        familyElement.setAttribute("id",dutyType.getId());
        familyElement.setAttribute("hours",String.valueOf(dutyType.getHours()));
        familyElement.setAttribute("km",String.valueOf(dutyType.getKm()));
        familyElement.setAttribute("dutyTypeString",dutyType.getId());
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
			System.out.println("XML file updated successfully");
			
		} catch (TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public String[] getAllDutyTypStrings()
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
    		            					Integer.parseInt(eElement.getAttribute("hours")),
    		            					Integer.parseInt(eElement.getAttribute("km")),
    		            					Integer.parseInt(eElement.getAttribute("Rate")));
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