package GameStoreM.Modifiers;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;
//Packaging is done

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public final class DeGame extends JFrame implements ActionListener,ItemListener
{
	public String x,e;
	private JTextField nameText;
	private JComboBox genBox;
	private JButton ok,exit;
	
	public DeGame(String y)
	{	
		this.e=y;
		JLabel gen = new JLabel ("Gener :");
		gen.setBounds (30,30,50,30);
		
		String gener [] = {"Select","Action","Adventure","Racing","Rpg","Stealth","Strategy"};
		this.genBox = new JComboBox (gener);
		genBox.setBounds(100,30,65,30);
		genBox.addItemListener(this);
		
		JLabel name = new JLabel ("Name :");
		name.setBounds (30,70,50,30);
				
		this.nameText = new JTextField (10);
		nameText.setBounds (100,75,110,20);
		nameText.addActionListener(this);
		
		
		this.ok = new JButton ("Delete");
		ok.setBounds (100,150,75,20);
		ok.addActionListener(this);
		
		this.exit = new JButton ("Exit");
		exit.setBounds (180,150,65,20);
		exit.addActionListener(this);
		
		this.add(gen);
		this.add(genBox);
		this.add(name);
		this.add(nameText);
		this.add(ok);
		this.add(exit);
		this.setSize(300,220);
		this.setLayout(null);
		this.setVisible(true);
		
		exit.addActionListener(x->
		{
			User lol = new User (e);
			lol.setVisible(true);
		
		});
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent eos) 
				{
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					User lol = new User (e);
					lol.setVisible(true);
				}
			});	
		
					
	}
	
		public void itemStateChanged( ItemEvent event )
            {
				if (event.getSource()==genBox)
				{
					if ( event.getStateChange() == ItemEvent.SELECTED )
					{
						x = String.valueOf(genBox.getSelectedItem());
				    }
				}
            }
		public void actionPerformed(ActionEvent z)
			{
				if (z.getSource ()== ok)
				{	
					System.out.println(e);
					String gName= nameText.getText();
					String gameName =gName.toUpperCase();
			
					if ( x != "Select")
					{
						try
						{
							int r=0;
							String query = "DELETE FROM "+x+" WHERE game = '"+gameName+"' and mail = '"+e+"'";
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
							PreparedStatement ps = con.prepareStatement(query); 
							r=ps.executeUpdate(query);
			
							if (r!=0)
							{
								JOptionPane.showMessageDialog(null,"Successfull");
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Nothing Found");
							}
							con.close();
						}
						catch (Exception ex)
						{
							JOptionPane.showMessageDialog(null,"Some Error Occured");
						}	 
					}
				}
			}	
			
	
		public static void main (String [] args)
		{
		}
}
