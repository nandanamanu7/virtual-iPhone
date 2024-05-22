import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.time.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;

public class NotesAppPanel extends AnimatedPanel implements ActionListener {
	private BufferedImage backgroundImage;
	
	// GUI Components
	private JTextPane userInput;
	private JButton saveButton;
	private JButton accessButton;
	private Clock systemClock;
	
	private int textFieldDistanceFromX = 50;
	private int textFieldDistanceFromY = 50;
	private int textFieldXBound = 200;
	private int textFieldYBound = 150;

	private String textFont = "serif";
	private int fontSize = 20;
	private int fontFormat = 0;

	// Format Buttons
	private JButton italicsFormatButton;
	private JButton boldFormatButton;

	// Boolean values for font format
	private boolean italics;
	private boolean bold;
	
	
	public NotesAppPanel() {
		// Creates a new GridBagLayout so we can manage the layout/position of our JTextField
		new GridBagLayout();
		loadImages();
		loadTextField();
		loadButtons();
	} 

	public void loadImages() {
		 File background = new File("src/Images/NotesApp/background.png");
		 try {
	            this.backgroundImage = ImageIO.read(background);
	      } catch (IOException e) {
	            System.out.println(background.getAbsolutePath());
	      }
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


	public void loadTextField () {
		this.italics = false;
		this.bold = false;
		this.userInput = new JTextPane();
		userInput.setBackground(Color.PINK);
	    Font font = new Font(this.textFont, this.fontFormat, this.fontSize);
	    userInput.setFont(font);
		this.userInput.setBounds(textFieldDistanceFromX, textFieldDistanceFromY, textFieldXBound, textFieldYBound);
		this.userInput.setPreferredSize(new Dimension(textFieldXBound, textFieldYBound));
		add(this.userInput);
	}
	
	public void updateUserInput() {
		System.out.println("called");
		if (this.italics) {
			System.out.println("reached");
			this.fontFormat = Font.ITALIC;
			italicsFormatButton.setBackground(Color.PINK);
		}
		else if (this.bold) {
			this.fontFormat = Font.BOLD;
			boldFormatButton.setBackground(Color.PINK);
		}
		else {
			System.out.println("else");
			this.fontFormat = 0;
			italicsFormatButton.setBackground(Color.WHITE);
			boldFormatButton.setBackground(Color.WHITE);
		}
		System.out.println("reached end");
		Font font = new Font(this.textFont, this.fontFormat, this.fontSize);
	    userInput.setFont(font);
	}
	
	private void loadButtons() {
		// TODO Auto-generated method stub
		this.saveButton = new JButton("Save Note");                                     
		this.saveButton.addActionListener(this);
	    add(this.saveButton);
	    this.accessButton = new JButton("Access old notes");                                     
		this.accessButton.addActionListener(this);
	    add(this.accessButton);
	    this.italicsFormatButton = new JButton("Italics");                                     
		this.italicsFormatButton.addActionListener(this);
	    add(this.italicsFormatButton);
	    this.boldFormatButton = new JButton("Bold");                                     
		this.boldFormatButton.addActionListener(this);
	    add(this.boldFormatButton);
	}


	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public String getDate() {
		this.systemClock = Clock.systemDefaultZone();
		String currentDate = ("" + systemClock.instant()).substring(0,10);
		return currentDate;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			 String text = userInput.getText();
			// File newTextFile = new File()
			 System.out.println(text);
		}
		else if (e.getSource() == accessButton) {
			String accessDate = JOptionPane.showInputDialog("Note Date");
			JOptionPane.showMessageDialog(accessButton, accessDate);
		}
		else if (e.getSource() == italicsFormatButton) {
			System.out.println("italics btn clicked");
			this.italics = !this.italics;
			if (this.italics) {
				italicsFormatButton.setBackground(Color.PINK);
			}
			updateUserInput();
		}
		else if (e.getSource() == boldFormatButton) {
			System.out.println("btn clicked");
			this.bold = !this.bold;
			if (this.bold) {
				boldFormatButton.setBackground(Color.PINK);
			}
			updateUserInput();
		}
		else {
			return;
		}
	}
	
}


