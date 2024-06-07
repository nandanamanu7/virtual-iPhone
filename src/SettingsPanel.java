import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends AnimatedPanel{
    // To eliminate a warning showing in Eclipse
    private static final long serialVersionUID = 1L;
    
    private BufferedImage background;
    private BufferedImage timeButton;
    private BufferedImage militaryTime;
    private BufferedImage goodTime;
    
    private int timeOffset;
    private boolean inverted;
    private Color barColor;
    
    private int boxDimensions;
    private boolean AMERICAN;
    
    private JComboBox<String> colorSelector;

    public SettingsPanel() {
        this.AMERICAN = false;
        this.boxDimensions = 35;
        this.timeOffset = 0;
        this.barColor = Color.black;
        this.inverted = false;
        loadImages();
        setLayout(null); // Disable the layout manager
        initializeColorSelector();
    }
    
    private void initializeColorSelector() {
        String[] colors = { "Black", "White", "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet" };
        colorSelector = new JComboBox<>(colors);
        colorSelector.setBounds(145, 135, 60, 20);
        colorSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) colorSelector.getSelectedItem();
                switch (selectedColor) {
	                case "Black":
	                    barColor = Color.BLACK;
	                    break;
	                case "White":
	                    barColor = Color.WHITE;
	                    break;
                    case "Red":
                        barColor = Color.RED;
                        break;
                    case "Orange":
                        barColor = Color.ORANGE;
                        break;
                    case "Yellow":
                        barColor = Color.YELLOW;
                        break;
                    case "Green":
                        barColor = Color.GREEN;
                        break;
                    case "Blue":
                        barColor = Color.BLUE;
                        break;
                    case "Indigo":
                        barColor = new Color(75, 0, 130); // Indigo is not predefined in Color
                        break;
                    case "Violet":
                        barColor = new Color(238, 130, 238); // Violet is not predefined in Color
                        break;
                }
                repaint();
            }
        });
        this.add(colorSelector);
    }

    public void loadImages() {
        File bg = new File("src/Images/SettingsApp/SettingsWallpaper.png");
        File time = new File("src/Images/SettingsApp/Time.png");
        File military = new File("src/Images/SettingsApp/MilitaryTime.png");
        File good = new File("src/Images/SettingsApp/GoodTime.png");
        try {
            this.background = ImageIO.read(bg);
            this.timeButton = ImageIO.read(time);
            this.militaryTime = ImageIO.read(military);
            this.goodTime = ImageIO.read(good);
        } catch (IOException e) {
            System.out.println(bg.getAbsolutePath());
            System.out.println(time.getAbsolutePath());
            System.out.println(military.getAbsolutePath());
            System.out.println(good.getAbsolutePath());
        }
    }

    @Override
    public void updateAnimation() {
        // TODO Auto-generated method stub
    }

    @Override
    public void clickEvent(int x, int y) {
        // TODO Auto-generated method stub
        if((x>=160 && x<=160+this.boxDimensions)&&(y>=55 && y<= 55+this.boxDimensions)) {
            this.inverted = (!this.inverted);
        } else if (x == 160 && (y>=220 && y<= 240)) {
            this.timeOffset *= -1;
            this.inverted = (!this.inverted);
            System.out.println("bruh");
        } else if ((x>=120 && x<=160)&&(y>=220 && y<= 240)) {
            this.timeOffset--;
        } else if ((x>=160 && x<=200)&&(y>=220 && y<= 240)) {
            this.timeOffset++;
        } else if ((x>=140 && x<=220)&&(y>=273 && y<= 292)) {
            this.AMERICAN = false;
        } else if ((x>=140 && x<=220)&&(y>=294 && y<= 314)) {
            this.AMERICAN = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, this);
        drawBox(g);
        g.drawImage(this.timeButton, 120, 200, this);
        String time = "+";
        if(this.timeOffset < 0) {
            time = "";
        }
        g.setColor(Color.BLACK);
        g.drawString(time+this.timeOffset+":00", 142, 215);
        if(this.AMERICAN) {
            g.drawImage(this.goodTime, 140, 273, this);
        } else {
            g.drawImage(this.militaryTime, 140, 273, this);
        }
    }

    public void drawBox(Graphics g) {
        String text = "off";
        Color boxColor = Color.RED;
        if (this.inverted) {
            text = "on";
            boxColor = Color.GREEN;
        }
        g.setColor(boxColor);
        g.fillRect(160, 55, this.boxDimensions, this.boxDimensions);
        g.setColor(Color.BLACK);
        g.drawString(text, 170, 75);
    }

    public boolean getInverted() {
        return this.inverted;
    }

    public int getTimeOffset() {
        return this.timeOffset;
    }

    public Color getBarColor() {
        return this.barColor;
    }

    public boolean getTimeSystem() {
        return this.AMERICAN;
    }
}
