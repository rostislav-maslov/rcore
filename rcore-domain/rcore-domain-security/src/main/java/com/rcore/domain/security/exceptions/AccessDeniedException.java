package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

/**
 * Ошибка прав доступа
 */
public class AccessDeniedException extends DomainException {

    public AccessDeniedException() {
        super("Access denied");
    }
}
