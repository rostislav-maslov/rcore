package com.rcore.domain.userEmailVerification.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;

import java.util.Date;
import java.util.List;

public interface UserEmailVerificationRepository extends CRUDRepository<String, UserEmailVerificationEntity> {
    public List<UserEmailVerificationEntity> findNotVerifiedAndActive(String email, String code);
}
