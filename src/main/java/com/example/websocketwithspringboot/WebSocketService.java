package com.example.websocketwithspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final CallDetailsRepository callDetailsRepository;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate,
                            CallDetailsRepository callDetailsRepository) {

        this.messagingTemplate = messagingTemplate;
        this.callDetailsRepository = callDetailsRepository;
    }

    public void makeCall(String fromWho, String toWhom) {
        CallDetails callDetails = new CallDetails();
        callDetails.setFromWho(fromWho);
        callDetails.setToWhom(toWhom);
        callDetails.setTime(TimeFormatter.getCurrentTimeStamp());
        callDetailsRepository.save(callDetails);
    }

    public void getMissedCalls(String phoneNumber) {
        CallDetails missedCall = callDetailsRepository.findCallByToWhom(phoneNumber);
        ResponseMessage responseMessage = new ResponseMessage("Sizi arayan numara: "
                + missedCall.getFromWho()
                + ", Saat: " + missedCall.getTime());
        messagingTemplate.convertAndSend("/topic/" + missedCall.getToWhom(), responseMessage);
    }

    public void becomeAvailable(String phoneNumber) {
        CallDetails missedCall = callDetailsRepository.findCallByToWhom(phoneNumber);
        ResponseMessage responseMessage = new ResponseMessage(missedCall.getTime()
                + " tarihinde aradığınız " + missedCall.getToWhom()
                + " numaralı kişiye şuan ulaşabilirsiniz.");

        messagingTemplate.convertAndSend("/topic/" + missedCall.getFromWho(), responseMessage);
    }
}
