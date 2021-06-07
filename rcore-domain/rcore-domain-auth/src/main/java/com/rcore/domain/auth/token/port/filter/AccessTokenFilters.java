package com.rcore.domain.auth.token.port.filter;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.commons.port.dto.SearchFilters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class AccessTokenFilters extends SearchFilters {
    private String credentialId;
    private String createByRefreshTokenId;
    private RefreshTokenEntity.Status status;
}
