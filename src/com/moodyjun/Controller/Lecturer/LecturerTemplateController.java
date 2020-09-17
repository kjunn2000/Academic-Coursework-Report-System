package com.moodyjun.Controller.Lecturer;

import com.moodyjun.Controller.*;
import com.moodyjun.Services.User.AdminService;
import com.moodyjun.View.Admin.AdminEditProfilePage;
import com.moodyjun.View.Admin.AdminTemplatePage;
import com.moodyjun.View.Lecturer.LecturerTemplatePage;
import com.moodyjun.View.TemplatePage;

public class LecturerTemplateController extends TemplateController {

    public LecturerTemplateController(TemplatePage templatePage) {
        super(templatePage);
        navInit((LecturerTemplatePage)templatePage);
    }

    public void navInit(LecturerTemplatePage lecturerTemplatePage){
        lecturerTemplatePage.getEditButton().addActionListener(e -> {
            lecturerTemplatePage.setVisible(false);
            LecturerEditProfileController.getInstance().getEditProfilePage().setVisible(true);
        });

        lecturerTemplatePage.getNavButton1().addActionListener(e -> {
            lecturerTemplatePage.setVisible(false);
            LecturerHomePageController.getInstance().getLecturerHomePage().setVisible(true);
        });

        lecturerTemplatePage.getNavButton2().addActionListener(e -> {
            lecturerTemplatePage.setVisible(false);
            KeyResultController.getInstance().getKeyResultPage().setVisible(true);
        });

        lecturerTemplatePage.getNavButton3().addActionListener(e -> {
            lecturerTemplatePage.setVisible(false);
            LecturerTimetableController.getInstance().getLecturerTimetablePage().setVisible(true);
        });

        lecturerTemplatePage.getNavButton4().addActionListener(e -> {
            lecturerTemplatePage.setVisible(false);
            SearchStudentController.getInstance().getSearchStudentPage().setVisible(true);
        });

        lecturerTemplatePage.getNavButton5().addActionListener(e -> {
            lecturerTemplatePage.setVisible(false);
            LecturerReportGenerateController.getInstance().getLecturerReportGeneratePage().setVisible(true);
        });



    }



}