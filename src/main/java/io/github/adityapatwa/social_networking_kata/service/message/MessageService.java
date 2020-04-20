package io.github.adityapatwa.social_networking_kata.service.message;

import io.github.adityapatwa.social_networking_kata.domain.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class MessageService {

    public void displayMessageOnWall(Message message) {
        StringBuilder messageString = new StringBuilder();

        messageString.append("> ").append(message.getCreatedBy().getName()).append(" - ").append(message.getText());
        displayMessage(messageString, message.getCreatedAt());
    }

    public void displayMessageOnTimeLine(Message message) {
        StringBuilder messageString = new StringBuilder();

        messageString.append("> ").append(message.getText());
        displayMessage(messageString, message.getCreatedAt());
    }

    public void displayMessage(StringBuilder messageString, LocalDateTime createdAt) {
        long seconds = createdAt.until(LocalDateTime.now(), ChronoUnit.SECONDS);

        if (seconds < 60) {
            messageString.append(" (").append(seconds).append(" seconds ago)");
        } else {
            long minutes = createdAt.until(LocalDateTime.now(), ChronoUnit.MINUTES);
            if (minutes == 1) {
                messageString.append(" (").append(seconds).append(" minute ago)");
            } else {
                messageString.append(" (").append(seconds).append(" minutes ago)");
            }
        }
        System.out.println(messageString.toString());
    }

    public void displayErrorMessage () {
        System.out.println("> Could not execute the command" );
    }
}
