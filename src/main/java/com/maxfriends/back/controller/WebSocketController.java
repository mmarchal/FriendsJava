package com.maxfriends.back.controller;


import com.maxfriends.back.dto.WebSocketMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@Controller
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;   //1
    private final Set<String> connectedUsers;     //2

    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate; //1
        connectedUsers = new HashSet<>();  //2
    }

    @MessageMapping("/register")  //3
    @SendToUser("/queue/newMember")
    public Set<String> registerUser(String webChatUsername){
        if(!connectedUsers.contains(webChatUsername)) {
            connectedUsers.add(webChatUsername);
            simpMessagingTemplate.convertAndSend("/topic/newMember", webChatUsername); //4
            return connectedUsers;
        } else {
            return new HashSet<>();
        }
    }

    @MessageMapping("/unregister")  //5
    @SendTo("/topic/disconnectedUser")
    public String unregisterUser(String webChatUsername){
        connectedUsers.remove(webChatUsername);
        return webChatUsername;
    }

    @MessageMapping("/message")  //6
    public void greeting(WebSocketMessageDto message){
        simpMessagingTemplate.convertAndSendToUser(message.getToWhom(), "/msg", message);
    }
}