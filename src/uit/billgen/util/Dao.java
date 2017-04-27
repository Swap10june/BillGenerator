package uit.billgen.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uit.billgen.beans.ExtraCabObject;
import uit.billgen.beans.Customer2;
import uit.billgen.beans.DutyType;
import uit.billgen.beans.Vehicle;
import uit.billgen.db.SQliteConnection;
import uit.billgen.exceptions.PopupDialogs;


public class Dao
{
	private Connection connection = SQliteConnection.getSQliteConnection("BillGen.db");/*DBConnection.getConnectionInstance();*/
	private static String ADD_DUTY_TYPE = "insert into SDUTYTYPE values(?,?,?,?,?,?,?,?,?,?)";
	private static String ADD_CUSTOMER ="insert into SCUSTOMER values(?,?,?,?,?,?)";
	//private static String EDIT_CUSTOMER ="UPDATE CUSTOMERS SET CUSTOMER_NAME=?,VENDOR_CODE=?,ADDRESS=? WHERE CUSTOMER_NAME=?";
	private static String ADD_VEHICLE ="insert into SVEHICLE values(?,?,?,?,?,?,?,?)";
	private static String ADD_BOM ="insert into SBOM values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	//private static String EDIT_VEHICLE ="UPDATE RATES SET CUSTOMER=?, DUTY_TYPE=?,VEHICLE_TYPE=?,PKG_RATE=?, EXTRA_RATE=?,VENDOR_CODE=?,AC_NOAC=? WHERE CUSTOMER_NAME=? and VEHICLE_TYPE=?;";
	//private static String GET_CUSTOMER_LIST ="select DISTINCT CUSTOMER_NAME FROM CUSTOMERS;";
	//private static String GET_VEHICLE_LIST ="select DISTINCT VEHICLE_TYPE FROM RATES where CUSTOMER=?;";
	//private static String GET_CUSTOMER ="select * FROM CUSTOMERS WHERE CUSTOMER_NAME=?;";
	//private static String GET_VEHICLE ="select * FROM RATES where CUSTOMER=? AND VEHICLE_TYPE=?;";
	
	
	PreparedStatement pStmtDao;
	
	/*public List<String> getCustomerList()
	{		
		List<String> customerList = new ArrayList<String>();
		try {
			 pStmtDao = DBConnection.getConnectionInstance().prepareStatement(GET_CUSTOMER_LIST);
			ResultSet Rset = pStmtDao.executeQuery();
			
			while (Rset.next()) {
				customerList.add(Rset.getString("CUSTOMER_NAME"));
				
			}//pStmtDao.setInt(1,Integer.parseInt(familyId));
			//pStmtDao.setString(2,((SFamily) table).getMembers().get(0).getM_name_e());
			pStmtDao.executeUpdate();
			pStmtDao.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return customerList;
	}*/
	
	/*public List<String> getVehicleList(String customer){		
		List<String> vehicleList =  new ArrayList<String>();
		try {
			pStmtDao = DBConnection.getConnectionInstance().prepareStatement(GET_VEHICLE_LIST);
			pStmtDao.setString(1,customer);
			ResultSet Rset = pStmtDao.executeQuery();
			
			while (Rset.next()) {
				vehicleList.add(Rset.getString("VEHICLE_TYPE"));
				
			}
			
			pStmtDao.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		return vehicleList;
	}*/
	
	public void addCustomer(Customer2 customer2)
	{		
		if(connection!=null)
		{
			try
			{
			
				pStmtDao = connection.prepareStatement(ADD_CUSTOMER);
				pStmtDao.setString(1,String.valueOf(customer2.getUid()));
				pStmtDao.setString(2,customer2.getSuid());
				pStmtDao.setString(3,customer2.getcName());
				pStmtDao.setString(4,customer2.getcAddress());
				pStmtDao.setString(5,customer2.getcVendorCode());
				pStmtDao.setString(6,customer2.getCustomerDepartmentName());
				
				pStmtDao.executeUpdate();
				pStmtDao.close();

			}
			catch (SQLException e)
			{
				new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
			}
		}
	}
	
