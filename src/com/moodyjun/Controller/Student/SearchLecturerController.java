package com.moodyjun.Controller.Student;

import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Services.User.LecturerService;
import com.moodyjun.View.Student.SearchLecturerPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.List;
import java.util.stream.Collectors;

public class SearchLecturerController extends StudentTemplateController {

    private SearchLecturerPage searchLecturerPage;
    private LecturerService lecturerService;
    private static SearchLecturerController searchLecturerController;

    private SearchLecturerController(SearchLecturerPage searchLecturerPage , LecturerService lecturerService) {
        super(searchLecturerPage);
        this.searchLecturerPage = searchLecturerPage;
        this.lecturerService = lecturerService;
        studDataInit();
        searchButtonInit();
    }

    private void searchButtonInit(){
        searchLecturerPage.getSearchButton().addActionListener(e->studDataInit());
    }


    private void studDataInit(){
        List<Lecturer>  lecturerList = lecturerService.getAllUser() ;
        if(!searchLecturerPage.getNameField().getText().equals("")) {
            lecturerList = lecturerList.stream()
                    .filter(lecturer -> lecturer.getName().startsWith(searchLecturerPage.getNameField().getText()))
                    .collect(Collectors.toList());
        }
        String[] data = new String[3];
        DefaultTableModel model = new DefaultTableModel();
        searchLecturerPage.getLecTable().setModel(model);
        model.addColumn("NAME");
        model.addColumn("EMAIL");
        model.addColumn("PHONE NUMBER");
        setColumnWidth(searchLecturerPage.getLecTable());
        for(Lecturer lecturer : lecturerList) {
            data[0] = lecturer.getName();
            data[1] = lecturer.getEmail();
            data[2] = lecturer.getPhoneNum();
            model.insertRow(model.getRowCount(), data);
        }

    }

    private void setColumnWidth(JTable jtable){
        int[] columnsWidth = {100,200,200};
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = jtable.getColumnModel().getColumn(i++);

            column.setPreferredWidth(width);
        }
    }

    public SearchLecturerPage getSearchStudentPage() {
        return searchLecturerPage;
    }

    public static SearchLecturerController getInstance(){
        searchLecturerController = new SearchLecturerController(new SearchLecturerPage(),
                LecturerService.getInstance());
        return searchLecturerController;
    }
}

