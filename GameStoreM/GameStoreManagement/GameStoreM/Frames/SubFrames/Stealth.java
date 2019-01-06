package GameStoreM.Frames.SubFrames;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;
//Packaging Ends Here

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public final class Stealth extends JFrame 
{
	public Stealth ()
	{
		JTable actionTable = new JTable();
		
		//Fow Viewing Available games;
		Vector data = new Vector ();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
           		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM STEALTH";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				Vector row = new Vector ();
				row.add(rs.getString("game"));
				row.add(rs.getInt("copy"));
				row.add(rs.getInt("price"));
				
				data.add(row);
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this,"No Games Found");
		}

		Vector col = new Vector ();
		col.add("Stealth");
		col.add("Copies");
		col.add("Price per Copy");
		actionTable = new JTable(data,col);
		JScrollPane pnl = new JScrollPane (actionTable);

		this.getContentPane().add(pnl);
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		this.setSize(400,400);
		this.setVisible(true);
			
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) 
			{
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				Gener lol = new Gener ();
				lol.setVisible(true);
			}
		});	
	}
	
	public static void main (String [] args)
	{
		new Stealth();
	}
}
