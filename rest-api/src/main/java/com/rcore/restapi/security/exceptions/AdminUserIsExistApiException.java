package com.rcore.restapi.security.exceptions;

import lombok.NoArgsConstructor;

public class AdminUserIsExistApiException extends ApiAuthenticationException {

    public AdminUserIsExistApiException() {
        super("Администратор уже инициализирован", "INIT_ADMIN", "ADMIN_USER_IS_EXIST");
    }

    public static AdminUserIsExistApiException of() {
        return new AdminUserIsExistApiException();
    }
}