	public void addVehicles(Vehicle vehicle)
	{		
		if(connection!=null)
		{
			try
			{
				
				pStmtDao = connection.prepareStatement(ADD_VEHICLE);
				pStmtDao.setString(1,String.valueOf(vehicle.getUid()));
				pStmtDao.setString(2,vehicle.getStringUID());
				pStmtDao.setString(3,vehicle.getVehicleName());
				pStmtDao.setString(4,vehicle.getCustomerName());
				pStmtDao.setString(5,vehicle.getVehicleNumber());
				pStmtDao.setString(6,vehicle.getMonthlyRate());
				pStmtDao.setString(7,vehicle.getExtraKmRate());
				pStmtDao.setString(8,vehicle.getExtraHourRate());
				pStmtDao.executeUpdate();
				pStmtDao.close();
				
			} catch (SQLException e)
			{
				
				new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
			}
		}
			
	}
	
/*public void editCustomer(Customer1 customerBean)
{		
		
		try {
			pStmtDao = DBConnection.getConnectionInstance().prepareStatement(EDIT_CUSTOMER);
			pStmtDao.setString(1,customerBean.getName());
			pStmtDao.setString(2,customerBean.getVendorCode());
			pStmtDao.setString(3,customerBean.getAddress());
			pStmtDao.setString(4,customerBean.getOldName());
			pStmtDao.executeUpdate();
			pStmtDao.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
	}*/
/*public void editVehicle(Rate vehicle)
{		
	
	try {
		pStmtDao = DBConnection.getConnectionInstance().prepareStatement(EDIT_VEHICLE);
		pStmtDao.setString(1,vehicle.getCustomerName());
		pStmtDao.setString(2,vehicle.getDutyType());
		pStmtDao.setString(3,vehicle.getVehicleType());
		pStmtDao.setString(4,vehicle.getPackageRate());
		pStmtDao.setString(5,vehicle.getExtraRate());
		pStmtDao.setString(6,vehicle.getVendorCode());
		pStmtDao.setString(7,vehicle.getAcNonAcStatus());
		pStmtDao.setString(8,vehicle.getCustomerName());
		pStmtDao.setString(9,vehicle.getOldVehicleType());
		pStmtDao.executeUpdate();
		pStmtDao.close();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		
}*/

/*public Customer1 getCustomer(String customereName){		
	Customer1 customer = new Customer1();
	try {
		pStmtDao = DBConnection.getConnectionInstance().prepareStatement(GET_CUSTOMER);
		ResultSet Rset = pStmtDao.executeQuery();
		
		while (Rset.next()) {
			customer.setName(Rset.getString("CUSTOMER_NAME"));
			customer.setVendorCode(Rset.getString("VENDOR_CODE"));
			customer.setAddress(Rset.getString("ADDRESS"));
			
		}
		pStmtDao.executeUpdate();
		pStmtDao.close();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
	return customer;
}*/

/*public Rate getVehicle(String customerName,String vehicleName)
{		
	Rate vehicle = new Rate();
	try {
		pStmtDao = DBConnection.getConnectionInstance().prepareStatement(GET_VEHICLE);
		pStmtDao.setString(1,customerName);
		pStmtDao.setString(2,vehicleName);
		ResultSet Rset = pStmtDao.executeQuery();
		
		while (Rset.next()) {
			vehicle.setCustomerName(Rset.getString("CUSTOMER"));
			vehicle.setDutyType(Rset.getString("DUTY_TYPE"));
			vehicle.setVehicleType(Rset.getString("VEHICLE_TYPE"));
			vehicle.setPackageRate(Rset.getString("PKG_RATE"));
			vehicle.setExtraRate(Rset.getString("EXTRA_RATE"));
			vehicle.setVendorCode(Rset.getString("VENDOR_CODE"));
			vehicle.setAcNonAcStatus(Rset.getString("AC_NOAC"));
			
		}
		
		pStmtDao.close();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		
	return vehicle;
}*/
public void addDutyType(DutyType dutyType)
{
	if (connection!=null)
	{
		try
		{
			pStmtDao = connection.prepareStatement(ADD_DUTY_TYPE);
			pStmtDao.setString(1, String.valueOf(dutyType.getUid()));
			pStmtDao.setString(2, dutyType.getId());
			pStmtDao.setString(3, String.valueOf(dutyType.getHours()));
			pStmtDao.setString(4, String.valueOf(dutyType.getKm()));
			pStmtDao.setString(5, String.valueOf(dutyType.getPackageRate()));
			pStmtDao.setString(6, dutyType.getCustomerName());
			pStmtDao.setString(7, dutyType.getVehicleType());
			pStmtDao.setString(8, String.valueOf(dutyType.getExtraKmRate()));
			pStmtDao.setString(9, dutyType.getDutyTypeString());
			pStmtDao.setString(10, dutyType.getAcNonAcType());
			pStmtDao.executeUpdate();

			pStmtDao.close();

		} catch (SQLException e)
		{
			new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
		}
	}
}
public void addBOM(ExtraCabObject bom)
{		
	if(connection!=null)
	{
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try
		{
			
			
		   
		    pStmtDao = connection.prepareStatement(ADD_BOM);
			pStmtDao.setString(1,bom.getBillNumber());
			pStmtDao.setString(2,bom.getCustomerName());
			pStmtDao.setDate(3,new java.sql.Date( df.parse(bom.getBillDate()).getTime()));
			pStmtDao.setDate(4,new java.sql.Date( df.parse(bom.getDateOfTravels()).getTime()));
			pStmtDao.setDate(5,new java.sql.Date( df.parse(bom.getDateOfReturn()).getTime()));
			pStmtDao.setString(6,bom.getTypeOfVehicle());
			pStmtDao.setString(7, bom.getVehicleNumber());
			pStmtDao.setString(8, bom.getVendorCode());
			pStmtDao.setDouble(9,Double.parseDouble(bom.getStartKM()));
			pStmtDao.setDouble(10,Double.parseDouble(bom.getEndKM()));
			pStmtDao.setString(11, bom.getStartTime());
			pStmtDao.setString(12, bom.getEndTime());
			pStmtDao.setDouble(13,Double.parseDouble(bom.getTotalKM()));
			pStmtDao.setString(14,bom.getDutyType());
			pStmtDao.setDouble(15,Double.parseDouble(bom.getPackageKM()));
			pStmtDao.setDouble(16,Double.parseDouble(bom.getPackageRate()));
			pStmtDao.setDouble(17,Double.parseDouble(bom.getPakageAmount()));
			pStmtDao.setDouble(18,Double.parseDouble(bom.getExtraKM()));
			pStmtDao.setDouble(19,Double.parseDouble(bom.getExtraRate()));
			pStmtDao.setDouble(20,Double.parseDouble(bom.getExtraTotalAmount()));
			pStmtDao.setDouble(21,Double.parseDouble(bom.getTollCharges()));
			pStmtDao.setDouble(22,Double.parseDouble(bom.getNightHaltRate()));
			pStmtDao.setDouble(23,Double.parseDouble(bom.getGrandTotal()));
			pStmtDao.setDouble(24,Double.parseDouble(bom.getServiceTaxCarges()));
			pStmtDao.setDouble(25,Double.parseDouble(bom.getTotalWithoutTax()));
			
			
			pStmtDao.executeUpdate();
			pStmtDao.close();

		}
		catch (SQLException e)
		{
			new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	public void editDutyType(DutyType newDutyType)
	{
		removeDutyType(String.valueOf(newDutyType.getUid()));
		addDutyType(newDutyType);
	}
	private void removeDutyType(String duid)
	{
		if(connection!=null)
		{
			try
			{
				String Query = "delete from SDUTYTYPE where DUID='"+duid+"'";
				System.out.println("delete query fired: "+Query);
				connection.createStatement().executeQuery(Query.toUpperCase());
			}
			catch (SQLException e)
			{
				new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
			}
		}
	}
	public void editCustomer(Customer2 customer)
	{
		removeCustomerFromDB(String.valueOf(customer.getUid()));
		addCustomer(customer);
	}
	private void removeCustomerFromDB(String cuid)
	{
		if(connection!=null)
		{
			try
			{
				String Query = "delete from SCUSTOMER where CUID='"+cuid+"'";
				System.out.println("delete query fired: "+Query);
				connection.createStatement().executeQuery(Query.toUpperCase());
			}
			catch (SQLException e)
			{
				new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
			}
		}
	}
	public void editVehicle(Vehicle vehicle)
	{
		removeVehicleFromDB(String.valueOf(vehicle.getUid()));
		addVehicles(vehicle);
	}
	private void removeVehicleFromDB(String vuid)
	{
		if(connection!=null)
		{
			try
			{
				String Query = "delete from SVEHICLE where VUID='"+vuid+"'";
				System.out.println("delete query fired: "+Query);
				connection.createStatement().executeQuery(Query.toUpperCase());
			}
			catch (SQLException e)
			{
				new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
			}
		}
	}
	private  ResultSet querySELECT(String Query) throws ClassNotFoundException, SQLException
    {
      if(connection!=null)
      {
        System.out.println("Select query fired: "+Query);
        ResultSet set=null;
        Statement statement = connection.createStatement();
        set=statement.executeQuery(Query.toUpperCase());
        return set;
      }
	return null;

    }
    @SuppressWarnings("unused")
	private ArrayList<String> getValueListForColumnName(String tableName,String colmnName)
    {
    	try
    	{
    		ArrayList<String> list = new ArrayList<String>();
    		ResultSet set = querySELECT("select "+colmnName+" from "+tableName);
    		if(set!=null)
    		{
    			while(set.next())
	    		{
	    			list.add(set.getString(colmnName));
	    		}
	    		return list;
    		}
    		else
    		{
    			new PopupDialogs("Result is empty", PopupDialogs.INFORMATION_MESSAGE);
    		}
    		
    	} catch (ClassNotFoundException | SQLException e)
    	{
    		new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
    	}
    	return null;
    }
    private Map<String,Vehicle> getAllVehicleUidsFromDB()
    {
		Map<String,Vehicle> uids = null;
		try
		{
			ResultSet rSet = querySELECT("Select * from SVEHICLE");
			if(rSet!=null)
			{
				uids = new HashMap<String, Vehicle>();
				while(rSet.next())
	    		{
					uids.put(rSet.getString("VUID"),
							new Vehicle
							(
									Integer.parseInt(rSet.getString("VUID")),
									rSet.getString("VEHICLENAME"),
									rSet.getString("CUSTOMERNAME"),
									rSet.getString("VEHICLENUMBER"),
									rSet.getString("MRATE"),
									rSet.getString("EXKMRATE"),
									rSet.getString("EXHOURRATE")
							)
							);
	    		}
			}
			else
			{
				new PopupDialogs("Result is empty", PopupDialogs.INFORMATION_MESSAGE);
			}
			
		} catch (ClassNotFoundException |SQLException e)
		{
			new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
		}
		return uids;
    }
    public void updateOrInsertVehicleIntoDB(List<Vehicle> vehicleList)
    {
    	Map<String, Vehicle> presentUids = getAllVehicleUidsFromDB();
    	if(presentUids!=null)
    	{
	    	for (int i = 0; i < vehicleList.size(); i++)
	    	{
				String uid = String.valueOf(vehicleList.get(i).getUid());
				if(presentUids.containsKey(uid))
					continue;
				else
				{
					addVehicles(vehicleList.get(i));
				}
					
			}
    	}
    }
    
    private Map<String,Customer2> getAllCustomerUidsFromDB()
    {
		Map<String,Customer2> uids = null;
		try
		{
			ResultSet rSet = querySELECT("Select * from SCUSTOMER");
			if(rSet!=null)
			{
				uids = new HashMap<String, Customer2>();
				while(rSet.next())
				{
					uids.put(
						rSet.getString("CUID"),
						new Customer2(Integer.parseInt(rSet.getString("CUID")), rSet.getString("CNAME"), rSet.getString("CADDRESS"), rSet.getString("CVCODE"), rSet.getString("CDEPT")));
				}
			}
			else
			{
				new PopupDialogs("Result is empty", PopupDialogs.INFORMATION_MESSAGE);
			}
			
		} catch (ClassNotFoundException |SQLException e)
		{
			new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
		}
		return uids;
    }
    public void updateOrInsertCustomerIntoDB(List<Customer2> customerList)
    {
    	Map<String, Customer2> presentUids = getAllCustomerUidsFromDB();
    	for (int i = 0; i < customerList.size(); i++)
    	{
			String uid = String.valueOf(customerList.get(i).getUid());
			if(presentUids.containsKey(uid))
				continue;
			else
			{
				addCustomer(customerList.get(i));
			}
				
		}
    }
	public void updateOrInsertDutyTypesIntoDB(List<DutyType> dutyTypeList)
	{
		Map<String, DutyType> presentUids = getAllDutyTypesFromDB();
    	for (int i = 0; i < dutyTypeList.size(); i++)
    	{
			String uid = String.valueOf(dutyTypeList.get(i).getUid());
			if(presentUids.containsKey(uid))
				continue;
			else
			{
				addDutyType(dutyTypeList.get(i));
			}
				
		}
	}
	private Map<String, DutyType> getAllDutyTypesFromDB()
	{
		Map<String,DutyType> uids = new HashMap<String, DutyType>();
		try
		{
			ResultSet rSet = querySELECT("Select * from SDUTYTYPE");
			if(rSet!=null)
			{
				while(rSet.next())
	    		{
					uids.put(
							rSet.getString("DUID"),
							new DutyType(Integer.parseInt(rSet.getString("DUID")),
									Integer.parseInt(rSet.getString("HOURS")),
									Integer.parseInt(rSet.getString("KM")),
									Double.parseDouble(rSet.getString("PKGRATE")),
									Double.parseDouble(rSet.getString("EXKMRATE")),
									rSet.getString("CNAME"),
									rSet.getString("VNAME"),
									rSet.getString("ACTYPE")));
	    		}
			}
			else
			{
				new PopupDialogs("Result is empty", PopupDialogs.INFORMATION_MESSAGE);
			}
			
		} catch (ClassNotFoundException |SQLException e)
		{
			new PopupDialogs("DB Error", PopupDialogs.ERROR_MESSAGE);
		}
		return uids;
	}

	public ExtraCabObject getBOM(String string, java.sql.Date date) 
	{
		try 
		{
			ResultSet set = querySELECT("select * from SBOM");
			while(set.next())
			{
				System.out.println(set.getDate("billDate"));
				System.out.println(set.getDate("dateOfTravels"));
				System.out.println(set.getDate("dateOfreturn"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
