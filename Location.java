package location;

public class Location {

	enum FLOOR {
	};

	enum ISLE {
	};

	enum SECTION {
	};

	enum SHELF {
	};

	private FLOOR floor;
	private ISLE isle;
	private SECTION section;
	private SHELF shelf;

	public Location(FLOOR floor, ISLE isle, SECTION section, SHELF shelf) {
		this.floor = floor;
		this.isle = isle;
		this.section = section;
		this.shelf = shelf;
	}

	public FLOOR getFloor() {
		return floor;
	}

	public ISLE getIsle() {
		return isle;
	}

	public SECTION getSection() {
		return section;
	}

	public SHELF getShelf() {
		return shelf;
	}

}
