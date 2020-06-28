package com.anim.ui;

import javax.swing.JFrame;

import com.anim.constants.Constants;
import com.anim.image.Image;
import com.anim.image.Images;

public class GameMainFrame extends JFrame {
	
	public GameMainFrame () {
		
		initializeLayout();
	}
	
	public void initializeLayout() {
		
		add(new GamePanel());
		
		
		setTitle(Constants.TITLE);
		
		setIconImage(Images.createImage(Image.SPACESHIP).getImage());
		
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
		setVisible(true);
	}
	

}
