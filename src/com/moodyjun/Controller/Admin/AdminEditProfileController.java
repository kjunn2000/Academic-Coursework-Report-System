package com.moodyjun.Controller.Admin;

import com.moodyjun.Controller.LogInController;
import com.moodyjun.Controller.Util.Format;
import com.moodyjun.Exception.EmailFormatException;
import com.moodyjun.Exception.InvalidAgeException;
import com.moodyjun.Exception.NullValueException;
import com.moodyjun.Exception.PhoneFormatException;
import com.moodyjun.Model.User.Admin;
import com.moodyjun.Model.User.Gender;
import com.moodyjun.Model.User.Student;
import com.moodyjun.Model.User.User;
import com.moodyjun.Services.System.LogService;
import com.moodyjun.Services.User.AdminService;
import com.moodyjun.View.Admin.AdminEditProfilePage;

import javax.swing.*;

public class AdminEditProfileController extends AdminTemplateController {
    private static AdminEditProfileController editProfileController;
    private AdminEditProfilePage  editProfilePage;
    private AdminService adminService;

    private AdminEditProfileController(AdminEditProfilePage editProfilePage , AdminService adminServices){
        super(editProfilePage);
        this.editProfilePage = editProfilePage;
        this.adminService = adminServices;
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
        try {
            String userName = editProfilePage.getEditProfileForm().getUserNameField().getText();
            if (userName.equals("")) throw new NullValueException();
            String password = String.valueOf(editProfilePage.getEditProfileForm().getPasswordField().getPassword());
            if (password.equals("")) throw new NullValueException();
            String confirmPass = String.valueOf(editProfilePage.getEditProfileForm().getConfirmPasswordField().getPassword());
            if (!password.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this.editProfilePage, "Password and confirm password are not same.");
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
            User newUser = new Admin(user.getId(), user.getRole(), userName, password, user.getGender(), age ,email, phoneNum);
            int result = adminService.updateProfile(newUser);
            if(result == 1) {
                JOptionPane.showMessageDialog(this.editProfilePage,"Update Successfully. Please Log In again");
                LogService.getInstance().logOut();
                this.getEditProfilePage().setVisible(false);
                LogInController.getInstance().getLogInPage().setVisible(true);
            }
            else if(result == 0) {
                JOptionPane.showMessageDialog(this.editProfilePage,"Username exist.");
            }
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

    public AdminEditProfilePage getEditProfilePage() {
        return editProfilePage;
    }

    public static AdminEditProfileController getInstance(){
        editProfileController = new AdminEditProfileController(new AdminEditProfilePage(), AdminService.getInstance());
        return editProfileController;
    }
}
