package GameStoreM.Logs;
import GameStoreM.*;
import GameStoreM.Frames.*;
import GameStoreM.Logs.*;
import GameStoreM.Modifiers.*;
import GameStoreM.Frames.SubFrames.*;
import GameStoreM.Frames.BridgeFrames.*;

//Packeging is done here;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public final class UserSignUp extends JFrame 
{
	public UserSignUp ()
	{
		//Configuration of Form
		JPanel upper = new JPanel ();
		JLabel header = new JLabel("Give Information :",JLabel.CENTER);
		upper.setLayout(new BorderLayout());
		
		JPanel upper1 = new JPanel ();
		SpringLayout layout = new SpringLayout ();
		upper.add(upper1,BorderLayout.CENTER);
		
		
			
		//Constructing Form;
		JLabel name = new JLabel ("Name");
		JTextField nameText = new JTextField(12);
		
		
		JLabel storeName = new JLabel ("Store Name");
		JTextField storeNameText = new JTextField(12);
		
		JLabel email = new JLabel ("E Mail");
		JTextField emailText = new JTextField(12);
		
		JLabel pass = new JLabel ("PassWord");
		JTextField passText = new JTextField(12);
		
		//Constructing Spring Layout to visualate Form;
		
		
		upper1.add(name);
		upper1.add(nameText);
		upper1.add(storeName);
		upper1.add(storeNameText);
		upper1.add(email);
		upper1.add(emailText);
		upper1.add(pass);
		upper1.add(passText);
		upper1.setLayout(layout);
		
		//For Name and Textfield of Name;
		layout.putConstraint(SpringLayout.WEST, name, 10, SpringLayout.WEST, upper1);
		layout.putConstraint(SpringLayout.NORTH, name, 25, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.NORTH, nameText, 25, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.WEST, nameText, 54, SpringLayout.EAST, name);

		
		//For Store Name and It's TextField;
		layout.putConstraint(SpringLayout.WEST, storeName, 10, SpringLayout.WEST, upper1);
		layout.putConstraint(SpringLayout.NORTH, storeName, 45, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.NORTH, storeNameText, 45, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.WEST, storeNameText, 20, SpringLayout.EAST, storeName);
		
		//For Email and it's TextField;
		layout.putConstraint(SpringLayout.WEST, email, 10, SpringLayout.WEST, upper1);
		layout.putConstraint(SpringLayout.NORTH, email, 65, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.NORTH, emailText, 65, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.WEST, emailText, 54, SpringLayout.EAST, email);

		//For Password and it's TextField;
		layout.putConstraint(SpringLayout.WEST, pass, 10, SpringLayout.WEST, upper1);
		layout.putConstraint(SpringLayout.NORTH, pass, 85, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.NORTH, passText, 85, SpringLayout.NORTH, upper1);
		layout.putConstraint(SpringLayout.WEST, passText, 27, SpringLayout.EAST, pass);

		//Configuring Okay Button;
		JPanel but = new JPanel();
		JButton ok = new JButton("Okay");
		but.setLayout(new FlowLayout());
		but.add(ok);
		JPanel lower = new JPanel ();
		lower.setLayout(new BorderLayout());
		lower.add(but,BorderLayout.SOUTH);
		
		this.add(upper);
		this.add(lower);
		this.setSize(300,300);
		this.setLayout(new GridLayout(2,1));
		this.setVisible(true);
		
		ok.addActionListener (e->
		{
			String uName=nameText.getText();
			String uStore=storeNameText.getText();
			String uEmail=emailText.getText();
			String uPass =passText.getText();
			try
			{
				String query ="INSERT INTO storem VALUES(?,?,?,?)";
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GameStoreM", "root", "");
				
				PreparedStatement ps = con.prepareStatement(query);                    
				ps.setString(1,uName);
				ps.setString(2,uStore);
				ps.setString(3,uEmail);
				ps.setString(4,uPass);
				ps.executeUpdate();
				
				con.close();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Some Error Ocurred");
			}
		});
		
	addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e) 
				{
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					LogOrSign lol = new LogOrSign();
					lol.setVisible(true);
				}
			});
	}
	
	public static void main (String [] args)
	{
		
	}
}
