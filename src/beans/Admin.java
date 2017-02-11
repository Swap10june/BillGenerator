package beans;

import java.util.List;

public class Admin
{
	private String 			panNumber	=	null;
	private List<String>	mobNumbers	=	null;
	private String 			emailID		=	null;
	private String			address		=	null;
	private String			name		=	null;
	
	
	
	
	
	public Admin(String panNumber, List<String> mobNumbers, String emailID,
			String address, String name)
	{
		this.panNumber = panNumber;
		this.mobNumbers = mobNumbers;
		this.emailID = emailID;
		this.address = address;
		this.name = name;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public List<String> getMobNumbers() {
		return mobNumbers;
	}
	public void setMobNumbers(List<String> mobNumbers) {
		this.mobNumbers = mobNumbers;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
