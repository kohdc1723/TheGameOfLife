import java.awt.Color;
import java.util.ArrayList;

public abstract class Animal extends Lifeform {

	/* ---------- Field ---------- */
	public static int MAX_LIFESPAN = 5;
	protected int lifespan = MAX_LIFESPAN;
	
	/* ---------- Constructor ---------- */
	public Animal(Cell cell, Color color) {
		super(cell, color);
	}
	
	/* ---------- Method ---------- */
	public abstract void behave();
	
	public abstract ArrayList<Cell> getInteractableCells();
	
	public void move(Cell fromCell, Cell toCell) {
		if (toCell != null) {
			fromCell.removeLife();
			toCell.addLife(this);
		}
		starve();
	}
	
	public void eat(Cell eater, Cell beingEaten) {
		if (beingEaten != null) {
			eater.removeLife();
			beingEaten.addLife(this);
		}
		eaten();
	}
	
	public void stay() {
		starve();
	}
	
	public void die() {
		this.residingCell.removeLife();
	}
	
	public void starve() {
		this.lifespan--;
	}
	
	public void eaten() {
		this.lifespan = MAX_LIFESPAN;
	}

}
