package grafikdelar;
import java.awt.Graphics;

import states.GameModel;

/**
* Joker är en typ av fiende.
* Joker behöver undvikas eller förgöras med en batarang.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class Joker extends Enemy {
	
	private int width = 50;
	private int height = 140;
	
	
	public Joker(int x, int y, GameModel model) {
		super(x, y, model);
		this.setImage(this.getModel().getImagemanager().getObjectImage("joker"));
		this.setPosX(this.getPosX()-10);
		this.setPosY(this.getPosY());
	}
	
	public void doCommand() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.getPosX(), this.getPosY(), width, height, null);
		
	}
	
	
}
