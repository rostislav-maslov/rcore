package com.rcore.restapi.security.web.api;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    private String email;
    private String password;
}
