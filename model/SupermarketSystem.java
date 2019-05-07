package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import view.*;
import warehouse.Product;
import warehouse.Warehouse;
import controller.*;
import sale.Sale;
import sale.SaleLineItem;
import user.Customer;
import user.Employee;
import user.Manager;
import user.SalesStaff;



public class SupermarketSystem {

	private boolean exitSystem;
	
	private Warehouse warehouse;	
	
	
	private List<Customer> customerList;
	private List<Manager> managerList;
	private List<SalesStaff> salesStaffList;
	private List<Employee> warehouseStaffList;
	
	private List<Sale> activeSales;
	private List<Sale> saleHistory;
	
	SupermarketView supermarketView;
	
	public SupermarketSystem(){ 
		warehouse = importWarehouse();
		customerList = importCustomerList();
		managerList = importManagerList();
		salesStaffList = importSalesStaffList();
		warehouseStaffList = importWarehouseStaffList();
		
		activeSales = new ArrayList<Sale>();
		saleHistory = importSalesHistory();
		
		exitSystem = false;
	}
	

	public void run() {


		exitSystem = false;
		setView_System();
		
		while(!exitSystem) {
			supermarketView.show();
		}
	}

	public void setExit(boolean exitSystem) {
		this.exitSystem = exitSystem;
	}
	
	// Init Functions

	
	private Warehouse importWarehouse() {
		//System.out.println("importWarehouse()");
		return new Warehouse();
	}
	
