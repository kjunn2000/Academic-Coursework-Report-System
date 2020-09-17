package com.moodyjun.View.Lecturer;

import com.moodyjun.View.Admin.AdminTemplatePage;
import com.moodyjun.View.Util.GenerateReportPanel;

import javax.swing.*;

public class LecturerReportGeneratePage extends LecturerTemplatePage {

    private GenerateReportPanel generateReportPanel;

    public LecturerReportGeneratePage() {

        generateReportPanel = new GenerateReportPanel();
        getTabbedPane().add(new JScrollPane(generateReportPanel),"Generate Report");
        generateReportPanel.getRadioButton1().setText("Module Report");
        generateReportPanel.getRadioButton2().setText("Student Result Report");
        generateReportPanel.getRadioButton3().setVisible(false);
        generateReportPanel.getRadioButton4().setVisible(false);


    }

    public GenerateReportPanel getGenerateReportPanel() {
        return generateReportPanel;
    }
}
