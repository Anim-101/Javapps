package GameStoreM.Frames;
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

public final class CustomerInterface extends JFrame
{
	public CustomerInterface ()
	{
		JPanel panel = new JPanel ();
		panel.setLayout(new GridLayout (2,2,10,10));
		
		//one ->First Panel;
		JPanel one = new JPanel ();
		JButton game = new JButton ("Games");
		JButton review = new JButton ("Review");
		one.add(game);
		one.add(review);
		one.setLayout(new FlowLayout());
		
		//two ->Second Panel;
		JPanel two = new JPanel ();
		JTextField doSearch = new JTextField (7);
		JButton searching = new JButton("Search");
		two.add(doSearch);
		two.add(searching);
		two.setLayout(new FlowLayout());
		
		//three ->Thrid Panel;
		JPanel three = new JPanel ();
		JButton exit = new JButton ("EXIT");
		three.setLayout(new FlowLayout());
		three.add(exit);
		
		//four -> Fourth Panel;
		JPanel four = new JPanel ();
		JButton buy = new JButton ("Buy");
		four.add(buy);
		four.setLayout(new FlowLayout());
				
		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(four);
		
		this.add(panel);
		this.setSize(450,250);
		this.setVisible(true);
				
		game.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				Gener g =new Gener ();
				g.setVisible(true);
				setVisible(false);				
			}		
		});
		
		review.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				AddReview ad =new AddReview ();
				ad.setVisible(true);
				setVisible(false);
			}
		
		});
		
		buy.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				Buy bu =new Buy ();
				bu.setVisible(true);	
				setVisible(false);
			}
		
		});
		
		exit.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				dispose();
				Main lol = new Main ();
				lol.setVisible (true);
			
			}
		
		});
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) 
				{
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					Main lol = new Main ();
					lol.setVisible(true);
				}
			});
	
	    searching.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				String u1=doSearch.getText();
				String u = u1.toUpperCase();

				try
				{	
					int l=0;
					String query [] ={"SELECT game FROM  ACTION WHERE game =?",
						 "SELECT game FROM  ADVENTURE WHERE game =?",
						 "SELECT game FROM  RACING WHERE game =?",
						 "SELECT game FROM  RPG WHERE game =?",
						 "SELECT game FROM  STEALTH WHERE game =?",
						 "SELECT game FROM  STRATEGY WHERE game =?"};

					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
					PreparedStatement ps;
					ResultSet rs;

					for (String q : query)
					{
						ps = con.prepareStatement(q);                    
						ps.setString(1,u);	
						rs=ps.executeQuery();
						if(rs.next() == true)
							{
								JOptionPane.showMessageDialog(null, "Available");
								break;	
							}
							l++;
					}

					if (l >=6)
					{
						JOptionPane.showMessageDialog(null, "Not Available");								
					}
					
					con.close();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Some Error Occured");		
				}
			}
		});
	}
	
	public static void main (String [] args)
	{
	}
}
