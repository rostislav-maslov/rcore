package com.rcore.domain.user.entity;

import com.rcore.domain.base.entity.BaseEntity;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.role.entity.RoleEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    protected String id;

    protected Set<RoleEntity> roles = new HashSet<>();

    protected UserStatus status = UserStatus.ACTIVE;

    protected String firstName = "";
    protected String lastName = "";
    protected String secondName = "";
    protected String fullName = "";

    protected String email;
    protected Long phoneNumber;
    protected String login;
    protected String password;

    protected List<SocialAccount> socialAccounts = new ArrayList<>();

    protected Integer fails = 0;
    protected LocalDateTime lastFailDate = LocalDateTime.now();

    public UserEntity(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserEntity(String email) {
        this.email = email;
    }

    public Boolean hasAccess(Access access) {

        for (RoleEntity role : roles) {
            for (Access roleAccess : role.getAccesses())
                if (roleAccess.getId().equals(access.getId())) return true;
        }

        return false;
    }

    public Set<Access> getAccesses() {
        Set<Access> accesses = new HashSet<>();
        roles.stream().forEach(role -> accesses.addAll(role.getAccesses()));
        return accesses;
    }

    public String getNoNullEmail() {
        if (email != null) {
            return email;
        }

        for (SocialAccount socialAccount : socialAccounts) {
            if (socialAccount.getEmail() != null && socialAccount.getEmail().isEmpty() == false) {
                return socialAccount.getEmail();
            }
        }

        return null;
    }

    public Set<String> getAllUserEmails() {
        Set<String> emails = new HashSet<>();

        if (email != null && emails.isEmpty() == false) {
            emails.add(email);
        }

        for (SocialAccount socialAccount : socialAccounts) {
            if (socialAccount.getEmail() != null && socialAccount.getEmail().isEmpty() == false) {
                emails.add(socialAccount.getEmail());
            }
        }

        return emails;
    }
}
