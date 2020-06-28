package com.anim.image;

import javax.swing.ImageIcon;

import com.anim.constants.Constants;

public class Images {
	
	public static ImageIcon createImage(Image image) {
		
		ImageIcon imageIcon = null;
		
		switch(image) {
		
		case UFO:
			imageIcon = new ImageIcon(Constants.UFOIMAGE);
			break;
			
		case BOMB:
			imageIcon = new ImageIcon(Constants.BOMBIMAGE);
			break;
			
		case BACKGROUND:
			imageIcon = new ImageIcon(Constants.BACKGROUNDIMAGE);
			break;
			
		case SPACESHIP:
			imageIcon = new ImageIcon(Constants.SPACESHIPIMAGE);
			break;
			
		case LASER:
			imageIcon = new ImageIcon(Constants.LASERIMAGE);
			break;
			
		default:
			return null;
				
		}
			
		return imageIcon;
	}	
}
