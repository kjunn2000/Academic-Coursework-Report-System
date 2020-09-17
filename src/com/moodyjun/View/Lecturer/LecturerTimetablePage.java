package com.moodyjun.View.Lecturer;

import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.View.Util.JTimeTablePanel;

import javax.swing.*;

public class LecturerTimetablePage extends LecturerTemplatePage{

    private JTimeTablePanel timeTablePanel;

    public LecturerTimetablePage(){
        timeTablePanel = new JTimeTablePanel();
        getTabbedPane().add(new JScrollPane(timeTablePanel),"Timetable");
    }

    public JTimeTablePanel getTimeTablePanel() {
        return timeTablePanel;
    }
}
