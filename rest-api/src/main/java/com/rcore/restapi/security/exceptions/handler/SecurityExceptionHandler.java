package com.rcore.restapi.security.exceptions.handler;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.exception.*;
import com.rcore.restapi.exceptions.ExceptionDTO;
import com.rcore.restapi.security.exceptions.*;
import com.rcore.restapi.web.api.response.ErrorApiResponse;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            UserNotExistApiException.class,
            TokenExpiredApiException.class,
            InvalidTokenFormatApiException.class,
            AuthForbiddenApiException.class
    })
    public ErrorApiResponse<List<ExceptionDTO>> handleApiForbidden(ApiAuthenticationException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({UserNotExistException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleForbidden(UserNotExistException e) {
        log.error("Token exception: ", e);
        return ErrorApiResponse.of(new UserNotExistApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({TokenExpiredException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleForbidden(TokenExpiredException e) {
        return ErrorApiResponse.of(new TokenExpiredApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({UserBlockedApiException.class, UserBlockedException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleApiUnauthorized(Exception e) {
        return ErrorApiResponse.of(new UserBlockedApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(BlockedUserTriesToLogoutException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleBlockedUserTriesToLogoutException(Exception e) {
        return ErrorApiResponse.of(new BlockedUserTriesToLogoutApiException().getErrors());
    }

//    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(UserBlockedException.class)
//    public ErrorApiResponse<List<ExceptionDTO>> handleUnauthorized(AuthenticationException e) {
//        return ErrorApiResponse.of(ExceptionDTO.builder().build());
//    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleAuthorizationException(Exception e) {
        return ErrorApiResponse.of(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Ошибка аутентификации")
                        .message("Доступ запрещен")
                        .build())
                .domain("SERVER")
                .details("ACCESS_DENIED")
                .build()));
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleAccessDeniedException(Exception e) {
        return ErrorApiResponse.of(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .title("Доступ запрещён")
                        .message("Недостаточно прав для доступа. Обратитесь к администратору сервиса или вернитесь в предыдущий раздел")
                        .build())
                .domain("AUTH")
                .details("ACCESS_DENIED")
                .build()));
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({com.rcore.domain.token.exception.AuthenticationException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleAuthenticationException(AuthenticationException e) {
        log.error("Token exception: ", e);
        return ErrorApiResponse.of(new UserNotExistApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AdminUserIsExistException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInitAdmin(Exception e) {
        return ErrorApiResponse.of(AdminUserIsExistApiException.of().getErrors());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
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

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedApiException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleAuthenticationException(UnauthorizedApiException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

}
