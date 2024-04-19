import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HomeScreenPanel extends AnimatedPanel{

	private BufferedImage homeScreenImage;

	public HomeScreenPanel() {
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
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}

}
