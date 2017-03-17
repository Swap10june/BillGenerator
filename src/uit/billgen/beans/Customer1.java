package uit.billgen.beans;

public class Customer1
{
	private String name						=		null;
	private String oldName					=		null;
	
	private String address					=		null;
	private String vendorCode				=		null;
	private String customerPersonName		=		null;
	private String customerDepartmentName	=		null;
	
	
	
	
	
	
	public Customer1(String name, String address, String vendorCode,
			String customerPersonName, String customerDepartmentName)
	{
		this.name = name;
		this.address = address;
		this.vendorCode = vendorCode;
		this.customerPersonName = customerPersonName;
		this.customerDepartmentName = customerDepartmentName;
	}
	public Customer1(String name, String address, String vendorCode,String oldName)
	{
		this.name = name;
		this.address = address;
		this.vendorCode = vendorCode;
		this.oldName = oldName;
	}
	
	
	public Customer1() {
		// TODO Auto-generated constructor stub
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
	public String getOldName() {
		return oldName;
	}


	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	
}
