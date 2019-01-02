import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;

class FileExplore extends JPanel implements ActionListener
{
    JTextField textField;
    JTextArea textArea;
    JTree fileTree;
    JButton refresh;
    JTable table;
    JScrollPane scrollPane;
    JScrollPane scrollPaneTable;

    //String currentDirectory = null;

    final String [] columnHeads = {"File Name", "SIZE{in bytes}", "Read Only", "Hidden"};
    String [] [] data = {{"","","",""}};

    FileExplore(String path)
    {
        textField = new JTextField();
        textArea = new JTextArea(5,30);
        refresh = new JButton("Refresh");

        File temp = new File(path);
        DefaultMutableTreeNode top = createTree(temp);

        fileTree = new JTree(top);

        scrollPane = new JScrollPane(fileTree);

        final String [] columnHeads = {"File Name", "SIZE{in bytes}", "Read Only", "Hidden"};
        String [][] data = {{"","","",""}};

        table = new JTable(data,columnHeads);
        scrollPaneTable = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.WEST);
        add(scrollPaneTable, BorderLayout.CENTER);
        add(refresh,BorderLayout.SOUTH);

        fileTree.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent mouseEvent)
                {
                    mouseCliked(mouseEvent);
                }
            });

        textField.addActionListener(this);
        refresh.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev)
    {
        File temp = new File (textField.getText());
        DefaultMutableTreeNode newTop = createTree(temp);

        if(newTop != null)
        {
            fileTree = new JTree(newTop);
        }

        remove(scrollPane);
        scrollPane = new JScrollPane(fileTree);
        setVisible(false);
        add(scrollPane,BorderLayout.WEST);

        fileTree.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent mouseEvent)
            {
                mouseCliked(mouseEvent);
            }
        });

        setVisible(true);
    }

    DefaultMutableTreeNode createTree(File temp)
    {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(temp.getPath());

        if(!(temp.exists() && temp.isDirectory()))
        {
            return top;
        }

        filePathTree (top, temp.getPath());

        return top;
    }

    void filePathTree (DefaultMutableTreeNode root, String fileName)
    {
        File temp = new File(fileName);

        if(!((temp.exists()) && temp.isDirectory()))
        {
            return;
        }

        File [] fileList = temp.listFiles();

        for(int i=0; i<fileList.length; i++)
        {
            if(!fileList[i].isDirectory())
            {
                continue;
            }

            final DefaultMutableTreeNode tempFileNow = new DefaultMutableTreeNode(fileList[i].getName());
            root.add(tempFileNow);

            final String newFileName = new String(fileName+"\\"+fileList[i].getName());

            Thread thread = new Thread()
            {
                public void run()
                {
                    filePathTree(tempFileNow, newFileName);
                }
            };

            if(thread == null)
            {
                System.out.println("No Thread Allowed "+newFileName);
                return;
            }

            thread.start();
        }
    }

    void mouseCliked(MouseEvent mouseEvent)
    {
        TreePath treePath = fileTree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());

        if(treePath == null)
        {
            return;
        }

        String string = treePath.toString();
        string = string.replace("[", "");
        string = string.replace("]", "");
        string = string.replace(", ", "\\");

        textField.setText(string);
        showFiles(string);
    }

    void showFiles(String fileName)
    {
        File temp = new File(fileName);
        data = new String [][] {{"","","",""}};
        remove(scrollPaneTable); 
        table = new JTable(data,columnHeads);
        scrollPaneTable = new JScrollPane(table);
        setVisible(false);
        add(scrollPaneTable,BorderLayout.CENTER);
        setVisible(true);

        if(!temp.exists())
        {
            return;
        }

        if(!temp.isDirectory())
        {
            return;
        }

        File [] fileList = temp.listFiles();
        int fileCounter = 0;
        data = new String[fileList.length][4];

        for(int i=0; i<fileList.length; i++)
        {
            if(fileList[i].isDirectory())
            {
                continue;
            }

            data[fileCounter][0] = new String(fileList[i].getName());
            data[fileCounter][1] = new String(fileList[i].length()+"");
            data[fileCounter][2] = new String(!fileList[i].canWrite()+"");
            data[fileCounter][3] = new String(fileList[i].isHidden()+"");

            fileCounter++;
        }

        String dataTemp [][] = new String[fileCounter][4];

        for(int j=0; j<fileCounter; j++)
        {
            dataTemp[j] = data[j];
        }

        data = dataTemp;

        remove(scrollPaneTable);
        table = new JTable(data,columnHeads);
        scrollPaneTable = new JScrollPane(table);
        setVisible(false);
        add(scrollPaneTable,BorderLayout.CENTER);
        setVisible(true);
    }
}

class FileExplorer extends JFrame
{
    
    FileExplorer(String path)
    {
        super("Windows File Explorer");
        add(new FileExplore(path),"Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setVisible(true);
    }

    public static void main (String [] args)
    {
        new FileExplorer(".");
    }
}