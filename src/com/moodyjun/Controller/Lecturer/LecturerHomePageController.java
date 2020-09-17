package com.moodyjun.Controller.Lecturer;

import com.moodyjun.Controller.Admin.AdminEditProfileController;
import com.moodyjun.Controller.Admin.EnrollmentController;
import com.moodyjun.Controller.Admin.ModuleMgmtController;
import com.moodyjun.Controller.Admin.UserMgmtController;
import com.moodyjun.View.Lecturer.LecturerHomePage;

import javax.swing.*;
import java.awt.*;

public class LecturerHomePageController extends LecturerTemplateController {

    private LecturerHomePage lecturerHomePage;
    private static LecturerHomePageController lecturerHomePageController;

    private LecturerHomePageController(LecturerHomePage lecturerHomePage) {
        super(lecturerHomePage);
        this.lecturerHomePage = lecturerHomePage ;
        viewInit();
        actionInit();
    }

    private void viewInit(){
        lecturerHomePage.getHomePanel().getWelcomeLabel().setText("Welcome "+user.getName()+" ,wish you have a nice day!");
        lecturerHomePage.getHomePanel().getButton1().setText("Key Result");
        lecturerHomePage.getHomePanel().getButton2().setText("View Timetable");
        lecturerHomePage.getHomePanel().getButton3().setText("Search Student");
        lecturerHomePage.getHomePanel().getButton4().setText("Report Printing");
    }

    private void actionInit(){

        lecturerHomePage.getHomePanel().getButton1().addActionListener(e -> {
            lecturerHomePage.setVisible(false);
            KeyResultController.getInstance().getKeyResultPage().setVisible(true);
        });

        lecturerHomePage.getHomePanel().getButton2().addActionListener(e -> {
            lecturerHomePage.setVisible(false);
            LecturerTimetableController.getInstance().getLecturerTimetablePage().setVisible(true);
        });

        lecturerHomePage.getHomePanel().getButton3().addActionListener(e -> {
            lecturerHomePage.setVisible(false);
            SearchStudentController.getInstance().getSearchStudentPage().setVisible(true);
        });

        lecturerHomePage.getHomePanel().getButton4().addActionListener(e -> {
            lecturerHomePage.setVisible(false);
            LecturerReportGenerateController.getInstance().getLecturerReportGeneratePage().setVisible(true);
        });
    }

    public static LecturerHomePageController getInstance(){
        lecturerHomePageController = new LecturerHomePageController(new LecturerHomePage());
        return lecturerHomePageController;
    }

    public LecturerHomePage getLecturerHomePage() {
        return lecturerHomePage;
    }
}
