package GameStoreM.Frames;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;;
//Packeging stops here;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class User extends JFrame 
{	public String email;
	public User (String y)
	{	
		this.email=y;
		JPanel one = new JPanel ();
		JPanel two = new JPanel ();
		JPanel three = new JPanel ();
		JPanel four = new JPanel ();
		
		//Start of Congiruring First Panel;
		JButton add = new JButton ("Add");
		JButton update = new JButton ("Update");
		JButton delete = new JButton ("Delete");
		JTextField searchText = new JTextField (12);
		JButton search = new JButton ("Search");
		
		//Panel to Show Upper Options : Add, Delete,Update
		JPanel subOne = new JPanel ();
		subOne.setLayout(new FlowLayout());
		subOne.add(add);
		subOne.add(update);
		subOne.add(delete);
		
		//Panel to Show Search Field and Search below the Add,Update,Delete
		JPanel subOne2p = new JPanel();
		subOne2p.setLayout(new FlowLayout());
		subOne2p.add(searchText);
		subOne2p.add(search);
		
		
		//Panel to store Search and SearchField on Upper level of Currend BorderLayout's Center
		JPanel subOne2 = new JPanel();
		JLabel centerLabel = new JLabel ("Gaming is Passion !",JLabel.CENTER);
		subOne2.setLayout(new BorderLayout());		
		subOne2.add(subOne2p,BorderLayout.NORTH);
		subOne2.add(centerLabel,BorderLayout.CENTER);
		
		//Finally Full Layout Configuration of First Panel !!
		one.setLayout(new BorderLayout());
		one.add(subOne,BorderLayout.NORTH);
		one.add(subOne2,BorderLayout.CENTER);
		
		//Configuration of button Panle for Sub Panel for Buttons of Second Panel;
		JPanel option1 = new JPanel ();
		JButton select = new JButton ("Select");
		JButton save = new JButton("Save");
		option1.setLayout(new FlowLayout());
		option1.add(select);
		option1.add(save);
		
		//Configuration of Sub Panel for Buttons of Second Panel;	
		JPanel option = new JPanel ();
		option.setLayout(new BorderLayout());
		option.add(option1,BorderLayout.SOUTH);
		
		
		//Configuration of Second Panel;
		two.setLayout(new BorderLayout());
		two.add(option,BorderLayout.SOUTH);		
			
		//Fourth Panel Configuration ^+^
		JButton exit = new JButton ("Exit");
		JButton logout = new JButton ("Log out");
		JLabel middle = new JLabel ("Gaming is Fun !",JLabel.CENTER);
		JPanel subFour = new JPanel ();
		subFour.setLayout(new FlowLayout());
		subFour.add(exit);
		subFour.add(logout);
		four.setLayout(new BorderLayout());
		four.add(middle,BorderLayout.CENTER);
		four.add(subFour,BorderLayout.SOUTH);
		
		
		this.add(one);
		this.add(four);
		this.setLayout(new GridLayout(2,2));
		this.setSize(500,500);
		this.setVisible (true);
		
		add.addActionListener(e->
		{
			AddGames a = new AddGames (email);
			a.setVisible(true);
			setVisible(false);				
		});
		
		update.addActionListener(e->
		{
			UpdateGames up = new UpdateGames(email);
			up.setVisible(true);
			setVisible(false);
		});
		
		search.addActionListener (e->
		{				
					
			String u1=searchText.getText();
			String u = u1.toUpperCase();
			try
			{	
				int l=0;
				String query [] ={"SELECT game FROM  ACTION WHERE game = ? and mail = ?",
					 "SELECT game FROM  ADVENTURE WHERE game =? and mail = ?",
					 "SELECT game FROM  RACING WHERE game =? and mail = ?",
					 "SELECT game FROM  RPG WHERE game =? and mail =?",
					 "SELECT game FROM  STEALTH WHERE game =? and mail =?",
					"SELECT game FROM  STRATEGY WHERE game =? and mail =?"};

				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
				PreparedStatement ps;
				ResultSet rs;

				for (String q : query)
				{
					ps = con.prepareStatement(q);  
					ps.setString(1,u);
					ps.setString(2,email);
					rs=ps.executeQuery();
					if ( rs.next() == true)
					{
						JOptionPane.showMessageDialog(null, "Available");
						break;			
					}
				}
						
				con.close();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Some Error Occured");		
				}				
		});
		
		delete.addActionListener(e->
		{	
			DeGame de = new DeGame (email);
			de.setVisible(true);
			setVisible(false);
		});
		
		exit.addActionListener(e->
		{
				System.exit(0);
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
		
		logout.addActionListener(e->
		{
			new LogOrSign ();
			setVisible(false);				
		});
		
	public static void main (String [] args)
	{
	}	
}
