package com.rcore.adapter.domain.role.dto;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.role.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoleDTO {
    private String id;
    private String name;
    private String locale;
    private Set<Access> accesses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timeZone;
    private List<RoleEntity.AuthType> availableAuthTypes;
}
