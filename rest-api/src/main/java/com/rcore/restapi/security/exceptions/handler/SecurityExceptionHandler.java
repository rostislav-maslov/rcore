package com.rcore.restapi.security.exceptions.handler;

import com.rcore.domain.token.exception.AuthorizationException;
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

@Order(1)
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            UserNotExistApiException.class,
            TokenExpiredApiException.class,
            InvalidTokenFormatApiException.class
    })
    public ErrorApiResponse<ExceptionDTO> handleApiForbidden(ApiAuthenticationException e) {
        return ErrorApiResponse.of(e.getError());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            UserNotExistException.class,
            TokenExpiredException.class
    })
    public ErrorApiResponse<ExceptionDTO> handleForbidden(ApiAuthenticationException e) {
        return ErrorApiResponse.of(e.getError());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserBlockedApiException.class)
    public ErrorApiResponse<ExceptionDTO> handleApiUnauthorized(AuthenticationException e) {
        return ErrorApiResponse.of(ExceptionDTO.builder().build());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserBlockedException.class)
    public ErrorApiResponse<ExceptionDTO> handleUnauthorized(AuthenticationException e) {
        return ErrorApiResponse.of(ExceptionDTO.builder().build());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AccessDeniedException.class, AuthorizationException.class, com.rcore.domain.token.exception.AuthenticationException.class})
    public ErrorApiResponse<ExceptionDTO> handleAccessDenied(Exception e) {
        return ErrorApiResponse.of(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message("Ошибка прав доступа")
                        .build())
                .domain("SERVER")
                .details(e.getMessage())
                .build());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AdminUserIsExistException.class})
    public ErrorApiResponse<ExceptionDTO> handleInitAdmin(Exception e) {
        return ErrorApiResponse.of(AdminUserIsExistApiException.of().getError());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTokenFormatException.class})
    public ErrorApiResponse<ExceptionDTO> handleInvalidToken(Exception e) {
        return ErrorApiResponse.of(new InvalidTokenFormatApiException().getError());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({TokenGenerateException.class})
    public ErrorApiResponse<ExceptionDTO> handleTokenGenerate(Exception e) {
        return ErrorApiResponse.of(new TokenGenerateApiException().getError());
    }
}
