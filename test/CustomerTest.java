package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import user.Customer;

public class CustomerTest {

	Customer cust1;
	
	@Before
	public void setUp() {
		cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(100);
	}

	@Test
	public void idTest() {
		assertEquals("123", cust1.getId());
	}
	@Test
	public void nameTest() {
		assertEquals("Harris", cust1.getName());
	}
	@Test
	public void addressTest() {
		assertEquals("41 Happy st", cust1.getAddress());
	}
	@Test
	public void phoneTest() {
		assertEquals("041234456", cust1.getPhoneNo());
	}
	@Test
	public void pointsTest() {
		assertEquals(100, cust1.getPoints(), 0.001);
	}
	@Test
	public void consumePointsTest() {
		cust1.consumePoints();
		assertEquals(0,cust1.getPoints(),0.001);
	}

}
