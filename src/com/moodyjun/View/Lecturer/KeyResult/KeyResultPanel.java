package com.moodyjun.View.Lecturer.KeyResult;

import com.moodyjun.View.Util.JResultBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KeyResultPanel extends JPanel{

    private JLabel titleLabel;
    private JLabel moduleLabel;
    private JLabel searchLabel;
    private JLabel testPctLabel;
    private JLabel examPctLabel;
    private JLabel assignmentPctLabel;
    private JTextArea testPct;
    private JTextArea examPct;
    private JTextArea assignmentPct;
    private JLabel notificationLabel;
    private JTextField searchField;
    private JComboBox moduleBox;
    private JButton resetButton;
    private JButton keyInButton;
    private List<JResultBox> resultBoxList;
    private GridBagConstraints gbc;

    public KeyResultPanel(){

        setLayout(new GridBagLayout());

        titleLabel = new JLabel("Key Result");
        titleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        moduleLabel = new JLabel("Module :");
        moduleBox = new JComboBox();
        testPctLabel = new JLabel("Test Percentage :");
        examPctLabel = new JLabel("Exam Percentage :");
        assignmentPctLabel = new JLabel("Assignment Percentage :");
        testPct = new JTextArea();
        testPct.setEditable(false);
        examPct = new JTextArea();
        examPct.setEditable(false);
        assignmentPct = new JTextArea();
        assignmentPct.setEditable(false);
        searchLabel = new JLabel("Student ID:");
        searchField = new JTextField();
        resetButton = new JButton("Reset");
        keyInButton = new JButton("Key In");
        resultBoxList = new ArrayList<>();
        notificationLabel = new JLabel();
        notificationLabel.setForeground(Color.RED);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,50,20,50);
        gbc.gridx = 1 ; gbc.gridy = 0 ;
        add(titleLabel,gbc);
        gbc.gridx = 0 ; gbc.gridy = 1 ;gbc.gridwidth = 1 ;
        add(moduleLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 1 ; gbc.gridwidth = 2 ;
        add(moduleBox,gbc);
        gbc.gridx = 0 ; gbc.gridy = 2 ;gbc.gridwidth = 1 ;
        add(testPctLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 2 ; gbc.gridwidth = 1 ;
        add(testPct,gbc);
        gbc.gridx = 0 ; gbc.gridy = 3 ;gbc.gridwidth = 1 ;
        add(examPctLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 3 ; gbc.gridwidth = 1 ;
        add(examPct,gbc);
        gbc.gridx = 0 ; gbc.gridy = 4 ;gbc.gridwidth = 1 ;
        add(assignmentPctLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 4 ; gbc.gridwidth = 1 ;
        add(assignmentPct,gbc);
        gbc.gridx = 0 ; gbc.gridy = 5 ;gbc.gridwidth = 1 ;
        add(searchLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 5 ; gbc.gridwidth = 2 ;
        add(searchField,gbc);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JTextArea getTestPct() {
        return testPct;
    }

    public JTextArea getExamPct() {
        return examPct;
    }

    public JTextArea getAssignmentPct() {
        return assignmentPct;
    }

    public JLabel getNotificationLabel() {
        return notificationLabel;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JComboBox getModuleBox() {
        return moduleBox;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getKeyInButton() {
        return keyInButton;
    }

    public List<JResultBox> getResultBoxList() {
        return resultBoxList;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }
}
