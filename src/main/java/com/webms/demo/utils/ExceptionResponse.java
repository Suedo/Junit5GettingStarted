package com.webms.demo.utils;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ExceptionResponse {
    private LocalDate timestamp;
    private String message;
    private String details;
    
    public ExceptionResponse(LocalDate timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
