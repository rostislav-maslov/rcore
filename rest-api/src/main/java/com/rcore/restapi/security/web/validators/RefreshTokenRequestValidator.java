package com.rcore.restapi.security.web.validators;

import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.restapi.exceptions.UnauthorizedRequestApiException;
import com.rcore.restapi.security.exceptions.UserNotExistApiException;
import com.rcore.restapi.security.web.api.RefreshTokenRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RefreshTokenRequestValidator {

    public final UserNotExistApiException invalidToken = new UserNotExistApiException();
    public final UnauthorizedRequestApiException expiredToken = new UnauthorizedRequestApiException("Доступ запрещён", "Пожалуйста, выполните вход повторно", "AUTH", "UNSUCCESSFUL_REFRESH");

    public void validate(RefreshTokenRequest request) {
        if (!StringUtils.hasText(request.getRefreshToken()))
            throw invalidToken;
    }

}
