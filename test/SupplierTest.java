package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import warehouse.Supplier;

public class SupplierTest {
	
	Supplier supplier1;
	
	@Before
	public void Setup() {
		
		supplier1 = new Supplier("s3102202", "Mark Sales");
	}

	@Test
	public void testGetId() {
		System.out.println(supplier1.getId());
		assertEquals("s3102202",supplier1.getId());
	}

	@Test
	public void testGetName() {
		System.out.println(supplier1.getName());
		assertEquals("Mark Sales",supplier1.getName());
		
		
	}

}
