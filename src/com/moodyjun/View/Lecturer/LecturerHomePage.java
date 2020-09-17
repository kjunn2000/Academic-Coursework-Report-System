package com.moodyjun.View.Lecturer;

import com.moodyjun.View.Util.HomePagePanel;

import javax.swing.*;

public class LecturerHomePage extends LecturerTemplatePage{
    private JLabel hello ;
    private HomePagePanel homePanel ;

    public LecturerHomePage() {
        homePanel = new HomePagePanel();
        getTabbedPane().add("Home", homePanel);
        hello = new JLabel();
        getHomePanel().add(hello);
    }

    public HomePagePanel getHomePanel() {
        return homePanel;
    }

}
