package com.rcore.domain.auth.credential.port;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;

import java.util.Optional;

public interface CredentialRepository extends CRUDRepository<String, CredentialEntity, SearchFilters> {
    Optional<CredentialEntity> findByUsername(String username);

    Optional<CredentialEntity> findByEmail(String email);

    Optional<CredentialEntity> findByPhone(String phone);
}
