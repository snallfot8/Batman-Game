package grafikdelar;

import java.awt.Color;
import java.awt.Graphics;

import states.GameModel;

/**
* Riddler är en typ av fiende.
* Riddler behöver undvikas eller förgöras med en batarang.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class Riddler extends Enemy {

	private int width = 90;
	private int height = 120;
	
	public Riddler(int x, int y, GameModel model) {
		super(x, y, model);
		this.setImage(this.getModel().getImagemanager().getObjectImage("riddler"));
		this.setPosX(this.getPosX()-10);
	
		this.setPosY(this.getPosY());
	}

	public void doCommand() {
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.getPosX(), this.getPosY(), width, height, null);

	}

}
