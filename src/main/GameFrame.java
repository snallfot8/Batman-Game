package main;
import states.GameModel;
import javax.swing.*;


/**
* GameFrame är Simulatorns fönster. 
* Här lägger vi till vår GamePanel, som sedan bestämmer storleken på fönstret.
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class GameFrame extends JFrame{

	public GameFrame(String name, GameModel model) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new GamePanel(model));
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setName(name);
		this.setTitle(name);
		this.setVisible(true);
		
	}
}
