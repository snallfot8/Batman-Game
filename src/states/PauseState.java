package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
* Detta state tillåter spelaren att andas ut (pausa) när simulatorn blir för överväldigande
* När man är redo igen klickar man enter för att gå in i playstate igen
* @author Erik Snällfot
* @author Axel Nilsson
*/

public class PauseState extends GameState {
private PlayState play;
private BufferedImage image;

	public PauseState(GameModel model, PlayState play) {
		super(model);
		this.play = play;
		image = this.model.getImagemanager().getObjectImage("pausestate");
	}
	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		 g.drawImage(image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);   
			g.setColor(Color.BLACK);
			g.setFont(new Font("Monospace", Font.PLAIN, 70));
			g.drawString(Integer.toString(model.getCurrentScore()), 450, 240);

	}

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_ENTER)
		model.switchState((play));
	}

}
