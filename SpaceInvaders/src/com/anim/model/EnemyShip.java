package com.anim.model;

import javax.swing.ImageIcon;

import com.anim.image.Image;
import com.anim.image.Images;

public class EnemyShip extends Sprite {
	
	private boolean visible = true;
	
	public EnemyShip(int x, int y) {
		
		this.x = x;
		
		this.y = y;
		
		initilize();
	}

	private void initilize() {
		
		ImageIcon imageIcon = Images.createImage(Image.UFO);
		
		setImage(imageIcon.getImage());
		
	}
	
	public void setVisible(boolean visible) {
		
		this.visible = visible;
	}
	
	public boolean isVisible() {
		
		return visible;
	}
	
	public void move(int direction) {
		
		this.x += direction;
	}

	@Override
	public void move() {
		
		
	}
}
