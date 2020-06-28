package com.anim.callbacks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.anim.ui.GamePanel;


public class GameEventListener extends KeyAdapter {
	
	private GamePanel gamePanel;
	
	public GameEventListener(GamePanel gamePanel) {
		
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyReleased(KeyEvent event) {
		
		this.gamePanel.keyReleased(event);
		
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		
		this.gamePanel.keyPressed(event);
	}
}
