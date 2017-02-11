package beans;

public class Rate
{
	private String 		customerName			=	null;
	private String 		dutyType				=	null;
	private String 		vehicleType				=	null;
	private String 		vendorCode				=	null;
	private boolean		acNonAcStatus;
	private double 		packageRate				=	0.0;
	private double		extraRate				=	0.0;
	
	public Rate(String customerName, String dutyType, String vehicleType,
			String vendorCode, boolean acNonAcStatus, double packageRate,
			double extraRate)
	{
		this.customerName = customerName;
		this.dutyType = dutyType;
		this.vehicleType = vehicleType;
		this.vendorCode = vendorCode;
		this.acNonAcStatus = acNonAcStatus;
		this.packageRate = packageRate;
		this.extraRate = extraRate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDutyType() {
		return dutyType;
	}

	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public boolean isAcNonAcStatus() {
		return acNonAcStatus;
	}

	public void setAcNonAcStatus(boolean acNonAcStatus) {
		this.acNonAcStatus = acNonAcStatus;
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
	
	
	
	
}
