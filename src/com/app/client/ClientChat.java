package com.app.client;

import com.app.util.AppUtil;
import com.app.util.MyFile;
import com.app.util.User;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * com.app.client
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 7:38 PM
 * Description: ...
 */
public class ClientChat {
    private String _message;

    private String userContact;

    private SendFile sendFile;
    private FileReceived fileReceived;

    private JFrame frameMain;
    private JPanel mainPanel;
    private JLabel username;
    private JTextField chatField;
    private JButton sendButton;
    private JTextArea textArea1;
    private JButton fileReceivedButton;
    private JButton sendFileButton;


    public ClientChat(String userContact) {


        this.userContact = userContact;
        username.setText(userContact);
        _message = "";

        sendFile = new SendFile(userContact);
        fileReceived = new FileReceived(userContact);

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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chatField.getText();
                if (!Objects.equals(message, "")) {
                    User.client.sendMessage(userContact, message);

                    chatField.setText("");
                    _message += AppUtil.user + ": " + message + "\n";
                    refresh();
                }

            }
        });


        sendFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendFile.start();
            }
        });
        fileReceivedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileReceived.start();
            }
        });
    }

    public FileReceived getFileReceived() {
        return fileReceived;
    }

    public String getUserContact() {
        return userContact;
    }

    public void addMessage(String message) {
        _message += userContact + ": " + message + "\n";
        refresh();
    }

    public void show() {
        frameMain.setVisible(true);
    }

    public void refresh() {
        textArea1.setText(_message);
        textArea1.validate();
        textArea1.repaint();
    }
}
