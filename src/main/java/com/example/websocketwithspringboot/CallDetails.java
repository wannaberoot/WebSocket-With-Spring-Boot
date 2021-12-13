package com.example.websocketwithspringboot;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CallDetails {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fromWho;
    private String toWhom;
    private String time;
    private boolean isRead;

    public CallDetails(String fromWho, String toWhom) {
        this.fromWho = fromWho;
        this.toWhom = toWhom;
        this.time = TimeFormatter.getCurrentTimeStamp();
        this.isRead = false;
    }
}
