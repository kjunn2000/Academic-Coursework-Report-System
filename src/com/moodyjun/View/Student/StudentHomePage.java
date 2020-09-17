package com.moodyjun.View.Student;

import com.moodyjun.View.Util.HomePagePanel;

import javax.swing.*;

public class StudentHomePage extends StudentTemplatePage{
    private JLabel hello ;
    private HomePagePanel homePanel ;

    public StudentHomePage() {
        homePanel = new HomePagePanel();
        getTabbedPane().add("Home", homePanel);
        hello = new JLabel();
        getHomePanel().add(hello);
    }

    public HomePagePanel getHomePanel() {
        return homePanel;
    }

}
