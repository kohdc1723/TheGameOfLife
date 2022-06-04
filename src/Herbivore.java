import java.awt.Color;
import java.util.ArrayList;

public class Herbivore extends Animal {
	
	/* ---------- Field ---------- */
	public static int ONE = 1;
	public static int TWO = 2;
	
	/* ---------- Constructor ---------- */
	public Herbivore(Cell cell) {
		super(cell, Color.yellow);
	}
	
	/* ---------- Method ---------- */
	@Override
	public void actByTurn() {
		reproduce();
		behave();
	}
	
	@Override
	public void behave() {
		ArrayList<Cell> interactableCells = getInteractableCells();
		if (interactableCells.size() > 0) {
			Cell nextCell = chooseOneCell(interactableCells);
			if (nextCell.getLifeform() == null) {
				move(this.residingCell, nextCell);
			} else if (nextCell.getLifeform() instanceof Plant) {
				eat(this.residingCell, nextCell);
			}
		} else {
			stay();
		}
		
		if (lifespan < 0) {
			die();
		}
	}
	
	@Override
	public void reproduce() {
		Cell cell = chooseOneCell(getAdjacentEmpty());
		if (isReproducable()) {
			cell.addLife(new Herbivore(cell));
		}
	}
	
	@Override
	public boolean isReproducable() {
		if (countAdjacentHerbivores() >= ONE &&
				countAdjacentEmpty() >= TWO &&
				countAdjacentPlants() >= TWO) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public ArrayList<Cell> getInteractableCells() {
		ArrayList<Cell> interactableCells = new ArrayList<Cell>();
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() == null || cell.getLifeform() instanceof Plant) {
				interactableCells.add(cell);
			}
		}
		return interactableCells;
	}
}
