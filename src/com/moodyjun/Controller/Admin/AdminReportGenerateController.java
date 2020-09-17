package com.moodyjun.Controller.Admin;

import com.moodyjun.Services.PDF.PDFService;
import com.moodyjun.View.Admin.AdminReportGeneratePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminReportGenerateController extends AdminTemplateController implements ActionListener {

    private AdminReportGeneratePage adminReportGeneratePage;
    private PDFService pdfService;
    private static AdminReportGenerateController adminReportGenerateController;

    public AdminReportGenerateController(AdminReportGeneratePage adminReportGeneratePage, PDFService reportService) {
        super(adminReportGeneratePage);
        this.adminReportGeneratePage=adminReportGeneratePage ;
        this.pdfService = reportService;
        reportButtonInit();
    }

    private void reportButtonInit(){
        adminReportGeneratePage.getGenerateReportPanel().getGenerateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

       if(adminReportGeneratePage.getGenerateReportPanel().getRadioButton1().isSelected()) {
           if( pdfService.generateAgeReport()==1) JOptionPane.showMessageDialog(adminReportGeneratePage,"Successfully generate age report.");
       }
       else if(adminReportGeneratePage.getGenerateReportPanel().getRadioButton2().isSelected()) {
           if( pdfService.generateGenderReport()==1) JOptionPane.showMessageDialog(adminReportGeneratePage,"Successfully generate gender report.");
       }
       else if(adminReportGeneratePage.getGenerateReportPanel().getRadioButton3().isSelected()) {
           if( pdfService.generateModuleReport()==1) JOptionPane.showMessageDialog(adminReportGeneratePage,"Successfully generate module report.");
       }
       else if(adminReportGeneratePage.getGenerateReportPanel().getRadioButton4().isSelected()) {
           if( pdfService.generateUserTypeReport()==1) JOptionPane.showMessageDialog(adminReportGeneratePage,"Successfully generate user type report.");
       }
    }

    public static AdminReportGenerateController getInstance(){
        adminReportGenerateController = new AdminReportGenerateController(new AdminReportGeneratePage(), PDFService.getInstance());
        return adminReportGenerateController;
    }


    public AdminReportGeneratePage getAdminReportGeneratePage() {
        return adminReportGeneratePage;
    }

}
