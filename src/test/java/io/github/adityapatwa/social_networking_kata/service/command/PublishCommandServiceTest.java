package io.github.adityapatwa.social_networking_kata.service.command;

import io.github.adityapatwa.social_networking_kata.domain.Message;
import io.github.adityapatwa.social_networking_kata.domain.User;
import io.github.adityapatwa.social_networking_kata.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PublishCommandServiceTest {

    private PublishCommandService publishCommandService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userRepository.save("Bob");
        publishCommandService = new PublishCommandService(userRepository);
    }

    @Test
    void executeForNewUser() {
        publishCommandService.execute("Alice -> I like the weather today");
        User alice = userRepository.getUser("Alice");
        Message message = alice.getMessages().get(0);
        assertEquals(message.getCreatedBy(), alice);
        assertEquals(message.getText(), "I like the weather today");
    }

    @Test
    void executeForExistingUser() {
        publishCommandService.execute("Bob -> I might go for a run");
        User bob = userRepository.getUser("Bob");
        Message message = bob.getMessages().get(0);
        assertEquals(message.getCreatedBy(), bob);
        assertEquals(message.getText(), "I might go for a run");
    }

    @Test
    void canParse() {
        assertTrue(publishCommandService.canParse("Alice -> I like the weather today"));
    }
}