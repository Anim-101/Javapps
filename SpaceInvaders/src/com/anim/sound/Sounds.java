package com.anim.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	
	private Clip clip;
	
	public Sounds() {
		
	}
	
	public void Laser() {
		
		try {
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Sounds/laser.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioInputStream);
			
			clip.start();
			
		} catch(Exception exception) {
			
			exception.printStackTrace();
		}
	}
	
	public void Explosion() {
		
		try {
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Sounds/explosion.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioInputStream);
			
			clip.start();
			
		} catch (Exception exception) {
			
			exception.printStackTrace();
		}
	}
}
