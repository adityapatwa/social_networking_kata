package io.github.adityapatwa.social_networking_kata.repositories;

import io.github.adityapatwa.social_networking_kata.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRepository {

    private final Map<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public User save(String name) {
        User newUser = new User(name);
        users.put(name, newUser);
        return newUser;
    }

    public User getUser(String name) {
        return this.users.get(name);
    }

    public boolean exists(String name) {
        return this.users.containsKey(name);
    }

}
