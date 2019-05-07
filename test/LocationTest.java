package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import warehouse.Location;
import warehouse.Location.FLOOR;
import warehouse.Location.ISLE;
import warehouse.Location.SECTION;
import warehouse.Location.SHELF;

public class LocationTest {

	Location location1;
	
	@Before
	public void Setup() {
		location1 = new Location(FLOOR.F2,ISLE.A06,SECTION.THREE,SHELF.S004);
	}
	
	@Test
	public void testGetFloor() {
		assertEquals(FLOOR.F2,location1.getFloor());
	}

	@Test
	public void testGetisle() {
		assertEquals(ISLE.A06,location1.getIsle());
	}

	@Test
	public void testGetSection() {
		assertEquals(SECTION.THREE,location1.getSection());
	}

	@Test
	public void testGetShelf() {
		assertEquals(SHELF.S004,location1.getShelf());
	}

}