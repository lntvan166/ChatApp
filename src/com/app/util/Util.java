package com.app.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * com.app.util
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 8:03 PM
 * Description: ...
 */
public class Util {
    // read user information from text file
    // format: username`password`name
    public static List<User> readUser(String filename) throws IOException {
        List<User> userList = new ArrayList<>();

        BufferedReader fileIn = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = fileIn.readLine()) != null) {
            String[] buffer = line.split("`");
            String username = buffer[0];
            String password = buffer[1];
            String name = buffer[2];

            User tempUser = new User(username, name, password);
            userList.add(tempUser);

        }

        fileIn.close();

        return userList;
    }

    // write user information to text file
    // format: username`password`name
    public static void writeUser(String filename, List<User> userList) throws IOException {
        BufferedWriter fileOut = new BufferedWriter(new FileWriter(filename));

        for(User user : userList) {
            fileOut.write(user.toString());
            fileOut.newLine();
        }

        fileOut.close();
    }
}
