package com.example.websocketwithspringboot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(Message message) {
        return new ResponseMessage("Your number: " + HtmlUtils.htmlEscape(message.getMessageContent()));
    }
}
