package com.moodyjun.Controller.Lecturer;

import com.moodyjun.Controller.LogInController;
import com.moodyjun.Controller.Util.Format;
import com.moodyjun.Exception.EmailFormatException;
import com.moodyjun.Exception.InvalidAgeException;
import com.moodyjun.Exception.NullValueException;
import com.moodyjun.Exception.PhoneFormatException;
import com.moodyjun.Model.User.*;
import com.moodyjun.Services.System.LogService;
import com.moodyjun.Services.User.LecturerService;
import com.moodyjun.View.Lecturer.LecturerEditProfilePage;

import javax.swing.*;

public class LecturerEditProfileController extends LecturerTemplateController {
    private static LecturerEditProfileController editProfileController;
    private LecturerEditProfilePage  editProfilePage;
    private LecturerService lecturerService;

    private LecturerEditProfileController(LecturerEditProfilePage editProfilePage , LecturerService lecturerService){
        super(editProfilePage);
        this.editProfilePage = editProfilePage;
        this.lecturerService = lecturerService;
        userInformationInit();
        actionButtonInit();
    }

    private void userInformationInit(){
        editProfilePage.getIdField().setText(user.getId().toString());
        editProfilePage.getEditProfileForm().getUserNameField().setText(user.getName());
        editProfilePage.getEditProfileForm().getPasswordField().setText(user.getPassword());
        editProfilePage.getEditProfileForm().getConfirmPasswordField().setText(user.getPassword());
        if(user.getRole()==2) {
            editProfilePage.getEditProfileForm().getIntakeCodeField().setText(((Student) user).getIntakeCode());
        }
        editProfilePage.getEditProfileForm().getAgeField().setText(Integer.toString(user.getAge()));
        editProfilePage.getEditProfileForm().getEmailField().setText(user.getEmail());
        editProfilePage.getEditProfileForm().getPhoneNumField().setText(user.getPhoneNum());
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
            Lecturer lecturer = (Lecturer) user;
            User newUser = new Lecturer(lecturer.getId() , lecturer.getRole(),userName,password,lecturer.getGender(),age,email,phoneNum,lecturer.getModuleList());
            if(lecturerService.updateProfile(newUser)==1) {
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

    public LecturerEditProfilePage getEditProfilePage() {
        return editProfilePage;
    }

    public static LecturerEditProfileController getInstance(){
        editProfileController = new LecturerEditProfileController(new LecturerEditProfilePage(), LecturerService.getInstance());
        return editProfileController;
    }
}
