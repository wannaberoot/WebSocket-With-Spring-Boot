package com.example.websocketwithspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void getNotify(String message) {
        ResponseMessage responseMessage = new ResponseMessage(message);
        messagingTemplate.convertAndSend("/topic/messages", responseMessage);
    }
}
