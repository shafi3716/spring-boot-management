package com.shafi.springmanagement.authentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

    private final String jwt_token;
    private final String message;

    public AuthenticationResponse(String jwt_token, String message) {
        this.jwt_token = jwt_token;
        this.message = message;
    }

    @JsonProperty("jwt_token")
    public String getJwt() {
        return jwt_token;
    }

    public String getMessage() {
        return message;
    }
}
