package com.app.client;

import com.app.util.AppUtil;
import com.app.util.MyFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * com.app.client
 * Create by Le Nguyen Tu Van
 * Date 12/20/2021 - 6:11 PM
 * Description: ...
 */
public class FileReceived {
    private JFrame frameMain;
    private JPanel mainPanel;
    private JLabel userLabel;
    private JPanel filePane;

    private List<MyFile> myFiles = new ArrayList<>();

    FileReceived(String userContact) {
        userLabel.setText("File receive from " + userContact);

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

    public JPanel getFilePane() {
        return filePane;
    }

    public void start() {
        frameMain.setVisible(true);
    }

    public void refresh(String fileName, byte[] file) {
        myFiles.add(new MyFile(fileName, file, AppUtil.getFileExtension(fileName)));
        filePane.removeAll();
        filePane.setLayout(new GridLayout(4,4));
        JButton[] buttons = new JButton[myFiles.size()];
        for(int i=0; i<myFiles.size();i++) {
            buttons[i] = new JButton(myFiles.get(i).getName());
            buttons[i].setSize(100, 100);

            String tempFilename = myFiles.get(i).getName();
            byte[] tempFile = myFiles.get(i).getData();

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame cancelFrame = new JFrame("DOWNLOAD");
                        if(JOptionPane.showConfirmDialog(cancelFrame, "Confirm if you want to download", "DOWNLOAD",
                                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                            File fileToDownLoad = new File(tempFilename);
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(fileToDownLoad);
                                fileOutputStream.write(tempFile);
                                fileOutputStream.close();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                }
            });
            filePane.add(buttons[i]);
        }
        frameMain.validate();
        frameMain.repaint();
    }
}
