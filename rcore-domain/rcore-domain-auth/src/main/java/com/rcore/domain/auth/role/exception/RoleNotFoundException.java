package com.rcore.domain.auth.role.exception;

import com.rcore.domain.commons.exception.DomainException;

public class RoleNotFoundException extends DomainException {

    public RoleNotFoundException() {
        super("Role not found");
    }

    public RoleNotFoundException(String id) {
        super("Role not found by ID: " + id);
    }
}
