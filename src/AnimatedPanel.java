import java.awt.Graphics;
import java.awt.color.*;
import javax.swing.JPanel;

public abstract class AnimatedPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public AnimatedPanel() {
		// JPanel constructor with true says to use DoubleBuffering
		super(false);
	}
	
	// All child classes must implement this method
	public abstract void updateAnimation();

}
