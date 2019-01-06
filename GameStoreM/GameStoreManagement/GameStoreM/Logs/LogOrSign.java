package GameStoreM.Logs;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;
//Packaging is Done Here ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class LogOrSign extends JFrame 
{
	public LogOrSign ()
	{
		JButton log = new JButton ("Log In");
		log.setBounds (60,40,77,20);
		
		JLabel or = new JLabel ("Or");
		or.setBounds (88,70,40,20);
		
		JButton sign =  new JButton ("Sign Up");
		sign.setBounds (60,95,77,20);
		
		this.add(log);
		this.add(or);
		this.add(sign);
		this.setSize(200,200);
		this.setLayout(null);
		this.setVisible(true);
		
		log.addActionListener (e->
		{
			LogIn l = new LogIn ();
			this.setVisible (false);
			l.setVisible(true);
		});
		
		
		sign.addActionListener (e->
		{
			UserSignUp u = new UserSignUp ();
			this.setVisible (false);
			u.setVisible(true);
		});
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) 
				{
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					Main lol = new Main();
					lol.setVisible(true);
				}
			});
	}
	
	public static void main (String [] args)
	{
		
	}
}