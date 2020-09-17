package com.moodyjun.View.Student;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class ViewResultPage extends StudentTemplatePage{

    private JPanel viewResultPanel ;
    private JTable resultTable;
    GridBagConstraints gbc ;


    public ViewResultPage(){
        viewResultPanel = new JPanel() ;
        resultTable = new JTable() ;
        resultTable.setEnabled(false);
        viewResultPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        JScrollPane sp=new JScrollPane(resultTable);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0 ;
        gbc.gridy = 0 ;
        gbc.gridwidth = 3;
        viewResultPanel.add(sp,gbc);
        getTabbedPane().add(new JScrollPane(viewResultPanel),"View Result");
    }

    public JPanel getViewResultPanel() {
        return viewResultPanel;
    }

    public JTable getResultTable() {
        return resultTable;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }
}
