package com.app.util;

import com.app.client.ClientChat;

/**
 * com.app.util
 * Create by Le Nguyen Tu Van
 * Date 12/19/2021 - 8:38 PM
 * Description: ...
 */
public class ChatWindow {
    private String userContact;
    private ClientChat clientChat;
    private String message;

    public ChatWindow(String userContact, ClientChat clientChat, String message) {
        this.userContact = userContact;
        this.clientChat = clientChat;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void addMessage(String newMessage) {
        message += newMessage + "\n";
    }
}
