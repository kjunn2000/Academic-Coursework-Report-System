package com.moodyjun.Controller.Lecturer;

import com.moodyjun.Services.PDF.PDFService;
import com.moodyjun.View.Lecturer.LecturerReportGeneratePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecturerReportGenerateController extends LecturerTemplateController implements ActionListener {

    private LecturerReportGeneratePage lecturerReportGeneratePage;
    private PDFService pdfService;
    private static LecturerReportGenerateController lecturerReportGenerateController;

    public LecturerReportGenerateController(LecturerReportGeneratePage lecturerReportGeneratePage, PDFService reportService) {
        super(lecturerReportGeneratePage);
        this.lecturerReportGeneratePage =lecturerReportGeneratePage ;
        this.pdfService = reportService;
        reportButtonInit();
    }

    private void reportButtonInit(){
        lecturerReportGeneratePage.getGenerateReportPanel().getGenerateButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(lecturerReportGeneratePage.getGenerateReportPanel().getRadioButton1().isSelected()) {
           if( pdfService.generateModuleReport()==1) JOptionPane.showMessageDialog(lecturerReportGeneratePage,"Successfully generate module report.");
       }
       else if(lecturerReportGeneratePage.getGenerateReportPanel().getRadioButton2().isSelected()) {
           if( pdfService.generateStudentResultReport()==1) JOptionPane.showMessageDialog(lecturerReportGeneratePage,"Successfully generate student result report.");
       }
    }

    public static LecturerReportGenerateController getInstance(){
        lecturerReportGenerateController = new LecturerReportGenerateController(new LecturerReportGeneratePage(), PDFService.getInstance());
        return lecturerReportGenerateController;
    }


    public LecturerReportGeneratePage getLecturerReportGeneratePage() {
        return lecturerReportGeneratePage;
    }

}
