package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Warehouse {

	
	private Map<String, Integer> inventory;
	private Map<String, Location> productLocation;
	private Map<String, List<Supplier>> suppliers;
	
	Warehouse(){
		inventory = createInventory();
	}
	
	private Map<String, Integer> createInventory() {
		//System.out.println("createInventory()");
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
	public Map<String, Integer> getInventory(){
		return inventory;
	}
	public Map<String, Location> getProductLocation(){
		return productLocation;
	}
	public Map<String, List<Supplier>> getSuppliers(){
		return suppliers;
	}
	
		
	public void restock(String productId, int qty) {
		Integer productStock = inventory.get(productId);
		inventory.remove(productId);
		productStock = new Integer(productStock.intValue() + qty);
		inventory.put(productId,productStock);
	}
	public void take(String productId, int qty) {
		Integer productStock = inventory.get(productId);
		inventory.remove(productId);
		productStock = new Integer(productStock.intValue() - qty);
		inventory.put(productId,productStock);
	}
	public int getStockLevel(String productId) {
		Integer productStock = inventory.get(productId);
		return productStock.intValue();
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
			System.out.println("\t" + key + ": " + value.toString());
		}
	}
	
}
