package com.ub.core.security.annotationSecurity;

import com.ub.core.base.role.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface AvailableForRoles {
    Class<? extends Role>[] value();
}
