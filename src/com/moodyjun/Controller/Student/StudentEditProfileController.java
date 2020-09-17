package com.moodyjun.Controller.Student;

import com.moodyjun.Controller.LogInController;
import com.moodyjun.Controller.Util.Format;
import com.moodyjun.Exception.EmailFormatException;
import com.moodyjun.Exception.InvalidAgeException;
import com.moodyjun.Exception.NullValueException;
import com.moodyjun.Exception.PhoneFormatException;
import com.moodyjun.Model.User.Gender;
import com.moodyjun.Model.User.Lecturer;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;
import com.moodyjun.Services.System.LogService;
import com.moodyjun.Services.User.AdminService;
import com.moodyjun.Services.User.StudentService;
import com.moodyjun.View.Admin.AdminEditProfilePage;
import com.moodyjun.View.Student.StudentEditProfilePage;

import javax.swing.*;

public class StudentEditProfileController extends StudentTemplateController {
    private static StudentEditProfileController editProfileController;
    private StudentEditProfilePage editProfilePage;
    private StudentService studentService;

    private StudentEditProfileController(StudentEditProfilePage editProfilePage , StudentService studentService){
        super(editProfilePage);
        this.editProfilePage = editProfilePage;
        this.studentService = studentService;
        userInformationInit();
        actionButtonInit();
    }

    private void userInformationInit(){
        editProfilePage.getIdField().setText(user.getId().toString());
        editProfilePage.getEditProfileForm().getUserNameField().setText(user.getName());
        editProfilePage.getEditProfileForm().getPasswordField().setText(user.getPassword());
        editProfilePage.getEditProfileForm().getConfirmPasswordField().setText(user.getPassword());
        editProfilePage.getEditProfileForm().getEmailField().setText(user.getEmail());
        editProfilePage.getEditProfileForm().getPhoneNumField().setText(user.getPhoneNum());
        editProfilePage.getEditProfileForm().getLevelBox().setSelectedItem(((Student) user).getLevel());
        if(user.getRole()==2) {
            editProfilePage.getEditProfileForm().getIntakeCodeField().setText(((Student) user).getIntakeCode());
        }
        editProfilePage.getEditProfileForm().getAgeField().setText(Integer.toString(user.getAge()));
        if(user.getGender()== Gender.MALE) {
            editProfilePage.getEditProfileForm().getMaleRadioButton().setSelected(true);
            editProfilePage.getEditProfileForm().getMaleRadioButton().setVisible(true);
        }
        else editProfilePage.getEditProfileForm().getFemaleRadioButton().setSelected(true);
    }

    private void actionButtonInit(){
        editProfilePage.getEditProfileForm().getActionButton().addActionListener(e->updateProfile());
    }

    private void updateProfile(){
        try{
            String userName = editProfilePage.getEditProfileForm().getUserNameField().getText();
            if (userName.equals("")) throw new NullValueException();
            String password = String.valueOf(editProfilePage.getEditProfileForm().getPasswordField().getPassword());
            if (password.equals("")) throw new NullValueException();
            String confirmPass = String.valueOf(editProfilePage.getEditProfileForm().getConfirmPasswordField().getPassword());
            if(!password.equals(confirmPass)){
                JOptionPane.showMessageDialog(this.editProfilePage,"Password and confirm password are not same.");
                return;
            }
            String ageString = editProfilePage.getEditProfileForm().getAgeField().getText();
            if(ageString.equals("")) throw new NullValueException();
            int age = Integer.parseInt(ageString);
            if(age>60||age<15) throw new InvalidAgeException(age);
            String email = editProfilePage.getEditProfileForm().getEmailField().getText();
            if(! Format.isEmailValid(email)) throw new EmailFormatException(email);
            String phoneNum = editProfilePage.getEditProfileForm().getPhoneNumField().getText();
            if(! Format.isPhoneNumValid(phoneNum)) throw new PhoneFormatException(phoneNum);
            Student student = (Student) user;
            User newUser = new Student(student.getId() , student.getRole(),userName,password,student.getGender(),age,email,phoneNum,student.getLevel(),student.getIntakeCode(),student.getModuleList(),student.getResults());
            if(studentService.updateProfile(newUser)==1) {
                JOptionPane.showMessageDialog(this.editProfilePage,"Update Successfully. Please Log In again");
                LogService.getInstance().logOut();
                this.getEditProfilePage().setVisible(false);
                LogInController.getInstance().getLogInPage().setVisible(true);
            }
            else JOptionPane.showMessageDialog(this.editProfilePage,"Username exist.");
        }catch(InvalidAgeException e) {
            JOptionPane.showMessageDialog(editProfilePage,e.toString());
        }catch(NullValueException e){
            JOptionPane.showMessageDialog(editProfilePage,e.toString());
        } catch (EmailFormatException e) {
            JOptionPane.showMessageDialog(editProfilePage,e.toString());
        } catch (PhoneFormatException e) {
            JOptionPane.showMessageDialog(editProfilePage,e.toString());
        }
    }

    public StudentEditProfilePage getEditProfilePage() {
        return editProfilePage;
    }

    public static StudentEditProfileController getInstance(){
        editProfileController = new StudentEditProfileController(new StudentEditProfilePage(), StudentService.getInstance());
        return editProfileController;
    }
}
