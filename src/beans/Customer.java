package beans;

public class Customer
{
	private String name						=		null;
	private String address					=		null;
	private String vendorCode				=		null;
	private String customerPersonName		=		null;
	private String customerDepartmentName	=		null;
	
	
	
	
	
	
	public Customer(String name, String address, String vendorCode,
			String customerPersonName, String customerDepartmentName)
	{
		this.name = name;
		this.address = address;
		this.vendorCode = vendorCode;
		this.customerPersonName = customerPersonName;
		this.customerDepartmentName = customerDepartmentName;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getCustomerPersonName() {
		return customerPersonName;
	}
	public void setCustomerPersonName(String customerPersonName) {
		this.customerPersonName = customerPersonName;
	}
	public String getCustomerDepartmentName() {
		return customerDepartmentName;
	}
	public void setCustomerDepartmentName(String customerDepartmentName) {
		this.customerDepartmentName = customerDepartmentName;
	}
	
	
}
