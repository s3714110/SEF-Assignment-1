package warehouse;

public class Location {

public static enum FLOOR{F1,F2,F3};

public static enum ISLE {A01,A02,A03,A04,A05,A06,A07};

public static enum SECTION {ONE,TWO,THREE};

public static enum SHELF {S001,S002,S003,S004};

	private FLOOR floor;
	private ISLE aisle;
	private SECTION section;
	private SHELF shelf;

	public Location(FLOOR floor, ISLE aisle, SECTION section, SHELF shelf) {
		this.floor = floor;
		this.aisle = aisle;
		this.section = section;
		this.shelf = shelf;
	}

	public FLOOR getFloor() {
		return floor;
	}

	public ISLE getIsle() {
		return aisle;
	}

	public SECTION getSection() {
		return section;
	}

	public SHELF getShelf() {
		return shelf;
	}

}