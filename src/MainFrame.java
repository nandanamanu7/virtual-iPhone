import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MainFrame extends JFrame {
	// To eliminate a warning showing in Eclipse
	private static final long serialVersionUID = 1L;
	
	// To set bounds of the frame
	public static final int WIDTH = 400;
	public static final int HEIGHT = 800;
	public static final int SCREEN_WIDTH = 220;
	public static final int SCREEN_HEIGHT = 350;
	
	// Constants for each screen
	private static final int SCREEN_PANEL = 0;
	private static final int TICTACTOE_PANEL = 1;
	
	// Set up array of AnimatedPanels for each screen along with int for the current screen
	private AnimatedPanel[] screens;
	private int currentPanel = 0;
	
	// Used to establish the animation speed
	public static int delay = 10;
	
	public MainFrame(){
		
	}
	
	public void run() throws InterruptedException{
		System.out.println("this works");
		MainFrame theGUI = new MainFrame();
        SwingUtilities.invokeLater( () -> theGUI.createFrame(theGUI) );
        synchronized (theGUI ) {
            theGUI.wait();
        }
	}
	 
	 public void createFrame(Object semaphore) {
		 this.setTitle("Virtual iPhone");
		 this.setSize(HEIGHT,WIDTH);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	     // Set the current frame and this JFrame to be visible
	     this.setVisible(true);

	     // tell the main thread that we are done creating our stuff
	     synchronized (semaphore) {
	         semaphore.notify();
	     }
	 }
	 
	 private void showPanel(int index) {
	        System.out.printf("Show Panel. Thread is: %s\n", Thread.currentThread().getName());

	        // hide the current panel
	        screens[currentPanel].setVisible(false);

	        // show the correct panel
	        currentPanel = index;
	        screens[currentPanel].setVisible(true);

	        // The animation will start on the main thread.
	        // Do nothing in the UI thread
	    }
	 
}
