package grafikdelar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import constants.Constants;
import states.GameModel;

/**
* En roterande batarang, som man kastar på fiender. 
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class ThrowBatarang {

	private int posX;
	private int posY;
	private int width = 100;
	private int height = 40;
	private int angle = 0;
	private BufferedImage image;
	private GameModel model;

	private Graphics2D g2d;
	private AffineTransform at;


	public ThrowBatarang(GameModel model, BatmanCar player) {
		this.model = model;
		this.setImage(this.getModel().getImagemanager().getObjectImage("batarang"));
		this.posX = player.getPosX() + 20;
		this.posY = player.getPosY();
	}

	public void draw(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.drawImage(image,at, null);
		
	}

	public void doCommand(BatmanCar player) {
		model.setAmmo(model.getAmmo() - 1);

	}

	public void update() {
		setPosY(this.getPosY() - 10);
		at = AffineTransform.getTranslateInstance(posX, posY);
		at.scale(0.08, 0.08);
		at.rotate(Math.toRadians(angle += 30), image.getWidth()/2, image.getHeight()/2);
		
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public GameModel getModel() {
		return model;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}

}
