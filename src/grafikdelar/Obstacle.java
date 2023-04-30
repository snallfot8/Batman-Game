package grafikdelar;


import java.awt.Graphics;

import main.Main;
import states.EndState;
import states.GameModel;

/**
* Kollision med en obstacle innebär att simulatorn avslutas.
* Kan ej förgöras och behöver således undvikas.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class Obstacle extends MovingItem {
	private int width = 120;
	private int height = 140;
	private GameModel model;
	
	public Obstacle(int x, int y, GameModel model) {
		super(x,y, model);
		this.setImage(this.getModel().getImagemanager().getObjectImage("obstacle"));
		this.setPosX(this.getPosX()- 40);
		
		this.setPosY(this.getPosY());
		this.model = model;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.getPosX(), this.getPosY(), width, height, null);
	}

	@Override
	public void doCommand(BatmanCar player) {
		model.getFilemanager().intIntoFile(model.getCurrentScore());

		Main.GAME_ON = false;
		this.getModel().switchState(new EndState(this.getModel()));
	}
	
	

}
