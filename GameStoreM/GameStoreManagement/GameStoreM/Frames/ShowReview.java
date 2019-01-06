package GameStoreM.Frames;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;
//Packeging stops here;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public final class ShowReview extends JFrame
{
	public ShowReview ()
	{
		JTable actionTable = new JTable();
		//Fow Viewing Available games;
		Vector data = new Vector ();
		try
		
		{
			Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM REVIEW";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				Vector row = new Vector ();
				row.add(rs.getString("rev"));
				data.add(row);
			}
		}
		
		catch (Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this,"No Review Avaiable !!");
			}
			
			Vector col = new Vector ();
			col.add("Reviews are given by Gamers & System Users");
			actionTable = new JTable(data,col);
			JScrollPane pnl = new JScrollPane (actionTable);
			
			this.getContentPane().add(pnl);
			this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
			this.setSize(500,500);
			this.setVisible(true);
			
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) 
				{
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					Main lol = new Main ();
					lol.setVisible(true);
				}
			});
	}
	
	public static void main (String [] args)
	{
		
	}
}