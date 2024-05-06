import java.awt.Graphics;
import java.awt.color.*;
import javax.swing.JPanel;

public abstract class AnimatedPanel extends JPanel{
	
	public static final int SCREEN_WIDTH = 220;
	public static final int SCREEN_HEIGHT = 335;
	public static final int SCREEN_DISTANCE_X = 15;
	public static final int SCREEN_DISTANCE_Y = 45;
	
	private static final long serialVersionUID = 1L;
	
	public AnimatedPanel() {
		// JPanel constructor with true says to use DoubleBuffering
		super(false);
	}
	
	// All child classes must implement this method
	public abstract void updateAnimation();


	
	public abstract void clickEvent(int x, int y);
}