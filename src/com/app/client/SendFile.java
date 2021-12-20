package com.app.client;

import com.app.util.AppUtil;
import com.app.util.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * com.app.client
 * Create by Le Nguyen Tu Van
 * Date 12/20/2021 - 5:16 PM
 * Description: ...
 */
public class SendFile {
    private JFrame frameMain;
    private JPanel panelMain;
    private JButton sendFileButton;
    private JButton chooseFileButton;
    private JLabel fileLabel;

    private File fileToSend;
    private String userContact;

    public SendFile(String userContact) {

        this.userContact = userContact;

        frameMain = new JFrame("Send file");
        frameMain.setContentPane(panelMain);
        frameMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frameMain.setVisible(false);
            }
        });
        frameMain.setLocationRelativeTo(null);
        frameMain.pack();

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a file to send");

                if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileToSend = fileChooser.getSelectedFile();
                    fileLabel.setText("The file you want to send is: " + fileToSend.getName());
                }
            }
        });

        sendFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileToSend == null) fileLabel.setText("Choose file first");
                else {
                    User.client.sendFile(userContact, fileToSend);
                }
            }
        });
    }

    public void start() {
        frameMain.setVisible(true);
    }
}
