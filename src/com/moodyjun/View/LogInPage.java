package com.moodyjun.View;

import javax.swing.*;
import java.awt.*;

public class LogInPage extends JFrame{

    JPanel logInPanel ;
    JPanel backgroundPanel ;
    JLabel logInTitle ;
    JLabel userNameLabel ;
    JLabel passwordLabel ;
    JTextField userNameField;
    JPasswordField passwordField;
    JButton logInButton ;
    Font font ;

    public LogInPage(){

        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0,0,500,500);

        JLabel background ;
        ImageIcon img = new ImageIcon("LogInPageCover.jpg");
        background = new JLabel("",img,JLabel.CENTER);
        backgroundPanel.add(background);

        logInPanel = new JPanel();
        logInPanel.setLayout(null);
        logInPanel.setBounds(500,0,300,500);
        logInPanel.setBackground(Color.DARK_GRAY);

        logInTitle = new JLabel("Log In");
        logInTitle.setBounds(115,100,100,30);
        font = new Font(Font.SANS_SERIF,Font.BOLD,20);
        logInTitle.setFont(font);
        logInTitle.setForeground(Color.WHITE);

        userNameLabel = new JLabel("Username : ");
        userNameLabel.setBounds(40,150,100,20);
        font = new Font(Font.SANS_SERIF,Font.PLAIN,14);
        userNameLabel.setFont(font);
        userNameLabel.setForeground(Color.WHITE);

        userNameField = new JTextField();
        userNameField.setBounds(125,150,100,20);
        userNameField.setForeground(Color.WHITE);
        userNameField.setBackground(Color.DARK_GRAY);

        passwordLabel = new JLabel("Password : ");
        passwordLabel.setBounds(40,210,100,20);
        passwordLabel.setFont(font);
        passwordLabel.setForeground(Color.WHITE);

        passwordField = new JPasswordField();
        passwordField.setBounds(125,210,100,20);
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(Color.DARK_GRAY);

        logInButton = new JButton("Log In");
        logInButton.setBounds(115,280,70,20);
        logInButton.setForeground(Color.WHITE);
        logInButton.setBackground(Color.RED);

        logInPanel.add(logInTitle);
        logInPanel.add(userNameLabel);
        logInPanel.add(userNameField);
        logInPanel.add(passwordLabel);
        logInPanel.add(passwordField);
        logInPanel.add(logInButton);

        add(backgroundPanel);
        add(logInPanel);

        setLayout(null);
        setBounds(250,100,800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public void setUserNameField(JTextField userNameField) {
        this.userNameField = userNameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getLogInButton() {
        return logInButton;
    }

}

