package com.posto.mtcombustiveis.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private int status;
    private String error;
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String error) {
        this.status = status;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

}