package grafikdelar;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import states.GameModel;

/**
* Läggs till på vänstra sidan i PLayState.
* Visar HighScore.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class LeftPanel extends JPanel {
	private GameModel model;
	private BufferedImage image;

	public LeftPanel(GameModel model) {
		this.model = model;
		this.setSize(100, 1000);
		image = this.model.getImagemanager().getObjectImage("leftpanel");
	}

	@Override
	protected void paintComponent(Graphics g) {

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, SCREEN_HEIGHT);
	}

	public void draw(Graphics g) {

		g.setFont(new Font("Monospace", Font.BOLD, 30));
		g.drawImage(image, 0, 0, null);
		g.drawString(Integer.toString(model.getHighscore()), 20, 70);

	}
}
