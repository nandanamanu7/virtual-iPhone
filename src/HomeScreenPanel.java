import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HomeScreenPanel extends AnimatedPanel{
	
	
	private BufferedImage wallpaper;
	private BufferedImage[] wallpapers;
	private BufferedImage [] apps;
	private int currentWallpaper = 0;
	private int xDistanceBetweenApps = 10;
	private int yDistanceBetweenApps = 15;
	private int xBoundApp = 60;
	private int yBoundApp = 70;

	
	public HomeScreenPanel() {
		this.wallpapers = new BufferedImage[4];
		this.apps = new BufferedImage[4];
		loadImages();
		setWallpaper(currentWallpaper);
	}
	
	private void loadImages() {
		 File im0 = new File("src/Images/HomeScreen/wallpaper00.png");
		 File im1 = new File("src/Images/HomeScreen/wallpaper01.png");
		 File im2 = new File("src/Images/HomeScreen/wallpaper02.png");
		 File im3 = new File("src/Images/HomeScreen/wallpaper03.png");
		 File imWallpaperApp = new File("src/Images/HomeScreen/WallpaperApp.png");
		 File imSettingsApp = new File("src/Images/HomeScreen/SettingsApp.png");
		 File imNotesApp = new File("src/Images/HomeScreen/NotesApp.png");
		 File imClockApp = new File("src/Images/HomeScreen/ClockApp.png");
		 try {
	            this.wallpapers[0] = ImageIO.read(im0);
	            this.wallpapers[1] = ImageIO.read(im1);
	            this.wallpapers[2] = ImageIO.read(im2);
	            this.wallpapers[3] = ImageIO.read(im3);
	            this.apps[0] = ImageIO.read(imWallpaperApp);
	            this.apps[1] = ImageIO.read(imSettingsApp);
	            this.apps[2] = ImageIO.read(imNotesApp);
	            this.apps[3] = ImageIO.read(imClockApp);
	      } catch (IOException e) {
	            System.out.println(im0.getAbsolutePath());
	            System.out.println(im1.getAbsolutePath());
	            System.out.println(im2.getAbsolutePath());
	            System.out.println(im3.getAbsolutePath());
	            System.out.println(imWallpaperApp.getAbsolutePath());
	            System.out.println(imSettingsApp.getAbsolutePath());
	            System.out.println(imNotesApp.getAbsolutePath());
	            System.out.println(imClockApp.getAbsolutePath());
	      }
    }
	
	public void setWallpaper(int wallpaper) {
		this.wallpaper = wallpapers[wallpaper];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(wallpaper, 0, 0, this);
		int row = 1;
		int col = 1;
		int newRow = 0;
		for (BufferedImage app : apps) {
			int imgX = (row*xDistanceBetweenApps) + ((row-1)*xBoundApp);
			int imgY = (col*yDistanceBetweenApps) + ((col-1)*yBoundApp);
			g.drawImage(app, imgX, imgY, this);
			row++;
			if (row == 4) {
				row = 1;
				col ++;
			}
		}
	}
	
	@Override
	public void updateAnimation() {
	}

}
