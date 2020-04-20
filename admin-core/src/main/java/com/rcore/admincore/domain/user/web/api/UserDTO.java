package com.rcore.admincore.domain.user.web.api;

import com.rcore.domain.user.entity.SocialAccount;
import com.rcore.domain.user.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDTO {
    private String id;

    private UserStatus status;

    private String firstName;
    private String lastName;
    private String secondName;
    private String fullName;

    private String email;
    private Long phoneNumber;

    private List<SocialAccount> socialAccounts;

    private Integer fails = 0;
    private LocalDateTime lastFailDate;

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
