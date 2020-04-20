package io.github.adityapatwa.social_networking_kata.service.command;

import io.github.adityapatwa.social_networking_kata.domain.User;
import io.github.adityapatwa.social_networking_kata.repositories.UserRepository;
import io.github.adityapatwa.social_networking_kata.service.message.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FollowCommandServiceTest {

    private FollowCommandService followCommandService;
    private UserRepository userRepository;

    @Mock
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userRepository.save("Alice");
        userRepository.save("Bob");
        followCommandService = new FollowCommandService(userRepository, messageService);
    }

    @Test
    void execute() {
        followCommandService.execute("Alice follows Bob");

        User alice = userRepository.getUser("Alice");
        User bob = userRepository.getUser("Bob");

        assertEquals(alice.getFollowing().size(), 1);
        assertEquals(alice.getFollowing().get(0), bob);
    }

    @Test
    void canParse() {
        assertTrue(followCommandService.canParse("Alice follows Bob"));
    }
}