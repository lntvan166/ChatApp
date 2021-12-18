package com.app.menu;

import com.app.util.AppUtil;
import com.app.util.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 8:03 PM
 * Description: ...
 */
public class RegisterForm {
    private JFrame frameMain;

    private JTextField textField1;
    private JButton registerButton;
    private JPanel panelMain;
    private JButton cancelButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    public RegisterForm() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = String.valueOf(passwordField1.getPassword());
                String confirm = String.valueOf(passwordField2.getPassword());

                if (Objects.equals(username, "") ||  password.equals(""))
                    JOptionPane.showMessageDialog(null, "Cannot use empty information!");
                else {
                    if (!password.equals(confirm)) {
                        JOptionPane.showMessageDialog(null, "Password confirm failed!");
                    } else {
                        AppUtil.userList.add(new User(username, password));
                        try {
                            AppUtil.writeUser(AppUtil.userList);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Failed open text file!");
                        }
                        JOptionPane.showMessageDialog(null, "Register successfully!");
                        frameMain.dispose();
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
            }
        });
    }

    public void start() {
        frameMain = new JFrame("App chat register");
        frameMain.setContentPane(panelMain);
        frameMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
        frameMain.setVisible(true);
    }
}
