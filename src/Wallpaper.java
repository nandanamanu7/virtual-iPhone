import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wallpaper extends AnimatedPanel{
	
	private BufferedImage backgroundImage;
	private BufferedImage[] wallpapers;
	private int currentWallpaper = 0;
	
	private int xDistanceWall = 25;
	private int imageXDistance = 64;
	private int xDistanceApp = 40;
	private int yDistanceWall = 60;
	private int imageYDistance = 97;
	private int yDistanceApp = 28;
	
	
	public Wallpaper() {
		this.wallpapers = new BufferedImage[4];
		loadImages();
	} 
	
	
	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	public void loadImages() {
		File background = new File("src/Images/WallpaperApp/background.png");
		File im0 = new File("src/Images/WallpaperApp/appWallpaper00.png");
		 File im1 = new File("src/Images/WallpaperApp/appWallpaper01.png");
		 File im2 = new File("src/Images/WallpaperApp/appWallpaper02.png");
		 File im3 = new File("src/Images/WallpaperApp/appWallpaper03.png");
		 try {
	            this.wallpapers[0] = ImageIO.read(im0);
	            this.wallpapers[1] = ImageIO.read(im1);
	            this.wallpapers[2] = ImageIO.read(im2);
	            this.wallpapers[3] = ImageIO.read(im3);
	            this.backgroundImage = ImageIO.read(background);
	      } catch (IOException e) {
	            System.out.println(im0.getAbsolutePath());
	            System.out.println(im1.getAbsolutePath());
	            System.out.println(im2.getAbsolutePath());
	            System.out.println(im3.getAbsolutePath());
	            System.out.println(background.getAbsolutePath());
	      }
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int row = 0;
		int col = 0;
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
		for (BufferedImage wallpaper : wallpapers) {
			g.drawImage(wallpaper, xDistanceWall+row*(imageXDistance+xDistanceApp), yDistanceWall+col*(imageYDistance+yDistanceApp), this);
			row ++;
			if (row == 2) {
				row = 0;
				col = 1;
			}
		}
	}


	@Override
	public void clickEvent(int x, int y) {
		if (checkClick(x,y) != -1) {
			this.backgroundImage = this.wallpapers[checkClick(x,y)];
		}
		
	}


	private int checkClick(int x, int y) {
		int image = -1;
		for (int i = yDistanceWall; i < SCREEN_HEIGHT; i+= (imageYDistance+yDistanceApp)) {
			for (int j = xDistanceWall; j < SCREEN_WIDTH; j+= (imageXDistance+xDistanceApp)) {
				image++;
				if ((i < x && (i + imageXDistance) > x) && (j < y && (j + imageYDistance) > y)) {
					return image;
				}
			}
		}
		return -1;
	}

}
