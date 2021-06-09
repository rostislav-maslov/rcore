package com.rcore.domain.security.exceptions;

import com.rcore.domain.commons.exception.DomainException;

/**
 * Ошибка прав доступа
 */
public class AccessDeniedException extends DomainException {

    public AccessDeniedException() {
        super(new Error(
                "AUTH",
                AuthorizationErrorReason.ACCESS_DENIED.name(),
                "Access denied"
        ));
    }
}
