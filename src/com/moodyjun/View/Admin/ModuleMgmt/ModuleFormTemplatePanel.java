package com.moodyjun.View.Admin.ModuleMgmt;

import com.moodyjun.View.Util.JClassPicker;
import com.moodyjun.View.Util.JNumberTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ModuleFormTemplatePanel extends JPanel {

    protected  JLabel formLabel;
    protected  JLabel moduleIDLabel;
    protected  JLabel moduleNameLabel;
    protected  JLabel intakeCodesLabel;
    protected  JLabel numOfStudLabel;
    protected  JLabel testMarkPctLabel;
    protected  JLabel examMarkPctLabel;
    protected  JLabel assignmentMarkPctLabel;
    protected  JLabel classLabel;
    protected JLabel levelLabel;
    protected JComboBox<Integer> levelBox;
    protected  JComboBox<Integer> numOfStudBox;
    protected  JTextField moduleIDField;
    protected  JTextField moduleNameField;
    protected  List<JClassPicker> classPickerList;
    protected  JTextField intakesCodeField;
    protected  JNumberTextField testMarkPctField;
    protected  JNumberTextField examMarkPctField;
    protected  JNumberTextField assignmentMarkPctField;
    protected  JButton addClassButton;
    protected  JButton actionButton;
    protected  GridBagConstraints gbc;

    public ModuleFormTemplatePanel(){

        setLayout(new GridBagLayout());

        formLabel = new JLabel();
        formLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        moduleIDLabel =new JLabel("Module ID:");
        moduleIDField= new JTextField();
        moduleNameLabel =new JLabel("Module Name:");
        moduleNameField = new JTextField();
        levelLabel = new JLabel("Level:");
        Integer[] levels = {1,2,3};
        levelBox = new JComboBox<>(levels);
        intakeCodesLabel = new JLabel("Intake Code(s):");
        intakesCodeField = new JTextField();
        classLabel = new JLabel("Classes:");
        classPickerList = new ArrayList<>();
        classPickerList.add(new JClassPicker());
        classPickerList.get(0).getClassTypeBox().setSelectedIndex(0);
        classPickerList.get(0).getClassTypeBox().setEnabled(false);
        classPickerList.get(0).getDeleteClassButton().setVisible(false);
        classPickerList.add(new JClassPicker());
        classPickerList.get(1).getDeleteClassButton().setVisible(false);
        classPickerList.add(new JClassPicker());
        classPickerList.add(new JClassPicker());
        classPickerList.add(new JClassPicker());
        classPickerList.get(2).setVisible(false);
        classPickerList.get(3).setVisible(false);
        classPickerList.get(4).setVisible(false);
        numOfStudLabel = new JLabel("Number of Student:");
        addClassButton = new JButton("Add Class");
        Integer[] numOfStudArr = {30,40,50,60,70,80,90,100,110,120};
        numOfStudBox = new JComboBox<Integer>(numOfStudArr);
        numOfStudBox.setSelectedIndex(0);
        testMarkPctLabel = new JLabel("Test Mark Pct:");
        testMarkPctField = new JNumberTextField();
        testMarkPctField.setText("0");
        examMarkPctLabel = new JLabel("Exam Mark Pct:");
        examMarkPctField = new JNumberTextField();
        examMarkPctField.setText("0");
        assignmentMarkPctLabel = new JLabel("Assignment Mark Pct");
        assignmentMarkPctField = new JNumberTextField();
        assignmentMarkPctField.setText("0");
        actionButton = new JButton();

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,50,20,50);
        gbc.gridx = 1 ; gbc.gridy = 0 ;
        add(formLabel,gbc);
        gbc.gridx = 0 ; gbc.gridy = 1 ;gbc.gridwidth = 1 ;
        add(moduleIDLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 1 ; gbc.gridwidth = 1 ;
        add(moduleIDField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 2 ;gbc.gridwidth = 1 ;
        add(moduleNameLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 2 ; gbc.gridwidth = 2 ;
        add(moduleNameField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 3 ;gbc.gridwidth = 1 ;
        add(levelLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 3 ; gbc.gridwidth = 2 ;
        add(levelBox,gbc);
        gbc.gridx = 0 ; gbc.gridy = 4 ;gbc.gridwidth = 1 ;
        add(intakeCodesLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 4 ; gbc.gridwidth = 2 ;
        add(intakesCodeField,gbc);

        gbc.gridx = 0 ; gbc.gridy = 5 ;gbc.gridwidth = 1 ;
        add(classLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 5 ;gbc.gridwidth = 2 ;
        add(classPickerList.get(0),gbc);
        gbc.gridx = 1 ; gbc.gridy = 11 ;gbc.gridwidth = 2 ;
        add(classPickerList.get(1),gbc);

        gbc.gridx = 1 ; gbc.gridy = 17 ;gbc.gridwidth = 2 ;
        add(classPickerList.get(2),gbc);
        gbc.gridx = 1 ; gbc.gridy = 23 ;gbc.gridwidth = 2 ;
        add(classPickerList.get(3),gbc);
        gbc.gridx = 1 ; gbc.gridy = 29 ;gbc.gridwidth = 2 ;
        add(classPickerList.get(4),gbc);

        gbc.gridx = 2 ; gbc.gridy = 35 ; gbc.gridwidth = 1 ;
        add(addClassButton,gbc);
        gbc.gridx = 0 ; gbc.gridy = 36 ;gbc.gridwidth = 1 ;
        add(numOfStudLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 36; gbc.gridwidth = 2 ;
        add(numOfStudBox,gbc);
        gbc.gridx = 0 ; gbc.gridy = 37 ;gbc.gridwidth = 1 ;
        add(testMarkPctLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 37 ; gbc.gridwidth = 2 ;
        add(testMarkPctField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 38;gbc.gridwidth = 1 ;
        add(examMarkPctLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 38; gbc.gridwidth = 2 ;
        add(examMarkPctField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 39 ;gbc.gridwidth = 1 ;
        add(assignmentMarkPctLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 39 ; gbc.gridwidth = 2 ;
        add(assignmentMarkPctField,gbc);
        gbc.gridx = 2 ; gbc.gridy = 40 ; gbc.gridwidth = 1 ;
        add(actionButton,gbc);

    }



    public JComboBox<Integer> getNumOfStudBox() {
        return numOfStudBox;
    }

    public JTextField getModuleIDField() {
        return moduleIDField;
    }

    public JTextField getModuleNameField() {
        return moduleNameField;
    }

    public List<JClassPicker> getClassPickerList() { return classPickerList; }

    public JComboBox<Integer> getLevelBox() {
        return levelBox;
    }

    public JTextField getIntakesCodeField() {
        return intakesCodeField;
    }

    public JNumberTextField getTestMarkPctField() {
        return testMarkPctField;
    }

    public JNumberTextField getExamMarkPctField() {
        return examMarkPctField;
    }

    public JNumberTextField getAssignmentMarkPctField() {
        return assignmentMarkPctField;
    }

    public JButton getAddClassButton() {
        return addClassButton;
    }

    public JButton getActionButton() {
        return actionButton;
    }

}
