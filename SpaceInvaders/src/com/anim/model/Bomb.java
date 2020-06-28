package com.anim.model;

import javax.swing.ImageIcon;

import com.anim.constants.Constants;
import com.anim.image.Image;
import com.anim.image.Images;

public class Bomb extends Sprite {
	
	public Bomb(int x, int y) {
		
		this.x = x;
		
		this.y = y;
		
		initialize();
	}
	
	private void initialize() {
		
		ImageIcon imageIcon = Images.createImage(Image.BOMB);
		
		setImage(imageIcon.getImage());
	}

	@Override
	public void move() {
		
		this.y++;
		
		if(y >= Constants.BOARDHEIGHT - Constants.BOMBHEIGHT) {
			
			die();
		}
	}
}
