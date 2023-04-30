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
* Läggs till på högra sidan i PLayState.
* Visar nuvarande score och antal batarangs.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class RightPanel extends JPanel {

	private GameModel model;
	private BufferedImage image;

	public RightPanel(GameModel model) {
		this.setSize(100, 1000);
		this.model = model;
		image = this.model.getImagemanager().getObjectImage("rightpanel");

	}

	@Override
	protected void paintComponent(Graphics g) {

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, SCREEN_HEIGHT);
	}

	public void draw(Graphics g) {
		g.drawImage(image, 700, 0, null);

		g.setFont(new Font("Monospace", Font.BOLD, 30));
		g.drawString(Integer.toString(model.getCurrentScore()), 745, 55);

		g.drawString(Integer.toString(model.getAmmo()), 745, 110);
	}
}
