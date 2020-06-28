package com.anim.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.anim.callbacks.GameEventListener;
import com.anim.constants.Constants;
import com.anim.image.Image;
import com.anim.image.Images;
import com.anim.model.Bomb;
import com.anim.model.EnemyShip;
import com.anim.model.Laser;
import com.anim.model.SpaceShip;
import com.anim.sound.Sounds;

public class GamePanel extends JPanel{
	
	private ImageIcon backgroundImage;
	
	private Timer timer;
	
	private SpaceShip spaceShip;
	
	private Laser laser;
	
	private boolean inGame = true;
	
	private int direction = -1;
	
	private List<EnemyShip> enemyShips;
	
	private List<Bomb> bombs;
	
	private Sounds sounds;
	
	private Random generator;
	
	private String message;
	
	private int death;
	
	private int scores;
	
	private int numberOfShields = 2;

	public GamePanel() {
		
		initializeVariables();
		
		initializeLayout();
		
		initializeGame();
	}
	
	private void initializeVariables() {
		
		this.spaceShip = new SpaceShip();
		
		this.enemyShips = new ArrayList<>();
		
		this.bombs = new ArrayList<>();
		
		this.laser = new Laser();
		
		this.sounds = new Sounds();
		
		this.backgroundImage = Images.createImage(Image.BACKGROUND);
		
		this.timer = new Timer(Constants.GAMESPEED, new GameLoop(this));
				
		this.timer.start();
		
		this.generator = new Random();
	}
	
	private void initializeLayout() {
		
		addKeyListener(new GameEventListener(this));
		
		setFocusable(true);
		
		setPreferredSize(new Dimension(Constants.BOARDWIDTH, Constants.BOARDHEIGHT));
	}
	
	private void initializeGame() {
		
		for(int i = 0; i < Constants.ENEMYSHIPSROW; i++) {
			
			for(int j = 0; j < Constants.ENEMYSHIPSCOLUMN; j++) {
				
				EnemyShip enemyShip = new EnemyShip(Constants.ENEMYSHIPINTX + 50 * j, Constants.ENEMYSHIPINTY + 50 * i);
				
				this.enemyShips.add(enemyShip);
			}
		}
	}
	
	private void drawPlayer(Graphics graphics) {
		
		graphics.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}
	
	private void drawLaser(Graphics graphics) {
		
		if(!laser.isDead()) {
			
			graphics.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
		}
	}
	
	private void drawAliens(Graphics graphics) {
		
		for(EnemyShip enemyShip : this.enemyShips) {
			
			if(enemyShip.isVisible()) {
				
				graphics.drawImage(enemyShip.getImage(), enemyShip.getX(), enemyShip.getY(), this);
			}
		}
	}
	
	public void drawBombs(Graphics graphics) {
		
		for(Bomb bomb : this.bombs) {
			
			if(!bomb.isDead()) {
				
				graphics.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
			}
		}
	}
	
	public void drawGameOver(Graphics graphics) {
		
		graphics.drawImage(backgroundImage.getImage(), 0, 0, null);
		
		Font font = new Font("Helvetica", Font.BOLD, 50);
		
		FontMetrics fontMetrics = this.getFontMetrics(font);
		
		graphics.setColor(Color.white);
		
		graphics.setFont(font);
		
		graphics.drawString(message, (Constants.BOARDWIDTH - fontMetrics.stringWidth(message)) / 2, Constants.BOARDHEIGHT / 2 - 200);
	}
	
	public void drawScore(Graphics graphics) {
		
		if(!inGame) {
			
			return;
		}
		
		Font font = new Font("Helvetica", Font.BOLD, 20);
		
		graphics.setFont(font);
		
		graphics.setColor(Color.lightGray);
		
		graphics.drawString("Score: " + scores, Constants.BOARDWIDTH - 150, 50);
		
		graphics.drawString("Shileds: " + numberOfShields, 50, 50);
	}
		
	@Override
	protected void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		graphics.drawImage(backgroundImage.getImage(), 0, 0, null);
		
