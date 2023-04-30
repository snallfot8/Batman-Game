package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


/**
* Genererar menyn när detta state är aktivt
* Här kan man klicka sig vidare till spelläget eller highscores
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class MenuState extends GameState {

	private BufferedImage image;
	private GameModel model;
	private int choice = 1;

	public MenuState(GameModel model) {
		super(model);
		this.model = model;
		image = this.model.getImagemanager().getObjectImage("menustate");
	}

	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

		if (choice == 1) {
			g.drawRect(30, 200, 370, 180);
		}
		if (choice == 2) {
			g.drawRect(30, 404, 370, 180);
		}
		if (choice == 3) {
			g.drawRect(30, 620, 370, 180);
		}

	}

	@Override
	public void keyPressed(int key) {
		System.out.println("Trycker på " + KeyEvent.getKeyText(key) + " i MenuState");

		if (key == KeyEvent.VK_ENTER && choice == 1)
			model.switchState(new InfoState(model));

		if (key == KeyEvent.VK_ENTER && choice == 2)

			model.switchState(new HighscoreState(model));
		if (key == KeyEvent.VK_ENTER && choice == 3)

			System.exit(0);

		if (key == KeyEvent.VK_DOWN && choice == 2) {
			choice = 3;
		}
		if (key == KeyEvent.VK_UP && choice == 2) {
			choice = 1;
		}
		if (key == KeyEvent.VK_UP && choice == 3) {
			choice = 2;
		}
		if (key == KeyEvent.VK_DOWN && choice == 1) {
			choice = 2;
		}

	}

	@Override
	public void update() {
	}

	public GameModel getModel() {
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}
}
