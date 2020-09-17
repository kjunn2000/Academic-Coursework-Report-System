package com.moodyjun.Controller.Lecturer;

import com.moodyjun.Model.Module.Class;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Services.User.LecturerService;
import com.moodyjun.View.Lecturer.LecturerTimetablePage;
import com.moodyjun.View.TemplatePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LecturerTimetableController extends LecturerTemplateController{

    private LecturerTimetablePage lecturerTimetablePage;
    private LecturerService lecturerService;
    private static LecturerTimetableController lecturerTimetableController;

    public LecturerTimetableController(LecturerTimetablePage lecturerTimetablePage, LecturerService lecturerService) {
        super(lecturerTimetablePage);
        this.lecturerTimetablePage = lecturerTimetablePage;
        this.lecturerService = lecturerService;
        timetableInit();
    }

    private void timetableInit(){
        Lecturer lecturer = (Lecturer) user;
        List<Class> classList  = new ArrayList<>();
        lecturer.getModuleList().forEach(module -> classList.addAll(module.getClassList()));
        classList.sort(Comparator.comparing(Class::getDateTime));
        int dayOfWeek = 1 ;
        String[] data = new String[5];
        for(JTable jtable : lecturerTimetablePage.getTimeTablePanel().getTimetableList()) {
           DefaultTableModel model = new DefaultTableModel();
           jtable.setModel(model);
            model.addColumn("Class Name");
            model.addColumn("Start Time");
            model.addColumn("End Time");
            model.addColumn("Location");
            setColumnWidth(jtable);
            for(Class eachClass : classList){
                if(eachClass.getDateTime().get(ChronoField.DAY_OF_WEEK)==dayOfWeek){
                    data[0] = eachClass.getClassType();
                    data[1] = String.format("%02d",eachClass.getDateTime().getHour()) +":"+String.format("%02d",eachClass.getDateTime().getMinute());
                    data[2] = String.format("%02d",(eachClass.getDateTime().plusMinutes(eachClass.getDuration())).getHour()) +":"
                            +String.format("%02d",(eachClass.getDateTime().plusMinutes(eachClass.getDuration())).getMinute());
                    data[3] = eachClass.getLocation();
                    model.insertRow(model.getRowCount(), data);
                }
            }

           if(jtable.getRowCount()==0){
               jtable.removeAll();
               data[0] = "Not classes Today";
               data[1] = "-";
               data[2] = "-";
               data[3] = "-";
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



    public static LecturerTimetableController getInstance(){
        lecturerTimetableController = new LecturerTimetableController(new LecturerTimetablePage() , LecturerService.getInstance());
        return lecturerTimetableController;
    }

    public LecturerTimetablePage getLecturerTimetablePage() {
        return lecturerTimetablePage;
    }
}
