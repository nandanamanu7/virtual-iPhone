import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicPanel extends AnimatedPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	// JButtons and other GUI components
	private JButton selectButton;
	
	private JFileChooser j = new JFileChooser("src/music");    
	
	private BufferedImage backgroundImage;

	private Icon PlayButton;
	
	private Clip clip;
	
	private String currentFile;
	
	

	public MusicPanel() {
		setLayout(null);
		loadImages();
		loadButtons();
	    j.setFileFilter(new FileNameExtensionFilter("WAV Files", "wav"));
	} 

	public void loadImages() {
		 File background = new File("src/Images/MusicPanel/background.png");
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

	
	private void loadButtons() {
	    selectButton = new JButton("Select music");                                     
		selectButton.addActionListener(this);
		selectButton.setBackground(Color.PINK);
		selectButton.setBounds(SCREEN_DISTANCE_X, (335/3), 200, 50);
	    add(this.selectButton);
	    selectButton = new JButton("Select music");                                     
		selectButton.addActionListener(this);
		selectButton.setIcon(this.PlayButton);
		selectButton.setBounds(SCREEN_DISTANCE_X, (335/3), 200, 50);
	    add(this.selectButton);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectButton) {
			System.out.println("Here I fear ");
			selectButton.setBackground(Color.PINK);
			j.setCurrentDirectory(new File("src/Music"));
	        int result = j.showOpenDialog(this);
	        if (result == JFileChooser.APPROVE_OPTION) 
	        {
	            File selectedFile = j.getSelectedFile();
	            this.currentFile = selectedFile.getName();
	        }
	    }
//			JFileChooser j = new JFileChooser(new File("src/music"));
//			// Open the save dialog
//			j.showSaveDialog(null);
//			try {
//				String selectedFile = j.getSelectedFile().getAbsolutePath();
//		        j.setFileFilter(new FileNameExtensionFilter("WAV Files", "wav"));
//		        this.currentFile = j.getSelectedFile().getName();
//		        JOptionPane.showMessageDialog(selectButton, j.getSelectedFile().getName(), j.getSelectedFile().getName(), -1);
////			}
//			catch (Exception ex) {
//				System.out.println(ex);
//			}
	
		else {
			return;
		}
	}

	  private void playMusic() {
	        
	        if (clip != null && clip.isRunning()) 
	        {
	            clip.stop();
	        }
	        
	        try 
	        {
				File file = new File(currentFile);
	            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
	            
	            clip = AudioSystem.getClip();
	            clip.open(audioIn);
	            
	            clip.start();
	 
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	        
	    }
	  
	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}
		    
}
