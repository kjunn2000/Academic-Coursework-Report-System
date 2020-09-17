package com.moodyjun.View.Admin.ModuleMgmt;

import com.moodyjun.View.Util.JClassPicker;
import com.moodyjun.View.Util.JNumberTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateModulePanel extends ModuleFormTemplatePanel {

    private JButton findModuleButton;

    public UpdateModulePanel(){

        formLabel.setText("Update Module");
        actionButton.setText("Update");
        findModuleButton = new JButton("Find");
        testMarkPctField.setEditable(false);
        examMarkPctField.setEditable(false);
        assignmentMarkPctField.setEditable(false);


        gbc.gridx = 2 ; gbc.gridy = 1 ; gbc.gridwidth = 1 ;
        add(findModuleButton,gbc);

    }

    public JButton getFindModuleButton() {
        return findModuleButton;
    }
}
