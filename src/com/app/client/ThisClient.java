package com.app.client;


import com.app.menu.UserOnline;
import com.app.util.MyFile;
import com.app.util.User;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private String username;

    private ArrayList<MyFile> myFiles = new ArrayList<>();

    public ThisClient(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.dataOutputStream =  new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
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
                        if(Objects.equals(msgFromAnother, "File")) {
                            handleFileReceived();
                        } else {
                            handleMessageReceive(msgFromAnother);
                        }
                    } catch (IOException e) {
                        closeEverything(socket, bufferedWriter, bufferedReader);
                    }
                }

            }
        }).start();
    }

    public void handleFileReceived() throws IOException {
        System.out.println("---------receive");
        String userFrom = bufferedReader.readLine();
        System.out.println(userFrom);
        int fileNameLength = dataInputStream.readInt();
        System.out.println(fileNameLength);

        if (fileNameLength > 0) {
            byte[] fileNameBytes = new byte[fileNameLength];
            dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
            String filename = new String(fileNameBytes);
            System.out.println("file receive"+filename);
            int fileContentLength = dataInputStream.readInt();
            System.out.println(fileContentLength);

            if(fileContentLength > 0) {
                byte[] fileContentBytes = new byte[fileContentLength];
                dataInputStream.readFully(fileContentBytes, 0, fileContentLength);
                System.out.println(Arrays.toString(fileContentBytes));

//                User.userOnline.receiveFile(userFrom, filename, fileContentBytes);
            }
        }
    }


    public void handleMessageReceive(String message) throws IOException {
//        message = "GetUserOnline@#@irok@#@admin ah";
        if(Objects.equals(message, "OnlineUserChange")) {
            getUserOnline();
        } else {
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
                User.userOnline.receiveMessage(userFrom, bodyMessage);
            }
        }
    }

    public void sendFile(String userTo, File fileToSend) {
        System.out.println("-----send");
        try {
            if (socket.isConnected()) {
                bufferedWriter.write("File");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Send file");
                bufferedWriter.write(userTo);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Send" +userTo);

                FileInputStream fileInputStream = new FileInputStream(fileToSend.getAbsolutePath());

                String fileName = fileToSend.getName();
                byte[] fileNameBytes = fileName.getBytes();

                byte[] fileContentBytes = new byte[(int) fileToSend.length()];
                fileInputStream.read(fileContentBytes);

                dataOutputStream.writeInt(fileNameBytes.length);
                System.out.println(fileNameBytes.length);
                dataOutputStream.write(fileNameBytes);
                System.out.println(fileNameBytes.toString());

                dataOutputStream.writeInt(fileContentBytes.length);
                System.out.println(fileContentBytes.length);
                dataOutputStream.write(fileContentBytes);
                System.out.println(fileContentBytes.toString());



            }
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
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
}

