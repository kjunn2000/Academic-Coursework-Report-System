package com.moodyjun.Controller.Student;

import com.moodyjun.Controller.Admin.AdminEditProfileController;
import com.moodyjun.Controller.Admin.EnrollmentController;
import com.moodyjun.Controller.Admin.ModuleMgmtController;
import com.moodyjun.Controller.Admin.UserMgmtController;
import com.moodyjun.View.Student.StudentHomePage;

import javax.swing.*;
import java.awt.*;

public class StudentHomePageController extends StudentTemplateController {

    private StudentHomePage studentHomePage ;
    private static StudentHomePageController studentHomePageController ;

    public StudentHomePageController(StudentHomePage studentHomePage) {
        super(studentHomePage);
        this.studentHomePage=studentHomePage ;
        viewInit();
        actionInit();
    }

    private void viewInit(){
        studentHomePage.getHomePanel().getWelcomeLabel().setText("Welcome "+user.getName()
                +" ,wish you have a nice day!");
        studentHomePage.getHomePanel().getButton1().setText("View Timetable");
        studentHomePage.getHomePanel().getButton2().setText("View Result");
        studentHomePage.getHomePanel().getButton3().setText("Search Lecturer");
        studentHomePage.getHomePanel().getButton4().setText("Edit Profile");
    }

    private void actionInit(){

        studentHomePage.getHomePanel().getButton1().addActionListener(e -> {
            studentHomePage.setVisible(false);
            StudentTimetableController.getInstance().getStudentTimetablePage().setVisible(true);
        });
        studentHomePage.getHomePanel().getButton2().addActionListener(e -> {
            studentHomePage.setVisible(false);
            ViewResultController.getInstance().getViewResultPage().setVisible(true);
        });
        studentHomePage.getHomePanel().getButton3().addActionListener(e -> {
            studentHomePage.setVisible(false);
            SearchLecturerController.getInstance().getSearchStudentPage().setVisible(true);
        });
        studentHomePage.getHomePanel().getButton4().addActionListener(e -> {
            studentHomePage.setVisible(false);
            StudentEditProfileController.getInstance().getEditProfilePage().setVisible(true);
        });
    }

    public static StudentHomePageController getInstance(){
        studentHomePageController = new StudentHomePageController(new StudentHomePage());
        return studentHomePageController ;
    }

    public StudentHomePage getStudentHomePage() {
        return studentHomePage;
    }
}
