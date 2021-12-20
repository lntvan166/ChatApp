package com.app.menu;

import com.app.client.ClientChat;
import com.app.util.ChatWindow;
import com.app.util.Message;
import com.app.util.MyFile;
import com.app.util.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * com.app.menu
 * Create by Le Nguyen Tu Van
 * Date 12/19/2021 - 4:43 PM
 * Description: ...
 */
public class UserOnline {
    private String username;
    private List<ClientChat> clientChatList;

    private JFrame frameMain;
    private JPanel panelMain;
    private JButton refreshButton;
    private JLabel label;
    private JPanel userPane;

    UserOnline(String username) {
        clientChatList = new ArrayList<>();
        this.username = username;
        label.setText("Hi, "+username);

        frameMain = new JFrame("App Chat");
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
        userPane.validate();
        userPane.repaint();
    }

    public void showNoOneOnline() {
        userPane.removeAll();
        userPane.setLayout(new BorderLayout());
        JLabel offline = new JLabel("Everyone is offline this time!", SwingConstants.CENTER);
        userPane.add(offline);

        refresh();
    }

    public void showUserList(String[] userOnline) {
        int size = userOnline.length;

        userPane.removeAll();
        userPane.setLayout(new GridLayout(4, 4));
//        userPane.add(userList);
//        for(String user : userOnline) {
//            JButton tempButton = new JButton(user);
//            userList.add(tempButton);
//        }

        JButton[] buttons = new JButton[size];
        for(int i=0; i<size;i++) {
            String name = userOnline[i];
            ClientChat temp = new ClientChat(name);
            clientChatList.add(temp);

            buttons[i] = new JButton(name);
            buttons[i].setSize(100, 100);

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    temp.show();
                }
            });
            userPane.add(buttons[i]);
        }

        refresh();
    }

    public void receiveMessage(String userContact, String message) {
        for(ClientChat clientChat: clientChatList) {
            if (Objects.equals(userContact, clientChat.getUserContact())){
                clientChat.addMessage(message);
            }
        }
    }

    public void receiveFile(String userContact, String fileName, byte[] file) {
        for(ClientChat clientChat: clientChatList) {
            if (Objects.equals(userContact, clientChat.getUserContact())){

            }
        }
    }
}
