package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sale.Sale;
import user.SalesStaff;

public class SalesStaffTest {
	
	
	SalesStaff staff1;
	Sale Sale1;
	Sale Sale2;
	
	@Before
	public void setUp() {
		staff1 = new SalesStaff("s123", "Harris", "haha", "1123");
		Sale1 = new Sale("s123", "123", "1");
		Sale2 = new Sale("s123", "456", "2");
	}
	
	@Test
	public void getHistoryEmptyTest() {
		assertEquals(0, staff1.getSalesHistory().size());
	}
	
	@Test
	public void addToSalesHistory() {
		staff1.addToSalesHistory(Sale1);
		assertEquals(1, staff1.getSalesHistory().size());
	}
	@Test
	public void getSalesHistoryTest2() {
		staff1.addToSalesHistory(Sale1);
		staff1.addToSalesHistory(Sale2);
		assertEquals(Sale2,staff1.getSalesHistory().get(1));
	}

}
