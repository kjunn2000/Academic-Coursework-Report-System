package com.moodyjun.View.Util;

import javax.swing.*;
import java.awt.*;

public class JClassPicker extends JPanel {

    private JComboBox<String> classTypeBox;
    private Box durationBox;
    private ButtonGroup durationButtonGroup;
    private JRadioButton oneHourRadioButton ;
    private JRadioButton oneAndHalfHourRadioButton ;
    private JRadioButton twoHourRadioButton ;
    private JLabel dayLabel;
    private JLabel hoursLabel;
    private JLabel minutesLabel;
    private JLabel locationLabel;
    private JTextField locationField;
    private JComboBox<String> dayOfWeek;
    private JComboBox<Integer> hours ;
    private JComboBox<Integer> minutes;
    private JButton deleteClassButton;

    public JClassPicker(){

        String[] classTypes = {"Lecturer","Tutorial","Lab"};
        classTypeBox = new JComboBox<String>(classTypes);
        classTypeBox.setSelectedIndex(1);
        dayLabel = new JLabel("Day:");
        hoursLabel = new JLabel("Hours:");
        minutesLabel = new JLabel("Minutes:");
        String[] daysOption = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"};
        dayOfWeek = new JComboBox<>(daysOption);
        dayOfWeek.setSelectedIndex(0);
        Integer[] hoursOption = {8,9,10,11,12,13,14,15,16,17,18};
        hours = new JComboBox<Integer>(hoursOption);
        hours.setSelectedIndex(0);
        Integer[] minutesOption = {0,15,30,45};
        minutes = new JComboBox<Integer>(minutesOption);
        minutes.setSelectedIndex(0);
        durationBox = Box.createHorizontalBox();
        durationButtonGroup = new ButtonGroup();
        oneHourRadioButton = new JRadioButton("1 Hour");
        oneHourRadioButton.setSelected(true);
        twoHourRadioButton = new JRadioButton("2 Hours");
        oneAndHalfHourRadioButton = new JRadioButton("1 Hour 30 Minutes");
        durationButtonGroup.add(oneHourRadioButton);
        durationButtonGroup.add(twoHourRadioButton);
        durationButtonGroup.add(oneAndHalfHourRadioButton);
        oneHourRadioButton.setSelected(true);
        durationBox.add(oneHourRadioButton);
        durationBox.add(twoHourRadioButton);
        durationBox.add(oneAndHalfHourRadioButton);
        durationBox.setBorder(BorderFactory.createTitledBorder("Duration:"));
        locationLabel = new JLabel("Location: ");
        locationField = new JTextField();
        deleteClassButton = new JButton("Delete");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0; gbc.gridy=0;gbc.gridwidth = 5 ;
        add(classTypeBox,gbc);
        gbc.gridx=1; gbc.gridy=1;gbc.gridwidth = 1 ;
        add(dayLabel,gbc);
        gbc.gridx=2;
        add(dayOfWeek,gbc);
        gbc.gridx=3;
        add(hoursLabel,gbc);
        gbc.gridx=4;
        add(hours,gbc);
        gbc.gridx=5;
        add(minutesLabel,gbc);
        gbc.gridx=6;
        add(minutes,gbc);
        gbc.gridx=1; gbc.gridy=3;gbc.gridwidth = 6 ;
        add(durationBox,gbc);
        gbc.gridx=0; gbc.gridy=4;gbc.gridwidth = 2 ;
        add(locationLabel,gbc);
        gbc.gridx=2; gbc.gridy=4;gbc.gridwidth = 4 ;
        add(locationField,gbc);
        gbc.gridx=6; gbc.gridy=5;gbc.gridwidth = 1 ;
        add(deleteClassButton,gbc);
        setVisible(true);
    }



    public JRadioButton getOneHourRadioButton() {
        return oneHourRadioButton;
    }

    public JRadioButton getOneAndHalfHourRadioButton() {
        return oneAndHalfHourRadioButton;
    }

    public JRadioButton getTwoHourRadioButton() {
        return twoHourRadioButton;
    }

    public JButton getDeleteClassButton() {
        return deleteClassButton;
    }

    public JComboBox<String> getClassTypeBox() {
        return classTypeBox;
    }

    public JComboBox<String> getDayOfWeek() {
        return dayOfWeek;
    }

    public JComboBox<Integer> getHours() {
        return hours;
    }

    public JComboBox<Integer> getMinutes() {
        return minutes;
    }

    public JTextField getLocationField() {
        return locationField;
    }
}
