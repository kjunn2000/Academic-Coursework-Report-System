package com.moodyjun.View.Student;

import com.moodyjun.View.Lecturer.LecturerTemplatePage;
import com.moodyjun.View.Util.JTimeTablePanel;

import javax.swing.*;

public class StudentTimetablePage extends StudentTemplatePage {

    private JTimeTablePanel timeTablePanel;

    public StudentTimetablePage(){
        timeTablePanel = new JTimeTablePanel();
        getTabbedPane().add(new JScrollPane(timeTablePanel),"Timetable");
    }

    public JTimeTablePanel getTimeTablePanel() {
        return timeTablePanel;
    }
}
