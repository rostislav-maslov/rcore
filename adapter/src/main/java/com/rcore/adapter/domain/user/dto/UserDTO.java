package com.rcore.adapter.domain.user.dto;


import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.user.entity.SocialAccount;
import com.rcore.domain.user.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDTO {
    private String id;
    private List<RoleDTO> roles;

    private UserStatus userStatus;

    private String firstName;
    private String lastName;
    private String secondName;
    private String fullName;

    private String email;
    private Long phoneNumber;
    private String login;
    private String password;

    private List<SocialAccount> socialAccounts;

    private Integer fails = 0;
    private LocalDateTime lastFailDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timeZone;
}
