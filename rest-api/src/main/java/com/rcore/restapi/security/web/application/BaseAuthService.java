package com.rcore.restapi.security.web.application;

import com.rcore.domain.user.exception.AdminUserIsExistException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.restapi.security.web.api.AuthenticationDTO;
import com.rcore.restapi.security.web.api.RefreshTokenRequest;
import com.rcore.restapi.security.web.api.UserCredentialsDTO;
import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;

public interface BaseAuthService {
    OkApiResponse initAdmin(UserCredentialsDTO request) throws AdminUserIsExistException;
    SuccessApiResponse<AuthenticationDTO> login(UserCredentialsDTO request) throws UserBlockedException, TokenGenerateException;
    SuccessApiResponse<AuthenticationDTO> refresh(RefreshTokenRequest request) throws InvalidTokenFormatException, UserBlockedException, TokenGenerateException;
    OkApiResponse logout() throws InvalidTokenFormatException, UserNotExistException, UserBlockedException, TokenExpiredException;
}
