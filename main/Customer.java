package main;

public class Customer {
	
	private String id;
	private String name;
	private String address;
	private String phoneNo;
	private double points;
	
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
	
	public String getPhoneNum() {
		return phoneNo;
	}
	
	public double getPoints() {
		return points;
	}
	
	public void addPoints(double points) {
		this.points += points;
		//System.out.println(name + " points=" + Double.toString(points));
	}
	
	public void consumePoints(double points) {
		this.points -= points;
		//System.out.println(name + " points=" + Double.toString(this.points));
	}
	
	public String toString() {
		return "Customer: \n\tid:  " + id + "\n\tname:  " + name + "\n\taddress:  " + address + "\n\tPhone No.:  " + phoneNo + "\n\tpoints:  " + Double.toString(points);
	}


}
