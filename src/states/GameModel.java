package states;
import java.awt.*;
import java.util.ArrayList;

import grafikdelar.ImageManager;
import grafikdelar.MovingItem;
import grafikdelar.ThrowBatarang;


/**
* Vår model lagrar all data som våra klasser sedan använder och uppdaterar.
* Håller reda på vilken state Simulatorn befinner sig i.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class GameModel {
	
	private GameState currentState;
	private ArrayList<MovingItem> movingitems;
	private ImageManager imagemanager;
	private FileManager filemanager;
	private int currentScore = 0;
	private int GAME_SPEED;
	private int ammo = 0;
	private int highscore;
	private ArrayList<ThrowBatarang> batarangs;
	
	public GameModel() {
		newGame();
		this.currentState = new MenuState(this);	
	}
	 public void switchState(GameState nextState) {
	        currentState = nextState;
	    }
	 
	 
	 public void newGame(){
		 movingitems = new ArrayList<MovingItem>();
		 batarangs = new ArrayList<ThrowBatarang>();
		 imagemanager = new ImageManager();
		 filemanager = new FileManager();
		 currentScore = 0;
		 highscore = filemanager.getIntegerArray(0);
		 ammo= 0;
		 
	 }
	 public void keyPressed(int key) {
		 currentState.keyPressed(key);
	 }
	 
	 public void update() {
		 currentState.update();
	 }
	 
	 public void draw(Graphics g) {
		 currentState.draw(g);	 
	 }
	public ArrayList<MovingItem> getMovingItems() {
		return movingitems;
	}
	public void setMovingItem(MovingItem movingitem) {
		movingitems.add(movingitem);
	}
	public ArrayList<ThrowBatarang> getBatarangs() {
		return batarangs;
	}
	public void setBatarangs(ThrowBatarang throwbatarang) {
		batarangs.add(throwbatarang);
	}
	public int getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}
	public ImageManager getImagemanager() {
		return imagemanager;
	}
	public void setImagemanager(ImageManager imagemanager) {
		this.imagemanager = imagemanager;
	}
	public int getGAME_SPEED() {
		return GAME_SPEED;
	}
	public void setGAME_SPEED(int gAME_SPEED) {
		GAME_SPEED = gAME_SPEED;
	}
	public FileManager getFilemanager() {
		return filemanager;
	}
	public GameState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}
	public int getAmmo() {
		return ammo;
	}
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	public int getHighscore() {
		return highscore;
	}
	
	 
}
