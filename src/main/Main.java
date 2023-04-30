package main;

import java.awt.Toolkit;

import constants.Constants;
import grafikdelar.ImageManager;
import states.GameModel;


/**
* I Main körs vår Simluator. 
* Main har en model som innehåller information om vårt spel.
* Main skapar en frame till Simulatorn.
* Vår model uppdateras med en viss feekvens. 
* I samma takt som model uppdateras målar vi även ut informationen på vår frame.
* @author Erik Snällfot
* @author Axel Nilsson
* 
*/

public class Main {
	
	public static Boolean GAME_ON = true;

	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameFrame frame = new GameFrame("Batman Flight Simulator", model);
		

		// Set the number of frames
		final double fps = 60.0;

		// Calculate frequency
		double ms = 1000.0 / fps;

		while (true) {
			// Variable that will help us decide how long rendering and update took
			long lastTime = System.currentTimeMillis();

			// Perform game update and game rendering.
			model.update();
			frame.repaint();

			// Calculate the time it took to update and render
			long timer = System.currentTimeMillis() - lastTime;

			Toolkit.getDefaultToolkit().sync(); // Sync the graphics state (see java docs for more info)
			
			try {
				// The difference between ms and timer is how much faster/slower we are then 60
				// fps
				// if we are faster, sleep that amount else sleep 0 seconds
				Thread.sleep((long) Math.max(ms - timer, 0));
			} catch (InterruptedException e) {
				// Write to to log here
			}
		}
	}
}
