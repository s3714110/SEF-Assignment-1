package main;

public class Employee {
	
	private String id;
	private String name;
	private String address;
	private String phoneNo;

	public Employee (String id, String name, String address, String phoneNo) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
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
	
	public String toString() {
		return "Employee: \n\tid:  " + id + "\n\tname:  " + name + "\n\taddress:  " + address + "\n\tPhone No.:  " + phoneNo;
	}


}
