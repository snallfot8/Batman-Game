package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


/**
* När du avlidit i simulatorn dirigeras du automatiskt hit.
* Här kan du välja mellan att Simulera flygningen igen, eller återgå till menyn
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class EndState extends GameState implements MouseListener {
private BufferedImage image;
private int choice = 1;
	public EndState(GameModel model) {
		super(model);
		image = this.model.getImagemanager().getObjectImage("endstate");

	}
	
	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Monospace", Font.PLAIN, 50));
		g.drawString(Integer.toString(model.getCurrentScore()), 400, 615);

		if (choice == 1) {
			g.drawRect(60, 635, 315, 156);
		}
		if (choice == 2) {
			g.drawRect(418, 633, 315, 156);
		}

	}

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_RIGHT && choice == 1) {
			choice = 2;
		} else if (key == KeyEvent.VK_LEFT && choice == 2) {
			choice = 1;
		}
		
		
		if (key == KeyEvent.VK_ENTER && choice == 1) {
			model.getMovingItems().clear();
			model.newGame();
			model.switchState(new PlayState(model));
		}

		if (key == KeyEvent.VK_ENTER && choice == 2) {
			model.getMovingItems().clear();
			model.newGame();
			model.switchState(new MenuState(model));
		}

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
