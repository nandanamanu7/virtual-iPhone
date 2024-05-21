import java.awt.*;


public class SettingsPanel extends AnimatedPanel{
	
	
	public SettingsPanel() {
		
	}

	@Override
	public void updateAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickEvent(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
}
