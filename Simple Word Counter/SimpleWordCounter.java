import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleWordCounter extends JFrame implements ActionListener
{
	JLabel charLabel;
	JLabel wordLabel;
	JButton button;
	JButton padButton;
	JButton texButton;
	JTextArea textArea;

	SimpleWordCounter()
	{
		super("Simple Word Counter - Java");

		charLabel = new JLabel("Characters: ");
		charLabel.setBounds(50, 50, 100, 20);

		wordLabel = new JLabel("Words: ");
		wordLabel.setBounds(50, 80, 100, 20);

		textArea = new JTextArea();
		textArea.setFont(new Font("Serif", Font.ITALIC, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(50, 110, 300, 200);

		button = new JButton("Click");
		button.setBounds(50, 320, 80, 30);
		button.addActionListener(this);

		padButton = new JButton("Pad Color");
		padButton.setBounds(140, 320, 110, 30);
		padButton.addActionListener(this);

		texButton = new JButton("Text Color");
		texButton.setBounds(260, 320, 110, 30);
		texButton.addActionListener(this);

		add(charLabel);
		add(wordLabel);
		add(button);
		add(padButton);
		add(texButton);
		add(textArea);

		setSize(400,400);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == button)
		{
			String text = textArea.getText();
			if(text.length()== 0)
			{
				charLabel.setText("Characters: Null");
				wordLabel.setText("Words: Null");
			}
			else
			{
				charLabel.setText("Characters: "+text.length());
				String words [] = text.split("\\s");
				wordLabel.setText("Words: "+words.length);
			}
		}
		else if(e.getSource() == padButton)
		{
			Color color = JColorChooser.showDialog(this, "Choose Color", Color.BLACK);
			textArea.setBackground(color);
		}
		else if(e.getSource() == texButton )
		{
			Color color = JColorChooser.showDialog(this, "Choose Color", Color.BLACK);
			textArea.setForeground(color);
		}
	}

	public static void main (String [] args)
	{
		new SimpleWordCounter();
	}
}
