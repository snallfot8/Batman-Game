package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
* Innan Simulatorn börjar kommer InfoState förklara hur Simulatorn går till
* När du läst informationen och förstått klickar du enter för att börja Simulationen
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class InfoState extends GameState {
private GameModel model;
private BufferedImage image;
	
	
	public InfoState(GameModel model) {
		super(model);
		this.model = model;
		image = this.model.getImagemanager().getObjectImage("infostate");
	}
	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null); 

	}

	@Override
	public void keyPressed(int key) {
		if ( key == KeyEvent.VK_ENTER) {
			model.switchState(new PlayState(model));
		}

	}

}
