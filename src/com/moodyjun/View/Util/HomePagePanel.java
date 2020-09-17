package com.moodyjun.View.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HomePagePanel extends JPanel {
    private JLabel welcomeLabel;
    private JButton button1 ;
    private JButton button2 ;
    private JButton button3 ;
    private JButton button4 ;
    private List<JButton> buttonList ;

    public HomePagePanel(){
        welcomeLabel = new JLabel();
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        welcomeLabel.setFont(font);
        welcomeLabel.setForeground(new Color(22, 27, 33));
        welcomeLabel.setBounds(60,50,700,40);
        buttonList = new ArrayList<>();
        button1 = new JButton();
        button1.setBounds(100,200,200,100);
        button2 = new JButton();
        button2.setBounds(400,200,200,100);
        button3 = new JButton();
        button3.setBounds(100,350,200,100);
        button4 = new JButton();
        button4.setBounds(400,350,200,100);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        for (JButton button : buttonList){
            button.setForeground(new Color(244, 169, 80));
            button.setFocusPainted(false);
            button.setFont(new Font("Tahoma", Font.BOLD, 16));
            button.setBackground(new Color(22, 27, 33));
        }


        setLayout(null);
        add(welcomeLabel);
        add(button1);
        add(button2);
        add(button3);
        add(button4);




    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getButton3() {
        return button3;
    }

    public JButton getButton4() {
        return button4;
    }

    public List<JButton> getButtonList() {
        return buttonList;
    }
}
