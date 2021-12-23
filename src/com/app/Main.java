package com.app;

import com.app.menu.LoginForm;
import com.app.menu.PortChoose;
import com.app.util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * com.app
 * Create by Le Nguyen Tu Van
 * Date 12/16/2021 - 9:44 PM
 * Description: ...
 */
public class Main {
    public static void main(String[] args) {
//        try {
//            AppUtil.userList = AppUtil.readUser();
//        } catch (IOException e) {
//            AppUtil.userList = new ArrayList<>();
//        }

        PortChoose app = new PortChoose();
        app.start();
    }
}
