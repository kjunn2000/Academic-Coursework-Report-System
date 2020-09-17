package com.moodyjun.View.Admin.Enrollment;

import javax.swing.*;
import java.awt.*;

public class EnrollLecturerPanel extends EnrollFormTemplate {

    public EnrollLecturerPanel(){
        titleLabel.setText("Enroll Lecturer");
        titleLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        idLabel.setText("Lecturer ID:");
        searchButton.setText("Search");
    }
}
