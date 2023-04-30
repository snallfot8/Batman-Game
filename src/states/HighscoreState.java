package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


/**
* Kan nås från menustate
* Visar topp 5 highscores
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class HighscoreState extends GameState {
	private GameModel model;
	private BufferedImage image;
	
	public HighscoreState(GameModel model) {
		super(model);
		this.model = model;
		image = this.model.getImagemanager().getObjectImage("highscorestate");
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
		g.setFont(new Font("Monospace", Font.BOLD, 50));
		
		g.drawString(Integer.toString(model.getFilemanager().getIntegerArray(0)), 200, 185);
		g.drawString(Integer.toString(model.getFilemanager().getIntegerArray(1)), 200, 285);
		g.drawString(Integer.toString(model.getFilemanager().getIntegerArray(2)), 200, 385);
		g.drawString(Integer.toString(model.getFilemanager().getIntegerArray(3)), 200, 500);
		g.drawString(Integer.toString(model.getFilemanager().getIntegerArray(4)), 200, 600);

		
	
	}	

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_ESCAPE)
		model.switchState(new MenuState(model));	
	}
}
