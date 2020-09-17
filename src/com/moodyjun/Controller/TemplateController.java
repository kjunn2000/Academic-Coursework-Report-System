package com.moodyjun.Controller;

import com.moodyjun.Model.User.User;
import com.moodyjun.Services.System.LogService;
import com.moodyjun.View.TemplatePage;

import javax.swing.*;

public class TemplateController {

    public User user ;

    public TemplateController(TemplatePage templatePage) {
        this.user = LogService.getInstance().getCurrentUser();
        viewInit(templatePage);
        exitAction(templatePage);
        menuItemInit(templatePage);
    }

    private void menuItemInit(TemplatePage page){
        page.getMenuItem().addActionListener(e->JOptionPane.showMessageDialog(page,"Academic Coursework Report System 1.0"));
    }

    public void viewInit(TemplatePage page) {
        page.getId().setText(user.getId().toString());
        page.getUserName().setText(user.getName());
        String position="";
        switch(user.getRole()){
            case 0 : position = "Admin";break;
            case 1 : position = "Lecturer";break;
            case 2 : position = "Student";break;
        }
        page.getPosition().setText(position);
    }

    public void exitAction(TemplatePage templatePage){
        templatePage.getExitButton().addActionListener(e->{
            if(JOptionPane.showConfirmDialog(templatePage,"Are you sure to exit?")==0){
                LogService.getInstance().logOut();
                templatePage.setVisible(false);
                LogInController.getInstance().getLogInPage().setVisible(true);
                Runtime.getRuntime().removeShutdownHook(LogInController.getInstance().getLogOutThread());
            }
        });

    }

    public User getUser() {
        return user;
    }
}
