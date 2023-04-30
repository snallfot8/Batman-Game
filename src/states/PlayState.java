package states;

import static constants.Constants.SCREEN_HEIGHT;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import grafikdelar.*;

/**
* Vårt viktigaste och tyngsta state, där Simulatorn utförs
* Porträtterar Gotham där du är Batman och flyger bland skyskraporna
* Powerups, fiender och skyskrapor dyker upp som man kan plocka upp/döda/undvika
* @author Erik Snällfot
* @author Axel Nilsson
*/
public class PlayState extends GameState {
	private int tick = 0;

	private RightPanel right;
	private LeftPanel left;
	private BatmanCar player;
	private BufferedImage image;

	private int randomNumberw, randomEnemy, randomItem, randomStart, randomObstacleEnemy, randomThree, randomPickUp,
			randomSpace;
	int ticks = 0;

	public PlayState(GameModel model) {
		super(model);
		model.setGAME_SPEED(1);
		image = this.model.getImagemanager().getObjectImage("playstate");
		right = new RightPanel(model);
		left = new LeftPanel(model);
		player = new BatmanCar(model);
		addItems(true);

		for (int i = 0; i < 9; i++) {
			addItems(false);
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, 800, SCREEN_HEIGHT, null);
		left.draw(g);
		right.draw(g);
		player.draw(g);

		for (int i = 0; i < model.getMovingItems().size(); i++) {
			model.getMovingItems().get(i).draw(g);
		}

		for (int i = 0; i < model.getBatarangs().size(); i++) {
			model.getBatarangs().get(i).draw(g);
		}
	}

