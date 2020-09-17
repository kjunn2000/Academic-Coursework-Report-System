package com.moodyjun.View.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateReportPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel reportTypeLabel;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JButton generateButton;
    private ButtonGroup buttonGroup;

    public GenerateReportPanel() {
        titleLabel = new JLabel("Generate Report");
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 23);
        titleLabel.setFont(font);
        Font font2 = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        reportTypeLabel = new JLabel("Report Type :");
        reportTypeLabel.setFont(font2);
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        Box box = Box.createVerticalBox();
        Font font3 = new Font(Font.SANS_SERIF, Font.ITALIC, 16);
        box.add(radioButton1);
        box.add(radioButton2);
        box.add(radioButton3);
        box.add(radioButton4);
        radioButton1.setFont(font3);
        radioButton2.setFont(font3);
        radioButton3.setFont(font3);
        radioButton4.setFont(font3);


        generateButton = new JButton("Generate");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,20,20,20);
        gbc.fill = GridBagConstraints.VERTICAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(titleLabel, gbc);

        gbc.gridy ++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(reportTypeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        add(box, gbc);

        gbc.gridy =10;
        gbc.gridx = 1;
        add(generateButton, gbc);
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JRadioButton getRadioButton1() {
        return radioButton1;
    }

    public JRadioButton getRadioButton2() {
        return radioButton2;
    }

    public JRadioButton getRadioButton3() {
        return radioButton3;
    }

    public JRadioButton getRadioButton4() {
        return radioButton4;
    }


}