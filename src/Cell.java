import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Cell extends JPanel {
	/* ---------- Field ---------- */
	private int rowIndex;
	private int colIndex;
	private Lifeform lifeform;
	private World world;
	
	/* ---------- Constructor ---------- */
	public Cell(int row, int col, World world) {
		this.rowIndex = row;
		this.colIndex = col;
		this.world = world;
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/* ---------- Method ---------- */	
	public void addLife(Lifeform lifeform) {
		if (lifeform == null) {
			this.lifeform = null;
		} else {
			this.lifeform = lifeform;
			lifeform.residingCell = this;
		}
	}
	
	public void removeLife() {
		this.lifeform = null;
	}
	
	public void paintCell() {
		if (this.lifeform == null) {
			setBackground(Color.white);
		} else {
			setBackground(this.lifeform.getColor());
		}
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public int getRowIndex() {
		return this.rowIndex;
	}
	
	public int getColIndex() {
		return this.colIndex;
	}
	
	public Lifeform getLifeform() {
		return this.lifeform;
	}
}
