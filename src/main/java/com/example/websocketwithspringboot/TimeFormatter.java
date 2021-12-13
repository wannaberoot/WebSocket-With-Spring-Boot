package com.example.websocketwithspringboot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {

    private static final String TIME_FORMAT = "dd/MM/yy, HH:mm";

    public static String getCurrentTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return LocalDateTime.now().format(formatter);
    }
}
