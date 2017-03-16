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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.BOM;
import beans.BillRow;

public class MonthlyBillDataModel
{
	private Document doc;
	private String filePath = "resource/MonthlyBillModel.xml";
	
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
    public void addBillTransaction(List<BillRow> billrows)
    {
    	
    	NodeList totalNoOfSecondRootTags = doc.getElementsByTagName("MBills");
        Element eElement = (Element) totalNoOfSecondRootTags.item(0);
        Element element = doc.createElement("Bill");
        
        BillRow row = null;
        Element childElement = null;
        for (int i = 0; i < billrows.size(); i++)
        {
        	row = billrows.get(i);
        	childElement = doc.createElement("BillRow");
            childElement.setAttribute("billNumber",String.valueOf(row.getAmount()));
            //element.setAttribute("admin",bOM.getAdmin());
            element.appendChild(childElement);
		}
        
        eElement.appendChild(element);
        //eElement.appendChild(childElement);
        /*element.setAttribute("billNumber",bOM.getBillNumber());
        element.setAttribute("admin",bOM.getAdmin());
        element.setAttribute("customerName",bOM.getCustomerName());
        element.setAttribute("billDate",bOM.getBillDate());
        element.setAttribute("contactNumber",bOM.getContactNumber());
        element.setAttribute("email",bOM.getEmail());
        element.setAttribute("dateOfTravels", bOM.getDateOfTravels());
        element.setAttribute("dateOfreturn",bOM.getDateOfReturn());
        element.setAttribute("typeOfVehicle",bOM.getTypeOfVehicle());
        element.setAttribute("vehicleNumber",bOM.getVehicleNumber());
        element.setAttribute("customerName",bOM.getCustomerName());
        element.setAttribute("vendorCode",bOM.getVendorCode());
        element.setAttribute("packageType",bOM.getPackageType());
        element.setAttribute("startKM", bOM.getStartKM());
        element.setAttribute("endKM",bOM.getEndKM());
        element.setAttribute("totalKM",bOM.getTotalKM());
        element.setAttribute("startTime",bOM.getStartTime());
        element.setAttribute("endTime",bOM.getEndTime());
        element.setAttribute("dutyType",bOM.getDutyType());
        element.setAttribute("packageKM",bOM.getPackageKM());
        element.setAttribute("pakageAmount", bOM.getPakageAmount());
        element.setAttribute("pkgtotalAmount",bOM.getPkgtotalAmount());
        element.setAttribute("packageRate",bOM.getPackageRate());
        element.setAttribute("extraKM",bOM.getExtraKM());
        element.setAttribute("extraRate",bOM.getExtraRate());
        element.setAttribute("extraTotalAmount",bOM.getExtraTotalAmount());
        element.setAttribute("extraTimeHours",bOM.getExtraTimeHours());
        element.setAttribute("tollCharges", bOM.getTollCharges());
        element.setAttribute("monthlyExtraCharges",bOM.getMonthlyExtraCharges());
        element.setAttribute("serviceTaxCarges",bOM.getServiceTaxCarges());
        element.setAttribute("nightHaltRate",bOM.getNightHaltRate());
        element.setAttribute("grandTotal", bOM.getGrandTotal());
        */
        
        
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
    /*public DutyType getDutyType(String id)
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
    		            	dutyType = new DutyType
    		            			(
    		            					Integer.parseInt(eElement.getAttribute("hours")),
    		            					Integer.parseInt(eElement.getAttribute("km")),
    		            					Integer.parseInt(eElement.getAttribute("Rate")),
    		            					eElement.getAttribute("CName"),
    		            					eElement.getAttribute("VName")
    		            					
    		            			);
    		            }
    		           
    		        }
    		    }
    		    }
    			catch (Exception e)
    			{
    				e.printStackTrace();
    		    }
		return dutyType;
    }*/
    public int getNoOfTagsUnderTag(String tagName)
    {
    	NodeList list = doc.getElementsByTagName(tagName);
    	return list.getLength();
    }

}