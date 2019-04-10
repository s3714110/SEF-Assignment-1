package assignment1;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTest {
	
Customer cust1;
	
	@Before
	public void setUp() {
		cust1 = new Customer("123", "Harris", "100");
		
	}

	@Test
	public void idTest() {
		System.out.println(cust1.getCustId());
		assertEquals("123", cust1.getCustId());
	}
	@Test
	public void nameTest() {
		System.out.println(cust1.getCustName());
		assertEquals("Harris", cust1.getCustName());
	}
	@Test
	public void pointsTest() {
		System.out.println(cust1.getCustPoints());
		assertEquals("100", cust1.getCustPoints());
	}
	
	
	
	
	

	//@Test
	//public void test() {
		//fail("Not yet implemented");
	//}

}
