package com.moodyjun.View.Admin.UserMgmt;

import javax.swing.*;
import java.awt.*;

public class DeleteUserPanel extends JPanel {

    private JLabel deleteUserLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JButton deleteUserButton;
    private GridBagConstraints gbc;

    public DeleteUserPanel(){
        setLayout(new GridBagLayout());

        deleteUserLabel = new JLabel("Delete User");
        deleteUserLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        idLabel=new JLabel("ID:");
        idField = new JTextField();
        deleteUserButton = new JButton("Delete");

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,50,20,50);
        gbc.gridx = 1 ; gbc.gridy = 0 ;
        add(deleteUserLabel,gbc);
        gbc.gridx = 0 ; gbc.gridy = 2 ;gbc.gridwidth = 1 ;
        add(idLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 2 ; gbc.gridwidth = 2 ;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(idField,gbc);
        gbc.gridx = 2 ; gbc.gridy = 3 ; gbc.gridwidth = 1 ;
        add(deleteUserButton,gbc);
    }

    public JTextField getIdField() { return idField; }

    public JButton getDeleteUserButton() { return deleteUserButton; }
}
