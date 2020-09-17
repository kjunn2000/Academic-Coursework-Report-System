package com.moodyjun.View.Util;

import javax.swing.*;
import java.awt.*;

public class JResultBox extends JPanel {
    private JLabel studentIDLabel;
    private JTextField studentIDField;
    private JLabel studNameLabel;
    private JTextField studNameField;
    private JLabel testMarkLabel;
    private JNumberTextField testMarkField;
    private JLabel examMarkLabel;
    private JNumberTextField examMarkField;
    private JLabel assignmentMarkLabel;
    private JNumberTextField assignmentMarkField;
    private JLabel commentLabel;
    private JTextField commentField;


    public JResultBox(){
        setLayout(new GridBagLayout());
        studentIDLabel = new JLabel("Student ID:");
        studNameLabel = new JLabel("Student Name:");
        testMarkLabel = new JLabel("Test Mark:");
        examMarkLabel = new JLabel("Exam Mark:");
        assignmentMarkLabel = new JLabel("Assignment Mark:");
        studentIDField = new JTextField();
        studentIDField.setEditable(false);
        studNameField = new JTextField();
        studNameField.setEditable(false);
        testMarkField = new JNumberTextField();
        examMarkField = new JNumberTextField();
        assignmentMarkField = new JNumberTextField();
        commentLabel = new JLabel("Comment:");
        commentField = new JTextField();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,50,5,50);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0 ; gbc.gridy = 0 ;gbc.gridwidth = 1 ;
        add(studentIDLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 0 ; gbc.gridwidth = 2 ;
        add(studentIDField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 1 ;gbc.gridwidth = 1 ;
        add(studNameLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 1 ; gbc.gridwidth = 2 ;
        add(studNameField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 2 ;gbc.gridwidth = 1 ;
        add(testMarkLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 2 ; gbc.gridwidth = 2 ;
        add(testMarkField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 3 ;gbc.gridwidth = 1 ;
        add(examMarkLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 3 ; gbc.gridwidth = 2 ;
        add(examMarkField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 4 ;gbc.gridwidth = 1 ;
        add(assignmentMarkLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 4 ; gbc.gridwidth = 2 ;
        add(assignmentMarkField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 5 ;gbc.gridwidth = 1 ;
        add(commentLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 5 ; gbc.gridwidth = 4 ;
        add(commentField,gbc);
    }

    public JTextField getStudentIDField() {
        return studentIDField;
    }

    public JTextField getStudNameField() {
        return studNameField;
    }

    public JNumberTextField getTestMarkField() {
        return testMarkField;
    }

    public JNumberTextField getExamMarkField() {
        return examMarkField;
    }

    public JNumberTextField getAssignmentMarkField() {
        return assignmentMarkField;
    }

    public JTextField getCommentField() {
        return commentField;
    }
}
