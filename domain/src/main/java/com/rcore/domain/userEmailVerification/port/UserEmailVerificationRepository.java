package com.rcore.domain.userEmailVerification.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;

import java.util.Date;
import java.util.List;

public abstract class UserEmailVerificationRepository<Entity extends UserEmailVerificationEntity> extends CRUDRepository<String, Entity> {
    public abstract  List<Entity> findNotVerifiedAndActive(String email, String code);
}
