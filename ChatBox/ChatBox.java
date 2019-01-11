import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChatBox
{
    //Esential Reference Variables
    ArrayList <String> arrayList = new ArrayList <String> ();
    ArrayList <String> userArrayList = new ArrayList <String> ();
    ServerSocket serverSocket;
    Socket socket;

    public final static int PORT = 10;
    public final static String UPDATE_USERS = "updateruserlist:";
    public final static String LOGOUT_MESSAGE = "@@logouttime@@:";

    public ChatBox()
    {
        try
        {
            serverSocket = new ServerSocket(PORT);
            
            System.out.println("Server Started By the Port: " + serverSocket);

            while(true)
            {
                socket = serverSocket.accept();

                Runnable runnable = new ChatBoxThread(socket,arrayList,userArrayList);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }
        catch (Exception ex)
        {
            System.out.println("Server Constructor " + ex);
        }
    }

    public static void main(String [] args)
    {
        new ChatBox();
    }
}

class ChatBoxThread implements Runnable
{
    Socket socket;
    ArrayList <String> arrayList;
    ArrayList <String> userArrayList;
    String userName;

    ChatBoxThread(Socket socket, ArrayList <String> arrayList, ArrayList <String> userArrayList)
    {
        this.socket = socket;
        this.arrayList = arrayList;
        this.userArrayList = userArrayList;

        try 
        {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            
            userName = dataInputStream.readUTF();
            arrayList.add(socket.toString());
            userArrayList.add(userName);
            tellEveryOne("***** " + userName + " Logged in at " + (new Date()) + " *****");
            sendNewUserList();
            
        } 
        catch (Exception e)
        {
            System.out.println("My Thread Constructor " + e);
        }
    }

    public void run()
    {
        String string;

        try
        {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while(true)
            {
                string = dataInputStream.readUTF();

                if(string.toLowerCase().equals(ChatBox.LOGOUT_MESSAGE))
                {
                    break;
                }

                tellEveryOne(userName + " Logged Out at " + (new Date()) + " ******");
                sendNewUserList();
                arrayList.remove(socket.toString());
                socket.close();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Chat Box Thread  Run " + ex);
        }
    }

    public void sendNewUserList()
    {
        tellEveryOne(ChatBox.UPDATE_USERS + userArrayList.toString());
    }

    public void tellEveryOne(String string)
    {
        Iterator iterator = arrayList.iterator();

        while(iterator.hasNext())
        {
            try
            {
                Socket tempSocket = (Socket) iterator.next();
                DataOutputStream dataOutPutStream = new DataOutputStream(tempSocket.getOutputStream());
                dataOutPutStream.writeUTF(string);
                dataOutPutStream.flush();
            }
            catch (Exception ex)
            {
                System.out.println("Tell Every One Function : " + ex);
            }
        }
    }
}

class ChatBoxClient implements ActionListener
{
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    JButton sendButton, logoutButton, loginButton, exiButton;
    JFrame chatboxWindow;
    JTextArea textBroadcast;
    JTextArea textMessage;
    JList <String> userList;
    
    public void display()
    {
        chatboxWindow = new JFrame();
        textBroadcast = new JTextArea(5, 30);
        textBroadcast.setEditable(false);
        textMessage = new JTextArea(5,30);
        userList = new JList <String> ();

        sendButton = new JButton("Send");
        loginButton = new JButton("Log In");
        logoutButton = new JButton("Log Out");
        exiButton = new JButton("Exit");

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(new JLabel("Broadcast Chat Message from All Online Users", JLabel.CENTER), "North");
        centerPanel.add(new JScrollPane(textBroadcast),"Center");

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout());
        middlePanel.add(new JScrollPane(textMessage));
        middlePanel.add(sendButton);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout());
        lowerPanel.add(loginButton);
        lowerPanel.add(logoutButton);
        lowerPanel.add(exiButton);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(new JLabel("Online Users", JLabel.CENTER), "East");
        rightPanel.add(new JScrollPane(userList), "South");

        chatboxWindow.add(rightPanel, "East");
        chatboxWindow.add(centerPanel, "Center");
        chatboxWindow.add(lowerPanel, "South");

        chatboxWindow.pack();
        chatboxWindow.setTitle("Login to ChatBox to Chat");
        chatboxWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        chatboxWindow.setVisible(true);

        sendButton.addActionListener(this);
        loginButton.addActionListener(this);
        logoutButton.addActionListener(this);
        exiButton.addActionListener(this);
        logoutButton.setEnabled(false);
        loginButton.setEnabled(true);

        textMessage.addFocusListener(new FocusAdapter()
        {
            public void focusGained(FocusEvent focus)
            {
                textMessage.selectAll();
            }
        });

        chatboxWindow.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                if(socket != null)
                {
                    JOptionPane.showMessageDialog(chatboxWindow, "U are Logged out right now !", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    logoutSession();
                }

                System.exit(0);
            }
        });
    }


    public void actionPerformed(ActionEvent actionEvent)
    {
        JButton tempButton = (JButton) actionEvent.getSource();

        if(tempButton == sendButton)
        {
            if(socket == null)
            {
                JOptionPane.showMessageDialog(chatboxWindow, "You are Not Logged In ! Login First !!");
                return;
            }

            try
            {
                dataOutputStream.writeUTF(textMessage.getText());
                textMessage.setText("");
            }
            catch(Exception exception)
            {
                textBroadcast.append("\nSend Button Click:" + exception);
            }
        }

        if(tempButton == loginButton)
        {
            String userName = JOptionPane.showInputDialog(chatboxWindow, "Enter your name or user a pen name: ");

            if(userName != null)
            {
                clientChat(userName);
            }
        }

        if(tempButton == logoutButton)
        {
            if(socket != null)
            {
                logoutSession();
            }
        }

        if(tempButton == exiButton)
        {
            if(socket != null)
            {
                JOptionPane.showMessageDialog(chatboxWindow, "You are Logged out right now", "Exit", JOptionPane.INFORMATION_MESSAGE);
                logoutSession();
            }
            System.exit(0);
        }
    }

    public void logoutSession()
    {
        if(socket == null)
        {
            return;
        }
        try
        {
            dataOutputStream.writeUTF(ChatBox.LOGOUT_MESSAGE);
            Thread.sleep(500);
            socket = null;
        }
        catch(Exception exception)
        {
            textBroadcast.append("\n Inside LogoutSession Method" + exception);
        }

        logoutButton.setEnabled(false);
        loginButton.setEnabled(true);
        chatboxWindow.setTitle("Loging for Chat");    
    }

    public void clientChat(String userName)
    {
        try
        {            
            socket = new Socket(InetAddress.getLocalHost(), ChatBox.PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            ChatBoxClientThread chatboxClientThread = new ChatBoxClientThread(dataInputStream, this);
            Thread newThread = new Thread(chatboxClientThread);
            newThread.start();
            dataOutputStream.writeUTF(userName);
            chatboxWindow.setTitle(userName + "Chat Window");
        }
        catch(Exception exception)
        {
            textBroadcast.append("\n In Client Constructor" + exception);
            logoutButton.setEnabled(true);
            loginButton.setEnabled(false);
        }
    }

    public ChatBoxClient()
    {
        display();
    }

    public static void main(String [] args)
    {
        new ChatBoxClient();
    }
}

