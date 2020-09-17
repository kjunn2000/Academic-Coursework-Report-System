package com.moodyjun.Controller.Lecturer;

import com.moodyjun.Model.User.Gender;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Services.User.StudentService;
import com.moodyjun.View.Lecturer.SearchStudentPage;
import com.moodyjun.View.TemplatePage;
import com.sun.source.doctree.SummaryTree;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class SearchStudentController extends LecturerTemplateController{

    private SearchStudentPage searchStudentPage;
    private StudentService studentService;
    private static SearchStudentController searchStudentController;

    private SearchStudentController(SearchStudentPage searchStudentPage , StudentService studentService) {
        super(searchStudentPage);
        this.searchStudentPage = searchStudentPage;
        this.studentService = studentService;
        studDataInit();
        searchButtonInit();
    }

    private void studDataInit(){
        List<Student>  studList = studentService.getAllStudent();
        if(!searchStudentPage.getIdField().getText().equals("")) {
            studList = studList.stream()
                    .filter(student -> student.getId().toString().startsWith(searchStudentPage.getIdField().getText()))
                    .collect(Collectors.toList());
        }
        String[] data = new String[5];
        DefaultTableModel model = new DefaultTableModel();
        searchStudentPage.getStudTable().setModel(model);
        model.addColumn("TP");
        model.addColumn("NAME");
        model.addColumn("GENDER");
        model.addColumn("AGE");
        model.addColumn("INTAKE");
        for(Student student : studList) {
            data[0] = student.getId().toString();
            data[1] = student.getName();
            data[2] = student.getGender().equals(Gender.MALE) ? "Male" : "Female";
            data[3] = Integer.toString(student.getAge());
            data[4] = student.getIntakeCode();
            model.insertRow(model.getRowCount(), data);
        }

    }

    private void searchButtonInit(){
        searchStudentPage.getSearchButton().addActionListener(e->studDataInit());
    }


    public SearchStudentPage getSearchStudentPage() {
        return searchStudentPage;
    }

    public static SearchStudentController getInstance(){
        searchStudentController = new SearchStudentController(new SearchStudentPage(),
                StudentService.getInstance());
        return searchStudentController;
    }
}