	private List<Customer> importCustomerList() {
		//System.out.println("importCustomerList()");
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
	private List<Manager> importManagerList() {
		//System.out.println("importManagerList()");
		List<Manager> managerList = new ArrayList<Manager>();
		
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
				
				managerList.add(new Manager(id, name, address, phoneNo));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		
		return managerList;
	}
	private List<SalesStaff> importSalesStaffList() {
		//System.out.println("importSalesStaffList()");
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
	private List<Employee> importWarehouseStaffList() {
		//System.out.println("importWarehouseStaffList()");
		List<Employee> warehouseStaffList = new ArrayList<Employee>();
		
		String id = "";
		String name = "";
		String address = "";
		String phoneNo = "";	
		
					 
		BufferedReader reader;		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/warehousestaff.txt").getFile()));
			
			String line;
			String[] salesstaffInfo;	
			while ((line = reader.readLine()) != null) {
				
				salesstaffInfo = line.split("\t");
									
				id = salesstaffInfo[0];
				name = salesstaffInfo[1];
				address = salesstaffInfo[2];
				phoneNo = salesstaffInfo[3];				
				
				warehouseStaffList.add(new Employee(id, name, address, phoneNo));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		
		return warehouseStaffList;
	}
	
	private List<Sale> importSalesHistory(){
		//System.out.println("importSalesHistory()");
				List<Sale> saleHistory = new ArrayList<Sale>();
				
				String employeeId = "";
				String customerId = "";
				String saleId = "";
				
							 
				BufferedReader reader;		
				try {
					reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/saleshistory.txt").getFile()));
					
					String line;
					String[] salesstaffInfo;	
					while ((line = reader.readLine()) != null) {
						
						salesstaffInfo = line.split("\t");
											
						employeeId = salesstaffInfo[0];
						customerId = salesstaffInfo[1];
						saleId = salesstaffInfo[2];			
						
						saleHistory.add(new Sale(employeeId, customerId, saleId));
					}
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				 		
				
				return saleHistory;
	}
	
	
	// Get Functions		
	
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
	
	
	// Sale Functions
	public void processSale(Sale sale) {
		double customerDiscount = 0.0;
		
		
		List<SaleLineItem> itemList = sale.getItemList();
		
		for(SaleLineItem item : itemList) {
			warehouse.take(item.getProductId(), item.getQty());
		}
		
		Customer customer = getCustomer(sale.getCustomerId());
		customer.addPoints(sale.calculateCustomerPoints());		
		customerDiscount = customer.getCustomerDiscount();
		customer.consumePoints();
		
		double totalTransaction = sale.getTotal() - customerDiscount;

		saleHistory.add(sale);
		//System.out.println("customerDiscount=" + Double.toString(customerDiscount));
		//System.out.println("totalTransaction=" + Double.toString(totalTransaction));
	}
	
	public double calculateDiscount(String productId, int qty) {
		double totaldiscount = 0.0;
		// System.out.println("\ncalculateDiscount()");
		if(validProductId(productId)) {
			Product product = getProduct(productId);
		
			Set<Integer> keys = product.getDiscounts().keySet();
		
			if(!keys.isEmpty()) {
				int amount = qty;
				double discount = 0.0;
				int discountNum = 0;
				
				
				int bulkqty = amount;
				while(bulkqty != 0) {
					// System.out.println("bulkqty= " + Integer.toString(bulkqty));
					if(product.getDiscounts().containsKey(bulkqty)) {
						discountNum = amount/bulkqty;
						discount = product.getDiscounts().get(bulkqty);
						totaldiscount += discount * discountNum;
						amount = (amount - bulkqty * discountNum);
						bulkqty = amount;
						
	//					System.out.println("\tdiscountNum= " + Integer.toString(discountNum));
	//					System.out.println("\tdiscount= " + Double.toString(discount));
	//					System.out.println("\ttotaldiscount= " + Double.toString(totaldiscount));
	//					System.out.println("\tamount= " + Integer.toString(amount));
	//					System.out.println("\tbulkqty= " + Integer.toString(bulkqty));
	//					System.out.println("\t\tBREAK!!");
						
						continue;
					}
					bulkqty--;
				}				
				
			}
		}
		//System.out.println("\nreturn totalDiscount=" + Double.toString(totaldiscount));
		return totaldiscount;
	}
	
	
	// Warehouse Functions
	public Product getProduct(String productId) {
		return warehouse.getProduct(productId);
	}
	public List<Product> getProductList() {
		return warehouse.getProductList();
	}
	public boolean validProductId(String productId){
		return warehouse.validProductId(productId);
	}
	public void restock(String productId, int qty) {
		warehouse.restock(productId, qty);
	}
	public void take(String productId, int qty) {
		warehouse.take(productId, qty);
	}
	public int getStockLevel(String productId) {
		return warehouse.getStockLevel(productId);
	}
	
	
	// View functions
	public void setView(SupermarketView view){
		supermarketView = view;
	}
	
	public void setView_System(){
		SupermarketSystemController supermarketController = new SupermarketSystemController(this);
		SupermarketView supermarketView = new SupermarketSystemView(supermarketController);
		
		setView(supermarketView);
	}
	
	// Login Functions
	public void login_Customer(String id){
		SupermarketCustomerController supermarketController = new SupermarketCustomerController(this);
		SupermarketView supermarketView = new SupermarketCustomerView(supermarketController);
		
		setView(supermarketView);
	}
	public void login_Manager(String id){
		SupermarketManagerController supermarketController = new SupermarketManagerController(this);
		SupermarketView supermarketView = new SupermarketManagerView(supermarketController);
		
		setView(supermarketView);
	}
	public void login_SalesStaff(String id){
		SupermarketSalesStaffController supermarketController = new SupermarketSalesStaffController(this);
		SupermarketView supermarketView = new SupermarketSalesStaffView(supermarketController);
		
		setView(supermarketView);
	}	
	public void login_WarehouseStaff(String id){
		SupermarketWarehouseStaffController supermarketController = new SupermarketWarehouseStaffController(this, id);
		SupermarketView supermarketView = new SupermarketWarehouseStaffView(supermarketController);
		
		setView(supermarketView);
	}
	

	
	
	// User Id Functions
	public boolean validCustomerId(String id){
		boolean validId = false;
		
		for (Customer customer : customerList) { 
		    if(0 == id.compareTo(customer.getId())) {
		    	validId = true;
		    	break;
		    }
		}
		
		return validId;
	}
	public boolean validManagerId(String id){
		boolean validId = false;
		
		for (Manager manager : managerList) { 
		    if(0 == id.compareTo(manager.getId())) {
		    	validId = true;
		    	break;
		    }
		}
		
		return validId;
	}
	public boolean validSalesStaffId(String id){
		boolean validId = false;
		
		for (SalesStaff salesStaff : salesStaffList) { 
		    if(0 == id.compareTo(salesStaff.getId())) {
		    	validId = true;
		    	break;
		    }
		}
		
		return validId;
	}
	public boolean validWarehouseStaffId(String id){
		boolean validId = false;
		
		for (Employee warehouseStaff : warehouseStaffList) { 
		    if(0 == id.compareTo(warehouseStaff.getId())) {
		    	validId = true;
		    	break;
		    }
		}
		
		return validId;
	}
	
	// Print Functions
	public void printProductList() {
		System.out.println("printProductList()");
		for (Product product : getProductList()) { 
		    System.out.println(product.toString());
		}
	}
	public void printWarehouseInventory() {
		warehouse.printInventory();
	}
	public void printCustomerList() {
		System.out.println("printCustomerList()");
		for (Customer customer : customerList) { 
		    System.out.println(customer.toString());
		}
	}
	public void printManagerList() {
		System.out.println("printManagerList()");
		for (Manager manager : managerList) {
			System.out.println(manager.toString());	
		}
	}
	public void printSalesStaffList() {
		System.out.println("printSalesStaffList()");
		for (SalesStaff salesStaff : salesStaffList) { 
		    System.out.println(salesStaff.toString());
		}
	}
	public void printWarehouseStaffList() {
		System.out.println("printWarehouseStaffList()");
		for (Employee warehouseStaff : warehouseStaffList) { 
		    System.out.println(warehouseStaff.toString());
		}
	}
	public void printSaleHistory() {
		System.out.println("printWarehouseStaffList()");
		for (Sale sale : saleHistory) { 
		    System.out.println(sale.toString());
		}
	}
		
	
}
