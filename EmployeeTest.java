package system;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class EmployeeTest
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
		System.out.println(emp1.getId());
		assertEquals("007", emp1.getId());
	}
	
	@Test
	public void testName()
	{
		System.out.println(emp1.getName());
		assertEquals("Charlotte", emp1.getName());
	}

}
