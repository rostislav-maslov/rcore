package com.rcore.adapter.domain.user.dto;


import com.rcore.domain.role.entity.Role;
import com.rcore.domain.user.entity.SocialAccount;
import com.rcore.domain.user.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDTO {
    private String id;
    private Set<Role> roles;

    private UserStatus userStatus;

    private String firstName;
    private String lastName;
    private String secondName;
    private String fullName;

    private String email;
    private Long phoneNumber;
    private String login;
    private String password;

    private List<SocialAccount> socialAccounts = new ArrayList<>();

    private Integer fails = 0;
    private Date lastFailDate = new Date();
}