	@Override
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_RIGHT) {
			player.updateDirection('r');

		} else if (key == KeyEvent.VK_LEFT) {
			player.updateDirection('l');
		} else if (key == KeyEvent.VK_ESCAPE) {
			model.switchState(new PauseState(model, this));
		} else if ((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) && model.getAmmo() > 0) {
			model.getBatarangs().add(new ThrowBatarang(model, player));
			model.setAmmo(model.getAmmo() - 1);
		}
	}

	@Override
	public void update() {
		// Movingitems uppdateras
		for (int i = 0; i < model.getMovingItems().size(); i++) {
			model.getMovingItems().get(i).update();
		}
		// batman uppdateras
		player.update();

		// uppdaterar batarangs
		for (int i = 0; i < model.getBatarangs().size(); i++) {
			model.getBatarangs().get(i).update();
		}
		// tar bort batarangs som åkt utanför skärmen
		for (int i = 0; i < model.getBatarangs().size(); i++) {
			if (model.getBatarangs().get(i).getPosY() < 0) {
				model.getBatarangs().remove(model.getBatarangs().get(i));
			}
		}

		// checkar om en kollision har skett
		enemyCollision();

		// tar bort movingitems som åkt ut ur skärmen
		for (int i = 0; i < model.getMovingItems().size(); i++) {
			if (model.getMovingItems().get(i).getPosY() > 950) {
				model.getMovingItems().remove(model.getMovingItems().get(i));
				addItems(false);
			}
		}

		// ser till så att arraylisten med movingitems inte blir överdrivet stor
		if (model.getMovingItems().size() > 9) {
			model.getMovingItems().remove(model.getMovingItems().size() - 1);
			model.getMovingItems().remove(model.getMovingItems().size() - 1);
			model.getMovingItems().remove(model.getMovingItems().size() - 1);
		}

		// man får ett poäng vid varje 80:de tick
		if (tick % 80 == 0) {
			int mellanhigh = model.getCurrentScore();
			int high = mellanhigh + 1;
			model.setCurrentScore(high);
		}

		// var tionde poäng ökas speeden
		
		if (tick % 200 == 0) {
			System.out.println(model.getGAME_SPEED());
			model.setGAME_SPEED(model.getGAME_SPEED() + 1);
		}

		tick++;

	}

	public void addItems(boolean start) {
		System.out.println(model.getMovingItems().size());
		int[] randomx = randomLane();
		int randomspace = -400 + randomSpace();

		//lägger till items i början av spelet
		if (start) {
			randomStart = ThreadLocalRandom.current().nextInt(0, 3);
			int randomstart = randomStart;
			if (randomstart == 0) {
				model.setMovingItem(new Joker(160, 0, model));
				model.setMovingItem(new Penguin(610, 0, model));
				model.setMovingItem(new Batarang(400, 0, model));
			} else if (randomstart == 1) {
				model.setMovingItem(new Penguin(160, 0, model));
				model.setMovingItem(new Joker(400, 0, model));
				model.setMovingItem(new Batarang(610, 0, model));
			} else if (randomstart == 2) {
				model.setMovingItem(new Penguin(610, 0, model));
				model.setMovingItem(new Riddler(400, 0, model));
				model.setMovingItem(new Batarang(160, 0, model));
			}
			//lägger till items under spelets gång
		} else if (!start) {
			int y = model.getMovingItems().get(model.getMovingItems().size() - 1).getPosY() + randomspace;
			randomNumberw = ThreadLocalRandom.current().nextInt(0, 7);
			int randomnumber = randomNumberw;

			if (randomnumber == 0) { // ett movingitem ska spawna på vägen
				model.setMovingItem(randomMovingItem(randomx[0], y));
			}

			// hanterar när fler än ett item ska spawnas
			else {
				randomThree = ThreadLocalRandom.current().nextInt(0, 8);
				int randomthree = randomThree;
				if (randomthree == 0 || randomthree == 3) {
					model.setMovingItem(randomObstacleEnemy(randomx[0], y));
					model.setMovingItem(randomObstacleEnemy(randomx[1], y));

				} else if (randomthree == 1 || randomthree == 4) {
					model.setMovingItem(randomObstacleEnemy(randomx[0], y));
					model.setMovingItem(randomMovingItem(randomx[1], y));

				} else if (randomthree == 2) {
					model.setMovingItem(randomObstacleEnemy(randomx[0], y));
					model.setMovingItem(randomObstacleEnemy(randomx[1], y));
					model.setMovingItem(randomPickUp(randomx[2], y));
				}
				 else if (randomthree == 5) {
					model.setMovingItem(randomEnemy(randomx[0], y, model));
					model.setMovingItem(randomEnemy(randomx[1], y, model));
					if ( model.getAmmo() > 0) {
					model.setMovingItem(randomEnemy(randomx[2], y, model)); }
				 }
				else if (randomthree == 6 && model.getAmmo() > 0) {
					model.setMovingItem(randomObstacleEnemy(160, y));
					model.setMovingItem(randomObstacleEnemy(610, y));
					if ( model.getAmmo() > 0) {
					
					model.setMovingItem(randomEnemy(400, y, model));
					}
				} else if ( randomthree == 7) {
					model.setMovingItem(new Obstacle(randomx[0], y, model));
					model.setMovingItem(new Obstacle(randomx[1], y, model));
				}
				else {
					model.setMovingItem(randomObstacleEnemy(randomx[0], y));
					model.setMovingItem(randomObstacleEnemy(randomx[1], y));	
				}
			}

		}
	}

	public void enemyCollision() {
		for (int i = 0; i < model.getMovingItems().size(); i++) {
			//kollar om spelaren krockat med ett movingitem
			Rectangle e = new Rectangle(model.getMovingItems().get(i).getPosX(),
					model.getMovingItems().get(i).getPosY(), model.getMovingItems().get(i).getWidth(),
					model.getMovingItems().get(i).getHeight());
			Rectangle p = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

			if (p.intersects(e)) {
				model.getMovingItems().get(i).doCommand(player);
				System.out.println("kollision");
			}

			for (int l = 0; l < model.getBatarangs().size(); l++) {
				//kollar om en batarang krockar med ett movingitem
				Rectangle b = new Rectangle(model.getBatarangs().get(l).getPosX(),
						model.getBatarangs().get(l).getPosY(), model.getBatarangs().get(l).getWidth(),
						model.getBatarangs().get(l).getHeight());
				if (b.intersects(e) && model.getMovingItems().get(i) instanceof Obstacle) {
					model.getBatarangs().remove(model.getBatarangs().get(l));
				} else if (b.intersects(e) && model.getMovingItems().get(i) instanceof Enemy) {
					// model.getBatarangs().get(i).doCommand(player);
					model.getBatarangs().remove(model.getBatarangs().get(l));
					model.getMovingItems().remove(model.getMovingItems().get(i));

				}

			}
		}
	}

	//en random enemy spawnas
	public MovingItem randomEnemy(int x, int y, GameModel mode) {
		randomEnemy = ThreadLocalRandom.current().nextInt(0, 3);
		int randomenemy = randomEnemy;
		if (randomenemy == 0) {
			return new Joker(x, y, model);
		} else if (randomenemy == 1) {
			return new Penguin(x, y, model);
		} else if (randomenemy == 2) {
			return new Riddler(x, y, model);
		}
		return null;
	}

	// ett random movingitem spawnas
	public MovingItem randomMovingItem(int x, int y) {
		randomItem = ThreadLocalRandom.current().nextInt(0, 15); // bestämmer sannloikheten på att ett visst item
																	// spawnar
		int randomitem = randomItem;
		if (randomitem == 0 || randomitem == 12) {
			return new Joker(x, y, model);
		} else if (randomitem == 1 || randomitem == 10) {
			return new Penguin(x, y, model);
		} else if (randomitem == 2 || randomitem == 11) {
			return new Riddler(x, y, model);
		} else if (randomitem == 3 || randomitem == 4 || randomitem == 5) {
			return new Obstacle(x, y, model);
		} else if (randomitem == 6 || randomitem == 7 || randomitem == 8 || randomitem == 13 || randomitem == 14) {
			return new Batarang(x, y, model);
		} else if (randomitem == 9) {
			return new PowerUp(x, y, model);
		}
		return null;
	}

	//ett random obstacle eller enemy spawnas
	public MovingItem randomObstacleEnemy(int x, int y) {
		randomObstacleEnemy = ThreadLocalRandom.current().nextInt(0, 6); // bestämmer sannolikheten för vilken
																			// enemy/obstacler som ska spawnas
		int randomobstacleenemy = randomObstacleEnemy;

		if (randomobstacleenemy == 0 || randomobstacleenemy == 1 || randomobstacleenemy == 2) {
			return new Obstacle(x, y, model);
		} else if (randomobstacleenemy == 3) {
			return new Joker(x, y, model);
		} else if (randomobstacleenemy == 4) {
			return new Penguin(x, y, model);
		} else if (randomobstacleenemy == 5) {
			return new Riddler(x, y, model);
		}
		return null;
	}

	//ett random pickup spawnas
	public MovingItem randomPickUp(int x, int y) {
		randomPickUp = ThreadLocalRandom.current().nextInt(0, 4); // bestämmer sannolikhet för vilken pick up som ska
																	// spawnas
		int randompickup = randomPickUp;

		if (randompickup == 0) {
			return new PowerUp(x, y, model);
		} else if (randompickup == 1 || randompickup == 2 || randompickup == 3) {
			return new Batarang(x, y, model);
		}
		return null;
	}

	//bestämmer en random lane som ett föremål ska spawnas i
	public int[] randomLane() {
		int[] lanes = new int[] { 160, 400, 610 };
		lanes = shuffleArray(lanes);
		return lanes;
	}

	//används för att shuffle arrayen med lanes
	static int[] shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return ar;
	}

	//genererar ett random avstånd mellan föremålen
	public int randomSpace() {
		randomSpace = ThreadLocalRandom.current().nextInt(0, 3);
		int randspace = randomSpace;
		if (randspace == 0) {
			return 0;
		} else if (randspace == 1) {
			return -25;
		} else if (randspace == 2) {
			return -50;
		}
		return 0;
	}

}
