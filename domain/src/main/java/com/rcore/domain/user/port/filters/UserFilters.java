package com.rcore.domain.user.port.filters;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.user.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UserFilters extends SearchRequest {
    private String roleId;
    private List<RoleEntity.AuthType> authTypes;
    private UserStatus status;
}
