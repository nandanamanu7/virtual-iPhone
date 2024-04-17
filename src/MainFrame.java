import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MainFrame extends JFrame {
	private Screen screen = new Screen();
	private App testApp = new InitialApp();
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 800;
	public static final int SCREEN_WIDTH = 220;
	public static final int SCREEN_HEIGHT = 350;
	
	private static final int SCREEN_PANEL = 0;
	private static final int TICTACTOE_PANEL = 1;
	
	private AnimatedPanel[] screens;
	private int currentPanel = 0;
	
	public static int delay = 10;
	
	public MainFrame() {
		
	}
	
	 public static void startGUI() throws InterruptedException {
	        MainFrame theGUI = new MainFrame();
	        SwingUtilities.invokeLater( () -> theGUI.createFrame(theGUI) );
	        synchronized (theGUI ) {
	            theGUI.wait();
	        }
	    }
	 
	 public void createFrame(Object semaphore) {
		 this.setTitle("Home Screen");
		 this.setSize(HEIGHT,WIDTH);
	 }
	 
	 
}
