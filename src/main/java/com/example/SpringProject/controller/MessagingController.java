package com.example.SpringProject.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.SpringProject.model.Message;
import com.example.SpringProject.model.User;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagingController {

    List<Message> userMessages = new ArrayList<Message>();
    List<Message> senderMessages = new ArrayList<Message>();

    @PostConstruct
    private void initMessages() {
        userMessages.add(
                new Message(
                        new User("Aurelie"),
                        "Message from Lilly",
                        1, 2
                )
        );
        userMessages.add(
                new Message(
                        new User("Billy"),
                        "Message from Billy",
                        2, 3
                )
        );

        senderMessages.add(
                new Message(
                        new User("Ludovic", true),
                        "Message from Ludo",
                        1, 0
                )
        );
        senderMessages.add(
                new Message(
                        new User("Jessica", false),
                        "Message from Jess",
                        1, 1
                )
        );

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/get-user-messages")
    public List<Message> getUserMessages() {
        return userMessages;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/get-sender-messages")
    public List<Message> getSenderMessages() {
        return senderMessages;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/add-user-message")
    public List<Message> addUserMessage(@RequestBody Message newMessage) {
        userMessages.add(newMessage);
        return userMessages;
    }
}

// @RestController
// public class MessagingController {
//     @CrossOrigin(origins = "http://localhost:4200")
//     @GetMapping("/api/get-user-messages")
//     public List<Message> getUserMessages() {
//       List<Message> messages = new ArrayList<Message>();
//       messages.add(
//               new Message(
//                       new User("Aurelie"),
//                       "Message from Billy",
//                       1, 2
//               )
//       );
//       return messages;
//     }

//     @CrossOrigin(origins = "http://localhost:4200")
//     @GetMapping("/api/get-sender-messages")
//     public List<Message> getSenderMessages() {
//       List<Message> messages = new ArrayList<Message>();
//       messages.add(
//               new Message(
//                       new User("Ludovic", true),
//                       "Message from Ludo",
//                       1, 0
//               )
//       );
//       messages.add(
//               new Message(
//                       new User("Jessica", false),
//                       "Message from Jess",
//                       1, 1
//               )
//       );
//       return messages;
//     }
// }