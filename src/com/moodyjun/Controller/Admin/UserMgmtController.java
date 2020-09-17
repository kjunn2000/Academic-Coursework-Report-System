package com.moodyjun.Controller.Admin;

import com.moodyjun.Controller.Util.Format;
import com.moodyjun.Exception.*;
import com.moodyjun.Model.User.*;
import com.moodyjun.Model.Util.ID;
import com.moodyjun.Services.User.AdminService;
import com.moodyjun.View.Admin.UserMgmt.*;
import com.moodyjun.View.Util.UserFormTemplatePanel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class UserMgmtController extends AdminTemplateController implements ItemListener{

    private UserMgmtPage userMgmtPage;
    private AdminService adminService ;
    private static UserMgmtController userMgmtPageController;

    public UserMgmtController(UserMgmtPage userMgmtPage , AdminService adminService) {
        super(userMgmtPage);
        this.userMgmtPage = userMgmtPage ;
        this.adminService = adminService ;
        userSelectedItemListenerInit();
        buttonActionListenerInit();
    }

    private void userSelectedItemListenerInit(){
        this.userMgmtPage.getCreateUserPanel().getAdminRadioButton().addItemListener(this );
        this.userMgmtPage.getCreateUserPanel().getLecturerRadioButton().addItemListener(    this);
        this.userMgmtPage.getCreateUserPanel().getStudentRadioButton().addItemListener(this);
        this.userMgmtPage.getUpdateUserPanel().getAdminRadioButton().addItemListener(this);
        this.userMgmtPage.getUpdateUserPanel().getLecturerRadioButton().addItemListener(this);
        this.userMgmtPage.getUpdateUserPanel().getStudentRadioButton().addItemListener(this);
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==userMgmtPage.getCreateUserPanel().getStudentRadioButton()){
            this.userMgmtPage.getCreateUserPanel().getLevelLabel().setVisible(true);
            this.userMgmtPage.getCreateUserPanel().getLevelBox().setVisible(true);
            this.userMgmtPage.getCreateUserPanel().getIntakeCodeLabel().setVisible(true);
            this.userMgmtPage.getCreateUserPanel().getIntakeCodeField().setVisible(true);
        }else if(e.getSource()==userMgmtPage.getCreateUserPanel().getAdminRadioButton()||
                e.getSource()==userMgmtPage.getCreateUserPanel().getLecturerRadioButton()) {
            this.userMgmtPage.getCreateUserPanel().getLevelLabel().setVisible(false);
            this.userMgmtPage.getCreateUserPanel().getLevelBox().setVisible(false);
            this.userMgmtPage.getCreateUserPanel().getIntakeCodeLabel().setVisible(false);
            this.userMgmtPage.getCreateUserPanel().getIntakeCodeField().setVisible(false);
        }else if(e.getSource()==userMgmtPage.getUpdateUserPanel().getStudentRadioButton()){
            this.userMgmtPage.getUpdateUserPanel().getLevelLabel().setVisible(true);
            this.userMgmtPage.getUpdateUserPanel().getLevelBox().setVisible(true);
            this.userMgmtPage.getUpdateUserPanel().getIntakeCodeLabel().setVisible(true);
            this.userMgmtPage.getUpdateUserPanel().getIntakeCodeField().setVisible(true);
        }else if(e.getSource()==userMgmtPage.getUpdateUserPanel().getAdminRadioButton()||
                e.getSource()==userMgmtPage.getUpdateUserPanel().getLecturerRadioButton()) {
            this.userMgmtPage.getUpdateUserPanel().getLevelLabel().setVisible(false);
            this.userMgmtPage.getUpdateUserPanel().getLevelBox().setVisible(false);
            this.userMgmtPage.getUpdateUserPanel().getIntakeCodeLabel().setVisible(false);
            this.userMgmtPage.getUpdateUserPanel().getIntakeCodeField().setVisible(false);
        }
    }

    private void buttonActionListenerInit(){
        userMgmtPage.getCreateUserPanel().getActionButton().addActionListener(e -> createUser(userMgmtPage.getCreateUserPanel()));
        userMgmtPage.getUpdateUserPanel().getActionButton().addActionListener(e -> updateUser(userMgmtPage.getUpdateUserPanel()));
        userMgmtPage.getUpdateUserPanel().getFindUserButton().addActionListener(e -> findUser(userMgmtPage.getUpdateUserPanel()));
        userMgmtPage.getDeleteUserPanel().getDeleteUserButton().addActionListener(e -> deleteUser(userMgmtPage.getDeleteUserPanel()));
    }

    private void createUser(CreateUserPanel createUserPanel){
        try{
            int role = createUserPanel.getAdminRadioButton().isSelected() ? 0 :
                    (createUserPanel.getLecturerRadioButton().isSelected() ? 1 : 2 );
            String name = createUserPanel.getUserNameField().getText();
            if(name.equals("")) throw new NullValueException();
            String password = String.valueOf(createUserPanel.getPasswordField().getPassword());
            if(password.equals("")) throw new NullValueException();
            if(!password.equals(String.valueOf(createUserPanel.getConfirmPasswordField().getPassword()))){
                JOptionPane.showMessageDialog(createUserPanel,"Password and confirm password are not same.");
                return;
            }
            String ageString = createUserPanel.getAgeField().getText();
            if(ageString.equals("")) throw new NullValueException();
            int age = Integer.parseInt(ageString);
            if(age>60||age<15) throw new InvalidAgeException(age);
            String email = createUserPanel.getEmailField().getText();
            if(! Format.isEmailValid(email)) throw new EmailFormatException(email);
            String phoneNum = createUserPanel.getPhoneNumField().getText();
            if(! Format.isPhoneNumValid(phoneNum)) throw new PhoneFormatException(phoneNum);
            Gender gender = createUserPanel.getMaleRadioButton().isSelected() ? Gender.MALE : Gender.FEMALE;
            int level = Integer.parseInt(createUserPanel.getLevelBox().getSelectedItem().toString());
            String intakeCode = createUserPanel.getIntakeCodeField().getText();
            if(role==2 && intakeCode.equals("")) throw new NullValueException();
            User user = switch (role) {
                case 0 -> new Admin(null, role, name, password, gender, age, email, phoneNum);
                case 1 -> new Lecturer(null, role, name, password, gender, age,email, phoneNum, new ArrayList<>());
                case 2 -> new Student(null, role, name, password, gender, age,email , phoneNum,level, intakeCode);
                default -> null;
            };
            if(adminService.createUser(user)==1){
                JOptionPane.showMessageDialog(createUserPanel,"User Create Successfully.");
                clearFormValue(createUserPanel);
            }else{
                JOptionPane.showMessageDialog(createUserPanel,"Username Exist.");
            }
        }catch(InvalidAgeException e) {
            JOptionPane.showMessageDialog(createUserPanel,e.toString());
        }catch(NullValueException e){
            JOptionPane.showMessageDialog(createUserPanel,e.toString());
        } catch (EmailFormatException e) {
            JOptionPane.showMessageDialog(createUserPanel,e.toString());
        } catch (PhoneFormatException e) {
            JOptionPane.showMessageDialog(createUserPanel,e.toString());
        }
    }

    private void updateUser(UpdateUserPanel updateUserPanel) {
        try{
            String idString = updateUserPanel.getIdField().getText();
            if (! Format.isIDValid(idString)) throw new IDFormatException(idString);
            ID userID = ID.fromString(idString);
            int role = userID.getRolePart().equals("AD") ? 0 : (userID.getRolePart().equals("LC") ? 1 : 2);
            String name = updateUserPanel.getUserNameField().getText();
            if(name.equals("")) throw new NullValueException();
            String password = String.valueOf(updateUserPanel.getPasswordField().getPassword());
            if(password.equals("")) throw new NullValueException();
            if(!password.equals(String.valueOf(updateUserPanel.getConfirmPasswordField().getPassword()))){
                JOptionPane.showMessageDialog(updateUserPanel,"Password and confirm password are not same.");
                return;
            }
            int age = Integer.parseInt(updateUserPanel.getAgeField().getText());
            if(age>60||age<15) throw new InvalidAgeException(age);
            String email = updateUserPanel.getEmailField().getText();
            if(! Format.isEmailValid(email)) throw new EmailFormatException(email);
            String phoneNum = updateUserPanel.getPhoneNumField().getText();
            if(! Format.isPhoneNumValid(phoneNum)) throw new PhoneFormatException(phoneNum);
            Gender gender = updateUserPanel.getMaleRadioButton().isSelected() ? Gender.MALE : Gender.FEMALE;
            int level = Integer.parseInt(updateUserPanel.getLevelBox().getSelectedItem().toString());
            String intakeCode = updateUserPanel.getIntakeCodeField().getText();
            if(role==2 && intakeCode.equals("")) { JOptionPane.showMessageDialog(updateUserPanel,"Please enter intake code.");return; }
            User user = switch (role) {
                case 0 -> new Admin(userID, role, name, password, gender, age, email, phoneNum);
                case 1 -> new Lecturer(userID, role, name, password, gender, age, email, phoneNum, new ArrayList<>());
                case 2 -> new Student(userID, role, name, password, gender, age, email, phoneNum, level, intakeCode);
                default -> null;
            };
            if(adminService.updateProfile(user)==1){
                JOptionPane.showMessageDialog(updateUserPanel,"User Update Successfully.");
                updateUserPanel.getIdField().setText("");
                clearFormValue(updateUserPanel);
            }else if(adminService.updateProfile(user)==-1) {
                JOptionPane.showMessageDialog(updateUserPanel,"User not exist.");
            }
            else{
                JOptionPane.showMessageDialog(updateUserPanel,"Username Exist.");
            }
        }catch(InvalidAgeException e) {
            JOptionPane.showMessageDialog(updateUserPanel,e.toString());
        }catch(NullValueException e){
            JOptionPane.showMessageDialog(updateUserPanel,e.toString());
        } catch (EmailFormatException e) {
            JOptionPane.showMessageDialog(updateUserPanel,e.toString());
        } catch (PhoneFormatException e) {
            JOptionPane.showMessageDialog(updateUserPanel,e.toString());
        } catch (IDFormatException e) {
            JOptionPane.showMessageDialog(updateUserPanel,e.toString());
        }
    }

    private void findUser(UpdateUserPanel updateUserPanel) {
        clearFormValue(updateUserPanel);
        String userIDString = updateUserPanel.getIdField().getText();
        try{
            if(!Format.isIDValid(userIDString)) throw new IDFormatException(userIDString);
        } catch (IDFormatException e) {
            JOptionPane.showMessageDialog(updateUserPanel,e.toString());
            return;
        }
        ID userID = ID.fromString(userIDString);
        User user = adminService.getUserByID(userID);
        if (user==null){ JOptionPane.showMessageDialog(updateUserPanel,"User ID does not exist"); return;}
        switch(user.getRole()){
            case 0 ->updateUserPanel.getAdminRadioButton().setSelected(true);
            case 1 ->updateUserPanel.getLecturerRadioButton().setSelected(true);
            case 2 ->updateUserPanel.getStudentRadioButton().setSelected(true);
        }
        updateUserPanel.getUserNameField().setText(user.getName());
        updateUserPanel.getAgeField().setText(String.valueOf(user.getAge()));
        updateUserPanel.getEmailField().setText(user.getEmail());
        updateUserPanel.getPhoneNumField().setText(user.getPhoneNum());
        if(user.getRole()==2) {
            updateUserPanel.getLevelBox().setSelectedItem(((Student) user).getLevel());
            updateUserPanel.getIntakeCodeField().setText(((Student) user).getIntakeCode());
        }
        if (user.getGender()==Gender.MALE) updateUserPanel.getMaleRadioButton().setSelected(true);
        else updateUserPanel.getFemaleRadioButton().setSelected(true);
    }

    private void deleteUser(DeleteUserPanel deleteUserPanel) {
        int option = JOptionPane.showConfirmDialog(deleteUserPanel,"Do you sure delete this user?");
        if(option == 1) return ;
        try{
            String idString = deleteUserPanel.getIdField().getText();
            if (! Format.isIDValid(idString)) throw new IDFormatException(idString);
            ID id = ID.fromString(idString);
            if(adminService.deleteUser(id)==1){
                JOptionPane.showMessageDialog(deleteUserPanel,"Delete Successfully.");
                deleteUserPanel.getIdField().setText("");
            }else{
                JOptionPane.showMessageDialog(deleteUserPanel,"User does not exist.");
            }
        } catch (IDFormatException e) {
            JOptionPane.showMessageDialog(deleteUserPanel,e.toString());
        }
    }

    public void clearFormValue(UserFormTemplatePanel userFormTemplatePanel){
        userFormTemplatePanel.getUserNameField().setText("");
        userFormTemplatePanel.getPasswordField().setText("");
        userFormTemplatePanel.getConfirmPasswordField().setText("");
        userFormTemplatePanel.getAgeField().setText("0");
        userFormTemplatePanel.getEmailField().setText("");
        userFormTemplatePanel.getPhoneNumField().setText("");
        userFormTemplatePanel.getLevelBox().setSelectedIndex(0);
        userFormTemplatePanel.getIntakeCodeField().setText("");
        userFormTemplatePanel.getMaleRadioButton().setSelected(true);
    }

    public static UserMgmtController getInstance(){
        userMgmtPageController = new UserMgmtController(new UserMgmtPage(), AdminService.getInstance());
        return userMgmtPageController;
    }

    public UserMgmtPage getUserMgmtPage() {
        return userMgmtPage;
    }
}
