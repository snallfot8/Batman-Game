package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
* Huvudklass för alla andra states
* Ett state är ett visst läge i spelet
* @author Erik Snällfot
* @author Axel Nilsson
*/
public abstract class GameState {
	protected GameModel model;


    public GameState(GameModel model) {
        this.model = model;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void keyPressed(int key);

    public void drawBg(Graphics g, Color color) {
     g.setColor(color);
     g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void activate() { }

    public void deactivate() { }
}
