package io.github.adityapatwa.social_networking_kata.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<User> following;
    private List<Message> messages;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.following = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addFollowing(User user) {
        this.following.add(user);
    }

    public List<User> getFollowing() {
        return this.following;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Message> getMessageWall() {

        List<Message> messageWall = new ArrayList<>(this.messages);

        for (User friend : this.following) {
            messageWall.addAll(friend.getMessages());
        }
        messageWall.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        return messageWall;
    }

}
