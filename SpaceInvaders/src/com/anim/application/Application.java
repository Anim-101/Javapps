package com.anim.application;

import java.awt.EventQueue;

import com.anim.ui.GameMainFrame;

public class Application {
	
	public static void main(String [] args) {
		
		EventQueue.invokeLater( () -> {
			
			new GameMainFrame();
		});
	}
}
