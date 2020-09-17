package com.moodyjun.View.Lecturer;

import com.moodyjun.View.Util.UserFormTemplatePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchStudentPage extends LecturerTemplatePage {

    UserFormTemplatePanel searchStudPanel;
    private JLabel formLabel;
    private JLabel idLabel ;
    private JTextField idField ;
    private JButton searchButton;
    private JTable studTable;
    private GridBagConstraints gbc ;

    public SearchStudentPage(){

        JPanel searchStudPanel = new JPanel();
        getTabbedPane().add(new JScrollPane(searchStudPanel),"Search Student");
        formLabel = new JLabel("Search Student");
        formLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        idLabel = new JLabel("StudentID:");
        idField = new JTextField();
        searchButton =  new JButton("Search");
        studTable = new JTable();
        studTable.setEnabled(false);
        JScrollPane sp=new JScrollPane(studTable);
        gbc = new GridBagConstraints();
        searchStudPanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10,50,20,50);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 0 ;gbc.gridwidth = 1 ;
        searchStudPanel.add(formLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0 ; gbc.gridy = 1 ;gbc.gridwidth = 1 ;
        searchStudPanel.add(idLabel,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1 ; gbc.gridy = 1 ; gbc.gridwidth = 1 ;
        searchStudPanel.add(idField,gbc);
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

    public JTextField getIdField() { return idField; }

    public JTable getStudTable() {
        return studTable;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
