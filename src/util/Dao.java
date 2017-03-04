package util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.PopupDialogs;

import beans.BOM;
import beans.Customer1;
import beans.DutyType;
import beans.Rate;


public class Dao {
	
	private static final String ADD_DUTY_TYPE = "insert into DUTYTYPES values(?,?,?,?)";
	private static String ADD_CUSTOMER ="insert into CUSTOMER values(?,?,?)";
	private static String EDIT_CUSTOMER ="UPDATE CUSTOMERS SET CUSTOMER_NAME=?,VENDOR_CODE=?,ADDRESS=? WHERE CUSTOMER_NAME=?";
	private static String ADD_VEHICLE ="insert into RATES values(?,?,?,?,?,?,?)";
	private static String EDIT_VEHICLE ="UPDATE RATES SET CUSTOMER=?, DUTY_TYPE=?,VEHICLE_TYPE=?,PKG_RATE=?, EXTRA_RATE=?,VENDOR_CODE=?,AC_NOAC=? WHERE CUSTOMER_NAME=? and VEHICLE_TYPE=?;";
	private static String GET_CUSTOMER_LIST ="select DISTINCT CUSTOMER_NAME FROM CUSTOMERS;";
	private static String GET_VEHICLE_LIST ="select DISTINCT VEHICLE_TYPE FROM RATES where CUSTOMER=?;";
	private static String GET_CUSTOMER ="select * FROM CUSTOMERS WHERE CUSTOMER_NAME=?;";
	private static String GET_VEHICLE ="select * FROM RATES where CUSTOMER=? AND VEHICLE_TYPE=?;";
	
	
	PreparedStatement pStmtDao;
	
	public List<String> getCustomerList(){		
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
	
	public void addCustomers(Customer1 customerBean){		
		
		try {
			pStmtDao = DBConnection.getConnectionInstance().prepareStatement(ADD_CUSTOMER);
			pStmtDao.setString(1,customerBean.getName());
			pStmtDao.setString(2,customerBean.getVendorCode());
			pStmtDao.setString(3,customerBean.getAddress());
			pStmtDao.executeUpdate();
			pStmtDao.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void addVehicles(Rate vehicle){		
		
		try {
			pStmtDao = DBConnection.getConnectionInstance().prepareStatement(ADD_VEHICLE);
			pStmtDao.setString(1,vehicle.getCustomerName());
			pStmtDao.setString(2,vehicle.getDutyType());
			pStmtDao.setString(3,vehicle.getVehicleType());
			pStmtDao.setString(4,vehicle.getPackageRate());
			pStmtDao.setString(5,vehicle.getExtraRate());
			pStmtDao.setString(6,vehicle.getVendorCode());
			pStmtDao.setString(7,vehicle.getAcNonAcStatus());
			pStmtDao.executeUpdate();
			pStmtDao.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
	}
	
public void editCustomer(Customer1 customerBean){		
		
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
public void editVehicle(Rate vehicle){		
	
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

public Rate getVehicle(String customerName,String vehicleName){		
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
	try {
		pStmtDao = DBConnection.getConnectionInstance().prepareStatement(ADD_DUTY_TYPE);
		pStmtDao.setString(1,dutyType.getId());
		pStmtDao.setString(2,String.valueOf(dutyType.getHours()));
		pStmtDao.setString(3,String.valueOf(dutyType.getKm()));
		pStmtDao.setString(4,String.valueOf(dutyType.getPackageRate()));
		int status = pStmtDao.executeUpdate();
		if(status==1)
		{
			new PopupDialogs("Added Successfully",PopupDialogs.PLAIN_MESSAGE);
		}
		pStmtDao.close();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}
	
	public void addBill(BOM bom) 
{
	// TODO Auto-generated method stub
	
}
}
