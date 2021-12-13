package com.example.websocketwithspringboot;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMessage {

    @NonNull
    private String content;
}
