package io.github.adityapatwa.social_networking_kata;

import io.github.adityapatwa.social_networking_kata.repositories.CommandRepository;
import io.github.adityapatwa.social_networking_kata.service.command.CommandService;
import io.github.adityapatwa.social_networking_kata.service.message.MessageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SocialNetworkingKataApplicationCommandLineRunner implements CommandLineRunner {

    private static final String QUIT_SESSION = "QUIT";

    private final CommandRepository commandRepository;
    private final MessageService messageService;

    public SocialNetworkingKataApplicationCommandLineRunner(CommandRepository commandRepository, MessageService messageService) {
        this.commandRepository = commandRepository;
        this.messageService = messageService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String inputString = "";

        while (!QUIT_SESSION.equalsIgnoreCase(inputString)) {
            System.out.print("> ");
            inputString = scanner.nextLine().trim();

            CommandService commandService = commandRepository.findCommand(inputString);
            if (!inputString.equalsIgnoreCase(QUIT_SESSION)) {
                if (commandService != null) {
                    commandService.execute(inputString);
                } else {
                    messageService.displayErrorMessage();
                }
            }

        }
        System.out.println("======================= The session has been closed =======================");

    }
}
