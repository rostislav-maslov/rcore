package com.rcore.admincore.domain.user.web.api;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserDTO {
    private String email;
    private String password;
    private List<String> roleIds;
}
