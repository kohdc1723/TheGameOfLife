import java.awt.Color;
import java.util.ArrayList;

public abstract class Lifeform {

	/* ---------- Field ---------- */
	protected Cell residingCell;
	private Color color;
	
	/* ---------- Constructor ---------- */
	public Lifeform(Cell cell, Color color) {
		this.residingCell = cell;
		this.color = color;
	}
	
	/* ---------- Method ---------- */
	public abstract void actByTurn();
	
	public abstract void reproduce();
	
	public abstract boolean isReproducable();
	
	public ArrayList<Cell> getAdjacentCells() {
		ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
		for (int row = this.residingCell.getRowIndex() - 1; row <= this.residingCell.getRowIndex() + 1; row++) {
			for (int col = this.residingCell.getColIndex() - 1; col <= this.residingCell.getColIndex() + 1; col++) {
				if ( (0 <= row && row <= this.residingCell.getWorld().getHeight() - 1) &&
						(0 <= col && col <= this.residingCell.getWorld().getWidth() - 1) ) {
					if (this.residingCell.getWorld().getCellAt(row, col) != this.residingCell) {
						adjacentCells.add(this.residingCell.getWorld().getCellAt(row, col));
					}
				}
			}
		}
		return adjacentCells;
	}
	
	public ArrayList<Cell> getAdjacentEmpty() {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() == null) {
				emptyCells.add(cell);
			}
		}
		return emptyCells;
	}
	
	public int countAdjacentEmpty() {
		int count = 0;
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() == null) {
				count++;
			}
		}
		return count;
	}
	
	public int countAdjacentHerbivores() {
		int count = 0;
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() instanceof Herbivore) {
				count++;
			}
		}
		return count;
	}
	
	public int countAdjacentPlants() {
		int count = 0;
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() instanceof Plant) {
				count++;
			}
		}
		return count;
	}
	
	public int countAdjacentCarnivores() {
		int count = 0;
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() instanceof Carnivore) {
				count++;
			}
		}
		return count;
	}
	
	public int countAdjacentOmnivores() {
		int count = 0;
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() instanceof Omnivore) {
				count++;
			}
		}
		return count;
	}
	
	public Cell chooseOneCell(ArrayList<Cell> cellsList) {
		int size = cellsList.size();
		if (size == 0) {
			return null;
		} else {
			RandomGenerator.reset();
			int index = RandomGenerator.nextNumber(size);
			return cellsList.get(index);
		}
	}
	
	public Cell getResidingCell() {
		return this.residingCell;
	}
	
	public Color getColor() {
		return this.color;
	}
}
