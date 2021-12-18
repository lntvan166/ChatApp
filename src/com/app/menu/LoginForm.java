package com.app.menu;

import com.app.util.AppUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 8:02 PM
 * Description: ...
 */
public class LoginForm {
    private JFrame frameMain;

    private JPanel panelMain;
    private JTextField textField1;
    private JButton loginButton;
    private JButton registerButton;
    private JPasswordField passwordField1;

    public LoginForm() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = String.valueOf(passwordField1.getPassword());
                if(!AppUtil.authUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Invalid username/password!");
                } else {
                    JOptionPane.showMessageDialog(null, "Login successfully!");
                    AppUtil.user = AppUtil.getNameByUsername(username);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                registerForm.start();
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
