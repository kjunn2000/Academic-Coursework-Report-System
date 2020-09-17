package com.moodyjun.Controller.Admin;

import com.moodyjun.View.Admin.AdminHomePage;

import javax.swing.*;
import java.awt.*;

public class AdminHomePageController extends AdminTemplateController {

    private AdminHomePage adminHomePage ;
    private static AdminHomePageController adminHomePageController ;

    public AdminHomePageController(AdminHomePage adminHomePage) {
        super(adminHomePage);
        this.adminHomePage=adminHomePage ;
        viewInit();
        actionInit();
    }

    private void viewInit(){
        adminHomePage.getHomePanel().getWelcomeLabel().setText("Welcome "+user.getName()+" ,wish you have a nice day!");
        adminHomePage.getHomePanel().getButton1().setText("User Management");
        adminHomePage.getHomePanel().getButton2().setText("Module Management");
        adminHomePage.getHomePanel().getButton3().setText("Enrollment");
        adminHomePage.getHomePanel().getButton4().setText("Report Printing");
    }

    private void actionInit(){

        adminHomePage.getHomePanel().getButton1().addActionListener(e -> {
            adminHomePage.setVisible(false);
            UserMgmtController.getInstance().getUserMgmtPage().setVisible(true);
        });
        adminHomePage.getHomePanel().getButton2().addActionListener(e -> {
            adminHomePage.setVisible(false);
            ModuleMgmtController.getInstance().getModuleMgmtPage().setVisible(true);
        });
        adminHomePage.getHomePanel().getButton3().addActionListener(e -> {
            adminHomePage.setVisible(false);
            EnrollmentController.getInstance().getEnrollMgmtPage().setVisible(true);
        });
        adminHomePage.getHomePanel().getButton4().addActionListener(e -> {
            adminHomePage.setVisible(false);
            AdminReportGenerateController.getInstance().getAdminReportGeneratePage().setVisible(true);
        });
    }



    public static AdminHomePageController getInstance(){
        adminHomePageController = new AdminHomePageController(new AdminHomePage());
        return adminHomePageController ;
    }

    public AdminHomePage getAdminHomePage() {
        return adminHomePage;
    }
}
