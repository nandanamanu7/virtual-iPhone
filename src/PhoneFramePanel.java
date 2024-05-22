import java.awt.Color;
import java.time.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PhoneFramePanel extends AnimatedPanel implements MouseListener {	
	// To eliminate a warning showing in Eclipse
	private static final long serialVersionUID = 1L;

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
		private static final int NOTES_PANEL = 2;
		private static final int SETTINGS_PANEL = 3;
		private static final int CLOCK_PANEL = 4;
		private static final int HANGMAN_PANEL = 5;

		// constants for apps
		private int xDistanceBetweenApps = 10;
		private int yDistanceBetweenApps = 15;
		private int xBoundApp = 60;
		private int yBoundApp = 70;

		// Set up array of AnimatedPanels for each screen along with int for the current screen
		private AnimatedPanel[] screens;
		private int currentPanel = 0;
		
		// Clock
		private LocalDateTime localDateTime;
		
		// Boolean values to indicate whether an app is clicked on
		private boolean appClicked; 
	
	// Images
	private BufferedImage homeScreenImage;
		
	public PhoneFramePanel() {
		this.setLayout(null);
		this.screens = new AnimatedPanel[6];
		this.screens[SCREEN_PANEL] = new HomeScreenPanel();
		this.screens[WALLPAPER_PANEL] = new Wallpaper();
		this.screens[NOTES_PANEL] = new NotesAppPanel();
		this.screens[SETTINGS_PANEL] = new SettingsPanel();
		this.screens[CLOCK_PANEL] = new HomeScreenPanel();
		this.screens[HANGMAN_PANEL] = new HangmanPanel();
		//this.screens[TICTACTOE_PANEL] = new TicTacToePanel();
		loadImages();
		createPanel();
		this.appClicked = false;
	}
	
	/*private void showPanel(int index) {
        System.out.printf("Show Panel. Thread is: %s\n", Thread.currentThread().getName());

        // hide the current panel
        screens[currentPanel].setVisible(false);

        // show the correct panel
        currentPanel = index;
        screens[currentPanel].setVisible(true);

        // The animation will start on the main thread.
        // Do nothing in the UI thread
    }	*/
	
	

	private void createPanel() {
		for (AnimatedPanel screen : screens) {
	        screen.setBounds(SCREEN_DISTANCE_X, SCREEN_DISTANCE_Y, SCREEN_WIDTH, SCREEN_HEIGHT);
	        add(screen);
	        screen.setVisible(false);
	        screen.setOpaque(false);
		 }

		//this.currentPanel = SCREEN_PANEL;
		 screens[currentPanel].setVisible(true);
		 
	     // Set the current frame and this JFrame to be visible
	     //this.setVisible(true);
		 this.addMouseListener(this);
	}

	private String getTime() {
		this.localDateTime = LocalDateTime.now();
		LocalTime currentTime = localDateTime.toLocalTime();
		return (currentTime+"").substring(0,5);
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
		g.setColor(Color.WHITE);
		g.drawString(getTime(), BAR_DISTANCE_X+2, BAR_DISTANCE_Y+BAR_HEIGHT-2);
	}
	
	@Override
	public void updateAnimation() {
		screens[currentPanel].updateAnimation();
	}
	
	public boolean insideHomeButton (int x, int y) {
		double circle_x = 126.5;
		double circle_y = 414.5;
		double true_x = Math.pow(x - circle_x, 2);
		double true_y = Math.pow(y - circle_y, 2);
		double distance = Math.sqrt(true_x + true_y);
		return distance <= 20.2;
	}

	public void switchApp(int panel) {
		screens[currentPanel].setVisible(false);
		this.currentPanel = panel;
		screens[currentPanel].setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (insideHomeButton(x, y)) {
			if (appClicked) {
				changeScreen(currentPanel);
			}

			this.appClicked = false;
			switchApp(0);
		} 

		else if (!(insideApps(x,y)== 0) && !appClicked) {
			this.appClicked = true;
			System.out.println("app clicked");
			switchApp(insideApps(x,y));
		} else {
			x -= SCREEN_DISTANCE_X;
			y -= SCREEN_DISTANCE_Y;
			screens[currentPanel].clickEvent(x, y);
			return;
		}
	}

	private void changeScreen(int currentPanel) {
		if(currentPanel == WALLPAPER_PANEL) {
			Wallpaper object = (Wallpaper) screens[WALLPAPER_PANEL];
			HomeScreenPanel home = (HomeScreenPanel) screens[SCREEN_PANEL];
			home.setWallpaper(object.getWallpaper());
		}
	}

	private int insideApps(int x, int y) {
		int currentApp = 0;
		for (int j = (yDistanceBetweenApps + SCREEN_DISTANCE_Y); j < SCREEN_HEIGHT ; j+=  (xBoundApp+xDistanceBetweenApps)) {
			for (int i = (xDistanceBetweenApps + SCREEN_DISTANCE_X); i < SCREEN_WIDTH; i+= (xBoundApp+xDistanceBetweenApps)) {
				currentApp++;
				if ((i < x && (i + xBoundApp) > x) && (j < y && (j + yBoundApp) > y)) {
					return currentApp;
				}
			}
		}
		return 0;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
	}
}