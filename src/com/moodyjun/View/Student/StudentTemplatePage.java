package com.moodyjun.View.Student;

import com.moodyjun.View.TemplatePage;

public class StudentTemplatePage extends TemplatePage {

    public StudentTemplatePage(){
        getNavButton1().setText("Home Page");
        getNavButton2().setText("View Timetable");
        getNavButton3().setText("View Result");
        getNavButton4().setText("Search Lecturer");
        getNavButton5().setText("Result Printing");
        getNavButton6().setVisible(false);
    }
}


