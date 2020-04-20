package io.github.adityapatwa.social_networking_kata.service.command;

import io.github.adityapatwa.social_networking_kata.domain.Message;
import io.github.adityapatwa.social_networking_kata.domain.User;
import io.github.adityapatwa.social_networking_kata.repositories.UserRepository;
import io.github.adityapatwa.social_networking_kata.service.message.MessageService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReadCommandService implements CommandService {

    private static final String READ_SYNTAX = "^(?<user>[^\\s]+)$";

    private final UserRepository userRepository;
    private final MessageService messageService;

    public ReadCommandService(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @Override
    public void execute(String inputCommand) {
        Matcher matcher = Pattern.compile(READ_SYNTAX).matcher(inputCommand);

        if (matcher.matches()) {
            String userName = matcher.group("user");

            if (userRepository.exists(userName)) {
                User user = userRepository.getUser(userName);

                for (Message message : user.getMessages()) {
                    messageService.displayMessageOnTimeLine(message);
                }
            } else {
                messageService.displayErrorMessage();
            }

        }

    }

    @Override
    public boolean canParse(String inputCommand) {
        return inputCommand.matches(READ_SYNTAX);
    }
}
