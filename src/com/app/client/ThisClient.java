package com.app.client;


import com.app.menu.UserOnline;
import com.app.util.User;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

/**
 * com.app.client
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 11:21 PM
 * Description: ...
 */
public class ThisClient {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ThisClient(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public void getUserOnline() throws IOException {
        bufferedWriter.write("GetUserOnline@#@" + username + "@#@" + username + "@#@getOnlineUser");
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public void sendMessage(String userTo, String message) {
        try {
            if (socket.isConnected()) {
                bufferedWriter.write("message@#@" + username + "@#@" + userTo + "@#@" + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromAnother;

                while (socket.isConnected()) {
                    try {
                        msgFromAnother = bufferedReader.readLine();
                        System.out.println("Receive: " + msgFromAnother);
                        handleMessageReceive(msgFromAnother);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedWriter, bufferedReader);
                    }
                }

            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void handleMessageReceive(String message) {
//        message = "GetUserOnline@#@irok@#@admin ah";
        String[] buffer = message.split("@#@");
        String type = buffer[0];
        String userFrom = buffer[1];
        String bodyMessage = buffer[2];
        if (Objects.equals(type, "GetUserOnline")) {
            if (!Objects.equals(buffer[2], "noOneOnline")) {
                String[] userOnline = bodyMessage.split(" ");
                User.userOnline.showUserList(userOnline);
            } else {
                User.userOnline.showNoOneOnline();
            }
        }
        if (Objects.equals(type, "message")) {

        }
    }
}

