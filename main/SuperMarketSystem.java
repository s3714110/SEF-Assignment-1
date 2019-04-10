package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuperMarketSystem {

	private List<Product> productList;
	private Warehouse warehouse;
	private List<Customer> customerList;
	private List<SalesStaff> salesStaffList;
	private List<Receipt> receiptList;
	private Manager manager;
	
	public SuperMarketSystem(){
		productList = createProductList(); 
		warehouse = createWarehouse();
		customerList = createCustomerList();
		salesStaffList = createSalesStaffList();
		manager = createManager();
	}
	
	private List<Product> createProductList() {
		//System.out.println("getProductList()");
		List<Product> productList = new ArrayList<Product>();
		
		String id = "";
		String name = "";
		String type = "";
		double price = 0.0;	
					 
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/productlist.txt").getFile()));
			
			String line;
			String[] productInfo;
			while ((line = reader.readLine()) != null) {
				productInfo = line.split("\t");
				
				if(productInfo.length == 4) {
					id = productInfo[0];
					name = productInfo[1];
					type = productInfo[2];
					price = Double.parseDouble(productInfo[3]);
					
					productList.add(new Product(id, name, type, price));
				}
				else {
					//System.out.println("productInfo Length:  " + Integer.toString(productInfo.length));
				}
					
				
			}		
			reader.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return productList;
	}
	
	private Warehouse createWarehouse() {
		//System.out.println("getWarehouse()");
		return new Warehouse();
	}
	
	private List<Customer> createCustomerList() {
		//System.out.println("getCustomerList()");
		List<Customer> customerList = new ArrayList<Customer>();
		
		String id = "";
		String name = "";
		String address = "";
		String phoneNo = "";	
		
						 
		BufferedReader reader;		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/customerlist.txt").getFile()));
			
			String line;
			String[] customerInfo;
			while ((line = reader.readLine()) != null) {
				
				customerInfo = line.split("\t");				
				
				id = customerInfo[0];
				name = customerInfo[1];
				address = customerInfo[2];
				phoneNo = customerInfo[3];
				
				customerList.add(new Customer(id, name, address, phoneNo));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return customerList;
	}
	
	private List<SalesStaff> createSalesStaffList() {
		//System.out.println("getSalesStaffList()");
		List<SalesStaff> salesStaffList = new ArrayList<SalesStaff>();
		
		String id = "";
		String name = "";
		String address = "";
		String phoneNo = "";	
		
					 
		BufferedReader reader;		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/salesstaff.txt").getFile()));
			
			String line;
			String[] salesstaffInfo;	
			while ((line = reader.readLine()) != null) {
				
				salesstaffInfo = line.split("\t");
									
				id = salesstaffInfo[0];
				name = salesstaffInfo[1];
				address = salesstaffInfo[2];
				phoneNo = salesstaffInfo[3];				
				
				salesStaffList.add(new SalesStaff(id, name, address, phoneNo));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		
		return salesStaffList;
	}
	
	private Manager createManager() {
		//System.out.println("getManager()");
		String id = "";
		String name = "";
		String address = "";
		String phoneNo = "";	
		
						 
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/manager.txt").getFile()));
			
			String line;
			String[] managerInfo;
			while ((line = reader.readLine()) != null) {
				
				managerInfo = line.split("\t");
				
				id = managerInfo[0];
				name = managerInfo[1];
				address = managerInfo[2];
				phoneNo = managerInfo[3];				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		
		return new Manager(id, name, address, phoneNo);
	}
	
			
	public Product getProduct(String id) {
		Product product = null;
		for(int i=0; i < productList.size(); i++) {
			if( 0 == productList.get(i).getId().compareTo(id)) {
				product = productList.get(i);
			}
		}
		return product;
		//return productList.get(productList.indexOf(id));
	}
	
	public Customer getCustomer(String id) {
		Customer customer = null;
		for(int i=0; i < customerList.size(); i++) {
			if( 0 == customerList.get(i).getId().compareTo(id)) {
				customer = customerList.get(i);
			}
		}
		return customer;
		//return productList.get(productList.indexOf(id));
	}
	
	public void processSale(Receipt receipt) {
		int customerPointsThreshHold = 20;
		double customerDiscount = 0.0;
		
		
		List<ReceiptItem> itemList = receipt.getItemList();
		
		for(ReceiptItem item : itemList) {
			warehouse.take(item.getProductId(), item.getQty());
		}
		
		Customer customer = getCustomer(receipt.getCustomerId());
		customer.addPoints(calculateCustomerPoints(receipt.getTotal()));
		
		
		customerDiscount = getCustomerDiscount(customer);
		
		
		double totalTransaction = receipt.getTotal() - customerDiscount;


		//System.out.println("customerDiscount=" + Double.toString(customerDiscount));
		//System.out.println("totalTransaction=" + Double.toString(totalTransaction));
	}
	
	public double calculateCustomerPoints(double total) {
		double dollarPerPoint = 10.00;
		
		return total/dollarPerPoint;
		
		
	}
	
	public double getCustomerDiscount(Customer customer) {
		double pointsThreshold = 20.00;
		double discount = 5.00;
		
		int discountNum = (int) (customer.getPoints()/pointsThreshold);
		customer.consumePoints(discountNum * pointsThreshold);
		
		return discount * discountNum;
	}
	
	public Map<String, Integer> getWarehouseInventory() {
		return warehouse.getInventory();
	}
	
	public void warehouseRestock(String productId, int qty) {
		warehouse.restock(productId, qty);
	}
	
	public void printProductList() {
		System.out.println("printProductList()");
		for (Product product : productList) { 
		    System.out.println(product.toString());
		}
	}
	
	public void printCustomerList() {
		System.out.println("printCustomerList()");
		for (Customer customer : customerList) { 
		    System.out.println(customer.toString());
		}
	}
	
	public void printSalesStaffList() {
		System.out.println("printSalesStaffList()");
		for (SalesStaff salesStaff : salesStaffList) { 
		    System.out.println(salesStaff.toString());
		}
	}
	
	public void printManager() {
		System.out.println("printManager()");
		System.out.println(manager.toString());		
	}
	
	public void printWarehouseInventory() {
		warehouse.printInventory();
	}
	
}
