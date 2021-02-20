package com.rcore.domain.auth.passwordRecovery.port;

import com.rcore.domain.auth.passwordRecovery.entity.PasswordRecoveryEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;

public interface PasswordRecoveryRepository extends CRUDRepository<String, PasswordRecoveryEntity, SearchFilters> {
}
