package com.springreact.chating.bootstrap;

import com.springreact.chating.models.Message;
import com.springreact.chating.models.User;
import com.springreact.chating.repositories.MessageRepository;
import com.springreact.chating.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User test1 = new User("test1@gmail.com", "test1", "123123");
        User test2 = new User("test2@gmail.com", "test2", "123123");
        this.userRepository.save(test1);
        this.userRepository.save(test2);
        this.messageRepository.save(new Message(test1, test2, "Hi, there!"));
        this.messageRepository.save(new Message(test1, test2, "Shall we talk?"));
        this.messageRepository.save(new Message(test2, test1, "Sure!"));
        this.messageRepository.save(new Message(test2, test1, "Where to begin?"));

        System.out.println(messageRepository.findMessagesByUsers(test1.getId(), test2.getId()).size());
    }
}
