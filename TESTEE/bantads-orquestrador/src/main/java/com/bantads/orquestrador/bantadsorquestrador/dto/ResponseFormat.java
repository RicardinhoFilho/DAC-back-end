package com.bantads.orquestrador.bantadsorquestrador.dto;

public class ResponseFormat {
    public boolean status;
    public String message;

    public ResponseFormat(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
