import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class TurnListener extends MouseAdapter {
	/* ---------- Field ---------- */
    private GameFrame game;
    
    /* ---------- Constructor ---------- */
    public TurnListener(GameFrame game) {
        this.game = game;
    }

    /* ---------- Method ---------- */
    public void mouseClicked(MouseEvent e) {
        this.game.takeTurn();
    }
}
