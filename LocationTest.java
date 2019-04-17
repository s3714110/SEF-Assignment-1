package location;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import location.Location.AISLE;
import location.Location.FLOOR;
import location.Location.SECTION;
import location.Location.SHELF;

public class LocationTest {

	Location location1;
	
	@Before
	public void Setup() {
		location1 = new Location(FLOOR.F2,AISLE.A06,SECTION.THREE,SHELF.S004);
	}
	

	@Test
	public void testGetFloor() {
		System.out.println(location1.getFloor());
		assertEquals(FLOOR.F2,location1.getFloor());
	}

	@Test
	public void testGetAisle() {
		System.out.println(location1.getAisle());
		assertEquals(AISLE.A06,location1.getAisle());
	}

	@Test
	public void testGetSection() {
		System.out.println(location1.getSection());
		assertEquals(SECTION.THREE,location1.getSection());
	}

	@Test
	public void testGetShelf() {
		System.out.println(location1.getShelf());
		assertEquals(SHELF.S004,location1.getShelf());
	}

}
