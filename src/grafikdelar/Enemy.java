package grafikdelar;

import main.Main;
import states.EndState;
import states.GameModel;

/**
* Superklass till de olika fienderna.
* Kollision med en enemy innebär att simulatorn avslutas.
* Kan förgöras med hjälp av batarangs.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public abstract class Enemy extends MovingItem {

	private GameModel model;
	public Enemy(int x, int y, GameModel model) {
		super(x,y, model);
		this.model = model;
	}
	
	public void doCommand(BatmanCar player) {
		model.getFilemanager().intIntoFile(model.getCurrentScore());
		Main.GAME_ON = false;
		this.getModel().switchState(new EndState(this.getModel()));
		
	}
}
