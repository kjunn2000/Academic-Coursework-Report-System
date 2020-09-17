package com.moodyjun.View.Admin.UserMgmt;

import com.moodyjun.View.Util.UserFormTemplatePanel;

import javax.swing.*;

public class UpdateUserPanel extends UserFormTemplatePanel {

    private JLabel idLabel ;
    private JTextField idField ;
    private JButton findUserButton;

    public UpdateUserPanel(){
        formLabel.setText("Update User");
        findUserButton =  new JButton("Find");
        idLabel = new JLabel("UserID:");
        idField = new JTextField();
        adminRadioButton.setEnabled(false);
        lecturerRadioButton.setEnabled(false);
        studentRadioButton.setEnabled(false);
        actionButton.setText("Update");

        gbc.gridx = 0 ; gbc.gridy = 1 ;gbc.gridwidth = 1 ;
        add(idLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 1 ; gbc.gridwidth = 1 ;
        add(idField,gbc);
        gbc.gridx = 2 ; gbc.gridy = 1 ; gbc.gridwidth = 1 ;
        add(findUserButton,gbc);
    }

    public JTextField getIdField() { return idField; }

    public JButton getFindUserButton() {
        return findUserButton;
    }
}
