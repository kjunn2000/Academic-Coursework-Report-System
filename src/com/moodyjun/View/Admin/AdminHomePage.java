package com.moodyjun.View.Admin;

import com.moodyjun.Model.User.Admin;
import com.moodyjun.View.Student.StudentTemplatePage;
import com.moodyjun.View.Util.HomePagePanel;

import javax.swing.*;

public class AdminHomePage extends AdminTemplatePage {
    private HomePagePanel homePanel ;

    public AdminHomePage() {
        homePanel = new HomePagePanel();
        getTabbedPane().add("Home", homePanel);
    }

    public HomePagePanel getHomePanel() {
        return homePanel;
    }

}
