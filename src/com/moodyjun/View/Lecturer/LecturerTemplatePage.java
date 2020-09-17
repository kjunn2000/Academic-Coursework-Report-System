package com.moodyjun.View.Lecturer;

import com.moodyjun.View.TemplatePage;

public class LecturerTemplatePage extends TemplatePage {

    public LecturerTemplatePage(){
        getNavButton1().setText("Home Page");
        getNavButton2().setText("Key Result");
        getNavButton3().setText("View Timetable");
        getNavButton4().setText("Search Student");
        getNavButton5().setText("Report Printing");
        getNavButton6().setVisible(false);

    }
}


