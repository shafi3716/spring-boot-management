package com.shafi.springmanagement.authentication.model;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticationRequest {

    @ApiModelProperty(notes = "username is required.")
    private String username;
    @ApiModelProperty(notes = "password is required.")
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
