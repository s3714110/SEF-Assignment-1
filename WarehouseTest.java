package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Location;
import main.Location.FLOOR;
import main.Location.ISLE;
import main.Location.SECTION;
import main.Location.SHELF;
import main.Product;
import main.Warehouse;


public class WarehouseTest {
	
	Warehouse warehouse;
	Product product1;
	Product product2;
	Location location;

	@Before
	public void setUp() throws Exception {
		warehouse = new Warehouse();
	}

	@Test
	public void testUpdateLocationAndGetProduct() {
		Product product1 = new Product("1", "Chicken", "Food", 5.00);
		Product product2 =  new Product("2", "Soap", "Kitchen", 10.00);
		location = new Location(FLOOR.ONE, ISLE.A, SECTION.MEAT, SHELF.ONE);
		warehouse.updateLocation(location, product1);
		Assert.assertEquals("1", warehouse.getProductbyLocation(location).getID());
		Assert.assertEquals("Chicken", warehouse.getProductbyLocation(location).getName());
		Assert.assertEquals("Food", warehouse.getProductbyLocation(location).getType());
		Assert.assertEquals( 5.00, warehouse.getProductbyLocation(location).getPrice(),0);
	}

	@Test
	public void testRemoveLocation() {
		Product product1 = new Product("1", "Chicken", "Food", 5.00);
		Product product2 =  new Product("2", "Soap", "Kitchen", 10.00);
		location = new Location(FLOOR.ONE, ISLE.A, SECTION.MEAT, SHELF.ONE);
		warehouse.updateLocation(location, product1);
		warehouse.removeLocation(location);
		Assert.assertEquals(null, warehouse.getProductbyLocation(location));
		
	}
}
