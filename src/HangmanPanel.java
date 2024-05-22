import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HangmanPanel extends AnimatedPanel{
	// To eliminate a warning showing in Eclipse
	private static final long serialVersionUID = 1L;
	
	// Images
	private BufferedImage backgroundImage;
	
	// Words List
	private String[] wordsList;
	private String[] word;
	
	public HangmanPanel() {
		setupWordList();
		chooseNewWord();
		loadImages();
	}
	
	public void loadImages() {
		File background = new File("src/Images/HangmanApp/HangmanBackground.png");
		try {
			this.backgroundImage = ImageIO.read(background);
		} catch (IOException e) {
			System.out.println(background.getAbsolutePath());
		}
	}
	
	public void setupWordList() {
		this.wordsList = new String[10];
		this.word = new String[5];
	}
	
	public void chooseNewWord() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
	
	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
