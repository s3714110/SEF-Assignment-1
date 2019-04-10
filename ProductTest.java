package product;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	
	Product product1;
	
	@Before
	public void setUp()
	{
		product1 = new Product("s3661109","OATS","food",4.95);
	}

	@Test
	public void testGetID() {
			System.out.println(product1.getID());
			assertEquals("s3661109",product1.getID());
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
		assertEquals(4.95,product1.getPrice(),0.001);
	}
	
	



}
