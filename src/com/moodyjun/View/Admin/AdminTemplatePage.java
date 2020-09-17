package com.moodyjun.View.Admin;

import com.moodyjun.View.TemplatePage;

import javax.swing.*;

public class AdminTemplatePage extends TemplatePage {

    public AdminTemplatePage(){
        getNavButton1().setText("Home Page");
        getNavButton2().setText("User Management");
        getNavButton3().setText("Module Management");
        getNavButton4().setText("Enrollment");
        getNavButton5().setText("Report Printing");
        getNavButton6().setText("Generate Log File");

    }


}
