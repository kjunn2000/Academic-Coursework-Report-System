package com.moodyjun.View.Admin.UserMgmt;

import com.moodyjun.View.Admin.AdminTemplatePage;

import javax.swing.*;

public class UserMgmtPage extends AdminTemplatePage {

    private CreateUserPanel createUserPanel ;
    private UpdateUserPanel updateUserPanel;
    private DeleteUserPanel deleteUserPanel ;

    public UserMgmtPage(){
        createUserPanel = new CreateUserPanel();
        updateUserPanel = new UpdateUserPanel();
        deleteUserPanel = new DeleteUserPanel();
        userMgmtTabInit();
    }

    private void userMgmtTabInit(){
        getTabbedPane().add( new JScrollPane(createUserPanel),"Create User" );
        getTabbedPane().add( new JScrollPane(updateUserPanel),"Modify User"  );
        getTabbedPane().add( new JScrollPane(deleteUserPanel),"Delete User"  );
    }

    public CreateUserPanel getCreateUserPanel() {
        return createUserPanel;
    }

    public UpdateUserPanel getUpdateUserPanel() {
        return updateUserPanel;
    }

    public DeleteUserPanel getDeleteUserPanel() {
        return deleteUserPanel;
    }



}
