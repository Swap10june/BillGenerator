package uit.billgen.beans;

public class Vehicle {

	private int uid = 0;
	private String stringUID = null;
	private String vehicleName = null;
	private String vehicleNumber = null;
	private String customerName = null;
	private String monthlyRate	= null;
	private String extraKmRate	= null;
	private String extraHourRate = null;
	
	
	/**
	 * @return the extraHourRate
	 */
	public String getExtraHourRate() {
		return extraHourRate;
	}
	/**
	 * @param extraHourRate the extraHourRate to set
	 */
	public void setExtraHourRate(String extraHourRate) {
		this.extraHourRate = extraHourRate;
	}
	public Vehicle(int uid, String vehicleName,String customerName, String vNumber,String monthlyRate,String extraKmRate,String extraHourRate )
	{
		this.setUid(uid);
		this.setVehicleName(vehicleName);
		this.setCustomerName(customerName);
		this.setVehicleNumber(vNumber);
		this.setMonthlyRate(monthlyRate);
		this.setExtraKmRate(extraKmRate);
		this.setStringUID(vehicleName);
		this.setExtraHourRate(extraHourRate);
	}
	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}
	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
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
	 * @return the stringUID
	 */
	public String getStringUID() {
		return stringUID;
	}
	/**
	 * @param stringUID the stringUID to set
	 */
	public void setStringUID(String stringUID) {
		this.stringUID = stringUID;
	}
	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	/**
	 * @return the monthlyRate
	 */
	public String getMonthlyRate() {
		return monthlyRate;
	}
	/**
	 * @param monthlyRate the monthlyRate to set
	 */
	public void setMonthlyRate(String monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	/**
	 * @return the extraKmRate
	 */
	public String getExtraKmRate() {
		return extraKmRate;
	}
	/**
	 * @param extraKmRate the extraKmRate to set
	 */
	public void setExtraKmRate(String extraKmRate) {
		this.extraKmRate = extraKmRate;
	}
	
}
