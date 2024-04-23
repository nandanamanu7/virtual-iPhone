import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PhoneFramePanel extends AnimatedPanel{
	
	public static final int BAR_WIDTH = 220;
	public static final int BAR_HEIGHT = 15;
	public static final int BAR_DISTANCE_X = 15;
	public static final int BAR_DISTANCE_Y = 30;
	
	// Images
	private BufferedImage homeScreenImage;
		
	public PhoneFramePanel() {
		loadImages();
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
		// TODO Auto-generated method stub
	}

}