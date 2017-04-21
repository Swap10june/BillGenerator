package uit.billgen.beans;

public class BOM
{
	
	private String 		billNumber 				= 		null;
	private String		billDate				=		null;
	private String 		contactNumber			=		null;
	private String 		email					= 		null;
	private String 		customerName			=		null;
	private String		dateOfTravels			=		null;
	private String		dateOfreturn			=		null;
	private String 		typeOfVehicle			=		null;
	private String 		vehicleNumber			=		null;
	private String 		vendorCode				=		null;
	private String 		employeeNameUsedVehicle	=		null;
	private String		startKM					=		null;
	private String		endKM					=		null;
	private	String		startTime				=		null;
	private String		endTime					=		null;
	private String 		totalKM					=		null;
	private	String		dutyType				=		null;
	private String 		packageType				=		null;
	private String		packageKM				=		null;
	private String		packageRate				=		null;
	private String 		pakageAmount			=		null;
	private String 		extraKM					=		null;
	private String		extraRate				=		null;
	private String		extraTotalAmount		=		null;
	private String		tollCharges				=		null;
	private String		nightHaltRate			=		null;
	private	String		grandTotal				=		null;
	private String		serviceTaxCarges		=		null;
	private String		totalWithoutTax			=		null;
	private String		extraTimeHours			=		null;	
	private String		monthlyExtraCharges		=		null;
	
	
	
	
	
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getDateOfTravels() {
		return dateOfTravels;
	}
	public void setDateOfTravels(String dateOfTravels) {
		this.dateOfTravels = dateOfTravels;
	}
	public String getDateOfReturn() {
		return dateOfreturn;
	}
	public void setDateOfreturn(String dateOfreturn) {
		this.dateOfreturn = dateOfreturn;
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
	public String getStartKM() {
		return startKM;
	}
	public void setStartKM(String startKM) {
		this.startKM = startKM;
	}
	public String getEndKM() {
		return endKM;
	}
	public void setEndKM(String endKM) {
		this.endKM = endKM;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDutyType() {
		return dutyType;
	}
	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}
	public String getPackageKM() {
		return packageKM;
	}
	public void setPackageKM(String packageKM) {
		this.packageKM = packageKM;
	}
	public String getExtraKM() {
		return extraKM;
	}
	public void setExtraKM(String extraKM) {
		this.extraKM = extraKM;
	}
	public String getPackageRate() {
		return packageRate;
	}
	public void setPackageRate(String packageRate) {
		this.packageRate = packageRate;
	}
	public String getExtraRate() {
		return extraRate;
	}
	public void setExtraRate(String extraRate) {
		this.extraRate = extraRate;
	}
	
	public String getExtraTotalAmount() {
		return extraTotalAmount;
	}
	public void setExtraTotalAmount(String extraTotalAmount) {
		this.extraTotalAmount = extraTotalAmount;
	}
	public String getExtraTimeHours() {
		return extraTimeHours;
	}
	public void setExtraTimeHours(String extraTimeHours) {
		this.extraTimeHours = extraTimeHours;
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
	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the totalKM
	 */
	public String getTotalKM() {
		return totalKM;
	}
	/**
	 * @param totalKM the totalKM to set
	 */
	public void setTotalKM(String totalKM) {
		this.totalKM = totalKM;
	}
	/**
	 * @return the pakageAmount
	 */
	public String getPakageAmount() {
		return pakageAmount;
	}
	/**
	 * @param pakageAmount the pakageAmount to set
	 */
	public void setPakageAmount(String pakageAmount) {
		this.pakageAmount = pakageAmount;
	}
	
	/**
	 * @return the totalWithoutTax
	 */
	public String getTotalWithoutTax() {
		return totalWithoutTax;
	}
	/**
	 * @param totalWithoutTax the totalWithoutTax to set
	 */
	public void setTotalWithoutTax(String totalWithoutTax) {
		this.totalWithoutTax = totalWithoutTax;
	}
	
	
	

	
	
	
	
	
}
