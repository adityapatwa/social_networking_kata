package io.github.adityapatwa.social_networking_kata.repositories;

import io.github.adityapatwa.social_networking_kata.service.command.CommandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandRepository {

    private final List<CommandService> validCommands;

    public CommandRepository(List<CommandService> validCommands) {
        this.validCommands = validCommands;
    }

    public CommandService findCommand(String inputCommand) {
        for (CommandService validCommand : validCommands) {
            if (validCommand.canParse(inputCommand)) {
                return validCommand;
            }
        }

        return null;
    }
}
