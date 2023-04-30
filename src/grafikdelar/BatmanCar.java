package grafikdelar;

import static constants.Constants.SCREEN_HEIGHT;
//import static constants.Constants.SCREEN_WIDTH;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import states.GameModel;

/**
* Motsvarar "Player", som du styr.
* Skulle från början vara en Batmobile men ersattes av en flygande Batman. 
* Håller reda på sin egen position och rörelse. 
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class BatmanCar extends JComponent {

	
	private int posX;
	private int posY;
	private int width = 130;
	private int height = 130;
	private GameModel model;
	private BufferedImage image;
	private char direction;
	private char prevdirection;
	private boolean skipmiddle = false;

	public BatmanCar(GameModel model) {
		this.model = model;
		image = this.model.getImagemanager().getObjectImage("batman");
		posX = 350;
		posY = SCREEN_HEIGHT - 150;

	}

	public void updateDirection(char k) {
		prevdirection = direction;
		direction = k;
		if (k == 'r' && posX <= 350 && posX > 110 && prevdirection == 'r') {
			skipmiddle = true;
		} else if (k == 'l' && posX >= 350 && posX < 550 && prevdirection == 'l') {
			skipmiddle = true;
		}

	}

	public void throwBatarang() {
		if (model.getAmmo() > 0) {
			model.setAmmo(model.getAmmo() - 1);
		}
	}

	public void update() {

		if (direction == 'r') {

			if (posX != 550) {
				posX += 40;
				if (posX == 550 || posX == 350) {
					if (posX == 550) {
						direction = 'n';
					} else if (posX == 350 && !skipmiddle) {
						direction = 'n';
					}
					skipmiddle = false;
				}
			}
		} else if (direction == 'l') {
			if (posX != 110) {
				posX -= 40;
				if (posX == 350 || posX == 110) {
					if (posX == 110) {
						direction = 'n';
					} else if (posX == 350 && !skipmiddle) {
						direction = 'n';
					}
					skipmiddle = false;
				}
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(image, posX, posY, width, height, null);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}