package com.moodyjun.View.Admin.ModuleMgmt;

import com.moodyjun.View.Admin.AdminTemplatePage;

import javax.swing.*;

public class ModuleMgmtPage extends AdminTemplatePage {

    private final CreateModulePanel createModulePanel ;
    private final UpdateModulePanel updateModulePanel ;
    private final DeleteModulePanel deleteModulePanel ;

    public ModuleMgmtPage(){
        createModulePanel = new CreateModulePanel();
        updateModulePanel = new UpdateModulePanel();
        deleteModulePanel = new DeleteModulePanel();
        userMgmtTabInit();
    }

    private void userMgmtTabInit(){
        getTabbedPane().add( new JScrollPane(createModulePanel),"Create Module" );
        getTabbedPane().add( new JScrollPane(updateModulePanel),"Modify Module"  );
        getTabbedPane().add( new JScrollPane(deleteModulePanel),"Delete Module"  );
    }

    public CreateModulePanel getCreateModulePanel() {
        return createModulePanel;
    }

    public UpdateModulePanel getUpdateModulePanel() {
        return updateModulePanel;
    }

    public DeleteModulePanel getDeleteModulePanel() {
        return deleteModulePanel;
    }
}
