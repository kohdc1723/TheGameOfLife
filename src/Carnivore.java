import java.awt.Color;
import java.util.ArrayList;

public class Carnivore extends Animal {

	/* ---------- Field ---------- */
	public static int ONE = 1;
	public static int TWO = 2;
	public static int THREE = 3;
	
	/* ---------- Constructor ---------- */
	public Carnivore(Cell cell) {
		super(cell, Color.RED);
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
			} else if (nextCell.getLifeform() instanceof Herbivore ||
				nextCell.getLifeform() instanceof Omnivore) {
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
	public ArrayList<Cell> getInteractableCells() {
		ArrayList<Cell> interactableCells = new ArrayList<Cell>();
		for (Cell cell : getAdjacentCells()) {
			if (cell.getLifeform() == null ||
					cell.getLifeform() instanceof Herbivore ||
					cell.getLifeform() instanceof Omnivore) {
				interactableCells.add(cell);
			}
		}
		return interactableCells;
	}

	@Override
	public void reproduce() {
		Cell cell = chooseOneCell(getAdjacentEmpty());
		if (isReproducable()) {
			cell.addLife(new Carnivore(cell));
		}
	}

	@Override
	public boolean isReproducable() {
		if (countAdjacentCarnivores() >= ONE &&
				countAdjacentEmpty() >= THREE &&
				countAdjacentHerbivores() + countAdjacentOmnivores() == TWO) {
			return true;
		} else {
			return false;
		}
	}
}
