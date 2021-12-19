package com.app.client;

import com.app.util.Message;

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
    private JButton sendButton;
    private JTextField chatField;
    private JButton fileButton;
    private JTextArea bodyMessage;


    public ClientChat(String userContact) {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frameMain = new JFrame("App chat login");
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
        frameMain.setVisible(true);

        this.userContact = userContact;
        username.setText(userContact);
    }

    public void show() {
        frameMain.setVisible(true);
    }
}
