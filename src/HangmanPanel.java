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

	

	// Int variables

	private int displayLevel;

	

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

	

	public void keyEvent(String key) {

		System.out.println("Word: "+Arrays.toString(this.word));

		key = key.toLowerCase();

		int index = 0;

		for (String letter : this.word) {

			if (letter.equals(key)) {

				this.setVisible[index] = true;

				System.out.println("hangman");

			}

			index++;

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