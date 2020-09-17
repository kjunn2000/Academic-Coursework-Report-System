package com.moodyjun.Controller.Student;

import com.moodyjun.Model.Module.Class;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;
import com.moodyjun.Services.User.LecturerService;
import com.moodyjun.Services.User.StudentService;
import com.moodyjun.View.Student.StudentTimetablePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentTimetableController extends StudentTemplateController {

    private StudentTimetablePage studentTimetablePage;
    private StudentService studentService;
    private static StudentTimetableController studentTimetableController;

    public StudentTimetableController(StudentTimetablePage studentTimetablePage, StudentService studentService) {
        super(studentTimetablePage);
        this.studentTimetablePage = studentTimetablePage;
        this.studentService = studentService;
        timetableInit();
    }

    private void timetableInit(){
        Student student = (Student) user;
        List<Class> classList  = new ArrayList<>();
        student.getModuleList().forEach(module -> classList.addAll(module.getClassList()));
        classList.sort(Comparator.comparing(Class::getDateTime));
        int dayOfWeek = 1 ;
        String[] data = new String[5];
        for(JTable jtable : studentTimetablePage.getTimeTablePanel().getTimetableList()) {
           DefaultTableModel model = new DefaultTableModel();
           jtable.setModel(model);
            model.addColumn("Class Name");
            model.addColumn("Start Time");
            model.addColumn("End Time");
            model.addColumn("Duration");
            model.addColumn("Location");
            setColumnWidth(jtable);
            for(Class eachClass : classList){
                if(eachClass.getDateTime().get(ChronoField.DAY_OF_WEEK)==dayOfWeek){
                    data[0] = eachClass.getClassType();
                    data[1] = String.format("%02d",eachClass.getDateTime().getHour())
                            +":"+String.format("%02d",eachClass.getDateTime().getMinute());
                    data[2] = String.format("%02d",(eachClass.getDateTime().plusMinutes(eachClass.getDuration())).getHour()) +":"
                            +String.format("%02d",(eachClass.getDateTime().plusMinutes(eachClass.getDuration())).getMinute());
                    data[3] = String.valueOf(eachClass.getDuration());
                    data[4] = eachClass.getLocation();
                    model.insertRow(model.getRowCount(), data);
                }
            }

           if(jtable.getRowCount()==0){
               jtable.removeAll();
               data[0] = "Not classes Today";
               data[1] = "-";
               data[2] = "-";
               data[3] = "-";
               data[4] = "-";
               model.insertRow(model.getRowCount(), data);
           }
           
           dayOfWeek++;
        }

    }

    private void setColumnWidth(JTable jtable){
        int[] columnsWidth = {200,100,100,100};
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = jtable.getColumnModel().getColumn(i++);

            column.setPreferredWidth(width);
        }
    }



    public static StudentTimetableController getInstance(){
        studentTimetableController = new StudentTimetableController(new StudentTimetablePage() , StudentService.getInstance());
        return studentTimetableController;
    }

    public StudentTimetablePage getStudentTimetablePage() {
        return studentTimetablePage;
    }
}
