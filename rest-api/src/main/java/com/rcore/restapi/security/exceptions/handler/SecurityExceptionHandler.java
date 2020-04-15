package com.rcore.restapi.security.exceptions.handler;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.restapi.exceptions.ExceptionDTO;
import com.rcore.restapi.security.exceptions.*;
import com.rcore.restapi.web.api.response.ErrorApiResponse;
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
            InvalidTokenFormat.class
    })
    public ErrorApiResponse<ExceptionDTO> handleForbidden(ApiAuthenticationException e) {
        return ErrorApiResponse.of(e.getError());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserBlockedApiException.class)
    public ErrorApiResponse<ExceptionDTO> handleUnauthorized(AuthenticationException e) {
        return ErrorApiResponse.of(ExceptionDTO.builder().build());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AccessDeniedException.class, AuthorizationException.class})
    public ErrorApiResponse<ExceptionDTO> handleAccessDenied(Exception e) {
         return ErrorApiResponse.of(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message("Ошибка прав доступа")
                        .build())
                .domain("SERVER")
                .details(e.getMessage())
                .build());
    }
}
