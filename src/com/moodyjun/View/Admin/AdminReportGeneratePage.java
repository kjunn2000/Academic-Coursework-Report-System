package com.moodyjun.View.Admin;

import com.moodyjun.View.Util.GenerateReportPanel;
import com.moodyjun.View.Util.HomePagePanel;

import javax.swing.*;
import java.awt.*;

public class AdminReportGeneratePage extends AdminTemplatePage {

    private GenerateReportPanel generateReportPanel;

    public AdminReportGeneratePage() {

        generateReportPanel = new GenerateReportPanel();
        getTabbedPane().add(new JScrollPane(generateReportPanel),"Generate Report");
        generateReportPanel.getRadioButton1().setText("Age Report");
        generateReportPanel.getRadioButton2().setText("Gender Report");
        generateReportPanel.getRadioButton3().setText("Module Report");
        generateReportPanel.getRadioButton4().setText("User Type Report");
    }

    public GenerateReportPanel getGenerateReportPanel() {
        return generateReportPanel;
    }
}
