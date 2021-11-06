package com.rcore.domain.auth.role.exception;

public class RoleNotFoundException extends RoleDomainException {

    public RoleNotFoundException() {
        super("Role not found");
    }

    public RoleNotFoundException(String id) {
        super("Role not found by ID: " + id);
    }
}
