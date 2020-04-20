package io.github.adityapatwa.social_networking_kata.service.command;

import org.springframework.stereotype.Service;

@Service
public interface CommandService {

    void execute(String inputCommand);

    boolean canParse(String inputCommand);

}
