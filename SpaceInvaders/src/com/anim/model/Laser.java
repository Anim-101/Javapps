package com.anim.model;

import javax.swing.ImageIcon;

import com.anim.constants.Constants;
import com.anim.image.Image;
import com.anim.image.Images;

public class Laser extends Sprite {
	
	public Laser() {
		
	}

	public Laser(int x, int y) {
		
		this.x = x;
		
		this.y = y;
		
		initialize();
	}
	
	private void initialize() {
		
		ImageIcon imageIcon = Images.createImage(Image.LASER);
		
		setImage(imageIcon.getImage());
		
		setX(x + Constants.SPACESHIPWIDTH / 2);
		
		setY(y);
		
		
	}

	@Override
	public void move() {
		
		this.y -= Constants.LASERHORIZONTALTRANSLATION;
		
		if(this.y < 0) {
			
			this.die();
		}
		
	}

}
