package com.example.clo;

public class message {
    private String text;
    private boolean sentByMe;

    public message(String text, boolean sentByMe) {
        this.text = text;
        this.sentByMe = sentByMe;
    }

    public String getText() {
        return text;
    }

    public boolean isSentByMe() {
        return sentByMe;
    }
}
