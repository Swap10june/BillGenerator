package uit.billgen.beans;

public class BillRow 
{
	private String fromDate				=		null;
	private String toDate				=		null;
	private String vehicleDesc			=		null;
	private String vehicle				=		null;
	
	private int monthlyKm				=		0;
	private int extraKm					=		0;
	
	private double rate					=		0.0;
	private double amount				=		0.0;
	private double extraKmRate			=		0.0;
	private double extraKmAmpount		=		0.0;
	private double totalAmount			=		0.0;
	private double extraCharges			=		0.0;
	public BillRow(
			String fromDate, String toDate, String vehicleDesc,String vehicle, int monthlyKm, int extraKm, double rate,
			double amount, double extraKmRate, double extraKmAmpount,double totalAmount)
	{
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.vehicleDesc = vehicleDesc;
		this.vehicle = vehicle;
		this.monthlyKm = monthlyKm;
		this.extraKm = extraKm;
		this.rate = rate;
		this.amount = amount;
		this.extraKmRate = extraKmRate;
		this.extraKmAmpount = extraKmAmpount;
		this.totalAmount = totalAmount;
	}
	public BillRow() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the vehicleDesc
	 */
	public String getVehicleDesc() {
		return vehicleDesc;
	}
	/**
	 * @param vehicleDesc the vehicleDesc to set
	 */
	public void setVehicleDesc(String vehicleDesc) {
		this.vehicleDesc = vehicleDesc;
	}
	/**
	 * @return the vehicle
	 */
	public String getVehicle() {
		return vehicle;
	}
	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	/**
	 * @return the monthlyKm
	 */
	public int getMonthlyKm() {
		return monthlyKm;
	}
	/**
	 * @param monthlyKm the monthlyKm to set
	 */
	public void setMonthlyKm(int monthlyKm) {
		this.monthlyKm = monthlyKm;
	}
	/**
	 * @return the extraKm
	 */
	public int getExtraKm() {
		return extraKm;
	}
	/**
	 * @param extraKm the extraKm to set
	 */
	public void setExtraKm(int extraKm) {
		this.extraKm = extraKm;
	}
	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the extraKmRate
	 */
	public double getExtraKmRate() {
		return extraKmRate;
	}
	/**
	 * @param extraKmRate the extraKmRate to set
	 */
	public void setExtraKmRate(double extraKmRate) {
		this.extraKmRate = extraKmRate;
	}
	/**
	 * @return the extraKmAmpount
	 */
	public double getExtraKmAmpount() {
		return extraKmAmpount;
	}
	/**
	 * @param extraKmAmpount the extraKmAmpount to set
	 */
	public void setExtraKmAmpount(double extraKmAmpount) {
		this.extraKmAmpount = extraKmAmpount;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getExtraCharges()
	{
		return extraCharges;
	}
	
	
	

	
}
