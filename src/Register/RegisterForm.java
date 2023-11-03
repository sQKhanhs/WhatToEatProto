package Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class RegisterForm extends JFrame implements ActionListener {
    private Container container;
    private JLabel title;
    private JLabel userName;
    private JTextField userNameTextField;
    private JLabel password;
    private JPasswordField passwordField;
    private JCheckBox term;
    private JCheckBox restaurant;
    private JButton submit;
    private JButton reset;
    private JLabel notice;

    public RegisterForm() {
        setTitle("Registration Form");
        setBounds(300, 90, 500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Register");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(170, 30);
        container.add(title);

        userName = new JLabel("Username");
        userName.setFont(new Font("Arial", Font.PLAIN, 20));
        userName.setSize(100, 20);
        userName.setLocation(100, 100);
        container.add(userName);

        userNameTextField = new JTextField();
        userNameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        userNameTextField.setSize(190, 20);
        userNameTextField.setLocation(200, 100);
        container.add(userNameTextField);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 150);
        container.add(password);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setSize(150, 20);
        passwordField.setLocation(200, 150);
        container.add(passwordField);

        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 400);
        container.add(term);

        restaurant = new JCheckBox("Restaurant Owner");
        restaurant.setFont(new Font("Arial", Font.PLAIN, 15));
        restaurant.setSize(250, 20);
        restaurant.setLocation(150, 380);
        container.add(restaurant);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(150, 450);
        submit.addActionListener(this);
        container.add(submit);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        container.add(reset);

        notice = new JLabel("");
        notice.setFont(new Font("Arial", Font.PLAIN, 20));
        notice.setSize(500, 25);
        notice.setLocation(150, 500);
        container.add(notice);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submit) {
            boolean nameVerify = true;
            if (term.isSelected()) {
                File file;
                if(restaurant.isSelected()) {
                    file = new File("C:\\Users\\Public\\Documents\\Restaurant");
                    if(!file.exists()){
                        file.mkdirs();
                        file = new File("C:\\Users\\Public\\Documents\\Restaurant\\" +
                                userNameTextField.getText() + "\\" );
                        file.mkdirs();
                        file = new File("C:\\Users\\Public\\Documents\\Restaurant\\" +
                                userNameTextField.getText() + "\\" + userNameTextField.getText() + ".txt");
                    } else{
                        file = new File("C:\\Users\\Public\\Documents\\Restaurant\\" +
                                userNameTextField.getText() + "\\" );
                        file.mkdirs();
                        file = new File("C:\\Users\\Public\\Documents\\Restaurant\\" +
                                userNameTextField.getText() + "\\" + userNameTextField.getText() + ".txt");
                    }
                } else{
                    file = new File("C:\\Users\\Public\\Documents\\User");
                    if(!file.exists()){
                        file.mkdirs();
                        file = new File("C:\\Users\\Public\\Documents\\User\\" +
                                userNameTextField.getText() + ".txt" );
                    } else{
                        file = new File("C:\\Users\\Public\\Documents\\User\\" +
                                userNameTextField.getText() + ".txt" );
                    }
                }
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(file, true);
                    BufferedWriter bf = new BufferedWriter(fileWriter);
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (userNameTextField.getText().equalsIgnoreCase(line)) {
                            nameVerify = false;
                            break;
                        }
                    }
                    if(nameVerify) {
                        notice.setText("Registration Successfully..");
                        bf.write(userNameTextField.getText() + "\n");
                        bf.write(passwordField.getText() + "\n");
                        bf.close();
                    } else{
                        notice.setText("Username has been taken...");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                notice.setLocation(100, 500);
                notice.setText("Please accept the"
                        + " terms & conditions..");
            }
        } else if (event.getSource() == reset) {
            String blank = "";
            userNameTextField.setText(blank);
            passwordField.setText(blank);
            notice.setText(blank);
            term.setSelected(false);
        }
    }
}