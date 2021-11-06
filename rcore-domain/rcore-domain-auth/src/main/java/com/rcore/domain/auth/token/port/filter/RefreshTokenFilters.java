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
public class RefreshTokenFilters extends SearchFilters {
    private String credentialId;
    private RefreshTokenEntity.Status status;
}
