package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import view.*;
import warehouse.Product;
import warehouse.Warehouse;
import controller.*;
import sale.Discounts;
import sale.Sale;
import sale.SaleLineItem;
import user.Customer;
import user.Employee;
import user.Manager;
import user.SalesStaff;



public class SupermarketSystem {

	private boolean exitSystem;
	
	private Warehouse warehouse;	
	
	
	private Map<String, Customer> customerMap;
	private Map<String, Manager> managerMap;
	private Map<String, SalesStaff> salesStaffMap;
	private Map<String, Employee> warehouseStaffMap;
	
	private Map<String, Sale> activeSales;
	private List<Sale> saleHistory;
	
	private List<Product> productList;
	private Discounts discounts;
	
	SupermarketView supermarketView;
	
	public SupermarketSystem(){ 
		warehouse = importWarehouse();
		customerMap = importCustomers();
		managerMap = importManagers();
		salesStaffMap = importSalesStaff();
		warehouseStaffMap = importWarehouseStaff();
		
		activeSales = new HashMap<String, Sale>();
		saleHistory = importSalesHistory();
		
		productList = warehouse.getProductList();
		discounts = new Discounts();
		
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
	
	// Import Functions	
	private Warehouse importWarehouse() {
		//System.out.println("importWarehouse()");
		return new Warehouse();
	}
	
	private Map<String, Customer> importCustomers() {
		//System.out.println("importCustomerList()");
		Map<String, Customer> customerList = new HashMap<String, Customer>();
		
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
				
				customerList.put(id, new Customer(id, name, address, phoneNo));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return customerList;
	}
	private Map<String, Manager> importManagers() {
		//System.out.println("importManagers()");
		Map<String, Manager> managerList = new HashMap<String, Manager>();
		
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
				
				managerList.put(id, new Manager(id, name, address, phoneNo));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		
		return managerList;
	}
	private Map<String, SalesStaff> importSalesStaff() {
		//System.out.println("importSalesStaff()");
		Map<String, SalesStaff> salesStaffList = new HashMap<String, SalesStaff>();
		
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
				
				salesStaffList.put(id, new SalesStaff(id, name, address, phoneNo));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		
		return salesStaffList;
	}
	private Map<String, Employee> importWarehouseStaff() {
		//System.out.println("importWarehouseStaff()");
		Map<String, Employee> warehouseStaffList = new HashMap<String, Employee>();
		
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
				
				warehouseStaffList.put(id, new Employee(id, name, address, phoneNo));
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
		
		String customerId = "";
		String saleId = "";
		
					 
		BufferedReader reader;		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/saleshistory.txt").getFile()));
			
			String line;
			String[] salesstaffInfo;	
			while ((line = reader.readLine()) != null) {
				
				salesstaffInfo = line.split("\t");
					
				saleId = salesstaffInfo[0];	
				customerId = salesstaffInfo[1];
						
				
				saleHistory.add(new Sale(saleId, customerId));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 		
		
		return saleHistory;
	}
	
	
	// Get Functions		
	public Customer getCustomer(String customerId) {
		return customerMap.get(customerId);
	}
	public Manager getManager(String employeeId) {
		return managerMap.get(employeeId);
	}
	public SalesStaff getSalesStaff(String employeeId) {
		return salesStaffMap.get(employeeId);
	}
	public Employee getWarehouseStaff(String employeeId) {
		return warehouseStaffMap.get(employeeId);
	}
	public Sale getActiveSale(String customerId) {
		return activeSales.get(customerId);
	}
	public List<Customer> getCustomerList() {
		return new ArrayList<Customer>(customerMap.values());
	}
	public List<Manager> getManagerList() {
		return new ArrayList<Manager>(managerMap.values());
	}
	public List<SalesStaff> getSalesStaffList() {
		return new ArrayList<SalesStaff>(salesStaffMap.values());
	}
	public List<Employee> getWarehouseStaffList() {
		return new ArrayList<Employee>(warehouseStaffMap.values());
	}
	public List<Sale> getActiveSalesList() {
		return new ArrayList<Sale>(activeSales.values());
	}
	
	
	// Sale Functions
	public Sale createNewSale(String customerId){
		Sale sale = new Sale("SaleId", customerId);
		activeSales.put(customerId, sale);
		return sale; 
	}
	public void processSale(Sale sale) {
		double customerDiscount = 0.0;
		
		
		List<SaleLineItem> itemList = sale.getItemList();
		
		for(SaleLineItem item : itemList) {
			warehouse.take(item.getProductId(), item.getQty());
		}
		
		Customer customer = getCustomer(sale.getCustomerId());			
		customerDiscount = customer.getCustomerDiscount();
		customer.consumePoints();
		
		double totalTransaction = sale.getTotal() - customerDiscount;
		customer.addPoints(sale.calculateCustomerPoints());	
		
		supermarketView.showTotalTransaction(totalTransaction);
		
		saleHistory.add(sale);
	}
	public void addItem(String customerId, Product product, int qty){
		SaleLineItem item = new SaleLineItem(product, qty);		
		
		// if has promotion discount (then promotion discount is applied)
		if(discounts.hasPromotionDiscount(product.getId())) {
			item.setDiscount(discounts.calculatePromotionDiscount(product.getId(), qty));
		}
		// else if has bulk discount (then bulk discount is applied)
		else if (discounts.hasBulkDiscount(product.getId())) {
			item.setDiscount(discounts.calculateBulkDiscount(product.getId(), qty));
		}		
		
		Sale sale = getActiveSale(customerId);								
		if(sale == null) {					
			sale = createNewSale(customerId);
			sale.addItem(item);
		}
		else {
			sale.addItem(item);
		}
	}
	
	// Discount Functions
	public double calculateBulkDiscount(String productId, int qty) {
		return discounts.calculateBulkDiscount(productId, qty);
	}
	public double calculatePromotionDiscount(String productId, int qty) {
		return discounts.calculatePromotionDiscount(productId, qty);
	}
	public Map<Integer,Double> getBulkDiscounts(String productId) {
		return discounts.getBulkDiscounts(productId);
	}
	public void addBulkDiscount(String productId, int qty, double discount) {
		discounts.addBulkDiscount(productId, qty, discount);
	}
	public void addPromotionDiscount(String productId, double discount) {
		discounts.addPromotionDiscount(productId, discount);
	}
	
	
	// Warehouse Functions
	public Product getProduct(String productId) {
		return warehouse.getProduct(productId);
	}
	public List<Product> getProductList() {
		return productList;
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
		SupermarketView view = new SupermarketSystemView(this);
		view.setController(new SupermarketSystemController(this, view));
		setView(view);
	}
	
	// Login Functions
	public void login_Customer(String id){
		SupermarketView view = new SupermarketCustomerView(this);
		view.setController(new SupermarketCustomerController(this, view, id));
		setView(view);
	}
	public void login_Manager(String id){
		SupermarketView view = new SupermarketManagerView(this);
		view.setController(new SupermarketManagerController(this, view, id));
		setView(supermarketView);
	}
	public void login_SalesStaff(String id){
		
		SupermarketView view = new SupermarketSalesStaffView(this);
		view.setController(new SupermarketSalesStaffController(this, view, id));
		
		setView(supermarketView);
	}	
	public void login_WarehouseStaff(String id){
		
		SupermarketView view = new SupermarketWarehouseStaffView(this);
		view.setController(new SupermarketWarehouseStaffController(this, view, id));
		setView(view);
	}
	

	
	
	// User Id Functions
	public boolean validCustomerId(String id){
		return customerMap.containsKey(id);
	}
	public boolean validManagerId(String id){
		return managerMap.containsKey(id);
	}
	public boolean validSalesStaffId(String id){
		return salesStaffMap.containsKey(id);
	}
	public boolean validWarehouseStaffId(String id){
		return warehouseStaffMap.containsKey(id);
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
		
		List<Customer> customerList = getCustomerList();
		for (Customer customer : customerList) { 
		    System.out.println(customer.toString());
		}
	}
	public void printManagerList() {
		System.out.println("printManagerList()");
		
		List<Manager> managerList = getManagerList();
		for (Manager manager : managerList) {
			System.out.println(manager.toString());	
		}
	}
	public void printSalesStaffList() {
		System.out.println("printSalesStaffList()");
		
		List<SalesStaff> salesStaffList = getSalesStaffList();
		for (SalesStaff salesStaff : salesStaffList) { 
		    System.out.println(salesStaff.toString());
		}
	}
	public void printWarehouseStaffList() {
		System.out.println("printWarehouseStaffList()");
		
		List<Employee> warehouseStaffList = getWarehouseStaffList();
		for (Employee warehouseStaff : warehouseStaffList) { 
		    System.out.println(warehouseStaff.toString());
		}
	}
	public void printActiveSales() {
		System.out.println("printWarehouseStaffList()");
		
		List<Sale> activeSales = getActiveSalesList();
		for (Sale sale : activeSales) { 
		    System.out.println(sale.toString());
		}
	}
	public void printSaleHistory() {
		System.out.println("printWarehouseStaffList()");
		for (Sale sale : saleHistory) { 
		    System.out.println(sale.toString());
		}
	}
		
	
}
