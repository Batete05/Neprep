package com.eucl.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomRequestException extends Exception {
    private final HttpStatus status;
    public CustomRequestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
    public CustomRequestException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
