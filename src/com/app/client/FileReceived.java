package com.app.client;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * com.app.client
 * Create by Le Nguyen Tu Van
 * Date 12/20/2021 - 6:11 PM
 * Description: ...
 */
public class FileReceived {
    private JFrame frameMain;
    private JPanel mainPanel;
    private JScrollPane fileScrollPane;

    FileReceived() {
        frameMain = new JFrame("Send file");
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

    public void start() {
        frameMain.setVisible(true);
    }
}
