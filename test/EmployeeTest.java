package test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import user.Employee;

public class EmployeeTest
{

	Employee emp1;
	
	@Before
	public void setUp()
	{
		emp1 = new Employee("007", "Charlotte", "47 Howard Street", "12345");
	}
	
	@Test
	public void testID()
	{
		assertEquals("007", emp1.getId());
	}
	
	@Test
	public void testName()
	{
		assertEquals("Charlotte", emp1.getName());
	}
	
	@Test
	public void testAddress()
	{
		assertEquals("47 Howard Street", emp1.getAddress());
	}
	
	@Test
	public void testPhoneNo()
	{
		assertEquals("12345", emp1.getPhoneNo());
	}
	
}
