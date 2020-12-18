package com.rcore.domain.role.port.filters;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.role.entity.RoleEntity;
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
