package GameStoreM.Frames.BridgeFrames;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;
//Packaging ends here;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public final class Gener extends JFrame
{
	public Gener ()
	{
		JPanel gen = new JPanel ();
		JButton action = new JButton ("Action");
		JButton adventure = new JButton ("Adventure");
		JButton racing = new JButton ("Racing");
		JButton rpg = new JButton("Rpg");
		JButton strategy = new JButton ("Strategy");
		JButton stealth = new JButton ("Stealth");
		
		gen.setLayout(new GridLayout(2,3));
		gen.add(action);
		gen.add(adventure);
		gen.add(racing);
		gen.add(rpg);
		gen.add(strategy);
		gen.add(stealth);
		
		JPanel sub = new JPanel ();
		JButton back = new JButton ("Back");
		sub.setLayout(new FlowLayout());
		sub.add(back);
		
		this.add(gen);
		this.add(back);
		this.setSize(400,120);
		this.setLayout(new FlowLayout ());
		this.setVisible (true);
				
		action.addActionListener (e->
			{
				ActionG ac = new ActionG ();
				ac.setVisible(true);
				this.setVisible(false);
			});
			
		adventure.addActionListener (e->
			{	
				Adventure ad=new Adventure ();
				ad.setVisible(true);
				this.setVisible(false);
			});
		
		rpg.addActionListener (e->
			{
				Rpg rp =new Rpg ();
				rp.setVisible(true);
				this.setVisible(false);
			});

		racing.addActionListener (e->		
			{
				Racing rc =new Racing ();
				rc.setVisible(true);
				this.setVisible(false);
			});
			
		strategy.addActionListener (e->
			{
				Strategy st =new Strategy ();
				st.setVisible(true);
				this.setVisible(false);
			});
				
		stealth.addActionListener (e->
		
			{
				Stealth steal =new Stealth ();
				steal.setVisible(true);
				this.setVisible(false);
			});
			
				
		back.addActionListener (e->
		
			{
				this.dispose ();
				CustomerInterface lol = new CustomerInterface ();
				lol.setVisible(true);
				
			});
			
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) 
				{
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					CustomerInterface lol = new CustomerInterface ();
					lol.setVisible(true);
				}
			});
	}
	
	public static void main (String [] args)
	{
	}
}
