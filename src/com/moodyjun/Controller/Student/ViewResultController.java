package com.moodyjun.Controller.Student;

import com.moodyjun.Model.Module.Result;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Services.User.StudentService;
import com.moodyjun.View.Student.ViewResultPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ViewResultController extends StudentTemplateController {
    private static ViewResultController viewResultController;
    private ViewResultPage viewResultPage;
    private StudentService studentService;

    private ViewResultController(ViewResultPage viewResultPage ,StudentService studentService){
        super(viewResultPage);
        this.viewResultPage = viewResultPage;
        this.studentService = studentService;
        resultInit();
    }

    private void resultInit(){
        DefaultTableModel model = (DefaultTableModel) viewResultPage.getResultTable().getModel();
        model.addColumn("Module Name");
        model.addColumn("Test");
        model.addColumn("Exam");
        model.addColumn("Assignment");
        model.addColumn("Total");
        model.addColumn("Comment");
        Student student = (Student) user;
        setColumnWidth(viewResultPage.getResultTable());
        String[] data = new String[6];
        for(Result result : student.getResults().values()) {
            data[0] = result.getModule().getModuleName();
            data[1] = result.getTestMark()+"/"+result.getModule().getTestMarkPct();
            data[2] = result.getExamMark()+"/"+result.getModule().getExamMarkPct();
            data[3] = result.getAssignmentMark()+"/"+result.getModule().getAssignmentMarkPct();
            data[4] = result.getTotalMark()+"%";
            data[5] = result.getComment();
            model.insertRow(model.getRowCount(), data);
            addEmptyRow(model);
        }
        if(student.getResults().values().size()==0) {
            data[0] = "Not result available";
            data[1] = "-";
            data[2] = "-";
            data[3] = "-";
            data[4] = "-";
            data[5] = "-";
            model.insertRow(model.getRowCount(), data);
        }

    }

    private void addEmptyRow(DefaultTableModel model){
        model.insertRow(model.getRowCount(),new String[6]);
    }

    private void setColumnWidth(JTable jtable){
        int[] columnsWidth = {450,80,80,80,100,200};
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = jtable.getColumnModel().getColumn(i++);
            column.setPreferredWidth(width);
        }
    }

    public static ViewResultController getInstance(){
        viewResultController = new ViewResultController(new ViewResultPage(),StudentService.getInstance());
        return viewResultController;
    }

    public ViewResultPage getViewResultPage() {
        return viewResultPage;
    }
}
