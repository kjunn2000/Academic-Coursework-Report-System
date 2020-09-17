package com.moodyjun.Controller.Student;

import com.moodyjun.Controller.Admin.*;
import com.moodyjun.Controller.TemplateController;
import com.moodyjun.View.Admin.AdminTemplatePage;
import com.moodyjun.View.Student.SearchLecturerPage;
import com.moodyjun.View.Student.StudentTemplatePage;
import com.moodyjun.View.TemplatePage;

import javax.swing.text.View;

public class StudentTemplateController extends TemplateController {

    public StudentTemplateController(TemplatePage templatePage) {
        super(templatePage);
        navInit((StudentTemplatePage)templatePage);
    }

    public void navInit(StudentTemplatePage studentTemplatePage){
        studentTemplatePage.getEditButton().addActionListener(e -> {
            studentTemplatePage.setVisible(false);
            StudentEditProfileController.getInstance().getEditProfilePage().setVisible(true);
        });
        studentTemplatePage.getNavButton1().addActionListener(e -> {
            studentTemplatePage.setVisible(false);
            StudentHomePageController.getInstance().getStudentHomePage().setVisible(true);
        });
        studentTemplatePage.getNavButton2().addActionListener(e -> {
            studentTemplatePage.setVisible(false);
            StudentTimetableController.getInstance().getStudentTimetablePage().setVisible(true);
        });
        studentTemplatePage.getNavButton3().addActionListener(e -> {
            studentTemplatePage.setVisible(false);
            ViewResultController.getInstance().getViewResultPage().setVisible(true);
        });
        studentTemplatePage.getNavButton4().addActionListener(e -> {
            studentTemplatePage.setVisible(false);
            SearchLecturerController.getInstance().getSearchStudentPage().setVisible(true);
        });
        studentTemplatePage.getNavButton5().setVisible(false);

    }



}