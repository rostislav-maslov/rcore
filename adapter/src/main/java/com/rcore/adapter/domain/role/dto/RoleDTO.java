package com.rcore.adapter.domain.role.dto;

import com.rcore.domain.access.entity.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoleDTO {
    private String id;
    private String title;
    private String locale;
    private Set<Access> accesses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String timeZone;
}