class ChatBoxClientThread implements Runnable
{
    DataInputStream dataInputStream;
    ChatBoxClient chatBoxClient;

    ChatBoxClientThread(DataInputStream dataInputStream, ChatBoxClient chatBoxClient)
    {
        this.dataInputStream = dataInputStream;
        this.chatBoxClient = chatBoxClient;
    }

    public void run()
    {
        String string = "";

        while(true)
        {
            try
            {
                string = dataInputStream.readUTF();

                if(string.startsWith(ChatBox.UPDATE_USERS))
                {
                    updateruserlist(string);
                }
                else if(string.equals(ChatBox.LOGOUT_MESSAGE))
                {
                    break;
                }
                else
                {
                    chatBoxClient.textBroadcast.append("\n" + string);

                    int lineOffset = chatBoxClient.textBroadcast.getLineStartOffset(chatBoxClient.textBroadcast.getLineCount() - 1);

                    chatBoxClient.textBroadcast.setCaretPosition(lineOffset);
                }
            }
            catch(Exception exception)
            {
                chatBoxClient.textBroadcast.append("\n Client Thread Run: " + exception);
            }
        }
    }

    public void updateruserlist(String userList)
    {
        Vector <String> userListVector = new Vector <String> ();

        userList = userList.replace("[", ",");
        userList = userList.replace("]", ",");
        userList = userList.replace(ChatBox.UPDATE_USERS, "");
        
        StringTokenizer stringTokenizer = new StringTokenizer(userList, ",");

        while(stringTokenizer.hasMoreTokens())
        {
            String temp = stringTokenizer.nextToken();

            userListVector.add(temp);
        }

        chatBoxClient.userList.setListData(userListVector);
    }
}