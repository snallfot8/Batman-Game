package main;

import states.GameModel;
import javax.swing.*;
import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * GamePanel skapas i GameFrame.
* I GamePanel sätter vi storleken på fönstret.
* Vi har även en lyssnare som skickar vidare tangentryck till vår model
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class GamePanel extends JPanel {

	private GameModel model;

	public GamePanel(final GameModel model) {
		this.model = model;

		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				model.keyPressed(e.getKeyCode());
			}
		});

	}

	public void paintComponent(Graphics g) {
		model.draw(g);
	}
}
