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
	private BufferedImage[] currentLevel;
	
	// Int variables
	private int displayLevel;
	
	// Words List
	private String[] wordsList;
	private String[] word;
	
	public HangmanPanel() {
		setupVars();
		loadImages();
		loadLevelImages();
		chooseNewWord();
	}
	
	public void loadImages() {
		//Background Image
		File background = new File("src/Images/HangmanApp/HangmanBackground.png");
		try {
			//Background Image
			this.backgroundImage = ImageIO.read(background);
		} catch (IOException e) {
			//Background Image
			System.out.println(background.getAbsolutePath());
		}
	}
	
	public void loadLevelImages() {
		for (int level = 0; level < 9; level++) {
			File current = new File("src/Images/HangmanApp/HangmanStage"+level+".png");
			try {
				this.currentLevel[level] = ImageIO.read(current);
			} catch (IOException e) {
				System.out.println(current.getAbsolutePath());
			}
		}
	}
	
	public void setupVars() {
		this.displayLevel = 0;
		this.currentLevel = new BufferedImage[9];
		this.wordsList = new String[10];
		this.word = new String[5];
	}
	
	public void chooseNewWord() {
		
	}
	
	public void keyEvent(String key) {
		key = key.toLowerCase();
		if (key.equals("a")) {
			this.displayLevel ++;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(currentLevel[displayLevel], 92, 42, this);
	}
	
	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
		if (this.displayLevel == 8) {
			System.out.println("Game Over");
		}
	}

	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
