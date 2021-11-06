package com.rcore.domain.auth.token.port;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.filter.RefreshTokenFilters;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;

import java.util.List;

public interface RefreshTokenRepository extends CRUDRepository<String, RefreshTokenEntity, RefreshTokenFilters> {

    void expireRefreshToken(RefreshTokenEntity refreshTokenEntity);
    List<RefreshTokenEntity> findAllActiveByUserId(String userId);
}
