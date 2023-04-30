package grafikdelar;

import java.awt.Graphics;

import states.GameModel;

/**
* En batarang kan man plocka upp, för att sedan kasta på en fiende. 
* Model lagrar hur många batarangs spelaren har.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class Batarang extends PickUp {
	private int width = 120;
	private int height = 50;
	
	private GameModel model;
	
	public Batarang(int x, int y, GameModel model) {
		super(x,y, model);
		this.model = model;
		this.setImage(this.getModel().getImagemanager().getObjectImage("batarang"));
		this.setPosX(this.getPosX()-40);
		this.setPosY(this.getPosY()+30);
	}
	
	
	@Override
	public void doCommand(BatmanCar player) {
		model.setAmmo(model.getAmmo() + 1);
		model.getMovingItems().remove(this);

	}
	
	@Override
    public void draw(Graphics g){
		g.drawImage(this.getImage(), this.getPosX(), this.getPosY(), width, height, null);
}





	
	

}
