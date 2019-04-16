package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.Receipt;
import main.SalesStaff;

public class SalesStaffTest {
	
	
	SalesStaff staff1;
	Receipt receipt1;
	Receipt receipt2;
	
	@Before
	public void setUp() {
		staff1 = new SalesStaff("s123", "Harris", "haha", "1123");
		receipt1 = new Receipt("s123", "123", "1");
		receipt2 = new Receipt("s123", "456", "2");
	}
	
	@Test
	public void getHistoryEmptyTest() {
		assertEquals(0, staff1.getSalesHistory().size());
	}
	
	@Test
	public void addToSalesHistory() {
		staff1.addToSalesHistory(receipt1);
		assertEquals(1, staff1.getSalesHistory().size());
	}
	@Test
	public void getSalesHistoryTest2() {
		staff1.addToSalesHistory(receipt1);
		staff1.addToSalesHistory(receipt2);
		assertEquals(receipt2,staff1.getSalesHistory().get(1));
	}

}
