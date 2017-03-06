package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Customer2;
import beans.DutyType;
import beans.Vehicle;

public class DBUtils 
{

	Dao dao = new Dao();
    public  ResultSet querySELECT(String Query) throws ClassNotFoundException, SQLException
    {
      
        System.out.println("Select query fired: "+Query);
        ResultSet set=null;
        Statement statement = DBConnection.getConnectionInstance().createStatement();
        set=statement.executeQuery(Query.toUpperCase());
        return set;     

    }
    public ArrayList<String> getValueListForColumnName(String tableName,String colmnName)
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
				dao.addVehicles(vehicleList.get(i));
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
				dao.addCustomers(customerList.get(i));
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
				dao.addDutyType(dutyTypeList.get(i));
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
