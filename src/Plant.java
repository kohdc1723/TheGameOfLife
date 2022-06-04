import java.awt.Color;

public class Plant extends Lifeform {
	
	/* ---------- Field ---------- */
	public static int TWO = 2;
	public static int THREE = 3;
	
	/* ---------- Constructor ---------- */
	public Plant(Cell cell) {
		super(cell, Color.green);
	}
	
	/* ---------- Method ---------- */
	@Override
	public void actByTurn() {
		reproduce();
	}
	
	@Override
	public void reproduce() {
		Cell cell = chooseOneCell(getAdjacentEmpty());
		if (isReproducable()) {
			cell.addLife(new Plant(cell));
		}
	}
	
	@Override
	public boolean isReproducable() {
		if (countAdjacentPlants() >= TWO && countAdjacentEmpty() >= THREE) {
			return true;
		} else {
			return false;
		}
	}
}
