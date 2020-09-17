package com.moodyjun.View.Lecturer;

import com.moodyjun.View.Admin.AdminTemplatePage;
import com.moodyjun.View.Util.UserFormTemplatePanel;

import javax.swing.*;

public class LecturerEditProfilePage extends LecturerTemplatePage {
    private UserFormTemplatePanel editProfileForm;
    private JLabel idLabel ;
    private JTextField idField ;

    public LecturerEditProfilePage(){
        this.editProfileForm = new UserFormTemplatePanel();
        getTabbedPane().add(new JScrollPane(editProfileForm),"Edit Profile");
        getEditProfileForm().getUserBox().setVisible(false);
        getEditProfileForm().getActionButton().setText("Edit");
        getEditProfileForm().getFormLabel().setText("Edit Profile");
        idLabel = new JLabel("UserID:");
        idField = new JTextField();
        idField.setEditable(false);
        getEditProfileForm().getMaleRadioButton().setEnabled(false);
        getEditProfileForm().getFemaleRadioButton().setEnabled(false);
        getEditProfileForm().getIntakeCodeLabel().setVisible(false);
        getEditProfileForm().getIntakeCodeField().setVisible(false);
        getEditProfileForm().getLevelLabel().setVisible(false);
        getEditProfileForm().getLevelBox().setVisible(false);

        getEditProfileForm().getGbc().gridx = 0 ;
        getEditProfileForm().getGbc().gridy = 1 ;
        getEditProfileForm().getGbc().gridwidth = 1 ;
        getEditProfileForm().add(idLabel,getEditProfileForm().getGbc());
        getEditProfileForm().getGbc().gridx = 1 ;
        getEditProfileForm().getGbc().gridy = 1 ;
        getEditProfileForm().getGbc().gridwidth = 2 ;
        getEditProfileForm().add(idField,getEditProfileForm().getGbc());
    }

    public UserFormTemplatePanel getEditProfileForm() {
        return editProfileForm;
    }
    public JTextField getIdField() { return idField; }

}

