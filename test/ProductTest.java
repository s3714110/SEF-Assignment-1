package test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import warehouse.Product;

public class ProductTest {
	final Double doubleTestAccuracy = 0.001;
	
	Product product1;
	
	@Before
	public void setUp()
	{
		product1 = new Product("s3661109","OATS","food",4.95);
	}

	@Test
	public void testGetId() {
			System.out.println(product1.getId());
			assertEquals("s3661109",product1.getId());
		}
	
	
	@Test
	public void testGetName() {
		System.out.println(product1.getName());
		assertEquals("OATS",product1.getName());
	}

    @Test
	public void testGetType() {
    	System.out.println(product1.getType());
		assertEquals("food",product1.getType());
	}

	@Test
	public void testGetPrice() {
		System.out.println(product1.getPrice());
		assertEquals(4.95,product1.getPrice(),doubleTestAccuracy);
	}
	
	



}
