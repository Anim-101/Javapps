package GameStoreM.Modifiers;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;
//Packaging is done here
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public final class Buy extends JFrame implements ActionListener,ItemListener
{
	private String x;
	private JTextField copText,priceText,nameText;
	private JComboBox genBox;
	private JButton exit,ok;
	public Buy ()
	{
		JLabel gen = new JLabel ("Gener :");
		gen.setBounds (30,30,50,30);
		
		String gener [] = {"Select","Action","ADVENTURE","Racing","Rpg","Stealth","Strategy"};
		this.genBox = new JComboBox (gener);
		genBox.setBounds(100,30,65,30);
		genBox.addItemListener(this);
		
		JLabel name = new JLabel ("Name :");
		name.setBounds (30,70,50,30);
		
		this.nameText = new JTextField (10);
		nameText.setBounds (100,75,110,20);
		nameText.addActionListener(this);
		
		JLabel cop = new JLabel ("Copy :");
		cop.setBounds (30,110,50,30);
		
		this.copText = new JTextField (10);
		copText.setBounds (100,115,110,20);
		copText.addActionListener(this);
		
		JLabel price = new JLabel ("Price :");
		price.setBounds (30,150,50,30);
		
		this.priceText = new JTextField (10);
		priceText.setBounds (100,155,110,20);
		priceText.addActionListener(this);
		
		JLabel pricePerText = new JLabel ("(Per)");
		pricePerText.setBounds (30,175,50,30);
		
		this.ok = new JButton ("Done");
		ok.setBounds (175,250,65,20);
		ok.addActionListener(this);
		
		this.exit = new JButton ("Exit");
		exit.setBounds (245,250,65,20);
		exit.addActionListener(this);
		
		this.add(gen);
		this.add(genBox);
		this.add(name);
		this.add(nameText);
		this.add(cop);
		this.add(copText);
		this.add(price);
		this.add(priceText);
		this.add(pricePerText);
		this.add(ok);
		this.add(exit);
		this.setSize(330,330);
		this.setLayout(null);
		this.setVisible(true);
		
		exit.addActionListener(x->
		{
			this.dispose();
			CustomerInterface lol = new CustomerInterface();
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
	
	public void itemStateChanged( ItemEvent event )
            {
				if (event.getSource()==genBox)
				{
					if ( event.getStateChange() == ItemEvent.SELECTED )
					{
						this.x = String.valueOf(genBox.getSelectedItem());
				    }
				}
            }
	public void actionPerformed(ActionEvent e)
			{
				if (e.getSource ()== ok)
				{
					String g = nameText.getText();
					String gName = g.toUpperCase();
			
					if ( x != "Select")
					{
						
						try
						{
							String query = "SELECT * FROM "+x+" WHERE game =?";
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
							PreparedStatement ps = con.prepareStatement(query);  
							ps.setString(1,gName);
							ResultSet rs =ps.executeQuery();
							
							while (rs.next())
							{
								int copies = rs.getInt ("copy");
								int prices = rs.getInt ("price");
								Integer cp = Integer.parseInt(copText.getText());
								int c = copies-cp;
								if (c>=0)
								{	String que= "UPDATE "+x+" SET copy = "+c+" WHERE game = '"+gName+"'";
									ps = con.prepareStatement(que);  
									ps.executeUpdate();
									int money=prices*cp;
									String pr = String.valueOf(money);
									priceText.setText(pr);
									JOptionPane.showMessageDialog(null,"Complete your Bill at Cash Machine");
									break;
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Games out of Stock");
								}
							}
							con.close();
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null,"Transaction can't be done !");
						}	 
					}
				}
		
	}
	public static void main (String [] args)
	{
	}
}
