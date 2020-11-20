package com.rcore.domain.user.usecase.admin.commands;

import com.rcore.domain.user.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class CreateUserCommand {
    private String email;
    private UserStatus status;
    private String firstName;
    private String lastName;
    private String secondName;
    private String login;
    private String profileImageId;
    private String countryId;
    private Long phone;
    private String password;
    private List<String> roleIds;

}
