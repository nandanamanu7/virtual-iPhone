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
	private JButton playButton;
	private JButton stopButton;
	
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
	    playButton = new JButton("Play");
	    playButton.addActionListener(this);
		playButton.setBackground(Color.GREEN);
		playButton.setBounds(SCREEN_DISTANCE_X +80, (335/2), 75, 50);
	    add(this.playButton);
		stopButton = new JButton("Stop");
	    stopButton.addActionListener(this);
		stopButton.setBackground(Color.RED);
		stopButton.setBounds(SCREEN_DISTANCE_X, (335/2), 75, 50);
	    add(this.stopButton);
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
	            this.currentFile = selectedFile.getAbsolutePath();
	            playMusic();
	        }
	    }
		if (e.getSource() == stopButton) {
			stopPlaying();
	    }
		if (e.getSource() == playButton) {
			resumePlaying();
	    }
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
	  
	  private void stopPlaying() {
		  if (clip != null && clip.isRunning()) 
	        {
	            clip.stop();
	        }
	  }
	  
	  private void resumePlaying() {
		  if (clip != null && !clip.isRunning()) 
	        {
	            clip.start();
	        }
	  }
	  
	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}
		    
}
