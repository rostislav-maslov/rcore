package com.rcore.domain.user.entity;

import com.rcore.domain.base.entity.BaseEntity;
import com.rcore.domain.role.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class UserEntity extends BaseEntity {
    protected String id;

    protected Set<Role> roles = new HashSet<Role>();

    protected UserStatus userStatus = UserStatus.ACTIVE;

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
    protected Date lastFailDate = new Date();

    public Boolean hasRole(Role role){
        for(Role roleEntity : roles){
            if(roleEntity.getId().equals(role.getId())) return true;
        }

        return false;
    }

    public String getNoNullEmail() {
        if (email != null) {
            return email;
        }

        for(SocialAccount socialAccount : socialAccounts){
            if(socialAccount.getEmail() != null && socialAccount.getEmail().isEmpty() == false){
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

        for(SocialAccount socialAccount : socialAccounts){
            if(socialAccount.getEmail() != null && socialAccount.getEmail().isEmpty() == false){
                emails.add(socialAccount.getEmail());
            }
        }

        return emails;
    }
}
