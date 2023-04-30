package grafikdelar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
* Lagrar alla bilder i en hashmap.
* Detta gör det smidigt för klasserna att kalla på de bilder de behöver.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class ImageManager {
	
	 private Map<String, BufferedImage> imagemap = new HashMap<String, BufferedImage>();	
	 
	 public ImageManager() {
		 
		 setImage("joker","./images/jokerImage.png");
		 setImage("riddler", "./images/riddlerImage.png");
		 setImage("penguin", "./images/penguinImage.png");
		 setImage("batman", "./images/batmanImage.png");
		 setImage("batarang", "./images/batarangImage.png");
		 setImage("obstacle", "./images/obstacleImage.png");
		 setImage("playstate", "./images/playstatebackgroundImage.jpg");
		 setImage("clock", "./images/clockImage.png");
	
		 setImage("menustate", "./images/MenuState.png");
		 setImage("rightpanel", "./images/rightpanel.png");
		 setImage("leftpanel", "./images/leftpanel.png");
		 setImage("endstate", "./images/EndState.png");
		 setImage("pausestate", "./images/PauseState.png");
		 setImage("infostate", "./images/InfoState.png");
		 setImage("highscorestate", "./images/HighscoreState.png");
	 }
	 
	 private BufferedImage setImage(String key, String imgPath) {
	        try {
	            imagemap.put(key, ImageIO.read(new File(imgPath)));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	 public BufferedImage getObjectImage(String str) {
		 return imagemap.get(str);
	 }
}
