package grafikdelar;

import states.GameModel;

/**
* Superklass till de objekt man kan plocka upp.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public abstract class PickUp extends MovingItem {
	
	public PickUp(int x, int y, GameModel model) {
		super(x,y, model);
		
	}
	
	public abstract void doCommand(BatmanCar player);
	
}
