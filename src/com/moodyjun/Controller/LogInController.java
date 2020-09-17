package com.moodyjun.Controller;

import com.moodyjun.Controller.Admin.AdminHomePageController;
import com.moodyjun.Controller.Lecturer.LecturerHomePageController;
import com.moodyjun.Controller.Student.StudentHomePageController;
import com.moodyjun.Model.User.User;
import com.moodyjun.Services.System.LogService;
import com.moodyjun.View.LogInPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController implements ActionListener {

    private static LogInController logInController;
    private Thread logOutThread;
    private LogInPage logInPage ;
    private LogService logService ;

    public LogInController(LogInPage logInPage , LogService logService){
        this.logInPage = logInPage;
        this.logService = logService;
        initActionListener();
    }

    private void initActionListener (){
        logInPage.getLogInButton().addActionListener(this);
        logInPage.getRootPane().setDefaultButton(logInPage.getLogInButton());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Hi");
        String userName = logInPage.getUserNameField().getText();
        String password = String.valueOf(logInPage.getPasswordField().getPassword());
        User user = logService.logIn(userName , password);
        if(user != null){
            this.logInPage.setVisible(false);

            clearLogInForm();
            switch (user.getRole()) {
                case 0 -> AdminHomePageController.getInstance().getAdminHomePage().setVisible(true);
                case 1 -> LecturerHomePageController.getInstance().getLecturerHomePage().setVisible(true);
                case 2 -> StudentHomePageController.getInstance().getStudentHomePage().setVisible(true);

            }
            logOutThread = new Thread(() -> logService.logOut());
            Runtime.getRuntime().addShutdownHook(logOutThread);
        }else {
            JOptionPane.showMessageDialog(logInPage,"Invalid username or password.");
        }
    }

    public void clearLogInForm(){
        logInPage.getUserNameField().setText("");
        logInPage.getPasswordField().setText("");
    }

    public LogInPage getLogInPage() {
        return logInPage;
    }

    public Thread getLogOutThread() {
        return logOutThread;
    }

    public static LogInController getInstance(){
        if (logInController == null) {
            synchronized (LogInController.class){
                logInController = new LogInController(new LogInPage(), LogService.getInstance());
            }
        }
        return logInController;
    }
}