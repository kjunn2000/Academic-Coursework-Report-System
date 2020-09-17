package com.moodyjun.View.Lecturer.KeyResult;

import com.moodyjun.View.Lecturer.LecturerTemplatePage;

import javax.swing.*;

public class KeyResultPage extends LecturerTemplatePage {

    private KeyResultPanel keyResultPanel;

    public KeyResultPage(){

        keyResultPanel = new KeyResultPanel();

        getTabbedPane().add(new JScrollPane(keyResultPanel),"Key Result");
    }

    public KeyResultPanel getKeyResultPanel() {
        return keyResultPanel;
    }


}
