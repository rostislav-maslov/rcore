package com.rcore.domain.security.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Модель с доступами учетной записи
 */
@EqualsAndHashCode
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

    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Role {
        private String name;
    }
}
