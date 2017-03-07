package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Customer1;
import beans.Customer2;
import beans.DutyType;
import beans.Rate;
import beans.Vehicle;


public class Dao
{
	
	private static String ADD_DUTY_TYPE = "insert into SDUTYTYPE values(?,?,?,?,?,?,?,?,?)";
	private static String ADD_CUSTOMER ="insert into SCUSTOMER values(?,?,?,?,?,?)";
	private static String EDIT_CUSTOMER ="UPDATE CUSTOMERS SET CUSTOMER_NAME=?,VENDOR_CODE=?,ADDRESS=? WHERE CUSTOMER_NAME=?";
	private static String ADD_VEHICLE ="insert into SVEHICLE values(?,?,?,?,?)";
	private static String EDIT_VEHICLE ="UPDATE RATES SET CUSTOMER=?, DUTY_TYPE=?,VEHICLE_TYPE=?,PKG_RATE=?, EXTRA_RATE=?,VENDOR_CODE=?,AC_NOAC=? WHERE CUSTOMER_NAME=? and VEHICLE_TYPE=?;";
	private static String GET_CUSTOMER_LIST ="select DISTINCT CUSTOMER_NAME FROM CUSTOMERS;";
	private static String GET_VEHICLE_LIST ="select DISTINCT VEHICLE_TYPE FROM RATES where CUSTOMER=?;";
	private static String GET_CUSTOMER ="select * FROM CUSTOMERS WHERE CUSTOMER_NAME=?;";
	private static String GET_VEHICLE ="select * FROM RATES where CUSTOMER=? AND VEHICLE_TYPE=?;";
	
	
	PreparedStatement pStmtDao;
	
	public List<String> getCustomerList()
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
	}
	
	public List<String> getVehicleList(String customer){		
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
	}
	
	public void addCustomers(Customer2 customer2)
	{		
		
		try {
			pStmtDao = DBConnection.getConnectionInstance().prepareStatement(ADD_CUSTOMER);
			pStmtDao.setString(1,String.valueOf(customer2.getUid()));
			pStmtDao.setString(2,customer2.getSuid());
			pStmtDao.setString(3,customer2.getcName());
			pStmtDao.setString(4,customer2.getcAddress());
			pStmtDao.setString(5,customer2.getcVendorCode());
			pStmtDao.setString(6,customer2.getCustomerDepartmentName());
			
			pStmtDao.executeUpdate();
			pStmtDao.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void addVehicles(Vehicle vehicle)
	{		
		
		try {
			pStmtDao = DBConnection.getConnectionInstance().prepareStatement(ADD_VEHICLE);
			pStmtDao.setString(1,String.valueOf(vehicle.getUid()));
			pStmtDao.setString(2,vehicle.getStringUID());
			pStmtDao.setString(3,vehicle.getVehicleName());
			pStmtDao.setString(4,vehicle.getCustomerName());
			pStmtDao.setString(5,vehicle.getVehicleNumber());
			pStmtDao.executeUpdate();
			pStmtDao.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
	}
	
public void editCustomer(Customer1 customerBean)
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
			
	}
public void editVehicle(Rate vehicle)
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
		
}

public Customer1 getCustomer(String customereName){		
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
}

public Rate getVehicle(String customerName,String vehicleName)
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
}
public void addDutyType(DutyType dutyType)
{
	try
	{
		pStmtDao = DBConnection.getConnectionInstance().prepareStatement(ADD_DUTY_TYPE);
		pStmtDao.setString(1,String.valueOf(dutyType.getUid()));
		pStmtDao.setString(2,dutyType.getId());
		pStmtDao.setString(3,String.valueOf(dutyType.getHours()));
		pStmtDao.setString(4,String.valueOf(dutyType.getKm()));
		pStmtDao.setString(5,String.valueOf(dutyType.getPackageRate()));
		pStmtDao.setString(6,dutyType.getCustomerName());
		pStmtDao.setString(7,dutyType.getVehicleType());
		pStmtDao.setString(8,String.valueOf(dutyType.getExtraKmRate()));
		pStmtDao.setString(9,dutyType.getDutyTypeString());
		pStmtDao.executeUpdate();
		
		pStmtDao.close();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}

	public void editDutyType(DutyType newDutyType)
	{
		removeDutyType(String.valueOf(newDutyType.getUid()));
		addDutyType(newDutyType);
	}
	private void removeDutyType(String duid)
	{
		try
		{
			String Query = "delete from SDUTYTYPE where DUID='"+duid+"'";
			System.out.println("delete query fired: "+Query);
			DBConnection.getConnectionInstance().createStatement().executeQuery(Query.toUpperCase());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private  ResultSet querySELECT(String Query) throws ClassNotFoundException, SQLException
    {
      
        System.out.println("Select query fired: "+Query);
        ResultSet set=null;
        Statement statement = DBConnection.getConnectionInstance().createStatement();
        set=statement.executeQuery(Query.toUpperCase());
        return set;     

    }
    @SuppressWarnings("unused")
	private ArrayList<String> getValueListForColumnName(String tableName,String colmnName)
    {
    	try
    	{
    		ArrayList<String> list = new ArrayList<String>();
    		ResultSet set = querySELECT("select "+colmnName+" from "+tableName);
    		while(set.next())
    		{
    			list.add(set.getString(colmnName));
    		}
    		return list;
    		
    	} catch (ClassNotFoundException | SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }
    private Map<String,Vehicle> getAllVehicleUidsFromDB()
    {
		Map<String,Vehicle> uids = new HashMap<String, Vehicle>();
		try
		{
			ResultSet rSet = querySELECT("Select * from SVEHICLE");
			while(rSet.next())
    		{
				uids.put(
						rSet.getString("VUID"),
						new Vehicle(Integer.parseInt(rSet.getString("VUID")), rSet.getString("VEHICLENAME"), rSet.getString("CUSTOMERNAME"), rSet.getString("VEHICLENUMBER")));
    		}
			
		} catch (ClassNotFoundException |SQLException e)
		{
			e.printStackTrace();
		}
		return uids;
    }
    public void updateOrInsertVehicleIntoDB(List<Vehicle> vehicleList)
    {
    	Map<String, Vehicle> presentUids = getAllVehicleUidsFromDB();
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
    
    private Map<String,Customer2> getAllCustomerUidsFromDB()
    {
		Map<String,Customer2> uids = new HashMap<String, Customer2>();
		try
		{
			ResultSet rSet = querySELECT("Select * from SCUSTOMER");
			while(rSet.next())
    		{
				uids.put(
						rSet.getString("CUID"),
						new Customer2(Integer.parseInt(rSet.getString("CUID")), rSet.getString("CNAME"), rSet.getString("CADDRESS"), rSet.getString("CVCODE"), rSet.getString("CDEPT")));
    		}
			
		} catch (ClassNotFoundException |SQLException e)
		{
			e.printStackTrace();
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
				addCustomers(customerList.get(i));
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
			while(rSet.next())
    		{
				uids.put(
						rSet.getString("DUID"),
						new DutyType(Integer.parseInt(rSet.getString("DUID")), Integer.parseInt(rSet.getString("HOURS")), Integer.parseInt(rSet.getString("KM")),
								Double.parseDouble(rSet.getString("PKGRATE")), Double.parseDouble(rSet.getString("EXKMRATE")), rSet.getString("CNAME"), rSet.getString("VNAME")));
    		}
			
		} catch (ClassNotFoundException |SQLException e)
		{
			e.printStackTrace();
		}
		return uids;
	}

}
