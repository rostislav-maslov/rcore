package com.rcore.domain.auth.role.port.filters;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.commons.port.dto.SearchFilters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class RoleFilters extends SearchFilters {
    private List<RoleEntity.AuthType> authorizationTypes;
}
