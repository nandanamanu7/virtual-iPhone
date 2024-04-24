import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HomeScreenPanel extends AnimatedPanel {
	
	
	private BufferedImage wallpaper;

	
	public HomeScreenPanel() {
		loadImages();
	}
	
	private void loadImages() {
		 File imX = new File("src/Images/wallpaper01.png");
	        try {
	            this.wallpaper = ImageIO.read(imX);
	        } catch (IOException e) {
	            System.out.println(imX.getAbsolutePath());
	        }
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
