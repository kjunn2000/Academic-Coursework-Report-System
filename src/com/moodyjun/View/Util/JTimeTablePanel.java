package com.moodyjun.View.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JTimeTablePanel extends JPanel {

    private JTabbedPane timetableTab ;
    private List<JTable> timetableList ;
    private GridBagConstraints gbc;

    public JTimeTablePanel(){
        setLayout(new GridBagLayout());
        timetableTab = new JTabbedPane();
        timetableList = new ArrayList<>();
        String[] days = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"};
        for( int i = 0 ; i < 5 ; i++ ){
            JTable jTable = new JTable();
            timetableList.add(jTable);
            timetableTab.add(new JScrollPane(jTable),days[i]);
            jTable.setEnabled(false);
        }
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,50,20,50);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0 ;
        gbc.gridy = 0 ;
        gbc.gridwidth = 3;
        add(timetableTab,gbc);
    }

    public JTabbedPane getTimetableTab() {
        return timetableTab;
    }

    public List<JTable> getTimetableList() {
        return timetableList;
    }
}
