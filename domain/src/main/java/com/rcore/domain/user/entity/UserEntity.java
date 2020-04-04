package com.rcore.domain.user.entity;

import com.rcore.domain.base.entity.BaseEntity;
import com.rcore.domain.role.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class UserEntity extends BaseEntity {
    protected String id;

    protected Set<RoleEntity> roles = new HashSet<RoleEntity>();

    protected UserStatus userStatus = UserStatus.ACTIVE;

    protected String firstName = "";
    protected String lastName = "";
    protected String secondName = "";
    protected String fullName = "";

    protected String email;
    private Long phoneNumber;
    private String login;
    private String password;

    private List<SocialAccount> socialAccounts = new ArrayList<>();

    private Integer fails = 0;
    private Date lastFailDate = new Date();
}
