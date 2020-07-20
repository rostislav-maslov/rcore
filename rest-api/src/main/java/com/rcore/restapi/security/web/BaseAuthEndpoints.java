package com.rcore.restapi.security.web;

import com.rcore.domain.user.exception.AdminUserIsExistException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.restapi.exceptions.BadRequestApiException;
import com.rcore.restapi.exceptions.UnauthorizedRequestApiException;
import com.rcore.restapi.routes.BaseAuthRoutes;
import com.rcore.restapi.security.web.api.AuthenticationDTO;
import com.rcore.restapi.security.web.api.RefreshTokenRequest;
import com.rcore.restapi.security.web.api.UserCredentialsDTO;
import com.rcore.restapi.security.web.application.BaseAuthService;
import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BaseAuthEndpoints {

    private final BaseAuthService baseAuthService;

    @PostMapping(value = BaseAuthRoutes.INIT_ADMIN)
    public OkApiResponse initAdmin(@RequestBody UserCredentialsDTO request) throws AdminUserIsExistException {
        return baseAuthService.initAdmin(request);
    }

    @PostMapping(value = BaseAuthRoutes.EMAIL)
    public SuccessApiResponse<AuthenticationDTO> login(@RequestBody UserCredentialsDTO request) throws TokenGenerateException, BadRequestApiException, UserBlockedException {
        return baseAuthService.login(request);
    }

    @PostMapping(value = BaseAuthRoutes.REFRESH)
    public SuccessApiResponse<AuthenticationDTO> refresh(@RequestBody RefreshTokenRequest request) throws InvalidTokenFormatException, UserBlockedException, TokenGenerateException, UnauthorizedRequestApiException {
        return baseAuthService.refresh(request);
    }

    @PostMapping(value = BaseAuthRoutes.LOGOUT)
    public OkApiResponse logout() throws  InvalidTokenFormatException, UserNotExistException, UserBlockedException, TokenExpiredException {
        return baseAuthService.logout();
    }
}
