package uit.billgen.datamodel;
import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import uit.billgen.beans.BOM;
import uit.billgen.util.SConstants;

public class TelcoBillDataModel
{
	private Document doc;
	private String filePath = SConstants.TELCO_BILL_FILE_PATH;
	
	public TelcoBillDataModel()
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
    public void addBillTransaction(BOM bOM)
    {
    	NodeList totalNoOfSecondRootTags = doc.getElementsByTagName(SConstants.TBILLS_TAG);
        Element eElement = (Element) totalNoOfSecondRootTags.item(0);
        Element element = doc.createElement(SConstants.BILL_TAG);
        
        element.setAttribute(SConstants.BILL_NO_STRING_ATTR,bOM.getBillNumber());
       // element.setAttribute(SConstants.ADMIN_ATTR,bOM.getAdmin());
        element.setAttribute(SConstants.CUSTOMER_NAME_ATTR,bOM.getCustomerName());
        element.setAttribute(SConstants.BILL_DATE_ATTR,bOM.getBillDate());
        element.setAttribute(SConstants.CLIENT_MOB_NO_ATTR,bOM.getContactNumber());
        element.setAttribute(SConstants.CLIENT_EMAIL_ATTR,bOM.getEmail());
        element.setAttribute(SConstants.TRAVEL_STRAT_DATE, bOM.getDateOfTravels());
        element.setAttribute(SConstants.TRAVEL_CLOSE_DATE,bOM.getDateOfReturn());
        element.setAttribute(SConstants.VEHICLE_NAME_ATTR,bOM.getTypeOfVehicle());
        element.setAttribute(SConstants.VEHICLE_NO_ATTR,bOM.getVehicleNumber());
        //element.setAttribute("customerName",bOM.getCustomerName());
        element.setAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_CODE_ATTR,bOM.getVendorCode());
        element.setAttribute(SConstants.PKG_TYPE_ATTR,bOM.getPackageType());
        element.setAttribute(SConstants.START_KM_ATTR, bOM.getStartKM());
        element.setAttribute(SConstants.END_KM_ATTR,bOM.getEndKM());
        element.setAttribute(SConstants.TOTAL_KM_ATTR,bOM.getTotalKM());
        element.setAttribute(SConstants.START_TIME_ATTR,bOM.getStartTime());
        element.setAttribute(SConstants.END_TIME_ATTR,bOM.getEndTime());
        element.setAttribute(SConstants.DUTY_TYPE_ATTR,bOM.getDutyType());
        element.setAttribute(SConstants.PKG_KM_ATTR,bOM.getPackageKM());
        element.setAttribute(SConstants.PGK_AMOUNT_ATTR, bOM.getPakageAmount());
        //element.setAttribute(SConstants.PKG_TOTAL_AMOUNT,bOM.getPkgtotalAmount());
        element.setAttribute(SConstants.PKG_RATE_ATTR,bOM.getPackageRate());
        element.setAttribute(SConstants.EXTRA_KM_ATTR,bOM.getExtraKM());
        element.setAttribute(SConstants.EXTRA_KM_RATE,bOM.getExtraRate());
        element.setAttribute(SConstants.EXTRA_KM_AMOUNT_ATTR,bOM.getExtraTotalAmount());
        //element.setAttribute(SConstants.EXTRA_TIME_HOURS_ATTR,bOM.getExtraTimeHours());
        element.setAttribute(SConstants.TOLL_CHARG_ATTR, bOM.getTollCharges());
        element.setAttribute(SConstants.MONTHLY_EXTRA_CHARGES_ATTR,bOM.getMonthlyExtraCharges());
        element.setAttribute(SConstants.SERVICE_TAX_ATTR,bOM.getServiceTaxCarges());
        element.setAttribute(SConstants.NIGHT_HALT_ATTR,bOM.getNightHaltRate());
        element.setAttribute(SConstants.GRAND_TOTAL, bOM.getGrandTotal());
        
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
	}
    public int getNoOfTags(String tagName)
    {
    	NodeList list = doc.getElementsByTagName(tagName);
    	return list.getLength();
    }

}