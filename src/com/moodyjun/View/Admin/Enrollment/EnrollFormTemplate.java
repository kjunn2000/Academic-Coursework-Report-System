package com.moodyjun.View.Admin.Enrollment;

import javax.swing.*;
import java.awt.*;

public class EnrollFormTemplate extends JPanel {

    protected JLabel titleLabel;
    protected JLabel idLabel;
    protected JLabel enrolledModuleLabel;
    protected JLabel enrollModuleLabel;
    protected JList enrolledModuleList;
    protected JList enrollModuleList;
    protected JTextField idField;
    protected JButton searchButton;
    protected JButton disenrollButton;
    protected JButton enrollButton;
    protected GridBagConstraints gbc;
    DefaultListModel model;

    public EnrollFormTemplate(){
        setLayout(new GridBagLayout());

        titleLabel = new JLabel();
        titleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        idLabel=new JLabel();
        idField = new JTextField();
        idField.setEditable(false);
        searchButton = new JButton("Search");
        enrolledModuleLabel = new JLabel("Enrolled module :");
        String[] defaultList = {"No module yet"};
        model = new DefaultListModel();
        enrolledModuleList = new JList<>(defaultList);
        enrolledModuleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        disenrollButton = new JButton("Disenroll");
        disenrollButton.setEnabled(false);
        enrollModuleLabel = new JLabel("Enroll module :");
        enrollModuleList = new JList(defaultList);
        enrollModuleList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        enrollButton = new JButton("Enroll");
        enrollButton.setEnabled(false);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,50,20,50);
        gbc.gridx = 1 ; gbc.gridy = 0 ;
        add(titleLabel,gbc);
        gbc.gridx = 0 ; gbc.gridy = 2 ;gbc.gridwidth = 1 ;
        add(idLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 2 ; gbc.gridwidth = 1 ;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(idField,gbc);
        gbc.gridx = 2 ; gbc.gridy = 2 ;gbc.gridwidth = 1 ;
        add(searchButton,gbc);
        gbc.gridx = 0 ; gbc.gridy = 3 ;gbc.gridwidth = 1 ;
        add(enrolledModuleLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 3 ; gbc.gridwidth = 2 ;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(enrolledModuleList,gbc);
        gbc.gridx = 2 ; gbc.gridy = 4 ;gbc.gridwidth = 1 ;
        add(disenrollButton,gbc);
        gbc.gridx = 0 ; gbc.gridy = 5 ;gbc.gridwidth = 1 ;
        add(enrollModuleLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 5 ; gbc.gridwidth = 2 ;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(enrollModuleList,gbc);
        gbc.gridx = 2 ; gbc.gridy = 6 ;gbc.gridwidth = 1 ;
        add(enrollButton,gbc);

    }

    public JList getEnrolledModuleList() {
        return enrolledModuleList;
    }

    public JList getEnrollModuleList() {
        return enrollModuleList;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getDisenrollButton() {
        return disenrollButton;
    }

    public JButton getEnrollButton() {
        return enrollButton;
    }

    public DefaultListModel getModel() {
        return model;
    }
}
