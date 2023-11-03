package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginForm extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel title = new JLabel("Login");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");

    LoginForm() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setTitle("Login Form");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(140, 60);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        passwordField.setEchoChar('*');
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(title);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //Coding Part of LOGIN button
        if (event.getSource() == loginButton) {
            String userText;
            String passwordText;
            userText = userTextField.getText();
            passwordText = passwordField.getText();
            boolean userNameVerify = false;
            boolean passwordVerify = false;
            try {
                File file = new File("C:\\Users\\Public\\Documents\\register.txt");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while((line = bufferedReader.readLine()) != null){
                    if(userText.equalsIgnoreCase(line)){
                        userNameVerify = true;
                        break;
                    }
                }
                if (passwordText.equalsIgnoreCase(bufferedReader.readLine())){
                    passwordVerify = true;
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (userNameVerify && passwordVerify) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                System.out.println("Login Successful");
                System.out.println("Welcome " + userTextField.getText() + "（＾ｖ＾）");
                System.out.println("Enter access code: ");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        if (event.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (event.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}

