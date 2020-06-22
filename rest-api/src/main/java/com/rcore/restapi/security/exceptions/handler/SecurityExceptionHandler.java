package com.rcore.restapi.security.exceptions.handler;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.exception.RefreshTokenIsExpiredException;
import com.rcore.domain.user.exception.AdminUserIsExistException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.restapi.exceptions.ExceptionDTO;
import com.rcore.restapi.security.exceptions.*;
import com.rcore.restapi.web.api.response.ErrorApiResponse;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@Order(100)
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            UserNotExistApiException.class,
            TokenExpiredApiException.class,
            InvalidTokenFormatApiException.class
    })
    public ErrorApiResponse<List<ExceptionDTO>> handleApiForbidden(ApiAuthenticationException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            UserNotExistException.class,
            TokenExpiredException.class
    })
    public ErrorApiResponse<List<ExceptionDTO>> handleForbidden(Exception e) {
        return ErrorApiResponse.of(new UserNotExistApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UserBlockedApiException.class, UserBlockedException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleApiUnauthorized(Exception e) {
        return ErrorApiResponse.of(new UserBlockedApiException().getErrors());
    }

//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(UserBlockedException.class)
//    public ErrorApiResponse<List<ExceptionDTO>> handleUnauthorized(AuthenticationException e) {
//        return ErrorApiResponse.of(ExceptionDTO.builder().build());
//    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AccessDeniedException.class, AuthorizationException.class, com.rcore.domain.token.exception.AuthenticationException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleAccessDenied(Exception e) {
        return ErrorApiResponse.of(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message("Ошибка прав доступа")
                        .build())
                .domain("SERVER")
                .details("Доступ запрещен")
                .build()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AdminUserIsExistException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInitAdmin(Exception e) {
        return ErrorApiResponse.of(AdminUserIsExistApiException.of().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTokenFormatException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidToken(Exception e) {
        return ErrorApiResponse.of(new InvalidTokenFormatApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({AuthInternalServerException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleAuthInternalServerException(AuthInternalServerException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({TokenGenerateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleTokenGenerate(Exception e) {
        return ErrorApiResponse.of(new TokenGenerateApiException().getErrors());
    }


}
