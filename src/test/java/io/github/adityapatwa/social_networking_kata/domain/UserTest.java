package io.github.adityapatwa.social_networking_kata.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User alice;
    private Message aliceMessage;
    private Message bobMessage;
    private Message charlieMessage;

    @BeforeEach
    void setUp() {
        alice = new User("Alice");
        aliceMessage = new Message(alice, "I love the weather today.", LocalDateTime.now().minusSeconds(2));
        alice.addMessage(aliceMessage);

        User bob = new User("Bob");
        bobMessage = new Message(bob, "Darn! We lost!", LocalDateTime.now().minusSeconds(1));
        bob.addMessage(bobMessage);

        User charlie = new User("Charlie");
        charlieMessage = new Message(charlie, "I'm in New York today! Anyone wants to have a coffee?", LocalDateTime.now());
        charlie.addMessage(charlieMessage);

        alice.addFollowing(bob);
        alice.addFollowing(charlie);
    }

    @Test
    void getMessageWall() {

        List<Message> messages = alice.getMessageWall();

        assertEquals(messages.size(), 3);
        assertEquals(messages.get(0), charlieMessage);
        assertEquals(messages.get(1), bobMessage);
        assertEquals(messages.get(2), aliceMessage);
    }
}