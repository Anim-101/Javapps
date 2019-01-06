package GameStoreM.Frames;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;
//Packeging is Done here;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public final class AddReview extends JFrame
{
	public AddReview ()
	{
		
		JLabel header = new JLabel ("Give Review :",JLabel.CENTER);
		
				
		final JTextArea reviewText = new JTextArea ();
		JScrollPane scroll = new JScrollPane (reviewText);
		JButton done = new JButton ("Done");
	
		JPanel panel = new JPanel ();
		panel.setLayout(new BorderLayout ());
		panel.add(scroll,BorderLayout.CENTER);
		JPanel d = new JPanel ();
		d.setLayout(new FlowLayout());
		d.add(done);		
		panel.add(d,BorderLayout.SOUTH);
	
		
		
		done.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			
			{
				String rev = reviewText.getText();
				
				try
				{
				
				String query ="INSERT INTO REVIEW VALUES(?)";
				Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1,rev);
				ps.executeUpdate();
				con.close();
				setVisible (false);
				}
				
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Please Give Your Review Within 250 Words");
				}
				
				CustomerInterface lol = new CustomerInterface();	
				lol.setVisible(true);
			}
		});
		
		
		
		this.add(header);
		this.add(panel);
		this.setLayout(new GridLayout(3,1));
		this.setSize(400,400);
		this.setVisible(true);
		
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
	
	public static void main (String [] args)
	{
	}
	
}