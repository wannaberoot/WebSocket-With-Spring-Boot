package com.example.websocketwithspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.*;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final CallDetailsRepository callDetailsRepository;
    private final MessageSource messageSource;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate,
                            CallDetailsRepository callDetailsRepository,
                            MessageSource messageSource) {

        this.messagingTemplate = messagingTemplate;
        this.callDetailsRepository = callDetailsRepository;
        this.messageSource = messageSource;
    }

    public void makeCall(String fromWho, String toWhom) {
        CallDetails callDetails = new CallDetails(fromWho, toWhom);
        callDetailsRepository.save(callDetails);
    }

    public void getMissedCalls(String phoneNumber, Locale locale) {
        List<CallDetails> missedCalls = callDetailsRepository.findCallByToWhom(phoneNumber);
        Notification notificationForSize = new Notification(MessageFormat
                .format(messageSource.getMessage("number.of.missed.call.notification", null, locale)
                , missedCalls.size()));
        messagingTemplate.convertAndSend("/topic/" + phoneNumber, notificationForSize);

        Set<String> callers = new HashSet<>();
        for (CallDetails callerInfo : missedCalls) {
            callers.add(callerInfo.getFromWho());
        }

        for (String caller : callers) {
            List<CallDetails> missedCallListByCaller = callDetailsRepository
                    .findCallByToWhomAndFromWho(phoneNumber, caller);
            CallDetails individualCall = missedCallListByCaller.get(missedCallListByCaller.size() -1 );

            Notification notificationForMissedCallsInfo = new Notification(MessageFormat
                    .format(messageSource.getMessage("get.missed.calls.notification", null, locale)
                    , individualCall.getFromWho(), individualCall.getTime(), missedCallListByCaller.size()));
            messagingTemplate.convertAndSend("/topic/"
                    + phoneNumber, notificationForMissedCallsInfo);
        }
    }

    public void sendDeliveryReport(String phoneNumber, Locale locale) {
        List<CallDetails> missedCalls = callDetailsRepository.findCallByToWhom(phoneNumber);
        Set<String> callers = new HashSet<>();
        for (CallDetails callerInfo : missedCalls) {
            callers.add(callerInfo.getFromWho());
        }

        for (String caller : callers) {
            List<CallDetails> missedCallListByCaller = callDetailsRepository
                    .findCallByToWhomAndFromWho(phoneNumber, caller);
            CallDetails individualCall = missedCallListByCaller.get(missedCallListByCaller.size() -1 );

            Notification notificationForAvailableNotification = new Notification(MessageFormat
                    .format(messageSource.getMessage("get.available.notification", null, locale)
                    , individualCall.getToWhom(), individualCall.getTime()));
            messagingTemplate.convertAndSend("/topic/"
                    + individualCall.getFromWho(), notificationForAvailableNotification);
        }
    }
}
