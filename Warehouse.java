package main;
import java.util.*;

public class Warehouse {

	private Map<Location, Product> locations = new HashMap<Location, Product>();
	private Map<String , Integer> inventory = new HashMap<String, Integer>();
	private Map<String, Supplier> suppliers = new HashMap<String, Supplier>();
	
	public Warehouse() {
		createInventory();
	}
	
	public void updateLocation(Location location, Product product) {
		locations.put(location, product);
		inventory.put(product.getID(), getStockLevel(product.getID()) + 1);
	}
	
	public void removeLocation(Location location) {
		inventory.put(locations.get(location).getID(), getStockLevel(locations.get(location).getID()) -1);
		locations.remove(location);
	}
	
	public Product getProductbyLocation(Location location) {
		
		return locations.get(location);
			
	}
	
	public LinkedList<Location> getProductLocation(Product product) {
		List<Location> locationList = new LinkedList<Location>();
		for (Location temp:locations.keySet()) {
			if(locations.get(temp) == product) {
				locationList.add(temp);
			}
		}
		return  (LinkedList<Location>) locationList;
	}
	
	private void createInventory() {
		for (String id: inventory.keySet()) {
			for (Location temp: locations.keySet()) {
				if (id == locations.get(temp).getID()) {
					inventory.put(id, getStockLevel(id) + 1);
				}
				else if (id == null) {
					inventory.put(locations.get(temp).getID(), 0);
				}
			}
		}
	}
	
	public int getStockLevel(String id) {
		for (String temp: inventory.keySet()) {
			if (id == temp) {
				return inventory.get(id);
			}
		}
		return 0;
	}
	
	public void setProductSupplier(String id, Supplier supplier) {
		suppliers.put(id, supplier);
	}
	
	public Supplier getProductSupplier(String id) {
		return suppliers.get(id);
	}
	
	public String toString() {
		String total = "";
		for (String id: inventory.keySet()) {
			
		}
		return total;
	}
}
