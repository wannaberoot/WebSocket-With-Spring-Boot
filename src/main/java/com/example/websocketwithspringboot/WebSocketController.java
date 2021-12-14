package com.example.websocketwithspringboot;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@RestController
@SecurityRequirement(name = "admin-api")
@RequestMapping("/api")
public class WebSocketController {

    private final WebSocketService webSocketService;

    @Autowired
    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @PostMapping("/makeCall/{fromWho}/{toWhom}")
    public String makeCall(@PathVariable String fromWho, @PathVariable String toWhom) {
        webSocketService.makeCall(fromWho, toWhom);
        return ("Call is successful.");
    }

    @GetMapping("/getMissedCalls/{phoneNumber}")
    public String getMissedCalls(@RequestHeader(name="Accept-Language", required=false) String localeString,
                               @PathVariable String phoneNumber) {
        Locale locale = new Locale(localeString);
        webSocketService.getMissedCalls(phoneNumber, locale);
        return ("Missed calls informations have been sent to client");
    }

    @GetMapping("/sendDeliveryReport/{phoneNumber}")
    public String sendDeliveryReport(@RequestHeader(name="Accept-Language", required=false) String localeString,
                                     @PathVariable String phoneNumber) {
        Locale locale = new Locale(localeString);
        webSocketService.sendDeliveryReport(phoneNumber, locale);
        return ("The delivery report have been sent to client.");
    }
}
