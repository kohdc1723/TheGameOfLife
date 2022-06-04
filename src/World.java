import java.util.ArrayList;

public class World {
	
	/* ------------------------------ Field ------------------------------ */
	public static final int GERERATE_MAX = 100;
	public static final int GENERATE_HERBIVORE = 80;
	public static final int GENERATE_PLANT = 60;
	public static final int GENERATE_CARNIVORE = 50;
	public static final int GENERATE_OMNIVORE = 45;
	private final Cell[][] worldCells;
	private final int height;
	private final int width;
	
	/* ------------------------------ Constructor ------------------------------ */
	public World(int height, int width) {
		this.height = height;
		this.width = width;
		this.worldCells = new Cell[height][width];
	}
	
	/* ------------------------------ Method ------------------------------ */
	public void init() {
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				this.worldCells[row][col] = new Cell(row, col, this);
				generateLife(row, col);
				this.worldCells[row][col].paintCell();
			}
		}
	}

	public void generateLife(int row, int col) {
		int random = RandomGenerator.nextNumber(GERERATE_MAX);
		if (random >= GENERATE_HERBIVORE) {
			this.worldCells[row][col].addLife(new Herbivore(this.worldCells[row][col]));
		} else if (random >= GENERATE_PLANT) {
			this.worldCells[row][col].addLife(new Plant(this.worldCells[row][col]));
		} else if (random >= GENERATE_CARNIVORE) {
			this.worldCells[row][col].addLife(new Carnivore(this.worldCells[row][col]));
		} else if (random >= GENERATE_OMNIVORE) {
			this.worldCells[row][col].addLife(new Omnivore(this.worldCells[row][col]));
		} else {
			this.worldCells[row][col].addLife(null);
		}
	}

	public ArrayList<Lifeform> getAllLife() {
		ArrayList<Lifeform> allLife = new ArrayList<Lifeform>();
		Lifeform life = null;
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				life = this.worldCells[row][col].getLifeform();
				if (life != null) {
					allLife.add(life);
				}
			}
		}
		return allLife;
	}

	public void updateWorld() {
		for (Lifeform life : getAllLife()) {
			life.actByTurn();
		}
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				getCellAt(row, col).paintCell();
			}
		}
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}
	
	public Cell getCellAt(int row, int col) {
		return this.worldCells[row][col];
	}
}
