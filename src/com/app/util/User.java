package com.app.util;

/**
 * com.app.util
 * Create by Le Nguyen Tu Van
 * Date 12/18/2021 - 8:05 PM
 * Description: ...
 */
public class User {
    private String username;
    private String name;
    private String password;

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // format: username`password`name
    public String toString() {
        return username + "`" + password + "`" + name;
    }
}
