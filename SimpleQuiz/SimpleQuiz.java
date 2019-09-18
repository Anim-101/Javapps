import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleQuiz extends JFrame implements ActionListener {

    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5];
    JButton buttonOne, buttonTwo;
    ButtonGroup buttonGroup;

    int count = 0, current = 0, x = 1, y = 1, press = 0;

    int m[] = new int[10];

    SimpleQuiz(String string) {

        super(string);

        label = new JLabel();

        add(label);

        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 5; i++) {

            radioButton[i] = new JRadioButton();

            add(radioButton[i]);

            buttonGroup.add(radioButton[i]);
        }

        buttonOne = new JButton("Next");
        buttonTwo = new JButton("BookMark");

        buttonOne.addActionListener(this);
        buttonTwo.addActionListener(this);

        add(buttonOne);

        add(buttonTwo);

        set();

        label.setBounds(30, 40, 50, 20);

        radioButton[0].setBounds(50, 80, 100, 20);
        radioButton[1].setBounds(50, 110, 100, 20);
        radioButton[2].setBounds(50, 140, 100, 20);
        radioButton[3].setBounds(50, 170, 100, 20);

        buttonOne.setBounds(100, 240, 100, 30);
        buttonTwo.setBounds(270, 240, 100, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        setLocation(250, 100);

        setVisible(true);

        setSize(600, 350);
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == buttonOne) {

            if (check()) {

                count = count + 1;
            }

            current++;

            set();

            if (current == 9) {

                buttonOne.setEnabled(false);
                buttonTwo.setText("Result");
            }
        }

        if (event.getActionCommand().equals("Bookmark")) {

            JButton bookmark = new JButton("Bookmark" + x);

            bookmark.setBounds(480, (20 + 30) * x, 100, 30);

            add(bookmark);

            bookmark.addActionListener(this);

            m[x] = current;

            x++;
            
            current++;

            set();

            if (current == 9) {

                buttonTwo.setText("Result");
            }

            setVisible(false);

            setVisible(true);
        }

        for (int i = 0, y = 1; i < x; i++, y++) {

            if (event.getActionCommand().equals("Bookmark" + y)) {

                if (check()) {

                    count = count + 1;
                }

                press = current;

                current = m[y];

                set();

                ((JButton) event.getSource()).setEnabled(false);

                current = press;
            }

            if (event.getActionCommand().equals("Result")) {

                if (check()) {

                    count = count + 1;
                }

                current++;

                JOptionPane.showMessageDialog(this, "correct ans = " + count);

                System.exit(0);

            }
        }
    }

    void set() {

        radioButton[4].setSelected(true);

        if (current == 0) {

            label.setText("Question1: Which one among these is not a primitive datatype?");

            radioButton[0].setText("int");
            radioButton[1].setText("Float");
            radioButton[2].setText("boolean");
            radioButton[3].setText("char");
        }

        if (current == 1) {

            label.setText("Question2: Which class is available to all the class automatically?");

            radioButton[0].setText("Swing");
            radioButton[1].setText("Applet");
            radioButton[2].setText("Object");
            radioButton[3].setText("ActionEvent");
        }

        if (current == 2) {

            label.setText("Question3: Which package is directly available to our class without importing it?");

            radioButton[0].setText("swing");
            radioButton[1].setText("applet");
            radioButton[2].setText("net");
            radioButton[3].setText("lang");
        }

        if (current == 3) {

            label.setText("Question4: String class is defined in which package");

            radioButton[0].setText("lang");
            radioButton[1].setText("swing");
            radioButton[2].setText("applet");
            radioButton[3].setText("awt");
        }

        if (current == 4) {

            label.setText("Question5: Which institute is best for Java Coaching?");

            radioButton[0].setText("Utek");
            radioButton[1].setText("Aptech");
            radioButton[2].setText("SSS IT");
            radioButton[3].setText("jtek");
        }

        if (current == 5) {

            label.setText("Question6: Which one among these is not a keyword?");

            radioButton[0].setText("class");
            radioButton[1].setText("int");
            radioButton[2].setText("get");
            radioButton[3].setText("if");
        }

        if (current == 6) {

            label.setText("Question7: Which one among these is not a class?");

            radioButton[0].setText("Swing");
            radioButton[1].setText("ActionPerformed");
            radioButton[2].setText("ActionEvent");
            radioButton[3].setText("Button");
        }

        if (current == 7) {

            label.setText("Question8: Which one among these is not a function of Object class?");

            radioButton[0].setText("int");
            radioButton[1].setText("main");
            radioButton[2].setText("start");
            radioButton[3].setText("destroy");
        }

        if (current == 8) {

            label.setText("Question9: Which function is not present in Applet class?");

            radioButton[0].setText("init");
            radioButton[1].setText("main");
            radioButton[2].setText("start");
            radioButton[3].setText("destroy");
        }

        if (current == 9) {

            label.setText("Question10: Which one among these is not a valid component?");

            radioButton[0].setText("JButton");
            radioButton[1].setText("JList");
            radioButton[2].setText("JButtonGroup");
            radioButton[3].setText("JTextArea");
        }

        label.setBounds(30, 40, 500, 20);

        for (int i = 0, j = 0; i <= 90; i = i + 30, j++) {

            radioButton[j].setBounds(50, 80 + i, 200, 20);
        }
    }

    boolean check() {

        if (current == 0) {

            return radioButton[1].isSelected();
        }

        if (current == 1) {

            return radioButton[2].isSelected();
        }

        if (current == 2) {

            return radioButton[3].isSelected();
        }

        if (current == 3) {

            return radioButton[0].isSelected();
        }

        if (current == 4) {

            return radioButton[2].isSelected();
        }

        if (current == 5) {

            return radioButton[2].isSelected();
        }

        if (current == 6) {

            return radioButton[1].isSelected();
        }

        if (current == 7) {

            return radioButton[3].isSelected();
        }

        if (current == 8) {

            return radioButton[1].isSelected();
        }

        if (current == 9) {

            return radioButton[2].isSelected();
        }

        return false;
    }

    public static void main(String[] args) {

        new SimpleQuiz("Welcome To QuiZ");
    }
}
