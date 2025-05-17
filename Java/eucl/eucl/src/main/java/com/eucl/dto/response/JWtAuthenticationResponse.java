package com.eucl.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class JWtAuthenticationResponse {
    @JsonProperty("message")
    private final   String message;
    @JsonProperty("error-message")
    private  final String Errormessage;
    @JsonProperty("error-status")
    private  final HttpStatus errorStatus;

    @JsonProperty("StatusCode")
    private final int statusCode;
}
