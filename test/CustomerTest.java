package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Customer;

public class CustomerTest {

	Customer cust1;
	
	@Before
	public void setUp() {
		cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(100);
	}

	@Test
	public void idTest() {
		System.out.println(cust1.getId());
		assertEquals("123", cust1.getId());
	}
	@Test
	public void nameTest() {
		System.out.println(cust1.getName());
		assertEquals("Harris", cust1.getName());
	}
	@Test
	public void addressTest() {
		System.out.println(cust1.getAddress());
		assertEquals("41 Happy st", cust1.getAddress());
	}
	@Test
	public void phoneTest() {
		System.out.println(cust1.getPhoneNum());
		assertEquals("041234456", cust1.getPhoneNum());
	}
	@Test
	public void pointsTest() {
		System.out.println(cust1.getPoints());
		assertEquals(100, cust1.getPoints(), 0.001);
	}
	@Test
	public void consumePointsTest() {
		cust1.consumePoints(50);
		System.out.println(cust1.getPoints());
		assertEquals(50,cust1.getPoints(),0.001);
	}

}
