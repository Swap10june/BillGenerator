package beans;

import java.sql.Date;
import java.sql.Time;

public class BOM
{
	private String 		billNumber 				= 		null;
	private Admin		admin					=		null;
	private Customer	customer				=		null;
	private Date		billDate				=		null;
	private Date		dateOfTravels			=		null;
	private Date		dateOfreturn			=		null;
	private String 		typeOfVehicle			=		null;
	private String 		vehicleNumber			=		null;
	private String 		vendorCode				=		null;
	private String 		employeeNameUsedVehicle	=		null;
	private String 		packageType				=		null;
	private double		startKM					=		0.0;
	private double		endKM					=		0.0;
	private	Time		startTime				=		null;
	private Time		endTime					=		null;
	private	String		dutyType				=		null;
	private double		packageKM				=		0.0;
	private double 		extraKM					=		0.0;
	private double		packageRate				=		0.0;
	private double		extraRate				=		0.0;
	private double		pkgtotalAmount			=		0.0;
	private double		extraTotalAmount		=		0.0;
	private double		extraTimeHours			=		0.0;	
	private double		tollCharges				=		0.0;
	private double		monthlyExtraCharges		=		0.0;
	private double		serviceTaxCarges		=		0.0;
	private double		nightHaltRate			=		0.0;
	private	double		grandTotal				=		0.0;
	
	
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Date getDateOfTravels() {
		return dateOfTravels;
	}
	public void setDateOfTravels(Date dateOfTravels) {
		this.dateOfTravels = dateOfTravels;
	}
	public Date getDateOfreturn() {
		return dateOfreturn;
	}
	public void setDateOfreturn(Date dateOfreturn) {
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
	public double getStartKM() {
		return startKM;
	}
	public void setStartKM(double startKM) {
		this.startKM = startKM;
	}
	public double getEndKM() {
		return endKM;
	}
	public void setEndKM(double endKM) {
		this.endKM = endKM;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getDutyType() {
		return dutyType;
	}
	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}
	public double getPackageKM() {
		return packageKM;
	}
	public void setPackageKM(double packageKM) {
		this.packageKM = packageKM;
	}
	public double getExtraKM() {
		return extraKM;
	}
	public void setExtraKM(double extraKM) {
		this.extraKM = extraKM;
	}
	public double getPackageRate() {
		return packageRate;
	}
	public void setPackageRate(double packageRate) {
		this.packageRate = packageRate;
	}
	public double getExtraRate() {
		return extraRate;
	}
	public void setExtraRate(double extraRate) {
		this.extraRate = extraRate;
	}
	public double getPkgtotalAmount() {
		return pkgtotalAmount;
	}
	public void setPkgtotalAmount(double pkgtotalAmount) {
		this.pkgtotalAmount = pkgtotalAmount;
	}
	public double getExtraTotalAmount() {
		return extraTotalAmount;
	}
	public void setExtraTotalAmount(double extraTotalAmount) {
		this.extraTotalAmount = extraTotalAmount;
	}
	public double getExtraTimeHours() {
		return extraTimeHours;
	}
	public void setExtraTimeHours(double extraTimeHours) {
		this.extraTimeHours = extraTimeHours;
	}
	public double getTollCharges() {
		return tollCharges;
	}
	public void setTollCharges(double tollCharges) {
		this.tollCharges = tollCharges;
	}
	public double getMonthlyExtraCharges() {
		return monthlyExtraCharges;
	}
	public void setMonthlyExtraCharges(double monthlyExtraCharges) {
		this.monthlyExtraCharges = monthlyExtraCharges;
	}
	public double getServiceTaxCarges() {
		return serviceTaxCarges;
	}
	public void setServiceTaxCarges(double serviceTaxCarges) {
		this.serviceTaxCarges = serviceTaxCarges;
	}
	public double getNightHaltRate() {
		return nightHaltRate;
	}
	public void setNightHaltRate(double nightHaltRate) {
		this.nightHaltRate = nightHaltRate;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	
	
	
	
	
}
