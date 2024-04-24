import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MainFrame extends JFrame {
	// To eliminate a warning showing in Eclipse
	private static final long serialVersionUID = 1L;
	
	private static volatile boolean done = false;
	
	// Images
	private BufferedImage homeScreenImage;
	
	// To set bounds of the frame
	public static final int WIDTH = 250;
	public static final int HEIGHT = 450;
	public static final int SCREEN_WIDTH = 220;
	public static final int SCREEN_HEIGHT = 335;
	public static final int SCREEN_DISTANCE_X = 15;
	public static final int SCREEN_DISTANCE_Y = 45;
	public static final int BAR_WIDTH = 220;
	public static final int BAR_HEIGHT = 15;
	public static final int BAR_DISTANCE_X = 15;
	public static final int BAR_DISTANCE_Y = 30;
	
	// Constants for each screen
	private static final int SCREEN_PANEL = 0;
	private static final int TICTACTOE_PANEL = 1;
	
	// Set up array of AnimatedPanels for each screen along with int for the current screen
	private AnimatedPanel[] screens;
	private int currentPanel = 0;
	
	// Creates Panel for Phone Frame
	private AnimatedPanel phoneFrame;
 	
	// Used to establish the animation speed
	public static int delay = 10;
	
	public MainFrame(){
		this.screens = new AnimatedPanel[1];
		this.screens[SCREEN_PANEL] = new HomeScreenPanel();
		//this.screens[TICTACTOE_PANEL] = new TicTacToePanel();
		this.phoneFrame = new PhoneFramePanel();
	}
	
	
	public void run() throws InterruptedException{
		System.out.println("this works");
		MainFrame theGUI = new MainFrame();
        SwingUtilities.invokeLater( () -> theGUI.createFrame(theGUI) );
        synchronized (theGUI ) {
            theGUI.wait();
        }
        while(true) {
        	theGUI.startAnimation();
        }
	}
	 
	 public void createFrame(Object semaphore) {
		 this.setTitle("Virtual iPhone");
		 this.setSize(266,489);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 this.phoneFrame.setBounds(0,0,WIDTH, HEIGHT);
		 add(phoneFrame);
		 phoneFrame.setVisible(true);
		 
		 for (AnimatedPanel screen : screens) {
	        screen.setBounds(SCREEN_DISTANCE_X, SCREEN_DISTANCE_Y, SCREEN_WIDTH, SCREEN_HEIGHT);
	        add(screen);
	        screen.setVisible(false);
		 }
		 
		 this.currentPanel = SCREEN_PANEL;
		 screens[currentPanel].setVisible(true);
	     // Set the current frame and this JFrame to be visible
	     this.setVisible(true);

	     // tell the main thread that we are done creating our stuff
	     synchronized (semaphore) {
	         semaphore.notify();
	     }
	 }

	 private void showPanel(int index) {
	        System.out.printf("Show Panel. Thread is: %s\n", Thread.currentThread().getName());
	        
	        MainFrame.done = true;

	        // hide the current panel
	        screens[currentPanel].setVisible(false);

	        // show the correct panel
	        currentPanel = index;
	        screens[currentPanel].setVisible(true);

	        // The animation will start on the main thread.
	        // Do nothing in the UI thread
	    }
	 
	 public void startAnimation() {
		 MainFrame.done = false;
		 try {
			 while (!MainFrame.done) {
				 //phoneFrame.updateAnimation();
				 screens[currentPanel].updateAnimation();
				 
				 repaint();
				 
				 Thread.sleep(MainFrame.delay);
			 }
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 
}
