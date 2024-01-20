import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.Scanner;


public class bankSystem implements ActionListener {

    public static String firstName = "John";
    public static String lastName = "Doe";
    public static int pin = 1234;
    public static double balance = 420.69;
    bankAccount acc1 = new cheqAccount(firstName,lastName,pin,balance);
    bankAccount acc2 = new savingAccount(firstName, lastName,pin, 1209);
    public boolean accecptPin = true;
    public boolean accountSelect = false;
    public boolean choice = false;




    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton[] numButtons = new JButton[10];
    JButton enterButton, delButton;
    JTextField textField;
    JTextArea textArea;

    public bankSystem(){

        frame = new JFrame("bankSystem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Exo 2", Font.BOLD, 30));
        textField.setBounds(50, 45, 250, 50);
        textField.setEditable(false);

        textArea = new JTextArea("Enter your pin");
        textArea.setFont(new Font("Exo 2", Font.BOLD, 20));
        textArea.setBounds(350, 45, 250, 400);
        textArea.setBackground(Color.lightGray);
        textArea.setEditable(false);

        panel.setBounds(50, 150, 250, 250);
        panel.setLayout(new GridLayout(4,3,10,10));

        for(int i=0;i<10;i++){
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(new Font("Exo 2", Font.BOLD, 30));
            numButtons[i].setFocusable(false);
        }

        enterButton = new JButton("Etr");
        enterButton.addActionListener(this);
//        enterButton.addKeyListener(this);
        enterButton.setFont(new Font("Exo 2", Font.BOLD, 30));
        enterButton.setFocusable(false);

        delButton = new JButton("<-");
        delButton.addActionListener(this);
        delButton.setFont(new Font("Exo 2", Font.BOLD, 30));
        delButton.setFocusable(false);


        for(int i=1;i<10;i++){
            panel.add(numButtons[i]);
        }

        panel.add(enterButton);
        panel.add(numButtons[0]);
        panel.add(delButton);

        frame.add(textArea);
        frame.add(textField);
        frame.add(panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i<numButtons.length;i++){
            if (e.getSource() == numButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource()==delButton){
            String str = textField.getText();
            textField.setText("");
            for(int i=0;i<str.length()-1;i++){
                textField.setText(textField.getText()+str.charAt(i));
            }
        }

        if (accecptPin){
            if(e.getSource()==enterButton){
                if(textField.getText().equals(String.valueOf(pin))){
                    accecptPin = false;
                    textField.setText("");
                    textArea.setText("Select an Account \n1. Chequing \n2. Saving");
                    accountSelect = true;
                }
                else{
                    textField.setText("");
                    textArea.setText("Incorrect");
                }
            }
        }

        if (accountSelect){
            if (e.getSource()==enterButton) {
                if (e.getSource() == enterButton && textField.getText().equals("1")) {
                    textArea.setText("Chequing Account \nMake a choice \n1. Deposit \n2. Withdraw \n3. Balance \n4. Transfer");
                    accountSelect = false;
                    choice = true;
                } else if (e.getSource() == enterButton && textField.getText().equals("2")) {
                    textArea.setText("Saving Account \nMake a choice \n1. Deposit \n2. Withdraw \n3. Balance \n4. Transfer");
                } else {
                    textArea.setText("Not a choice \nSelect an Account \n1. Chequing \n2. Saving");
                }
            }
        }

        if (choice){
            if (e.getSource() == enterButton && Integer.parseInt(textField.getText()) == 1){
                choice = false;
                textArea.setText("Deposit your amount");
                acc1.deposit(Double.valueOf(textField.getText()));
            }
            else if (e.getSource() == enterButton && Integer.parseInt(textField.getText()) == 2){
                choice = false;
                textArea.setText("Withdraw your amount");
                acc1.withdraw(Double.valueOf(textField.getText()));
            }
            else if (e.getSource() == enterButton && Integer.parseInt(textField.getText()) == 3){
                choice = false;
                textArea.setText("Your Balance is: \n" + acc1.getBalance());
            }
            else if (e.getSource() == enterButton && Integer.parseInt(textField.getText()) == 4){
                choice = false;
                textArea.setText("Transfer your amount");
                acc1.transfer(Double.valueOf(textField.getText()), acc2);
            }
            else{
                textArea.setText("Not a choice \nSaving Account \nMake a choice \n1. Deposit \n2. Withdraw \n3. Balance \n4. Transfer");
            }
        }

        


    }

    public static void main(String[] args) {

        bankSystem a = new bankSystem();




    }
}
