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

public class UpdateGames extends JFrame implements ActionListener,ItemListener
{
	public String x,em;
	private JTextField oldNameText,copText,priceText,nameText;
	private JComboBox genBox;
	private JButton ok,exit;
	
	public UpdateGames (String y)
	{	
		this.em=y;
		JLabel gen = new JLabel ("Gener :");
		gen.setBounds (30,30,50,30);
		
		String gener [] = {"Select","Action","Adventure","Racing","Rpg","Stealth","Strategy"};
		this.genBox = new JComboBox (gener);
		genBox.setBounds(100,30,65,30);
		genBox.addItemListener(this);
		
		JLabel oldName = new JLabel ("Name :");
		oldName.setBounds (30,70,50,30);
		JLabel old = new JLabel ("(Old)");
		old.setBounds (30,88,50,30);		
				
		this.oldNameText = new JTextField (10);
		oldNameText.setBounds (100,75,110,20);
		oldNameText.addActionListener(this);
		
		JLabel name = new JLabel ("Name :");
		name.setBounds (30,140,50,30);
		
		this.nameText = new JTextField (10);
		nameText.setBounds (100,145,110,20);
		nameText.addActionListener(this);
		
		JLabel cop = new JLabel ("Copy :");
		cop.setBounds (30,170,50,30);
		
		this.copText = new JTextField (10);
		copText.setBounds (100,175,110,20);
		copText.addActionListener(this);
		
		JLabel price= new JLabel ("Price :");
		price.setBounds (30,200,50,30);
		
		this.priceText = new JTextField (10);
		priceText.setBounds(100,205,110,20);
		priceText.addActionListener(this);
		
		
		this.ok = new JButton ("Update");
		ok.setBounds (185,300,75,20);
		ok.addActionListener(this);
		
		this.exit = new JButton ("Exit");
		exit.setBounds (270,300,65,20);
		exit.addActionListener(this);
		
		this.add(gen);
		this.add(genBox);
		this.add(oldName);
		this.add(old);
		this.add(oldNameText);
		this.add(name);
		this.add(nameText);
		this.add(cop);
		this.add(copText);
		this.add(price);
		this.add(priceText);
		//this.add(pricePerText);
		this.add(ok);
		this.add(exit);
		this.setSize(400,400);
		this.setLayout(null);
		this.setVisible(true);
		
		exit.addActionListener(x->
		{
			this.dispose();
			User lol = new User (em);
			lol.setVisible(true);
		});
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) 
				{
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					User lol = new User (em);
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
					String o = oldNameText.getText();
					String oldGameName= o.toUpperCase ();
					String g =nameText.getText();
					String gName = g.toUpperCase();
					Integer cp = Integer.parseInt(copText.getText());
					Integer pr = Integer.parseInt(priceText.getText());
					
					if ( x != "Select")
					{
						try
						{	int l=0;
							String query = "UPDATE "+x+" SET game ='"+gName+"',price ="+pr+",copy ="+cp+",mail= '"+em+"' WHERE game ='"+oldGameName+"' and mail = '"+em+"'";
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
						    PreparedStatement ps = con.prepareStatement(query); 
							l=ps.executeUpdate(query);
							if (l !=0)
							
							{
								JOptionPane.showMessageDialog(null,"Done");
							}
							if (l==0)
							{
								JOptionPane.showMessageDialog(null,"Nothing Found");
							}
							
							con.close();
						}
						catch (Exception ex)
						{
						}	 
					}
				}
	}
	
	public static void main (String [] args)
	{	
	}
}
