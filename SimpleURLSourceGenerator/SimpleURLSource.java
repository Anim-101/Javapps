import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.net.*;
import javax.swing.*;
public class SimpleURLSource extends JFrame implements ActionListener
{
    JTextField textField;
    JTextArea textArea;
    JButton button;
    JLabel label;
    JScrollPane scroll;
    
    SimpleURLSource()
    {
        super("Simple URL's Source Info Gathering Application");

        label = new JLabel("Enter URL:");
        label.setBounds(50,50,70,30);

        textField = new JTextField();
        textField.setBounds(120,50,250,30);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(120, 150, 450, 450);

        scroll = new JScrollPane(textArea);
        scroll.setBounds(120,150,450,450);

        button = new JButton("Find");
        button.setBounds(120,100,120,30);
        button.addActionListener(this);

        add(label);
        add(textField);
        add(scroll);
        add(button);
        setSize(600,700);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        String urlString = textField.getText();

        if(urlString.equals("") || urlString.equals(null))
        {
            textField.setText(null);
            JOptionPane.showMessageDialog(new JFrame(), "Input an URL !!");
        }
        else
        {
            try
            {
                URL url = new URL(urlString);
                URLConnection urlConnection = url.openConnection();

                InputStream input = urlConnection.getInputStream();
                int i;
                StringBuilder stringBuilder = new StringBuilder();

                while((i=input.read()) != -1)
                {
                    stringBuilder.append((char)i);
                }

                String source = stringBuilder.toString();
                textArea.setText(source);
            }
            catch(Exception ex)
            {
                textField.setText(null);
                JOptionPane.showMessageDialog(new JFrame(), "Invalid URL !!");
            }
        }
    }

    public static void main(String [] args)
    {
        new SimpleURLSource();
    }
}
