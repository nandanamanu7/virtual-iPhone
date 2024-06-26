import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class HangmanPanel extends AnimatedPanel{
	// To eliminate a warning showing in Eclipse
	private static final long serialVersionUID = 1L;

	// Images
	private BufferedImage backgroundImage;
	private BufferedImage[] currentLevel;
	private BufferedImage gameOverWinScreen;
	private BufferedImage gameOverLoseScreen;
	
	//variables
	private int displayLevel;
	private boolean gameOver;
	private boolean won;
	private int xFontDistance;
	private int yFontDistance;
	private int fontSpace;

	// Words List
	private String[][] wordsList;
	private String[] word;
	private Boolean[] setVisible;

	public HangmanPanel() {
		setupVars();
		loadImages();
		loadLevelImages();
		loadWordList();
		chooseNewWord();
		
	}

	public void loadImages() {
		//Background Image
		File loseScreen = new File("src/Images/HangmanApp/HangmanGameOverLose.png");
		File winScreen = new File("src/Images/HangmanApp/HangmanGameOverWin.png");
		File background = new File("src/Images/HangmanApp/HangmanBackground.png");
		try {
			//Background Image
			this.gameOverLoseScreen = ImageIO.read(loseScreen);
			this.gameOverWinScreen = ImageIO.read(winScreen);
			this.backgroundImage = ImageIO.read(background);
		} catch (IOException e) {
			//Background Image
			System.out.println(loseScreen.getAbsolutePath());
			System.out.println(winScreen.getAbsolutePath());
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
		this.xFontDistance = 13;
		this.yFontDistance = 195;
		this.fontSpace = 43;
		this.gameOver = false;
		this.won = true;
		this.displayLevel = 0;
		this.currentLevel = new BufferedImage[9];
		this.word = new String[5];
		this.setVisible = new Boolean[5];
	}

	public void loadWordList() {
		for (int i = 0; i < 5; i++) {
			this.setVisible[i] = false;
		}
		this.wordsList = new String[100][5];
		int currentWord = 0;
		int currentLetter = 0;
		File words = new File("src/HangmanWords/Words.txt");
		try (Scanner scn = new Scanner(words)) {
			while (scn.hasNext() && currentWord < 100) {
			    for (String nextLetter : tokenize(scn.next())) {
			    	this.wordsList[currentWord][currentLetter] = nextLetter;
			    	currentLetter++;
			    }
				currentWord++;
				currentLetter = 0;
			}
		} catch (IOException e) {
			System.out.println(words.getAbsolutePath());
		}
	} 

	public void chooseNewWord() {
		int randomChoice = (int) (Math.random()*100);
		this.word = this.wordsList[randomChoice];
	}

	public void setInvis() {
		for (int i = 0; i < 5; i++) {
			this.setVisible[i] = false;
		}
	}
	
	public void keyEvent(String key) {
		if(!this.gameOver) {
			System.out.println("Word: "+Arrays.toString(this.word));
			key = key.toLowerCase();
			boolean nextStage = true;
			boolean win = true;
			int index = 0;
			for (String letter : this.word) {
				if (letter.equals(key)) {
					this.setVisible[index] = true;
					nextStage = false;
				}
				index++;
			}
			for (boolean check : this.setVisible) {
				if (!check) {
					win = false;
				}
			}
			if (win) {
				this.won = win;
				this.gameOver = true;
			}
			if (nextStage) {
				this.displayLevel++;
				if (this.displayLevel == 8) {
					this.gameOver = true;
					this.won = false;
				}
			} 
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setFont(new Font("arial bold", 0, 45));
		super.paintComponent(g);
		if (!this.gameOver) {
			g.drawImage(backgroundImage, 0, 0, this);
			g.drawImage(currentLevel[displayLevel], 92, 42, this);
			int index = 0;
			for(Boolean display : this.setVisible) {
				if(display) {
					g.drawString(this.word[index], this.xFontDistance + this.fontSpace * index, this.yFontDistance);
				}
				index++;
			}
		} else {
			if (this.won) {
				g.drawImage(this.gameOverWinScreen, 0, 0, this);
			} else {
				g.drawImage(this.gameOverLoseScreen, 0, 0, this);
			}
		}
		
	}

	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
	}

	@Override
	public void clickEvent(int x, int y) {
		if (this.gameOver) {
			if (x >= 50 && x <= 170) {
				if (y >= 225 && y <= 275) {
					this.won = false;
					this.gameOver = false;
					this.displayLevel = 0;
					chooseNewWord();
					setInvis();
				}
			}
		}
	}

	public String[] tokenize(String exampleWord) {
		String[] fixed = new String[5];
		for (int i = 0; i < exampleWord.length(); i++) {
			if (i < 4) {
				fixed[i] = exampleWord.substring(i, i+1);
			} else {
				fixed[i] = exampleWord.substring(i);
			}
		}
		return fixed;
	}

}