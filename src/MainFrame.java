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
	
	// To set bounds of the frame
	public static final int WIDTH = 250;
	public static final int HEIGHT = 450;
	
	// Creates Panel for Phone Frame
	private AnimatedPanel phoneFrame;
 	
	// Used to establish the animation speed
	public static int delay = 10;
	
	public MainFrame(){
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
		 this.setLayout(null);
		 this.setTitle("Virtual iPhone");
		 this.setSize(266,489);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 this.phoneFrame.setBounds(0,0,WIDTH, HEIGHT);
		 add(phoneFrame);
		 phoneFrame.setVisible(true);
		 this.setVisible(true);
		 

	     // tell the main thread that we are done creating our stuff
	     synchronized (semaphore) {
	         semaphore.notify();
	     }
	 }

	 private void showPanel(int index) {
	        System.out.printf("Show Panel. Thread is: %s\n", Thread.currentThread().getName());
	        
	        MainFrame.done = true;

	    }
	 
	 public void startAnimation() {
		 MainFrame.done = false;
		 try {
			 while (!MainFrame.done) {
				 phoneFrame.updateAnimation();
				 
				 repaint();
				 
				 Thread.sleep(MainFrame.delay);
			 }
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 
}
