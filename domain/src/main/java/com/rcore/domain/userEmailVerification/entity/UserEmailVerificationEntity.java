package com.rcore.domain.userEmailVerification.entity;

import com.rcore.domain.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class UserEmailVerificationEntity extends BaseEntity {
    protected String id;
    protected String code = null;
    protected Boolean verified = false;
    protected Date verifiedDate = null;
    protected Date expiredDate = null;
    protected String userIdAfterConfirm = null;
}
