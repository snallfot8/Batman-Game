package grafikdelar;

import java.awt.Color;
import java.awt.Graphics;

import states.GameModel;

/**
* Kan plockjas upp, och gör att simulatorn går långsammare.
* För en märkbar skillnad blir spelet mer långsamt ju snabbare det var från början. 
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class PowerUp extends PickUp {
	private int width = 100;
	private int height = 100;

	public PowerUp(int x, int y, GameModel model) {
		super(x, y, model);
		this.setImage(this.getModel().getImagemanager().getObjectImage("clock"));
		this.setPosX(this.getPosX() - 40);
		this.setPosY(this.getPosY() + 10);
	}

	@Override
	public void doCommand(BatmanCar player) {
		if (this.getModel().getGAME_SPEED() > 15) {
			this.getModel().setGAME_SPEED(this.getModel().getGAME_SPEED() - 4);
		} else if (this.getModel().getGAME_SPEED() > 10) {
			this.getModel().setGAME_SPEED(this.getModel().getGAME_SPEED() - 3);
		} else if (this.getModel().getGAME_SPEED() > 5) {
			this.getModel().setGAME_SPEED(this.getModel().getGAME_SPEED() - 2);
		} else if (this.getModel().getGAME_SPEED() > 1) {
			this.getModel().setGAME_SPEED(this.getModel().getGAME_SPEED() - 1);
		}
		this.getModel().getMovingItems().remove(this);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.getPosX(), this.getPosY(), width, height, null);

	}

}
