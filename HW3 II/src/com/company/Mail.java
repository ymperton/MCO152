package com.company;

public class Mail {
    String subject, from, text;

    public Mail(String subject, String from, String text) {
        this.subject = subject;
        this.from = from;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Subject: %s\nFrom: %s\nText: %s",
                                subject, from, text);
    }
}
