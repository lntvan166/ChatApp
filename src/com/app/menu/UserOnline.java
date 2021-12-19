package com.app.menu;

import com.app.util.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/19/2021 - 4:43 PM
 * Description: ...
 */
public class UserOnline {
    public static JFrame frameMain;
    private JPanel panelMain;
    private JPanel userList;
    private JButton refreshButton;
    private JLabel label;

    UserOnline() {
        frameMain = new JFrame("User Online");
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


        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User.client.getUserOnline();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
    }

    public void refresh() {
        userList.validate();
        userList.repaint();
    }

    public void showNoOneOnline() {
        userList.removeAll();
        JLabel offline = new JLabel("Everyone is offline this time!");
        userList.add(offline);
        label.setText("No one online");
        refresh();
    }

}
