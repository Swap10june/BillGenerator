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

import uit.billgen.beans.CabObject;
import uit.billgen.constants.Errors;
import uit.billgen.constants.SConstants;
import uit.billgen.exceptions.BillGenException;

public class BillsDataModel
{
	private Document doc;
	private String filePath = SConstants.FILE_BILLS;
	
	public BillsDataModel() throws BillGenException 
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
	      catch (IOException e)
	      {
	    	  throw new BillGenException(Errors.IO_EXCEPTION_STRING);
	      } catch (ParserConfigurationException e)
		  {
	    	  throw new BillGenException(e.getMessage());
		  } catch (SAXException e)
		  {
			  throw new BillGenException(e.getMessage());
		  } 
	}
	 public void addBillTransaction(CabObject extraCab)
	    {
	    	NodeList totalNoOfSecondRootTags = doc.getElementsByTagName(SConstants.TBILLS_TAG);
	        Element eElement = (Element) totalNoOfSecondRootTags.item(0);
	        Element element = doc.createElement(SConstants.BILL_TAG);
	        
	        element.setAttribute(SConstants.BILL_TYPE_ATTR,extraCab.getBillType());
	 
	        element.setAttribute(SConstants.BILL_NO_STRING_ATTR,extraCab.getBillNumber());
	        element.setAttribute(SConstants.CUSTOMER_NAME_ATTR,extraCab.getCustomerName());
	        element.setAttribute(SConstants.BILL_DATE_ATTR,extraCab.getBillDate());
	        element.setAttribute(SConstants.CLIENT_MOB_NO_ATTR,extraCab.getContactNumber());
	        element.setAttribute(SConstants.CLIENT_EMAIL_ATTR,extraCab.getEmail());
	        
	        element.setAttribute(SConstants.TRAVEL_STRAT_DATE, extraCab.getDateOfTravels());
	        element.setAttribute(SConstants.TRAVEL_CLOSE_DATE,extraCab.getDateOfReturn());
	        
	        element.setAttribute(SConstants.VEHICLE_NAME_ATTR,extraCab.getTypeOfVehicle());
	        element.setAttribute(SConstants.VEHICLE_NO_ATTR,extraCab.getVehicleNumber());
	        element.setAttribute(SConstants.CUSTOMER_DATA_MODEL_CUST_CODE_ATTR,extraCab.getVendorCode());
	        
	        element.setAttribute(SConstants.PKG_TYPE_ATTR,extraCab.getPackageType());
	        element.setAttribute(SConstants.START_KM_ATTR, extraCab.getStartKM());
	        element.setAttribute(SConstants.END_KM_ATTR,extraCab.getEndKM());
	        element.setAttribute(SConstants.TOTAL_KM_ATTR,extraCab.getTotalKM());
	        element.setAttribute(SConstants.START_TIME_ATTR,extraCab.getStartTime());
	        element.setAttribute(SConstants.END_TIME_ATTR,extraCab.getEndTime());
	       
	        element.setAttribute(SConstants.DUTY_TYPE_ATTR,extraCab.getDutyType());
	        element.setAttribute(SConstants.PKG_KM_ATTR,extraCab.getPackageKM());
	        element.setAttribute(SConstants.PGK_AMOUNT_ATTR, extraCab.getPakageAmount());
	        element.setAttribute(SConstants.PKG_RATE_ATTR,extraCab.getPackageRate());
	        
	        element.setAttribute(SConstants.EXTRA_KM_ATTR,extraCab.getExtraKM());
	        element.setAttribute(SConstants.EXTRA_KM_RATE,extraCab.getExtraRate());
	        element.setAttribute(SConstants.EXTRA_KM_AMOUNT_ATTR,extraCab.getExtraTotalAmount());
	        
	        element.setAttribute(SConstants.EXTRA_TIME_HOURS_ATTR,extraCab.getExtraTimeHours());
	        element.setAttribute(SConstants.EXTRA_HOUR_RATE,extraCab.getExtraTimeHoursRate());
	        element.setAttribute(SConstants.EXTRA_HOUR_AMOUNT,extraCab.getExtraHourAmount());
	        
	        
	        element.setAttribute(SConstants.TOLL_CHARG_ATTR, extraCab.getTollCharges());
	        element.setAttribute(SConstants.MONTHLY_EXTRA_CHARGES_ATTR,extraCab.getMonthlyExtraCharges());
	        element.setAttribute(SConstants.SERVICE_TAX_ATTR,extraCab.getServiceTaxCarges());
	        element.setAttribute(SConstants.NIGHT_HALT_ATTR,extraCab.getNightHaltRate());
	        
	        element.setAttribute(SConstants.GRAND_TOTAL, extraCab.getGrandTotal());
	        
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
