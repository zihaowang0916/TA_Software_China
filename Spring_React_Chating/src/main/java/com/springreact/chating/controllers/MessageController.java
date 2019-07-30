package com.springreact.chating.controllers;

import com.springreact.chating.models.Message;
import com.springreact.chating.models.User;
import com.springreact.chating.repositories.MessageRepository;
import com.springreact.chating.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class MessageController {
    private UserRepository userRepository;
    private MessageRepository messageRepository;

    @Autowired
    public MessageController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/send")
    @ResponseBody
    public ResponseEntity<?> sendMessage(@RequestBody Object body, HttpSession session){
        Long toId = (Long) ((Map) body).get("to");
        String content = (String) ((Map) body).get("message");
        User currentUser = (User) session.getAttribute("user");
        Optional<User> to = userRepository.findById(toId);

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Not Authorized Action. Please Login.");
        }

        if (content == null || content.length() < 1) {
            return ResponseEntity.status(400).body("Please enter content");
        }

        if (to.isPresent()){
            Message message = new Message(currentUser, to.get(), content);
            messageRepository.save(message);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body("User Not Found.");
        }
    }

    @GetMapping("/listen")
    @ResponseBody
    public ResponseEntity<?> listenMessage(HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Not Authorized Action. Please Login.");
        }

        List<Message> messageList = messageRepository.findMessagesByUser(currentUser.getId());
        return ResponseEntity.ok(messageList);
    }

    @PostMapping("/listen")
    @ResponseBody
    public ResponseEntity<?> listenToUser(@RequestBody Long userId, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        Optional<User> other = userRepository.findById(userId);
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Not Authorized Action. Please Login.");
        }

        if (other.isPresent()) {
            List<Message> messageList = messageRepository.findMessagesByUsers(currentUser.getId(), other.get().getId());
            return ResponseEntity.ok(messageList);
        } else {
            return ResponseEntity.status(404).body("User Not Found");
        }
    }
}
