package com.moodyjun.Controller.Admin;

import com.moodyjun.Controller.*;
import com.moodyjun.Services.PDF.PDFService;
import com.moodyjun.View.Admin.AdminTemplatePage;
import com.moodyjun.View.TemplatePage;

import javax.swing.*;

public class AdminTemplateController extends TemplateController {

    public AdminTemplateController(TemplatePage templatePage) {
        super(templatePage);
        navInit((AdminTemplatePage)templatePage);
    }

    public void navInit(AdminTemplatePage adminTemplatePage){
        adminTemplatePage.getEditButton().addActionListener(e -> {
            adminTemplatePage.setVisible(false);
            AdminEditProfileController.getInstance().getEditProfilePage().setVisible(true);
        });
        adminTemplatePage.getNavButton1().addActionListener(e -> {
            adminTemplatePage.setVisible(false);
            AdminHomePageController.getInstance().getAdminHomePage().setVisible(true);
        });
        adminTemplatePage.getNavButton2().addActionListener(e -> {
            adminTemplatePage.setVisible(false);
            UserMgmtController.getInstance().getUserMgmtPage().setVisible(true);
        });
        adminTemplatePage.getNavButton3().addActionListener(e -> {
            adminTemplatePage.setVisible(false);
            ModuleMgmtController.getInstance().getModuleMgmtPage().setVisible(true);
        });
        adminTemplatePage.getNavButton4().addActionListener(e -> {
            adminTemplatePage.setVisible(false);
            EnrollmentController.getInstance().getEnrollMgmtPage().setVisible(true);
        });
        adminTemplatePage.getNavButton5().addActionListener(e -> {
            adminTemplatePage.setVisible(false);
            AdminReportGenerateController.getInstance().getAdminReportGeneratePage().setVisible(true);
        });
        adminTemplatePage.getNavButton6().addActionListener(e -> {
            if(PDFService.getInstance().generateLogFile()==1)
                JOptionPane.showMessageDialog(adminTemplatePage,"LogFile.pdf successfully generated.");
        });
    }


}