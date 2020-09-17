package com.moodyjun.Controller.Admin;

import com.moodyjun.Controller.Util.Format;
import com.moodyjun.Dao.Module.ModuleDao;
import com.moodyjun.Exception.IDFormatException;
import com.moodyjun.Exception.NullValueException;
import com.moodyjun.Model.Module.Module;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;
import com.moodyjun.Model.Util.ID;
import com.moodyjun.Services.Enroll.EnrollService;
import com.moodyjun.Services.User.AdminService;
import com.moodyjun.View.Admin.Enrollment.EnrollFormTemplate;
import com.moodyjun.View.Admin.Enrollment.EnrollMgmtPage;

import javax.swing.*;
import java.util.List;

public class EnrollmentController extends AdminTemplateController {

    private EnrollMgmtPage enrollMgmtPage;
    private EnrollService enrollService;
    private AdminService adminService;
    private static EnrollmentController enrollmentController;

    public EnrollmentController(EnrollMgmtPage enrollMgmtPage, EnrollService enrollService , AdminService adminService) {
        super(enrollMgmtPage);
        this.enrollMgmtPage = enrollMgmtPage;
        this.enrollService = enrollService;
        this.adminService = adminService;
        buttonActionInit(enrollMgmtPage);
    }

    private void buttonActionInit (EnrollMgmtPage enrollMgmtPage){
        enrollMgmtPage.getEnrollStudPanel().getSearchButton().addActionListener(e->searchAvailModule(enrollMgmtPage.getEnrollStudPanel(),2));
        enrollMgmtPage.getEnrollStudPanel().getDisenrollButton().addActionListener(e->disenrollModule(enrollMgmtPage.getEnrollStudPanel()));
        enrollMgmtPage.getEnrollStudPanel().getEnrollButton().addActionListener(e->enrollModule(enrollMgmtPage.getEnrollStudPanel()));
        enrollMgmtPage.getEnrollLecturerPanel().getSearchButton().addActionListener(e->searchAvailModule(enrollMgmtPage.getEnrollLecturerPanel(),1));
        enrollMgmtPage.getEnrollLecturerPanel().getDisenrollButton().addActionListener(e->disenrollModule(enrollMgmtPage.getEnrollLecturerPanel()));
        enrollMgmtPage.getEnrollLecturerPanel().getEnrollButton().addActionListener(e->enrollModule(enrollMgmtPage.getEnrollLecturerPanel()));
    }

    private void searchAvailModule(EnrollFormTemplate enrollForm,int rolePart){
        clearForm(enrollForm);
        String idString = JOptionPane.showInputDialog("Enter user ID :");
        if(idString==null) return;
        try{
            if(!Format.isIDValid(idString)) throw new IDFormatException(idString);
            User user ;
            user = AdminService.getInstance().getUserByID(ID.fromString(idString));
            if(user==null || user.getRole()!=rolePart){
                JOptionPane.showMessageDialog(enrollForm,"User does not exist.");
                return;
            }
            enrollForm.getIdField().setText(idString);
            if(rolePart==1){
                Lecturer lecturer = (Lecturer) user ;
                Object[] enrolledModuleID = lecturer.getModuleList().stream().map(module -> module.getModuleId()).toArray();
                if(enrolledModuleID.length>0) {
                    enrollForm.getEnrolledModuleList().setListData(enrolledModuleID);
                    enrollForm.getDisenrollButton().setEnabled(true);
                }
            }else if(rolePart==2){
                Student student = (Student) user;
                Object[] enrolledModuleID = student.getModuleList().stream().map(module -> module.getModuleId()).toArray();
                if(enrolledModuleID.length>0) {
                    enrollForm.getEnrolledModuleList().setListData(enrolledModuleID);
                    enrollForm.getDisenrollButton().setEnabled(true);
                }
            }
            List<Module> moduleList = EnrollService.getInstance().getAllAvailModuleByID(user.getId());
            Object[] availModuleID = moduleList.stream().map(module -> module.getModuleId()).toArray();
            if(availModuleID.length>0) {
                enrollForm.getEnrollModuleList().setListData(availModuleID);
                enrollForm.getEnrollButton().setEnabled(true);
            }
        } catch (IDFormatException e) {
            JOptionPane.showMessageDialog(enrollForm,e.toString());
        }
    }

    private void enrollModule(EnrollFormTemplate enrollForm ){

        User user = AdminService.getInstance().getUserByID(ID.fromString(enrollForm.getIdField().getText()));
        List<String> moduleIDList =  enrollForm.getEnrollModuleList().getSelectedValuesList();
        if (moduleIDList.size()==0) {JOptionPane.showMessageDialog(enrollForm,"Please select modules.");return;}

        if(user.getId().getRolePart().equals("TP")) {
            int result = enrollService.addModuleToStud(user.getId(),moduleIDList);
            if(result==0){
                JOptionPane.showMessageDialog(enrollForm,"Module is already full.");
                return;
            }else if (result ==-1){
                JOptionPane.showMessageDialog(enrollForm,"Student already enrolled to 7 module.");
                return;
            }
        }
        else if(user.getId().getRolePart().equals("LC")) {

            if(enrollService.addModuleToLec(user.getId(),moduleIDList)==0){
                JOptionPane.showMessageDialog(enrollForm,"Lecturer number is more than enough.");
                return;
            };
        }
        JOptionPane.showMessageDialog(enrollForm,"Enroll Successfully.");
        clearForm(enrollForm);
    }

    private void disenrollModule(EnrollFormTemplate enrollForm){

        User user = AdminService.getInstance().getUserByID(ID.fromString(enrollForm.getIdField().getText()));
        List<String> moduleIDList =  enrollForm.getEnrolledModuleList().getSelectedValuesList();
        if (moduleIDList.size()==0) {JOptionPane.showMessageDialog(enrollForm,"Please select modules.");return;}
        if(user.getId().getRolePart().equals("TP")) enrollService.removeModuleFromStud(user.getId(),moduleIDList);
        else if(user.getId().getRolePart().equals("LC")) {
            enrollService.removeModuleFromLec(user.getId(),moduleIDList);
        }
        JOptionPane.showMessageDialog(enrollForm,"Disenroll Successfully.");
        clearForm(enrollForm);
    }

    private void clearForm(EnrollFormTemplate enrollForm){
        enrollForm.getIdField().setText("");
        String[] nullData = {"Not module yet"};
        enrollForm.getEnrolledModuleList().setListData(nullData);
        enrollForm.getEnrollModuleList().setListData(nullData);
        enrollForm.getDisenrollButton().setEnabled(false);
        enrollForm.getEnrollButton().setEnabled(false);
    }

    public static EnrollmentController getInstance(){
        enrollmentController = new EnrollmentController(new EnrollMgmtPage(),EnrollService.getInstance(), AdminService.getInstance());
       return enrollmentController;
    }

    public EnrollMgmtPage getEnrollMgmtPage() {
        return enrollMgmtPage;
    }

    public EnrollService getEnrollService() {
        return enrollService;
    }

}
