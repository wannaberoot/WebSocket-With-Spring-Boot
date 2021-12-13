package com.example.websocketwithspringboot;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "admin-api")
@RequestMapping("/api")
public class WebSocketController {
    // TODO: It will be made to support 2 languages
    private final WebSocketService webSocketService;

    @Autowired
    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @PostMapping("/makeCall/{fromWho}/{toWhom}")
    public String makeCall(@PathVariable String fromWho, @PathVariable String toWhom) {
        webSocketService.makeCall(fromWho, toWhom);
        return ("You called: " + toWhom);
    }

    @GetMapping("/getMissedCalls/{phoneNumber}")
    public void getMissedCalls(@PathVariable String phoneNumber) {
        webSocketService.getMissedCalls(phoneNumber);
    }

    @GetMapping("/becomeAvailable/{phoneNumber}")
    public String becomeAvailable(@PathVariable String phoneNumber) {
        webSocketService.becomeAvailable(phoneNumber);
        return ("You are available now.");
    }
}
