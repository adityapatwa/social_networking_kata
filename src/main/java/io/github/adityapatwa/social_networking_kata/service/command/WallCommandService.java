package io.github.adityapatwa.social_networking_kata.service.command;

import io.github.adityapatwa.social_networking_kata.domain.Message;
import io.github.adityapatwa.social_networking_kata.domain.User;
import io.github.adityapatwa.social_networking_kata.repositories.UserRepository;
import io.github.adityapatwa.social_networking_kata.service.message.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WallCommandService implements CommandService {

    private static final String WALL_SYNTAX = "^(?<user>[^\\s]+) wall$";

    private final UserRepository userRepository;
    private final MessageService messageService;

    public WallCommandService(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @Override
    public void execute(String inputCommand) {
        Matcher matcher = Pattern.compile(WALL_SYNTAX).matcher(inputCommand);

        if (matcher.matches()) {
            String userName = matcher.group("user");
            User user = userRepository.getUser(userName);

            List<Message> messageWall = user.getMessageWall();

            for (Message message : messageWall) {
                messageService.displayMessageOnWall(message);
            }
        }
    }

    @Override
    public boolean canParse(String inputCommand) {
        return inputCommand.matches(WALL_SYNTAX);
    }
}
