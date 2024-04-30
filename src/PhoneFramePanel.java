import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PhoneFramePanel extends AnimatedPanel{
	
	// To set bounds of the frame
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
		private static final int WALLPAPER_PANEL = 1;
		
		// Set up array of AnimatedPanels for each screen along with int for the current screen
		private AnimatedPanel[] screens;
		private int currentPanel = 0;
	
	// Images
	private BufferedImage homeScreenImage;
		
	public PhoneFramePanel() {
		this.setLayout(null);
		this.screens = new AnimatedPanel[2];
		this.screens[SCREEN_PANEL] = new HomeScreenPanel();
		this.screens[WALLPAPER_PANEL] = new Wallpaper();
		//this.screens[TICTACTOE_PANEL] = new TicTacToePanel();
		loadImages();
		createPanel();
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
	
	/**
	 * Takes a homescreenpanel that has been put in a list of AnimatedPanels and returns it as a HomeScreenPanel
	 * @param An AnimatedPanel that represents a HomeScreenPanel
	 * @return A HomeScreenPanel
	 */
	private HomeScreenPanel getPanel(AnimatedPanel home) {
		return (HomeScreenPanel) home;
	}
	/**
	 * 
	 * @param An integer that represents the wallpaper value
	 */
	private void setWallpaper(int i) {
		getPanel(screens[SCREEN_PANEL]).setWallpaper(i);
	}
	
	private void createPanel() {
		for (AnimatedPanel screen : screens) {
	        screen.setBounds(SCREEN_DISTANCE_X, SCREEN_DISTANCE_Y, SCREEN_WIDTH, SCREEN_HEIGHT);
	        add(screen);
	        screen.setVisible(false);
		 }
		this.currentPanel = SCREEN_PANEL;
		 screens[currentPanel].setVisible(true);
	     // Set the current frame and this JFrame to be visible
	     //this.setVisible(true);
	}
	
	
	
	private void loadImages() {
        File imX = new File("src/Images/IPhone.png");
        try {
            this.homeScreenImage = ImageIO.read(imX);
        } catch (IOException e) {
            System.out.println(imX.getAbsolutePath());
        }
    }
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(homeScreenImage, 0, 0, this);
		g.setColor(Color.BLACK);
		g.fillRect(BAR_DISTANCE_X, BAR_DISTANCE_Y, BAR_WIDTH, BAR_HEIGHT);
	}
	
	@Override
	public void updateAnimation() {
		screens[currentPanel].updateAnimation();
	}

}