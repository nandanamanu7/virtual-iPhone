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
	private int currentWallpaper = 0;

	
	public HomeScreenPanel() {
		this.wallpapers = new BufferedImage[4];
		loadImages();
		setWallpaper(currentWallpaper);
	}
	
	private void loadImages() {
		 File im0 = new File("src/Images/HomeScreen/wallpaper00.png");
		 File im1 = new File("src/Images/HomeScreen/wallpaper01.png");
		 File im2 = new File("src/Images/HomeScreen/wallpaper02.png");
		 File im3 = new File("src/Images/HomeScreen/wallpaper03.png");
		 try {
	            this.wallpapers[0] = ImageIO.read(im0);
	            this.wallpapers[1] = ImageIO.read(im1);
	            this.wallpapers[2] = ImageIO.read(im2);
	            this.wallpapers[3] = ImageIO.read(im3);
	      } catch (IOException e) {
	            System.out.println(im0.getAbsolutePath());
	            System.out.println(im1.getAbsolutePath());
	            System.out.println(im2.getAbsolutePath());
	            System.out.println(im3.getAbsolutePath());
	      }
    }
	
	public void setWallpaper(int wallpaper) {
		this.wallpaper = wallpapers[wallpaper];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(wallpaper, 0, 0, this);
	}
	
	@Override
	public void updateAnimation() {
	}

}
