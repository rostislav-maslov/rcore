package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Модель с доступами учетной записи
 */
@AllArgsConstructor
@Getter
public class CredentialDetails {

    /**
     * Идентификатор учетной записи
     */
    private String id;

    /**
     * Массив ролей учетной записи
     */
    private List<Role> roles;

    @AllArgsConstructor
    @Getter
    public static class Role {
        private String id;
        private String name;
        private boolean hasBoundlessAccess;
        private List<String> accesses;
    }

    public boolean hasAccess(String access) {
        if (roles == null)
            return false;

        //Если есть роль с неограниченным доступом, то возврящаем true
        if (roles.stream().filter(Role::isHasBoundlessAccess).findFirst().isPresent())
            return true;

        return roles.stream()
                .flatMap(role -> role.getAccesses().stream())
                .collect(Collectors.toList())
                .contains(access);
    }
}
