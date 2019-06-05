package warehouse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import sale.Sale;
import sale.SaleLineItem;

public class Warehouse {

	private Map<String, Product> productMap;
	private Map<String, Integer> inventory;
	private Map<String, Location> productLocation;
	private Map<String, Supplier> suppliers;
	
	public Warehouse(){
		productMap = importProductMap();
		inventory = importInventory();
		suppliers = new HashMap<String, Supplier>();
	}
	
	private Map<String, Product> importProductMap() {
		Map<String, Product> productMap = new HashMap<String, Product>();
		
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
					
					productMap.put(id, new Product(id, name, type, price));
				}
				else {
					//System.out.println("productInfo Length:  " + Integer.toString(productInfo.length));
				}
					
				
			}		
			reader.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return productMap;
	}
	private Map<String, Integer> importInventory() {
		Map<String, Integer> inventory = new HashMap<String, Integer>();
		
		String id = "";
		Integer qty = new Integer(0);
					 
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/warehouse_inventory.txt").getFile()));
			
			String line;
			String[] warehouseInventory;
			while ((line = reader.readLine()) != null) {
				warehouseInventory = line.split("\t");
				
				if(warehouseInventory.length == 2) {
					id = warehouseInventory[0];
					qty = new Integer(Integer.parseInt(warehouseInventory[1]));
					
					inventory.put(id,qty);
				}
				else {
					System.out.println("productInfo Length:  " + Integer.toString(warehouseInventory.length));
				}
					
				
			}		
			reader.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inventory;
	}
	private Map<String,  List<Supplier>> importSupplier(){
		Map<String,  List<Supplier>> supplierMap = new HashMap<String,  List<Supplier>>();
		
		String id = "";
		Integer qty = new Integer(0);
					 
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(getClass().getResource("/SystemInfo/supplier.txt").getFile()));

			String line;
			while ((line = reader.readLine()) != null) {
				
				Scanner supplierScanner = new Scanner(line);
				supplierScanner.useDelimiter("\t");
				
				String supplierId = supplierScanner.next();
				String supplierName = supplierScanner.next();
				
				Supplier supplier = new Supplier(supplierId, supplierName);
				
				Scanner productScanner = new Scanner(supplierScanner.next());
				productScanner.useDelimiter(",");
				while(productScanner.hasNext()) {
					String productId = productScanner.next();
					
					List<Supplier> supplierList = supplierMap.get(productId);
					
					if(supplierList == null) {
						supplierList = new ArrayList<Supplier>();
						supplierMap.put(productId, supplierList);
					}
					
					supplierList.add(supplier);
				}
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return supplierMap;
	}
	public Product getProduct(String productId) {		
		return productMap.get(productId);
	}
	public List<Product> getProductList() {
		return new ArrayList<Product>(productMap.values());
	}
	public void restock(String productId, int qty) {
		int productStock = inventory.get(productId).intValue();
		productStock += qty;
		
		inventory.remove(productId);		
		inventory.put(productId, new Integer(productStock));
		updateInventoryFile();
	}
	public void take(String productId, int qty) {
		int productStock = inventory.get(productId).intValue();
		productStock -= qty;
		
		inventory.remove(productId);		
		inventory.put(productId, new Integer(productStock));
		updateInventoryFile();
	}
	public int getStockLevel(String productId) {
		return inventory.get(productId).intValue();			
	}
	
	public boolean validProductId(String productId) {
		return productMap.containsKey(productId);
	}
	
	public Map<String, Supplier> getSuppliers() {
		return suppliers;
	}
	
	public void printInventory() {
		Set<String> keys = inventory.keySet();
		
		Iterator<String> iterator = keys.iterator();
		String key;
		Integer value;
		
		System.out.println("\n\nWarehouse Inventory: ");
		while(iterator.hasNext()) {
			key = iterator.next();
			value = inventory.get(key);
			System.out.println("\t" + String.format("%-9s:   %-5s", key, value.toString()));
		}
	}
	
	public void updateInventoryFile() {
		
		String filename = "/SystemInfo/warehouse_inventory.txt";
		
		Set<String> keys = inventory.keySet();		
		Iterator<String> iterator = keys.iterator();
		String key;
		Integer value;
		
		try {
			FileWriter writer = new FileWriter(getClass().getResource(filename).getFile());
			BufferedWriter buff = new BufferedWriter(writer);
			
			
			
			while(iterator.hasNext()) {
				String line = "";
				key = iterator.next();		
				line = line.concat(key);
				line = line.concat("\t");
				line = line.concat(inventory.get(key).toString());
				
				buff.write(line);
				buff.newLine();
			}
					
			buff.flush();
			buff.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\nError\n");
		}	
	}
	
	
}
