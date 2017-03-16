package beans;

import java.util.ArrayList;
import java.util.List;

public class MonthlyBOM
{
	private String 		billNumber 				= 		null;
	private String		admin					=		null;
	private String		customer				=		null;
	private String 		customerName			=		null;
	private String		billDate				=		null;
	private String 		contactNumber			=		null;
	private String 		email					= 		null;
	private String 		typeOfVehicle			=		null;
	private String 		vehicleNumber			=		null;
	private String 		vendorCode				=		null;
	private String 		employeeNameUsedVehicle	=		null;
	private String 		packageType				=		null;
	private String		tollCharges				=		null;
	private String		monthlyExtraCharges		=		null;
	private String		serviceTaxCarges		=		null;
	private String		nightHaltRate			=		null;
	private	String		grandTotal				=		null;
	private String		totalWithoutTax			=		null;
	private List<BillRow> billRows 				=		null;
	
	
	public MonthlyBOM()
	{
		this.billRows = new ArrayList<BillRow>();
	}
	
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTypeOfVehicle() {
		return typeOfVehicle;
	}
	public void setTypeOfVehicle(String typeOfVehicle) {
		this.typeOfVehicle = typeOfVehicle;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getEmployeeNameUsedVehicle() {
		return employeeNameUsedVehicle;
	}
	public void setEmployeeNameUsedVehicle(String employeeNameUsedVehicle) {
		this.employeeNameUsedVehicle = employeeNameUsedVehicle;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getTollCharges() {
		return tollCharges;
	}
	public void setTollCharges(String tollCharges) {
		this.tollCharges = tollCharges;
	}
	public String getMonthlyExtraCharges() {
		return monthlyExtraCharges;
	}
	public void setMonthlyExtraCharges(String monthlyExtraCharges) {
		this.monthlyExtraCharges = monthlyExtraCharges;
	}
	public String getServiceTaxCarges() {
		return serviceTaxCarges;
	}
	public void setServiceTaxCarges(String serviceTaxCarges) {
		this.serviceTaxCarges = serviceTaxCarges;
	}
	public String getNightHaltRate() {
		return nightHaltRate;
	}
	public void setNightHaltRate(String nightHaltRate) {
		this.nightHaltRate = nightHaltRate;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getTotalWithoutTax() {
		return totalWithoutTax;
	}
	public void setTotalWithoutTax(String totalWithoutTax) {
		this.totalWithoutTax = totalWithoutTax;
	}
	/**
	 * @return the billRows
	 */
	public List<BillRow> getBillRows() {
		return billRows;
	}
	/**
	 * @param billRows the billRows to set
	 */
	public void setBillRows(List<BillRow> billRows) {
		this.billRows = billRows;
	}

	
	
}
