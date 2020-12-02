package com.rcore.domain.auth.credential.port;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.SearchFilters;

import java.util.Optional;

public interface CredentialRepository extends CRUDRepository<String, CredentialEntity, SearchFilters> {
    Optional<CredentialEntity> findByUsername(String username);
    Optional<CredentialEntity> findByPhone(String phone);
}
