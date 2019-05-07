package user;

public class Customer {
	private final int pointsThreshold = 20;
	private final double customerDiscountPerThresh = 5.00;
	
	private String id;
	private String name;
	private String address;
	private String phoneNo;
	private int points;
	
	public Customer(String id, String name, String address, String phoneNo) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.points = 0;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints(double points) {
		this.points += points;
		//System.out.println(name + " points=" + Double.toString(points));
	}
	
	public double getCustomerDiscount() {		
		
		int discountNum = (int) (points/pointsThreshold);
		
		return customerDiscountPerThresh * discountNum;
	}

	public void consumePoints() {
		
		int discountNum = (int) (points/pointsThreshold);
		points -= (int) discountNum * pointsThreshold;
	}
	
	public String toString() {
		return "Customer: \n\tid:  " + id + "\n\tname:  " + name + "\n\taddress:  " + address + "\n\tPhone No.:  " + phoneNo + "\n\tpoints:  " + Double.toString(points);
	}


}
