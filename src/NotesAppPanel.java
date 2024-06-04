import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.time.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;

public class NotesAppPanel extends AnimatedPanel implements ActionListener {
	// To eliminate a warning showing in Eclipse
	private static final long serialVersionUID = 1L;

	private BufferedImage backgroundImage;
	
	// GUI Components
	private JTextPane userInput;
	private JButton saveButton;
	private JButton accessButton;
	private Clock systemClock;
	
	private int textFieldDistanceFromX = 150;
	private int textFieldDistanceFromY = 150;
	private int textFieldXBound = 200;
	private int textFieldYBound = 150;

	private String textFont = "arial";
	private int fontSize = 10;
	private int fontFormat = 0;

	// Format Buttons
	private JButton italicsFormatButton;
	private JButton boldFormatButton;
	private JButton increaseFontSizeButton;
	private JButton decreaseFontSizeButton;

	// Boolean values for font format
	private boolean italics;
	private boolean bold;
	
	
	// Research java rich text file for formatting and research JFileCooser for the file display for access old notes!
	
	public NotesAppPanel() {
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
	    userInput.setLayout(null);
		userInput.setBounds(this.textFieldDistanceFromX, this.textFieldDistanceFromY, textFieldXBound, textFieldYBound);
		userInput.setPreferredSize(new Dimension(textFieldXBound, textFieldYBound));
        this.add(new JScrollPane(userInput)); 
		add(userInput);
	}
	
	public void updateUserInput() {
		if (this.italics && !this.bold) {
			this.fontFormat = 0;
			this.fontFormat = Font.ITALIC;
			italicsFormatButton.setBackground(Color.PINK);
			boldFormatButton.setBackground(Color.WHITE);
		}
		else if (this.bold && !this.italics) {
			this.fontFormat = 0;
			this.fontFormat = Font.BOLD;
			italicsFormatButton.setBackground(Color.WHITE);
			boldFormatButton.setBackground(Color.PINK);
		}
		else {
			this.fontFormat = 0;
			italicsFormatButton.setBackground(Color.WHITE);
			boldFormatButton.setBackground(Color.WHITE);
		}
		//this.italics = false;
		//this.bold
		Font font = new Font(this.textFont, this.fontFormat, this.fontSize);
	    userInput.setFont(font);
	}
	
	private void loadButtons() {
		// TODO Auto-generated method stub
		this.saveButton = new JButton("Save Note");                                     
		this.saveButton.addActionListener(this);
		saveButton.setBackground(Color.WHITE);
	    add(this.saveButton);
	    this.accessButton = new JButton("Access old notes");                                     
		this.accessButton.addActionListener(this);
		accessButton.setBackground(Color.WHITE);
	    add(this.accessButton);
	    this.italicsFormatButton = new JButton("Italics");                                     
		this.italicsFormatButton.addActionListener(this);
		italicsFormatButton.setBackground(Color.WHITE);
	    add(this.italicsFormatButton);
	    this.boldFormatButton = new JButton("Bold");                                     
		this.boldFormatButton.addActionListener(this);
		boldFormatButton.setBackground(Color.WHITE);
	    add(this.boldFormatButton);
	    this.increaseFontSizeButton = new JButton("Font +");                                     
		this.increaseFontSizeButton.addActionListener(this);
		increaseFontSizeButton.setBackground(Color.WHITE);
	    add(this.increaseFontSizeButton);
	    this.decreaseFontSizeButton = new JButton("Font -");                                     
		this.decreaseFontSizeButton.addActionListener(this);
		decreaseFontSizeButton.setBackground(Color.WHITE);
	    add(this.decreaseFontSizeButton);
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
			 try {
				 String fileName = JOptionPane.showInputDialog("Name your note");
				 Path filePath = Paths.get("src/Notes/"+fileName);
				 if (fileName.equals("")) {
			         JOptionPane.showMessageDialog(saveButton, "Please enter a name for the file!");
				 }
				 else if (Files.exists(filePath)) {
					 //System.out.println("File exists");
			         JOptionPane.showMessageDialog(saveButton, "File already exists! Select another file name");
				 }
				 else {
					 File newTextFile = new File("src/Notes/"+fileName);
			         newTextFile.createNewFile();
			         FileWriter newWriter = new FileWriter(newTextFile.getAbsolutePath());
			         newWriter.write(text);
			         newWriter.close();
			         JOptionPane.showMessageDialog(saveButton, "file saved");
					 userInput.setText("");
				 }
			 }
			 catch (Exception ex) {
				 System.out.println(ex);
			 }
			 //File newTextFile = new File(text);
			 System.out.println(text);
		}
		else if (e.getSource() == accessButton) {
			JFileChooser j = new JFileChooser(new File("src/Notes"));
			// Open the save dialog
			j.showSaveDialog(null);
			try {
				String selectedFile = j.getSelectedFile().getAbsolutePath();
				String fileContent = readFile(selectedFile);
				//JOptionPane.showMessageDialog(accessButton, fileContent, j.getSelectedFile().getName(), -1);
				userInput.setText(fileContent);
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		}
		else if (e.getSource() == italicsFormatButton) {
			System.out.println("italics btn clicked");
			if(!this.bold) {
				this.italics = !this.italics;
			}
			if (this.italics) {
				italicsFormatButton.setBackground(Color.PINK);
			}
			updateUserInput();
		}
		else if (e.getSource() == boldFormatButton) {
			System.out.println("btn clicked");
			if (!this.italics) {
				this.bold = !this.bold;
			}
			if (this.bold) {
				boldFormatButton.setBackground(Color.PINK);
			}
			updateUserInput();
		}
		else if (e.getSource() == increaseFontSizeButton) {
			this.fontSize += 5;
			updateUserInput();
		}
		else if (e.getSource() == decreaseFontSizeButton) {
			this.fontSize -= 5;
			if (this.fontSize <= 0) {
				JOptionPane.showMessageDialog(accessButton, "Font is size 0!", "Minimum font size reached", 2);
			}
			updateUserInput();
		}
		else {
			return;
		}
	}

	// This code utilizes aid from a stack overflow thread 
	private String readFile(String filePath) throws FileNotFoundException {
		    File file = new File(filePath);
		    StringBuilder fileContents = new StringBuilder((int)file.length());        

		    try (Scanner scanner = new Scanner(file)) {
		        while(scanner.hasNextLine()) {
		            fileContents.append(scanner.nextLine() + System.lineSeparator());
		        }
		        return fileContents.toString();
		    }
	}
	
}


