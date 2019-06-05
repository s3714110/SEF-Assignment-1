package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import view.*;
import warehouse.Product;
import warehouse.Supplier;
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
	
	private Map<String, Double> salePercentage;
	private Map<String, Integer> saleQuantity;
	private double totalSale;
	private int fastMovingLevel;
	
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
		
		salePercentage = new HashMap<>();
		saleQuantity = new HashMap<>();
		totalSale = 0;
		fastMovingLevel = 10;
		
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
		List<Sale> saleHistory = new ArrayList<Sale>();

		String customerId = "";
		String saleId = "";
		String dateString ="";
		Date dateSale = null;
		int counter = 0;

		BufferedReader reader;
		try {
			reader = new BufferedReader(
					new FileReader(getClass().getResource("/SystemInfo/saleshistory.txt").getFile()));

			String line;
			String[] salesstaffInfo;
			while ((line = reader.readLine()) != null) {

				Scanner token = new Scanner(line);
				token.useDelimiter("\t");
				
				saleId = token.next();
				customerId = token.next();
				dateString = token.next();
				try {
					dateSale = new SimpleDateFormat("HH:mm:ss/dd/MM/yyyy").parse(dateString);
				} catch (ParseException e) {
					
				} 

				saleHistory.add(new Sale(saleId, customerId));
				saleHistory.get(counter).setDate(dateSale);

				while (token.hasNext()) {
					Scanner saleToken = new Scanner(token.next());
					saleToken.useDelimiter(",");
					String id = saleToken.next();
					int qty = Integer.parseInt(saleToken.next());
					double subTotal = Double.parseDouble(saleToken.next());

					saleHistory.get(counter).addItem(new SaleLineItem(warehouse.getProduct(id), qty));
					saleHistory.get(counter).getItem(id).setSubTotal(subTotal);

				}
				counter++;
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
		
		sale.setDate(new Date());
		
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
	
	public double getTotalSale() {
		totalSale = 0;
		for (Sale sale : saleHistory) {
			for (SaleLineItem saleLineItem : sale.getItemList()) {
				totalSale += saleLineItem.getSubtotal();
			}
		}
		
		return totalSale;
	}
	
	public SaleLineItem[] getMostSoldItems() {
		SaleLineItem[] mostSoldItems = new SaleLineItem[(warehouse.getProductList().size())];
	
		
		SaleLineItem temp;

		for (int i = 0; i < mostSoldItems.length; i++) {

			if (mostSoldItems[i] == null) {
				mostSoldItems[i] = new SaleLineItem(warehouse.getProductList().get(i),0);
			}

		}
		
		

		for (int i = 0; i < mostSoldItems.length; i++) {
			for (Sale sale : saleHistory) {
				for (SaleLineItem saleLineItem : sale.getItemList()) {
					if (0 == mostSoldItems[i].getProductId().compareTo(saleLineItem.getProductId())) {
						
						
						double percentage = 0;
						int quantity = 0;
						mostSoldItems[i].setSubTotal(mostSoldItems[i].getSubtotal() + saleLineItem.getSubtotal());

						mostSoldItems[i].add(saleLineItem.getQty());
						quantity = mostSoldItems[i].getQty();

						percentage = (mostSoldItems[i].getSubtotal() / getTotalSale())*100 ;

						salePercentage.put(mostSoldItems[i].getProductId(), percentage);
						saleQuantity.put(mostSoldItems[i].getProductId(), quantity);
						
					
					}
				}

			}
		}

		for (int i = 0; i < mostSoldItems.length; i++) {
			for (int j = i + 1; j < mostSoldItems.length; j++) {
				if(mostSoldItems[i].getSubtotal() < mostSoldItems[j].getSubtotal()) {
					temp = mostSoldItems[i];
					mostSoldItems[i] = mostSoldItems[j];
					mostSoldItems[j] = temp;
					
				}
			}
		}
		// Collections.sort(mostSoldItems, Collections.reverseOrder());

		return mostSoldItems;

	}
	
	public Product[] getProductStockList() {
		Product[] productStockList = new Product[warehouse.getProductList().size()];
		Product temp;
		
		for (int i = 0; i < productStockList.length; i++) {

			if (productStockList[i] == null) {
				productStockList[i] = new Product(warehouse.getProductList().get(i).getId(),
						warehouse.getProductList().get(i).getName(), 
						warehouse.getProductList().get(i).getType(),
						warehouse.getProductList().get(i).getPrice());
			}

		}
		
		for (int i = 0; i < productStockList.length; i++) {
			for (int j = i + 1; j < productStockList.length; j++) {
				if(warehouse.getStockLevel(productStockList[i].getId()) 
						< warehouse.getStockLevel(productStockList[j].getId())) {
					temp = productStockList[i];
					productStockList[i] = productStockList[j];
					productStockList[j] = temp;
				}
			}
		}
		return productStockList;
	}
	public double getSaleTotalbyDate(Date startDate, Date endDate){
		double totalDateSale = 0;
		for (Sale sale : saleHistory) {
			if((startDate.equals(sale.getDate()) || startDate.before(sale.getDate()))
				&& (endDate.equals(sale.getDate()) || endDate.after(sale.getDate()))) {
				totalDateSale += sale.getTotal();
			}
		}
		return totalDateSale;
	}
	
	public LinkedList<Sale> getSalesbyDate(Date startDate, Date endDate){
		LinkedList<Sale> dateSales = new LinkedList<>();
		for (Sale sale : saleHistory) {
			if((startDate.equals(sale.getDate()) || startDate.before(sale.getDate())) 
					&& (endDate.equals(sale.getDate()) || endDate.after(sale.getDate()))) {
				dateSales.add(sale);
			}
		}
		return dateSales;
	}
	
	public LinkedList<SaleLineItem> getFastMovingItems(){
		LinkedList<SaleLineItem> fastMovingItems = new LinkedList<>();
		for(int i = 0; i < getMostSoldItems().length; i++) {
			if(getMostSoldItems()[i].getQty() >= fastMovingLevel) {
				fastMovingItems.add(getMostSoldItems()[i]);
			}
		}
		return fastMovingItems;
		
	}
	public Map<String, Double> getSalePercentage() {

		return salePercentage;

	}
	
	public Map<String, Integer> getSaleQuantity() {

		return saleQuantity;

	}
	
	public Map<String, Supplier> getSuppliers() {
		return warehouse.getSuppliers();
	}
	
	public int getFastMovingLevel() {
		return fastMovingLevel;
	}
	
	public void setFastMovingLevel(int fastMovingLevel) {
		this.fastMovingLevel = fastMovingLevel;
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
	
	public void removeBulkDiscount(String productId, int qty) {
		discounts.removeBulkDiscount(productId, qty);
	}
	
	public void removeAllBulkDiscount(String productId) {
		discounts.removeBulkDiscountAll(productId);
	}
	public boolean hasBulkDiscount(String productId) {
		return discounts.hasBulkDiscount(productId);
	}
	public void addPromotionDiscount(String productId, double discount) {
		discounts.addPromotionDiscount(productId, discount);
	}
	
	public void removePromotionDiscount(String productId) {
		discounts.removePromotionDiscount(productId);
	}
	
	public boolean hasPromotionDiscount(String productId) {
		return discounts.hasPromotionDiscount(productId);
	}
	
	public double getPromotionalDiscount(String productId) {
		return discounts.getPromotionDiscount(productId);
	}
	public void removeSale(String customerId) {
		activeSales.remove(customerId);
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
		if (warehouse.getStockLevel(productId) < warehouse.getProduct(productId).getReplenishLevel()) {
			warehouse.restock(productId, warehouse.getProduct(productId).getRestockQty());
		}
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
		setView(view);
	}
	public void login_SalesStaff(String id){
		
		SupermarketView view = new SupermarketSalesStaffView(this);
		view.setController(new SupermarketSalesStaffController(this, view, id));
		
		setView(view);
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
		System.out.println("Active Sales Size: " + activeSales.size());
		if(activeSales.size() == 0) {
			System.out.println("No Active Sales");
		}
		else {
			for (Sale sale : activeSales) { 
			    System.out.println(sale.toString());
			}
		}
	}
	public void printSaleHistory() {
		System.out.println("printWarehouseStaffList()");
		for (Sale sale : saleHistory) { 
		    System.out.println(sale.toString());
		}
	}
		
	
}
