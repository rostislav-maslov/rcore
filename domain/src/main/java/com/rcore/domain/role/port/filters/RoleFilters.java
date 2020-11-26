package com.rcore.domain.role.port.filters;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.role.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class RoleFilters extends SearchRequest {
    private List<RoleEntity.AuthType> authorizationTypes;
}
