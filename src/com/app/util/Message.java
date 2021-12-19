package com.app.util;

import java.util.ArrayList;
import java.util.List;

/**
 * com.app.util
 * Create by Le Nguyen Tu Van
 * Date 12/19/2021 - 3:46 PM
 * Description: ...
 */
public class Message {
    private String userContact;
    private List<String> messageHistory;

    Message(String userContact) {
        this.userContact = userContact;
        messageHistory = new ArrayList<>();
    }

    public void addMessage(String user, String body) {
        String newMessage = user + ": " + body;
        messageHistory.add(newMessage);
    }
}
