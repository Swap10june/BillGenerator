package uit.billgen.beans;

public class Rate
{
	private String 		customerName			=	null;
	private String 		dutyType				=	null;
	private String 		vehicleType				=	null;
	private String 		vendorCode				=	null;
	private String		acNonAcStatus;
	private String 		packageRate				=	null;
	private String		extraRate				=	null;
	private String 		oldVehicleType			=	null;
	
	public Rate(String customerName, String dutyType, String vehicleType,
			String vendorCode, String acNonAcStatus, String packageRate,
			String extraRate, String oldVehicleType)
	{
		this.customerName = customerName;
		this.dutyType = dutyType;
		this.vehicleType = vehicleType;
		this.vendorCode = vendorCode;
		this.acNonAcStatus = acNonAcStatus;
		this.packageRate = packageRate;
		this.extraRate = extraRate;
		this.oldVehicleType = oldVehicleType;
	}

	
	public Rate() {
		// TODO Auto-generated constructor stub
	}


	public String getOldVehicleType() {
		return oldVehicleType;
	}


	public void setOldVehicleType(String oldVehicleType) {
		this.oldVehicleType = oldVehicleType;
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

	public String getAcNonAcStatus() {
		return acNonAcStatus;
	}

	public void setAcNonAcStatus(String acNonAcStatus) {
		this.acNonAcStatus = acNonAcStatus;
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
	
	
	
	
}
