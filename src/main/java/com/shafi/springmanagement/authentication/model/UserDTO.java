package com.shafi.springmanagement.authentication.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDTO {

    @NotNull(message = "User Name is required.")
    private String username;

    @NotNull(message = "Password is required.")
    @Min(value = 5, message = "Password must be greater than or equal to 5")
    private String password;
}
