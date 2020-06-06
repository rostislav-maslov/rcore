package com.rcore.domain.user.entity;

import lombok.Data;

@Data
public class SocialAccount {
    private String type;
    private String id;
    private String email;
}
