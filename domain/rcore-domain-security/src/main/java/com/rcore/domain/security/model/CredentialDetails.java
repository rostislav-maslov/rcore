package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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
     * Доступы учетной записи
     */
    private List<String> accesses;

    public boolean hasAccess(String access) {
        return accesses.contains(access);
    }
}
