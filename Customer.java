package assignment1;

public class Customer {
	
	private String custId;
	private String custName;
	private String custAddress;
	private String custPhoneNum;
	private String custPoints;
	
	public Customer(String custId, String custName, String custPoints) {
		this.custId = custId;
		this.custName = custName;
		this.custPoints = custPoints;
	}
	
	public String getId() {
		return custId;
	}
	
	public String getAddress() {
		return custAddress;
	}
	
	public String getPhoneNum() {
		return custPhoneNum;
	}
	
	public String getPoints() {
		return custPoints;
	}
	



}
