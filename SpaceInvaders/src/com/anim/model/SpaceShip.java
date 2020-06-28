package com.anim.model;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.anim.constants.Constants;
import com.anim.image.Image;
import com.anim.image.Images;

public class SpaceShip extends Sprite {
	
	public SpaceShip() {
		
		initialize();
	}

	private void initialize() {
		
		ImageIcon imageIcon = Images.createImage(Image.SPACESHIP);
		
		setImage(imageIcon.getImage());
		
		int startX = Constants.BOARDWIDTH / 2 - Constants.SPACESHIPWIDTH / 2;
		
		int startY = Constants.BOARDHEIGHT - 100;
		
		setX(startX);
		
		setY(startY); 
	}

	@Override
	public void move() {
		
		x = x + dx;
		
		// Check Whether Space Ship is Outside of Left Side
		
		if(x < Constants.SPACESHIPWIDTH) {
			
			x = Constants.SPACESHIPWIDTH;
		}
		
		// Checking Whether Space Ship is Outside of Right Side
		
		if(x >= Constants.BOARDWIDTH - 2 * Constants.SPACESHIPWIDTH) {
			
			x = Constants.BOARDWIDTH - 2 * Constants.SPACESHIPWIDTH;
		}
			
	}

	public void keyPressed(KeyEvent event) {
		
		int key = event.getKeyCode();
		
		
		// SpaceShip Translating Left Side of Screen
		
		if(key == KeyEvent.VK_LEFT) {
			
			dx = -2;
		}
		
		// SpaceShip Translating Right Side of Screen
		
		if(key == KeyEvent.VK_RIGHT) {
			
			dx = 2;
		}
	}
	
	public void keyReleased(KeyEvent event) {
		
		int key = event.getKeyCode();
		
		// Stopping SpaceShip's Translation while Key Released for Right Side 
		
		if(key == KeyEvent.VK_RIGHT) {
			
			dx = 0;
		}
		
		// Stopping SpaceShip's Translation while Key Released for Left Side
		
		if(key == KeyEvent.VK_LEFT) {
			
			dx = 0;
		}
	}

}