		doDrawing(graphics);
	}
	
	private void doDrawing(Graphics graphics) {
		
		if(inGame) {
			
			drawScore(graphics);
			
			drawPlayer(graphics);
			
			drawLaser(graphics);
			
			drawAliens(graphics);
			
			drawBombs(graphics);
		}
		else {
			
			if(timer.isRunning()) {
				
				timer.stop();
			}
			
			drawGameOver(graphics);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}

	public void doOneLoop() {
		
		Update();
		
		repaint();
	}
	
	private void Update() {
		
		// Player Looses The Game
		
		if(spaceShip.isDead()) {
			
			inGame = false;
			
			message = Constants.GAMEOVER;
		}
		
		// Player Wins The Game
		
		if(death == this.enemyShips.size()) {
			
			inGame = false;
			
			message = Constants.WIN;
		}
		
		this.spaceShip.move();
		
		if(!laser.isDead()) {
			
			int shotX = laser.getX();
			
			int shotY = laser.getY();
			
			for(EnemyShip alien : this.enemyShips) {
				
				int alienX = alien.getX();
				
				int alienY = alien.getY();
				
				if(!alien.isVisible()) {
					
					continue;
				}
				
				// Collision Detection
				
				if(shotX >= (alienX) && shotX <= (alienX + Constants.ENEMYSHIPWIDTH) && shotY >= (alienY) && shotY <= (alienY + Constants.ENEMYSHIPHEIGHT)) {
					
					alien.setVisible(false);
					
					laser.die();
					
					death++;
					
					sounds.Explosion();
					
					scores += 10;
				}
			}
			
			this.laser.move();
		}
		
		for(EnemyShip enemyShip : this.enemyShips) {
			
			// EnemeyShip Movement
			
			if((enemyShip.getX() >= Constants.BOARDWIDTH - 2 * Constants.BOARDERPADDING && direction != -1) || (enemyShip.getX() <= Constants.BOARDERPADDING && direction != 1)) {
				
				direction *= -1;
				
				Iterator<EnemyShip> enemyShipIterator = enemyShips.iterator();
				
				while(enemyShipIterator.hasNext()) {
					
					EnemyShip enemy = enemyShipIterator.next();
					
					enemy.setY(enemy.getY() + Constants.DOWN);
				}
			}
			
			if(enemyShip.isVisible()) {
				
				// EnemyShips Reaching Bottom of Screen 
				
				if(enemyShip.getY() > Constants.BOARDHEIGHT - 100 - Constants.SPACESHIPHEIGHT) {
					
					spaceShip.die();
					
				}
				
				enemyShip.move(direction);
			}
		}
		
		// Bombs by Enemy Ships
		
		for(EnemyShip enemyShip : this.enemyShips) {
			
			if(enemyShip.isVisible() && generator.nextDouble() < Constants.BOMBDROPPINGPROBABILITY) {
				
				Bomb bomb = new Bomb(enemyShip.getX(), enemyShip.getY());
				
				this.bombs.add(bomb);
			}
		}
		
		// Moving Bombs
		
		for(Bomb bomb : bombs) {
			
			int bombX = bomb.getX();
			
			int bombY = bomb.getY();
			
			int spaceShipX = spaceShip.getX();
			
			int spaceShipY = spaceShip.getY();
			
			if(!bomb.isDead() && !spaceShip.isDead()) {
				
				if(bombX >= (spaceShipX) && bombX <= (spaceShipX + Constants.SPACESHIPWIDTH) && bombY >= (spaceShipY) && bombY <= (spaceShipY + Constants.SPACESHIPHEIGHT)) {
					
					bomb.die();
					
					numberOfShields--;
					
					if(numberOfShields < 0) {
						
						spaceShip.die();
					}
				}
			}
			
			if(!bomb.isDead()) {
				
				bomb.move();
			}
		}
	}

	public void keyReleased(KeyEvent event) {
		
		this.spaceShip.keyReleased(event);
	}

	public void keyPressed(KeyEvent event) {
		
		this.spaceShip.keyPressed(event);
		
		// Space Button Generates New Laser Beam
		
		int key = event.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			
			sounds.Laser();
			
			int laserX = this.spaceShip.getX();
			
			int laserY = this.spaceShip.getY();
			
			if(inGame && laser.isDead()) {
				
				laser = new Laser(laserX, laserY);
			}
		}
	}
}
