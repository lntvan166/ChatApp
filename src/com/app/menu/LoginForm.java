package com.app.menu;

import com.app.client.ThisClient;
import com.app.util.AppUtil;
import com.app.util.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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

                try {
                    Socket socket = new Socket("localhost", User.port);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                    dataOutputStream.writeUTF("auth");
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(username);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(password);
                    dataOutputStream.flush();

                    String auth = dataInputStream.readUTF();



                    if (auth.equals("true")) {
                        AppUtil.user = username;

                        User.client = new ThisClient(socket, username);
                        User.userOnline = new UserOnline(username);

                        User.client.getUserOnline();
                        User.client.listenForMessage();
                    } else {
                        dataOutputStream.close();
                        dataInputStream.close();
                        socket.close();
                        JOptionPane.showMessageDialog(null, "Invalid username/password!");
                    }

                    frameMain.setVisible(false);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
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
                if (JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to exit", "EXIT",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
        frameMain.setVisible(true);
    }

    public void show() {
        frameMain.setVisible(true);
    }
}
