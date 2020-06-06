package com.rcore.admincore.domain.user.web.api;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String email;
    private String password;
}
