import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class simpleIPFinder extends JFrame implements ActionListener
{
	JLabel label;
	JTextField textField;
	JButton button;
	
	simpleIPFinder()
	{
		super("Simple IP Finder Tool");
		
		label = new JLabel("Enter Url Address:");
		label.setBounds(50,50,150,30);
		
		textField = new JTextField();
		textField.setBounds(50,80,200,30);
		
		button = new JButton("Find IP");
		button.setBounds(50,150,80,30);
		button.addActionListener(this);
		
		add(label);
		add(textField);
		add(button);
		
		setSize(300,300);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String url = textField.getText();
		InetAddress inetAdd;
		String ip;
		
		if(!url.equals(""))
		{
			if(url.contains("."))
			{
				if(url.contains(".onion"))
				{
					
					JOptionPane.showMessageDialog(this,"Don't mess with Dark Web Sites!!");
				}
				else
				{
					try
					{
						inetAdd = InetAddress.getByName(url);
						ip = inetAdd.getHostAddress();
						JOptionPane.showMessageDialog(this,ip);
					}
					catch(UnknownHostException ex)
					{
						JOptionPane.showMessageDialog(this,"Not Found");
					}
				}
					
			}
			else
			{
				JOptionPane.showMessageDialog(this,"This isn't a Search Engine");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Enter an Url Address to find!");
		}
	
	}
	
	public static void main(String [] args)
	{
		new simpleIPFinder();
	}	
}
