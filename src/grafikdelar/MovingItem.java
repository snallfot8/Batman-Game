package grafikdelar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import constants.Constants;
import states.GameModel;

/**
* Superklass till alla upplockbara objekt och fiender/obstacles.
* Sköter rörelsen av objekten utifrån spelhastigheten, som successivt ökar.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public abstract class MovingItem {
	
	private int posX;
	private int posY;
	private int width = 20;
	private int height = 20;
	private BufferedImage image;
	private GameModel model;
		

		public MovingItem(int x, int y, GameModel model) {
			this.posX = x;
			this.posY = y;
			this.model = model;
		}
		
		public abstract void draw(Graphics g);
		public abstract void doCommand(BatmanCar player);
		
		public void update() {
			setPosY(this.getPosY() + model.getGAME_SPEED());
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
