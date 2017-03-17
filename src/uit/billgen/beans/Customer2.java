package uit.billgen.beans;

public class Customer2 
{
	private String cName					=		null;
	private String cAddress					=		null;
	private String cVendorCode				=		null;
	private String customerDepartmentName	=		null;
	private int uid = 0;
	private String suid = null;
	
	
	
	
	
	public Customer2( int uid,String cName, String cAddress, String cVendorCode,String customerDepartmentName) {
		
		this.cName = cName;
		this.cAddress = cAddress;
		this.cVendorCode = cVendorCode;
		this.customerDepartmentName = customerDepartmentName;
		this.uid = uid;
		
		this.setSuid(getcName());
	}
	/**
	 * @return the cName
	 */
	public String getcName() {
		return cName;
	}
	/**
	 * @param cName the cName to set
	 */
	public void setcName(String cName) {
		this.cName = cName;
	}
	/**
	 * @return the cAddress
	 */
	public String getcAddress() {
		return cAddress;
	}
	/**
	 * @param cAddress the cAddress to set
	 */
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	/**
	 * @return the cVendorCode
	 */
	public String getcVendorCode() {
		return cVendorCode;
	}
	/**
	 * @param cVendorCode the cVendorCode to set
	 */
	public void setcVendorCode(String cVendorCode) {
		this.cVendorCode = cVendorCode;
	}
	/**
	 * @return the customerDepartmentName
	 */
	public String getCustomerDepartmentName() {
		return customerDepartmentName;
	}
	/**
	 * @param customerDepartmentName the customerDepartmentName to set
	 */
	public void setCustomerDepartmentName(String customerDepartmentName) {
		this.customerDepartmentName = customerDepartmentName;
	}
	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * @return the suid
	 */
	public String getSuid() {
		return suid;
	}
	/**
	 * @param suid the suid to set
	 */
	public void setSuid(String suid) {
		this.suid = suid;
	}
	
	
}
