package com.moodyjun.View.Admin.ModuleMgmt;

import javax.swing.*;
import java.awt.*;

public class DeleteModulePanel extends JPanel {

    private JLabel deleteModuleLabel;
    private JLabel moduleIDLabel;
    private JTextField moduleIDField;
    private JButton deleteModuleButton;
    private GridBagConstraints gbc;

    public DeleteModulePanel(){
        setLayout(new GridBagLayout());

        deleteModuleLabel = new JLabel("Delete Module");
        deleteModuleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        moduleIDLabel =new JLabel("ID:");
        moduleIDField = new JTextField();
        deleteModuleButton = new JButton("Delete");

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,50,20,50);
        gbc.gridx = 1 ; gbc.gridy = 0 ;
        add(deleteModuleLabel,gbc);
        gbc.gridx = 0 ; gbc.gridy = 2 ;gbc.gridwidth = 1 ;
        add(moduleIDLabel,gbc);
        gbc.gridx = 1 ; gbc.gridy = 2 ; gbc.gridwidth = 2 ;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(moduleIDField,gbc);
        gbc.gridx = 0 ; gbc.gridy = 3 ;gbc.gridwidth = 1 ;
        gbc.gridx = 2 ; gbc.gridy = 9 ; gbc.gridwidth = 1 ;
        add(deleteModuleButton,gbc);
    }

    public JTextField getModuleIDField() { return moduleIDField; }

    public JButton getDeleteModuleButton() { return deleteModuleButton; }
}
