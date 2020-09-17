package com.moodyjun.View.Student;

import com.moodyjun.View.Util.UserFormTemplatePanel;

import javax.swing.*;
import java.awt.*;

public class SearchLecturerPage extends StudentTemplatePage {

    UserFormTemplatePanel searchStudPanel;
    private JLabel formLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton searchButton;
    private JTable lecTable;
    private GridBagConstraints gbc ;

    public SearchLecturerPage(){

        JPanel searchStudPanel = new JPanel();
        getTabbedPane().add(new JScrollPane(searchStudPanel),"Search Student");
        formLabel = new JLabel("Search Lecturer");
        formLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        nameLabel = new JLabel("Lecturer Name:");
        nameField = new JTextField();
        searchButton =  new JButton("Search");
        lecTable = new JTable();
        lecTable.setEnabled(false);
        JScrollPane sp=new JScrollPane(lecTable);
        gbc = new GridBagConstraints();
        searchStudPanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10,50,20,50);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 0 ;gbc.gridwidth = 1 ;
        searchStudPanel.add(formLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0 ; gbc.gridy = 1 ;gbc.gridwidth = 1 ;
        searchStudPanel.add(nameLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 1 ; gbc.gridwidth = 1 ;
        searchStudPanel.add(nameField,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2 ; gbc.gridy = 1 ; gbc.gridwidth = 1 ;
        searchStudPanel.add(searchButton,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0 ; gbc.gridy = 3 ; gbc.gridwidth = 3 ;
        searchStudPanel.add(sp,gbc);
    }


    public JPanel getSearchStudPanel() {
        return searchStudPanel;
    }

    public JTextField getNameField() { return nameField; }

    public JTable getLecTable() {
        return lecTable;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
