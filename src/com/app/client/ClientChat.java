package com.app.client;

import com.app.util.AppUtil;
import com.app.util.Message;
import com.app.util.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * com.app.client
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 7:38 PM
 * Description: ...
 */
public class ClientChat {
    private Message message;

    private String userContact;

    private JFrame frameMain;
    private JPanel mainPanel;
    private JLabel username;
    private JTextField chatField;
    private JButton sendButton;
    private JTextArea textArea1;
    private JButton fileButton;


    public ClientChat(String userContact) {


        this.userContact = userContact;
        username.setText(userContact);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chatField.getText();
                User.client.sendMessage(userContact, message);
            }
        });

        frameMain = new JFrame("[" + AppUtil.user + "] chat with " + userContact);
        frameMain.setContentPane(mainPanel);
        frameMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameMain.setVisible(false);
            }
        });
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();
    }

    public void show() {
        frameMain.setVisible(true);
    }
}
