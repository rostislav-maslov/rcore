package com.rcore.restapi.security.web.application.impl;

import com.rcore.adapter.domain.token.TokenAdapter;
import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.adapter.domain.user.UserAdapter;
import com.rcore.adapter.domain.user.dto.TokenPairDTO;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenIsExpiredException;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.user.exception.*;
import com.rcore.restapi.security.exceptions.UserNotExistApiException;
import com.rcore.restapi.security.web.api.AuthenticationDTO;
import com.rcore.restapi.security.web.api.RefreshTokenRequest;
import com.rcore.restapi.security.web.api.UserCredentialsDTO;
import com.rcore.restapi.security.web.application.BaseAuthService;
import com.rcore.restapi.security.web.validators.EmailAuthRequestValidator;
import com.rcore.restapi.security.web.validators.RefreshTokenRequestValidator;
import com.rcore.restapi.utils.WebRequestUtils;
import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BaseAuthServiceImpl implements BaseAuthService {

    @Value("${rcore.security.jwt.key}")
    private String secretKey;

    private final UserAdapter userAdapter;
    private final TokenAdapter tokenAdapter;
    private final AuthTokenGenerator<AccessTokenDTO> accessTokenGenerator;
    private final AuthTokenGenerator<RefreshTokenDTO> refreshTokenGenerator;
    private final AccessTokenStorage accessTokenStorage;

    private final EmailAuthRequestValidator emailAuthRequestValidator;
    private final RefreshTokenRequestValidator refreshTokenRequestValidator;

    @Override
    public OkApiResponse initAdmin(UserCredentialsDTO request) throws AdminUserIsExistException {
        userAdapter.getAll().initAdminUser(request.getEmail(), request.getPassword());
        return OkApiResponse.of();
    }

    @Override
    public SuccessApiResponse<AuthenticationDTO> login(UserCredentialsDTO request) throws UserBlockedException, TokenGenerateException {
        emailAuthRequestValidator.validate(request);
        TokenPairDTO tokenPair = null;
        try {
            tokenPair = userAdapter.getAll()
                    .authentication(request.getEmail(), request.getPassword());
        } catch (UserNotFoundException | AuthenticationException e) {
            throw emailAuthRequestValidator.invalidEmailOrPassword;
        }

        return SuccessApiResponse.of(AuthenticationDTO.builder()
                .accessToken(accessTokenGenerator.generate(tokenPair.getAccessToken(), secretKey))
                .refreshToken(refreshTokenGenerator.generate(tokenPair.getRefreshToken(), secretKey))
                .build());
    }

    @Override
    public SuccessApiResponse<AuthenticationDTO> refresh(RefreshTokenRequest request) throws InvalidTokenFormatException, UserBlockedException, TokenGenerateException {
        refreshTokenRequestValidator.validate(request);
        AccessTokenDTO accessToken = accessTokenGenerator.parseToken(WebRequestUtils.getAuthToken(), secretKey);

        //Проверка существование accessToken
        accessTokenStorage.findById(accessToken.getId())
                .orElseThrow(() -> new UserNotExistApiException());

        TokenPairDTO tokenPair = null;
        try {
            tokenPair = userAdapter.getAll()
                    .getNewTokenPairByRefreshToken(refreshTokenGenerator.parseToken(request.getRefreshToken(), secretKey));
        } catch (UserNotFoundException | AuthenticationException e) {
            throw refreshTokenRequestValidator.invalidToken;
        } catch (RefreshTokenIsExpiredException e) {
            throw refreshTokenRequestValidator.expiredToken;
        }

        return SuccessApiResponse.of(AuthenticationDTO.builder()
                .accessToken(accessTokenGenerator.generate(tokenPair.getAccessToken(), secretKey))
                .refreshToken(refreshTokenGenerator.generate(tokenPair.getRefreshToken(), secretKey))
                .build());
    }

    @Override
    public OkApiResponse logout() throws InvalidTokenFormatException, UserNotExistException, UserBlockedException, TokenExpiredException {
        UserDTO user = tokenAdapter.getUserByAccessToken(accessTokenGenerator.parseToken(WebRequestUtils.getAuthToken(), secretKey));
        tokenAdapter.logout(user);
        return OkApiResponse.of();
    }
}
