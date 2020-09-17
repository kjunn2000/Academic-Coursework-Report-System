package com.moodyjun.View.Admin.Enrollment;

import com.moodyjun.View.Admin.AdminTemplatePage;

import javax.swing.*;

public class EnrollMgmtPage extends AdminTemplatePage {

    private EnrollStudPanel enrollStudPanel;
    private EnrollLecturerPanel enrollLecturerPanel;


    public EnrollMgmtPage(){
        enrollStudPanel = new EnrollStudPanel();
        enrollLecturerPanel = new EnrollLecturerPanel();
        userMgmtTabInit();
    }

    private void userMgmtTabInit(){
        getTabbedPane().add( new JScrollPane(enrollStudPanel),"Enroll Student" );
        getTabbedPane().add( new JScrollPane(enrollLecturerPanel),"Enroll Lecturer");
    }


    public EnrollStudPanel getEnrollStudPanel() {
        return enrollStudPanel;
    }

    public EnrollLecturerPanel getEnrollLecturerPanel() {
        return enrollLecturerPanel;
    }
}
