package grafikdelar;


import java.awt.Graphics;

import states.GameModel;

/**
* Penguin är en typ av fiende.
* Penguin behöver undvikas eller förgöras med en batarang.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class Penguin extends Enemy {

	private int width = 90;
	private int height = 120;
	public Penguin(int x, int y, GameModel model) {
		super(x, y, model);
		this.setImage(this.getModel().getImagemanager().getObjectImage("penguin"));
		this.setPosX(this.getPosX()-20);
	
		this.setPosY(this.getPosY());
	}

	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.getPosX(), this.getPosY(), width, height, null);
	}

	public void doCommand() {

	}

}
