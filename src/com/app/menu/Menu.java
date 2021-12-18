package com.app.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 7:56 PM
 * Description: ...
 */
public class Menu {
    private JFrame frameMain;

    private JPanel panelMain;
    private JButton chatWithOtherUserButton;
    private JButton cancelButton;
    private JButton logoutButton;

    public Menu() {
        chatWithOtherUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame logoutFrame = new JFrame("LOG OUT");
                if(JOptionPane.showConfirmDialog(logoutFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                    LoginForm loginForm = new LoginForm();
                    loginForm.start();
                    frameMain.dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cancelFrame = new JFrame("EXIT");
                if(JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public void start() {
        frameMain = new JFrame("App chat login");
        frameMain.setContentPane(panelMain);
        frameMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame cancelFrame = new JFrame("EXIT");
                if(JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
        frameMain.setVisible(true);
    }
}
