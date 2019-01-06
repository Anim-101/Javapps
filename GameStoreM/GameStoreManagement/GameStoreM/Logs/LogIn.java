package GameStoreM.Logs;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.io.*;

public final class LogIn extends JFrame 
{
	JLabel mailLabel,passLabel;
	JButton log;
	JTextField mailText,passText;
	public LogIn ()
	{
		mailLabel = new JLabel ("E-Mail :");
		mailLabel.setBounds (30,50,90,25);
		
		mailText = new JTextField (15);
		mailText.setBounds (110,50,90,25);
		
		passLabel = new JLabel ("Password :");
		passLabel.setBounds(30,90,90,25);
		
		passText = new JTextField (15);
		passText.setBounds(110,90,90,25);
		
		log= new JButton("Log In");
		log.setBounds(130,150,68,23);
		
		this.add(mailLabel);
		this.add(mailText);
		this.add(passLabel);
		this.add(passText);
		this.add(log);
		this.setSize(250,250);
		this.setLayout (null);
		this.setVisible(true);
	
		log.addActionListener(e->
		{
			String email = mailText.getText();
			String password = passText.getText();
			
			try
			{				
				String query ="SELECT * FROM  STOREM WHERE mail = '"+email+"' and pass ='"+password+"'";
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");	
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery (query);
				
				if(rs.next()==true)
				{
					this.dispose ();
					User lol =new User (email);
					lol.setVisible(true);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"Wrong Email or PassWord");
				}
			}
			
			catch (Exception ex)
			{	ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Some Error Occured");
			}
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
