import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class NotesAppPanel extends AnimatedPanel implements ActionListener {
	private BufferedImage backgroundImage;
	
	// GUI Components
	private JTextField userInput;
	private JButton saveButton;
	
	private int textFieldDistanceFromX = 20;
	private int textFieldDistanceFromY = 50;
	private int textFieldXBound = 100;
	private int textFieldYBound = 200;
	
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
		this.userInput = new JTextField(20);
		this.userInput.addActionListener(this);
		
		// Learned about GridBagConstraints on Stack Overflow, allows us to set bounds and position for JTextField with ease
		GridBagConstraints c = new GridBagConstraints();
	    //c.gridwidth = GridBagConstraints.REMAINDER;
	    
	    // Sets insets or position and bounds of JTextField
	    c.insets = new Insets(this.textFieldDistanceFromX, this.textFieldDistanceFromY, this.textFieldXBound, this.textFieldYBound);
	    //c.fill = GridBagConstraints.HORIZONTAL;
	    add(this.userInput, c);
	}
	
	private void loadButtons() {
		// TODO Auto-generated method stub
		this.saveButton = new JButton(); 
		this.userInput.addActionListener(this);
	    add(this.saveButton);
	}


	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 String text = userInput.getText();
	}
	
}
