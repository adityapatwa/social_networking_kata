package io.github.adityapatwa.social_networking_kata.domain;

import java.time.LocalDateTime;

public class Message {

    private User createdBy;
    private String text;
    private LocalDateTime createdAt;

    public Message() {
    }

    public Message(User createdBy, String text, LocalDateTime createdAt) {
        this.createdBy = createdBy;
        this.text = text;
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
