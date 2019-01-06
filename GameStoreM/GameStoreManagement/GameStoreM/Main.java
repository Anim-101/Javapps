package GameStoreM;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;

//Packeging is done here !!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public final class Main extends JFrame
{
	private JButton gamers,reviews,storeUser;
	private JLabel welcome;
	public  Main ()
	{
		welcome = new JLabel ("WELCOME TO GAME STORE MANAGEMENT");
		welcome.setBounds (165,10,300,10);
		
		gamers= new JButton ("Gamers");
		gamers.setBounds(5,30,100,30);
		
		reviews = new JButton ("Reviews");
		reviews.setBounds (110,30,105,30);
		
		storeUser = new JButton ("Store User Login");
		storeUser.setBounds (400,30,180,30);
		
		this.add(welcome);	
		this.add(gamers);
		this.add(reviews);
		this.add(storeUser);
		this.setSize (600,600);
		this.setLayout(null);
		this.setVisible (true);
		
		gamers.addActionListener ( e->
		{
			CustomerInterface cus= new CustomerInterface ();
			cus.setVisible(true);
			this.setVisible(false);
		});
		
		reviews.addActionListener ( e->
		{
			ShowReview sho =new ShowReview();
			sho.setVisible(true);
			this.setVisible(false);
		});
		
		storeUser.addActionListener ( e->
		{
			LogOrSign ls =new LogOrSign ();
			ls.setVisible(true);
			this.setVisible(false);
		});
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		
	}
	
	public static void main (String [] args)
	{
		new Main ();
	}
	
}