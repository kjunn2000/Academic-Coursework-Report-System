package com.moodyjun.View;

import javax.swing.*;
import java.awt.*;

public class TemplatePage extends JFrame {

    private JPanel navPanel;
    private JPanel mainPanel;
    private JLabel id;
    private JLabel userName;
    private JLabel position;
    private JButton editButton;
    private Font font;
    private JMenuBar menuBar ;
    private JMenu menu ;
    private JMenuItem menuItem ;
    private JButton navButton1 ;
    private JButton navButton2 ;
    private JButton navButton3 ;
    private JButton navButton4 ;
    private JButton navButton5 ;
    private JButton navButton6 ;
    private JButton exitButton;
    private JTabbedPane tabbedPane ;

    public TemplatePage() {

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(200, 0, 690, 575);
        mainPanel.setBackground(null);

        navPanel = new JPanel();
        navPanel.setLayout(null);
        navPanel.setBounds(0, 0, 200, 575);
        navPanel.setBackground(new Color(22, 27, 33));

        font = new Font(Font.SANS_SERIF, Font.BOLD, 18);

        id = new JLabel();
        id.setBounds(30,10, 150, 30);
        id.setFont(font);
        id.setForeground(Color.WHITE);

        userName = new JLabel();
        userName.setBounds(30,45, 150, 30);
        userName.setFont(font);
        userName.setForeground(Color.WHITE);

        position = new JLabel();
        position.setBounds(30,90, 150, 20);
        position.setFont(font);
        position.setForeground(Color.WHITE);

        editButton = new JButton("Edit All");
        editButton.setBorderPainted(false);
        editButton.setBounds(30,130, 150, 20);
        editButton.setForeground(Color.WHITE);
        editButton.setBackground(Color.RED);

        navButton1 = new JButton();
        navButton1.setBounds(15,200, 170, 20);
        navButton1.setBorderPainted(false);

        navButton2 = new JButton();
        navButton2.setBounds(15,240, 170, 20);
        navButton2.setBorderPainted(false);

        navButton3 = new JButton();
        navButton3.setBounds(15,280, 170, 20);
        navButton3.setBorderPainted(false);

        navButton4 = new JButton();
        navButton4.setBounds(15,320, 170, 20);
        navButton4.setBorderPainted(false);

        navButton5 = new JButton();
        navButton5.setBounds(15,360, 170, 20);
        navButton5.setBorderPainted(false);

        navButton6 = new JButton();
        navButton6.setBounds(15,400, 170, 20);
        navButton6.setBorderPainted(false);

        exitButton = new JButton("Exit");
        exitButton.setBounds(65,500, 70, 20);
        exitButton.setBorderPainted(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);


        getNavPanel().add(navButton1);
        getNavPanel().add(navButton2);
        getNavPanel().add(navButton3);
        getNavPanel().add(navButton4);
        getNavPanel().add(navButton5);
        getNavPanel().add(navButton6);
        getNavPanel().add(exitButton);

        navPanel.add(id);
        navPanel.add(userName);
        navPanel.add(position);
        navPanel.add(editButton);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        mainPanel.add(tabbedPane);
        menuBar = new JMenuBar();
        menu = new JMenu("About");
        menuItem = new JMenuItem("Version");
        menu.add(menuItem);
        menuBar.add(menu);

        add(navPanel);
        add(mainPanel);

        setJMenuBar(menuBar);
        getContentPane().setBackground(Color.BLACK);
        setTitle("Academic Coursework Report System");
        setLayout(null);
        setBounds(200, 80, 900, 625);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public JLabel getId() {
        return id;
    }

    public JLabel getUserName() {
        return userName;
    }

    public JLabel getPosition() {
        return position;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getNavButton1() {
        return navButton1;
    }

    public JButton getNavButton2() {
        return navButton2;
    }

    public JButton getNavButton3() {
        return navButton3;
    }

    public JButton getNavButton4() {
        return navButton4;
    }

    public JButton getNavButton5() {
        return navButton5;
    }

    public JButton getNavButton6() { return navButton6; }

    public JButton getExitButton() {
        return exitButton;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JPanel getNavPanel() {
        return navPanel;
    }

}
