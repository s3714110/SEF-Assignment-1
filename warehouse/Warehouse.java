package warehouse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Warehouse {

	private Map<String, Product> productMap;
	private Map<String, Integer> inventory;
	private Map<String, Location> productLocation;
	private Map<String, Supplier> suppliers;
	
	public Warehouse(){
		productMap = importProductMap();
		inventory = importInventory();
		suppliers = new HashMap<>();
	}
	
	private Map<String, Product> importProductMap() {
		//System.out.println("importProductList()");
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
	}
	public void take(String productId, int qty) {
		int productStock = inventory.get(productId).intValue();
		productStock -= qty;
		
		inventory.remove(productId);		
		inventory.put(productId, new Integer(productStock));
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
			System.out.println("\t" + key + ": " + value.toString());
		}
	}
	
}
