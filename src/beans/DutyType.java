package beans;

import java.util.HashMap;
import java.util.Map;

public class DutyType
{
	private int hours 	= 	0;
	private int km		=	0;
	private String dutyTypeString =null;
	private int packageRate = 0;
	private String id = null;
	public  static Map<String,DutyType>  map = new HashMap<String,DutyType>();
	
	
	
	public int getPackageRate() {
		return packageRate;
	}
	public void setPackageRate(int packageRate) {
		this.packageRate = packageRate;
	}
	public DutyType(int hours, int km,int packageRate)
	{
		this.hours = hours;
		this.km = km;
		this.packageRate = packageRate;
		this.id = String.valueOf(hours)+" Hrs "+String.valueOf(km)+" KM";
		map.put(id, this);
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public String getDutyTypeString() {
		return dutyTypeString;
	}
	public void setDutyTypeString() {
		this.dutyTypeString = String.valueOf(hours)+" Hrs "+String.valueOf(km)+" KM";;
	}
	public DutyType getDutyType(String id)
	{
		return map.get(id);
		
	}
	
}
