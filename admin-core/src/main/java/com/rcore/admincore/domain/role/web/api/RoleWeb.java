package com.rcore.admincore.domain.role.web.api;

import com.rcore.domain.access.entity.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RoleWeb {
    private String id;
    private String name;
    private String locale;
    private Set<Access> accesses;
}
