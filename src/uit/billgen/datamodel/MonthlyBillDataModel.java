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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uit.billgen.beans.BillRow;
import uit.billgen.util.SConstants;

public class MonthlyBillDataModel
{
	private Document doc;
	private String filePath = SConstants.MBILL_DM_FILE_PATH;
	
	public MonthlyBillDataModel()
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
    public int addBillTransaction(List<BillRow> billrows)
    {
    	
    	NodeList totalNoOfSecondRootTags = doc.getElementsByTagName(SConstants.MBILL_DM_MBILLS_TAG);
        Element eElement = (Element) totalNoOfSecondRootTags.item(0);
        Element element = doc.createElement(SConstants.BILL_TAG);
        double fAmount = 0.0;
        for (int i = 0; i < billrows.size(); i++)
        {
        	fAmount+=billrows.get(i).getTotalAmount();
		}
        element.setAttribute(SConstants.UID_ATTR,String.valueOf(getAllMonthlyBillsUID().length+1) );
        element.setAttribute(SConstants.TOTAL_FINAL_AMOUNT_ATTR,String.valueOf(fAmount) );
        BillRow row = null;
        Element childElement = null;
        for (int i = 0; i < billrows.size(); i++)
        {
        	row = billrows.get(i);
        	childElement = doc.createElement(SConstants.BILL_ROW_STRING);
            childElement.setAttribute(SConstants.BILL_NO_STRING_ATTR,"MB:"+String.valueOf(getAllMonthlyBillsUID().length+1));
            childElement.setAttribute(SConstants.FROM_DATE_STRING_ATTR, row.getFromDate());
            childElement.setAttribute(SConstants.TO_DATE_STRING_ATTR, row.getToDate());
            childElement.setAttribute(SConstants.VEHICLE_DESC_ATTR, row.getVehicleDesc());
            childElement.setAttribute(SConstants.VEHICLE_NAME_ATTR, row.getVehicle());
            childElement.setAttribute(SConstants.PKG_KM_ATTR, String.valueOf(row.getMonthlyKm()));
            childElement.setAttribute(SConstants.EXTRA_KM_ATTR, String.valueOf(row.getExtraKm()));
            childElement.setAttribute(SConstants.PKG_RATE_ATTR, String.valueOf(row.getRate()));
            childElement.setAttribute(SConstants.PGK_AMOUNT_ATTR, String.valueOf(row.getAmount()));
            childElement.setAttribute(SConstants.EXTRA_KM_RATE, String.valueOf(row.getExtraKmRate()));
            childElement.setAttribute(SConstants.EXTRA_KM_AMOUNT_ATTR, String.valueOf(row.getExtraKmAmpount()));
            childElement.setAttribute(SConstants.TOTAL_AMOUNT_ATTR, String.valueOf(row.getTotalAmount()));
            element.appendChild(childElement);
		}
        
        eElement.appendChild(element);
       
        
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
		return 0;
	}
    public String[] getAllMonthlyBillsUID()
    {
    	List<String> values = new ArrayList<String>();
    	 try 
    	 {
    		 doc.getDocumentElement().normalize();
    		 NodeList nList = doc.getElementsByTagName(SConstants.BILL_TAG);
    		 for (int temp = 0; temp < nList.getLength(); temp++)
    		 {
    			 Node nNode = nList.item(temp);

    		        if (nNode.getNodeType() == Node.ELEMENT_NODE)
    				{
    		            Element eElement = (Element) nNode;
    		            values.add(eElement.getAttribute(SConstants.UID_ATTR));
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
    public int getNoOfTagsUnderTag(String tagName)
    {
    	NodeList list = doc.getElementsByTagName(tagName);
    	return list.getLength();
    }

}