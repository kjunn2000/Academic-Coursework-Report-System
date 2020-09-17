package com.moodyjun.View.Util;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JNumberTextField extends JTextField {
    public JNumberTextField (){
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' ||
                        ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ) {
                    setEditable(true);
                } else {
                    setEditable(false);
                    System.out.println("* Enter only numeric digits(0-9)");
                }
            }
        });
    }

    public JNumberTextField (boolean dash){
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = getText();
                int l = value.length();
                if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyChar() == '-')||
                        ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    setEditable(true);
                } else {
                    setEditable(false);
                    System.out.println("* Enter only numeric digits(0-9)");
                }
            }
        });
    }
}