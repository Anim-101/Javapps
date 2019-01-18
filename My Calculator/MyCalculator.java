import java.awt.*;
import java.awt.event.*;

public class MyCalculator extends Frame
{
    public boolean setClear = true;
    double number, memoryValue;
    char operator;

    String digitButtonText [] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "."};
    String operatorButtonText [] = {"/", "sqrt", "*", "%", "-", "1/x", "+", "="};
    String memoryButtonText [] = {"MC", "MR", "MS", "M+"};
    String specialButtonText [] = {"Back", "C", "CE"};

    MyDigitButton digitButton [] = new MyDigitButton[digitButtonText.length];
    MyOperatorButton operatorButton [] = new MyOperatorButton[operatorButtonText.length];
    MyMemoryButton memoryButton [] = new MyMemoryButton[memoryButtonText.length];
    MySpecialButton specialButton [] = new MySpecialButton [specialButtonText.length];

    Label displayLabel = new Label("0", Label.RIGHT);
    Label memoryLabel = new Label(" ", Label.RIGHT);

    final int Frame_Width = 325, Frame_Height = 325;
    final int Height = 30, Width = 30, Horizontal_Space = 10, Vertical_Space = 10;
    final int Top_X = 30, Top_Y = 50;

    MyCalculator(String frameText)
    {
        super(frameText);

        int tempX = Top_X, tempY = Top_Y;
        displayLabel.setBounds(tempX, tempY, 240, Height);
        displayLabel.setBackground(Color.BLUE);
        displayLabel.setForeground(Color.WHITE);
        add(displayLabel);

        memoryLabel.setBounds(Top_X, Top_Y + Height + Vertical_Space, Width, Height);
        add(memoryLabel);

        //Setting Co-Ordinates for Memory Buttons
        tempX = Top_X;
        tempY = Top_Y + 2 * (Height + Vertical_Space);
        
        for(int i = 0; i < memoryButton.length; i++)
        {
            memoryButton[i] = new MyMemoryButton(tempX, tempY, Width, Height, memoryButtonText[i], this);
            memoryButton[i].setForeground(Color.RED);
            tempY += Height + Vertical_Space;
        }

        //Setting Co-ordinates for Special Buttons
        tempX = Top_X + 1 * (Width + Horizontal_Space);
        tempY = Top_Y + 1 * (Height + Vertical_Space);

        for(int i = 0; i < specialButton.length; i++)
        {
            specialButton[i] = new MySpecialButton(tempX, tempY, Width*2, Height, specialButtonText[i], this);
            specialButton[i].setForeground(Color.RED);
            tempX = tempX +2 * Width + Horizontal_Space;
        }

        //Setting Co-ordinates for Digit Buttons
        int digitX = Top_X + Width + Horizontal_Space;
        int digitY = Top_Y + 2 * (Height + Vertical_Space);
        tempX = digitX;
        tempY = digitY;

        for(int i=0; i < digitButton.length ; i++)
        {
            digitButton[i] = new MyDigitButton(tempX, tempY, WIDTH, Height, digitButtonText[i], this);
            digitButton[i].setForeground(Color.BLUE);
            tempX += Width + Horizontal_Space;

            if((i + 1) % 3 == 0)
            {
                tempX = digitX;
                tempY += Height + Vertical_Space;
            }
        }

        //Seting Co-ordinates for Operator Buttons
        int operatorX = digitX + 2 * (Width + Horizontal_Space) + Horizontal_Space;
        int operatorY = digitY;
        tempX = operatorX;
        tempY = operatorY;

        for(int i = 0; i < operatorButton.length; i++)
        {
            tempX += Width + Horizontal_Space;
            operatorButton[i] = new MyOperatorButton(tempX, tempY, Width, Height, operatorButtonText[i], this);
            operatorButton[i].setForeground(Color.RED);

            if( (i+1) % 2 == 0)
            {
                tempX = operatorX;
                tempY +=  Height + Vertical_Space;
            }
        }

        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent event)
            {
                System.exit(0);
            }
        });

        setLayout(null);
        setSize(Frame_Width, Frame_Height);
        setVisible(true);
    }

    static String getFormattetText(double temp)
    {
        String resetText = "" + temp;

        if(resetText.lastIndexOf(".0") > 0)
        {
            resetText = resetText.substring(0, resetText.length() - 2);
            return resetText;
        }

        return resetText;
    }

    public static void main(String [] args)
    {
        new MyCalculator("Simple Calculator - MyCalculator");
    }
}

class MyDigitButton extends Button implements ActionListener
{
    MyCalculator myCalculator;

    MyDigitButton(int x, int y, int width, int height, String cap, MyCalculator calc)
    {
        super(cap);
        setBounds(x, y, width, height);
        this.myCalculator = calc;
        this.myCalculator.add(this);
        addActionListener(this);
    }

