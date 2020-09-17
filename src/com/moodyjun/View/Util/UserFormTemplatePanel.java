package com.moodyjun.View.Util;

import com.moodyjun.View.Util.JNumberTextField;

import javax.swing.*;
import java.awt.*;

public class UserFormTemplatePanel extends JPanel {

    protected ButtonGroup userButtonGroup;
    protected ButtonGroup genderButtonGroup;
    protected JRadioButton adminRadioButton ;
    protected JRadioButton lecturerRadioButton ;
    protected JRadioButton studentRadioButton ;
    protected JRadioButton maleRadioButton ;
    protected JRadioButton femaleRadioButton ;
    protected JLabel formLabel;
    protected JLabel userNameLabel ;
    protected JLabel passwordLabel ;
    protected JLabel confirmPasswordLabel ;
    protected JLabel ageLabel;
    protected JLabel intakeCodeLabel ;
    protected JLabel emailLabel;
    protected JLabel phoneNumLabel;
    protected JLabel levelLabel;
    protected JTextField userNameField;
    protected JTextField emailField;
    protected JNumberTextField ageField;
    protected JNumberTextField phoneNumField;
    protected JComboBox<Integer> levelBox;
    protected JTextField intakeCodeField;
    protected JPasswordField passwordField ;
    protected JPasswordField confirmPasswordField;
    protected JButton actionButton;
    protected Box genderBox;
    protected Box userBox ;
    protected GridBagConstraints gbc;

    public UserFormTemplatePanel(){
        setLayout(new GridBagLayout());

        formLabel = new JLabel();
        formLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        userBox = Box.createHorizontalBox();
        userButtonGroup = new ButtonGroup();
        adminRadioButton = new JRadioButton("Admin");
        lecturerRadioButton = new JRadioButton("Lecturer");
        studentRadioButton = new JRadioButton("Student");
        studentRadioButton.setSelected(true);
        userButtonGroup.add(adminRadioButton);
        userButtonGroup.add(lecturerRadioButton);
        userButtonGroup.add(studentRadioButton);
        userBox.add(adminRadioButton);
        userBox.add(lecturerRadioButton);
        userBox.add(studentRadioButton);
        userBox.setBorder(BorderFactory.createTitledBorder("User Role"));
        userNameLabel=new JLabel("Username:");
        userNameField = new JTextField();
        passwordLabel=new JLabel("Password:");
        passwordField = new JPasswordField();
        confirmPasswordLabel=new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();
        ageLabel = new JLabel("Age:");
        ageField = new JNumberTextField();
        ageField.setText("0");
        emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        phoneNumLabel = new JLabel("Phone number:");
        phoneNumField = new JNumberTextField(true);
        levelLabel = new JLabel("Level:");
        Integer[] levels = {1,2,3};
        levelBox = new JComboBox<>(levels);
        intakeCodeLabel = new JLabel("Intake Code:");
        intakeCodeField = new JTextField();
        genderBox = Box.createHorizontalBox();
        genderButtonGroup = new ButtonGroup();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderButtonGroup.add(maleRadioButton);
        maleRadioButton.setSelected(true);
        genderButtonGroup.add(femaleRadioButton);
        genderBox.add(maleRadioButton);
        genderBox.add(femaleRadioButton);
        genderBox.setBorder(BorderFactory.createTitledBorder("Gender:"));
        actionButton = new JButton();

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,50,20,50);
        gbc.gridx = 1 ; gbc.gridy = 0 ;
        add(formLabel,gbc);
        gbc.gridx = 0 ; gbc.gridy = 2 ; gbc.gridwidth = 3 ; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userBox,gbc);
        gbc.gridx = 0 ; gbc.gridy = 3 ;gbc.gridwidth = 1 ;
        add(userNameLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 3 ; gbc.gridwidth = 2 ;
        add(userNameField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 4 ;gbc.gridwidth = 1 ;
        add(passwordLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 4 ; gbc.gridwidth = 2 ;
        add(passwordField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 5 ;gbc.gridwidth = 1 ;
        add(confirmPasswordLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 5 ; gbc.gridwidth = 2 ;
        add(confirmPasswordField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 6 ;gbc.gridwidth = 1 ;
        add(ageLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 6 ; gbc.gridwidth = 1 ;
        add(ageField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 7 ;gbc.gridwidth = 1 ;
        add(emailLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 7 ; gbc.gridwidth = 2 ;
        add(emailField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 8 ;gbc.gridwidth = 1 ;
        add(phoneNumLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 8 ; gbc.gridwidth = 2 ;
        add(phoneNumField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 9 ;gbc.gridwidth = 1 ;
        add(levelLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 9 ; gbc.gridwidth = 2 ;
        add(levelBox,gbc);
        gbc.gridx = 0 ; gbc.gridy = 10 ;gbc.gridwidth = 1 ;
        add(intakeCodeLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 10; gbc.gridwidth = 1 ;
        add(intakeCodeField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 11; gbc.gridwidth = 3 ;
        add(genderBox,gbc);
        gbc.gridx = 2 ; gbc.gridy = 12; gbc.gridwidth = 1 ;
        add(actionButton,gbc);
    }

    public JLabel getFormLabel() {
        return formLabel;
    }

    public Box getUserBox() {
        return userBox;
    }


    public JTextField getUserNameField() {
        return userNameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public JRadioButton getAdminRadioButton() {
        return adminRadioButton;
    }

    public JRadioButton getLecturerRadioButton() {
        return lecturerRadioButton;
    }

    public JRadioButton getStudentRadioButton() {
        return studentRadioButton;
    }

    public JComboBox<Integer> getLevelBox() {
        return levelBox;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    public void setLevelBox(JComboBox<Integer> levelBox) {
        this.levelBox = levelBox;
    }

    public JLabel getIntakeCodeLabel() {
        return intakeCodeLabel;
    }

    public JNumberTextField getAgeField() {
        return ageField;
    }

    public JRadioButton getMaleRadioButton() {
        return maleRadioButton;
    }

    public JRadioButton getFemaleRadioButton() {
        return femaleRadioButton;
    }

    public JTextField getIntakeCodeField() {
        return intakeCodeField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JNumberTextField getPhoneNumField() {
        return phoneNumField;
    }

    public JButton getActionButton() {
        return actionButton;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }
}
