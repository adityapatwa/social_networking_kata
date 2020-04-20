package io.github.adityapatwa.social_networking_kata.service.command;

import io.github.adityapatwa.social_networking_kata.domain.Message;
import io.github.adityapatwa.social_networking_kata.domain.User;
import io.github.adityapatwa.social_networking_kata.repositories.UserRepository;
import io.github.adityapatwa.social_networking_kata.service.message.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PublishCommandService implements CommandService {

    private static final String PUBLISH_SYNTAX = "^(?<user>[^\\s]+) -> (?<text>.+)$";
    private final UserRepository userRepository;


    public PublishCommandService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String inputCommand) {
        Matcher matcher = Pattern.compile(PUBLISH_SYNTAX).matcher(inputCommand);

        if (matcher.matches()) {
            String userName = matcher.group("user");
            String text = matcher.group("text").trim();

            User user = (userRepository.exists(userName)) ? userRepository.getUser(userName) : userRepository.save(userName);
            Message message = new Message(user, text, LocalDateTime.now());

            user.addMessage(message);
        }


    }

    @Override
    public boolean canParse(String inputCommand) {
        return inputCommand.matches(PUBLISH_SYNTAX);
    }
}
