package io.github.adityapatwa.social_networking_kata.service.command;

import io.github.adityapatwa.social_networking_kata.domain.User;
import io.github.adityapatwa.social_networking_kata.repositories.UserRepository;
import io.github.adityapatwa.social_networking_kata.service.message.MessageService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FollowCommandService implements CommandService {

    private static final String FOLLOW_SYNTAX = "^(?<user>[^\\s]+) follows (?<friend>[^\\s]+)$";

    private final UserRepository userRepository;
    private final MessageService messageService;

    public FollowCommandService(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @Override
    public void execute(String inputCommand) {
        Matcher matcher = Pattern.compile(FOLLOW_SYNTAX).matcher(inputCommand);

        if (matcher.matches()) {
            String userName = matcher.group("user");
            String friendName = matcher.group("friend");

            User user = userRepository.getUser(userName);
            User friend = userRepository.getUser(friendName);

            if (user != null && friend != null && user != friend) {
                user.addFollowing(friend);
            } else {
                messageService.displayErrorMessage();
            }
        }
    }

    @Override
    public boolean canParse(String inputCommand) {
        return inputCommand.matches(FOLLOW_SYNTAX);
    }
}