    static boolean isInString(String string, char character)
    {
        for(int i = 0; i < string.length(); i++)
        {
            if(string.charAt(i) == character)
            {
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent event)
    {
        String tempText = ((MyDigitButton) event.getSource()).getLabel();

        if(tempText.equals("."))
        {
            if(myCalculator.setClear)
            {
                myCalculator.displayLabel.setText("0.");
                myCalculator.setClear = false;
            }
            else if(! isInString(myCalculator.displayLabel.getText(), '.'))
            {
                myCalculator.displayLabel.setText(myCalculator.displayLabel.getText() + ".");
            }
            return;
        }

        int index = 0;

        try
        {
            index = Integer.parseInt(tempText);
        }
        catch(NumberFormatException exception)
        {
            return;
        }

        if(index == 0 && myCalculator.displayLabel.getText().equals("0"))
        {
            return;
        }

        if(myCalculator.setClear)
        {
            myCalculator.displayLabel.setText("" + index);
            myCalculator.setClear = false;
        }
        else
        {
            myCalculator.displayLabel.setText(myCalculator.displayLabel.getText() + index);
        }
    }
}

class MyOperatorButton extends Button implements ActionListener
{
    MyCalculator myCalculator;

    MyOperatorButton(int x, int y, int width, int height, String cap, MyCalculator calc)
    {
        super(cap);
        setBounds(x, y, width, height);
        this.myCalculator = calc;
        this.myCalculator.add(this);
        addActionListener(this);
    }

    public void actionPerformed (ActionEvent event)
    {
        String operatorText  = ((MyOperatorButton) event.getSource()).getLabel();

        myCalculator.setClear = true;

        double temp = Double.parseDouble(myCalculator.displayLabel.getText());

        if(operatorText.equals("1/x"))
        {
            try
            {
                double tempd = 1 / (double) temp;

                myCalculator.displayLabel.setText(MyCalculator.getFormattetText(tempd));
            }
            catch(ArithmeticException exception)
            {
                myCalculator.displayLabel.setText("Divide By 0.");
            }
            return;
        }

        if(operatorText.equals("sqrt"))
        {
            try
            {
                double tempd = Math.sqrt(temp);

                myCalculator.displayLabel.setText(MyCalculator.getFormattetText(tempd));
            }
            catch(ArithmeticException exception)
            {
                myCalculator.displayLabel.setText("Divide by 0.");
            }
            return;
        }

        if(! operatorText.equals("="))
        {
            myCalculator.number = temp;
            myCalculator.operator = operatorText.charAt(0);
            return;
        }

        switch(myCalculator.operator)
        {
            case '+':
                temp += myCalculator.number;
                break;
            
            case '-':
                temp -= myCalculator.number;
                break;

            case '*':
                temp *= myCalculator.number;
                break;
            
            case '%':
                try
                {
                    temp = myCalculator.number % temp;
                }
                catch(ArithmeticException exception)
                {
                    myCalculator.displayLabel.setText("Divide by 0.");
                    return;
                }
                break;

            case '/':
                try
                {
                    temp = myCalculator.number / temp;
                }
                catch(ArithmeticException exception)
                {
                    myCalculator.displayLabel.setText("Divide by 0.");
                    return; 
                }
                break;
        }

        myCalculator.displayLabel.setText(MyCalculator.getFormattetText(temp));
    }
}

class MyMemoryButton extends Button implements ActionListener
{
    MyCalculator myCalculator;

    MyMemoryButton(int x, int y, int width, int height, String cap, MyCalculator calc)
    {
        super(cap);
        setBounds(x, y, width, height);
        this.myCalculator = calc;
        this.myCalculator.add(this);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event)
    {
        char memOperation = ((MyMemoryButton) event.getSource()).getLabel().charAt(1);

        myCalculator.setClear = true;
        double temp = Double.parseDouble(myCalculator.displayLabel.getText());

        switch(memOperation)
        {
            case 'C':
                myCalculator.memoryLabel.setText(" ");
                myCalculator.memoryValue = 0.0;
                break;
            
            case 'R':
                myCalculator.displayLabel.setText(MyCalculator.getFormattetText(myCalculator.memoryValue));
                break;

            case 'S':
                myCalculator.memoryValue=0.0;

            case '+':
                myCalculator.memoryValue += Double.parseDouble(myCalculator.displayLabel.getText());
                if(myCalculator.displayLabel.getText().equals("0") ||myCalculator.displayLabel.getText().equals("0.0"))
                {
                    myCalculator.memoryLabel.setText(" ");
                }
                else
                {
                    myCalculator.memoryLabel.setText("M");
                }
                break;
        }
    }
}

class MySpecialButton extends Button implements ActionListener
{
    MyCalculator myCalculator;

    MySpecialButton(int x, int y, int width, int height, String cap, MyCalculator calc)
    {
        super(cap);
        setBounds(x, y, width, height);
        this.myCalculator = calc;
        this.myCalculator.add(this);
        addActionListener(this);
    }

    static String backSpace(String string)
    {
        String res = "";
        
        for(int i = 0; i < res.length() - 1; i++)
        {
            res += string.charAt(i);
        }
        return res;
    }

    public void actionPerformed(ActionEvent event)
    {
        String operatorText = ((MySpecialButton) event.getSource()).getLabel();

        if(operatorText.equals("BNack"))
        {
            String tempText = backSpace(myCalculator.displayLabel.getText());

            if(tempText.equals(""))
            {
                myCalculator.displayLabel.setText(" ");
            }
            else
            {
                myCalculator.displayLabel.setText(tempText);
            }
            return;
        }

        if(operatorText.equals("C"))
        {
            myCalculator.number = 0.0;
            myCalculator.operator = ' ';
            myCalculator.memoryValue = 0.0;
            myCalculator.memoryLabel.setText("C");
        }

        myCalculator.displayLabel.setText("0");
        myCalculator.setClear = true;
    }
}