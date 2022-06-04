import java.awt.GridLayout;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	/* ---------- Field ---------- */
	private World world;
	
	/* ---------- Constructor ---------- */
	public GameFrame(World world) {
		this.world = world;
	}
	
	/* ---------- Method ---------- */
	public void init() {
		setTitle("The Game of Life");
		setLayout(new GridLayout(this.world.getHeight(), this.world.getWidth()));
		for (int row = 0; row < this.world.getHeight(); row++) {
			for (int col = 0; col < this.world.getWidth(); col++) {
				add(this.world.getCellAt(row, col));
			}
		}
		addMouseListener(new TurnListener(this));
	}
	
	public void takeTurn() {
		this.world.updateWorld();
	}
	
}
