package com.rcore.restapi.exceptions.handler;

import com.rcore.commons.exceptions.InvalidPhoneNumberFormatException;
import com.rcore.domain.user.exception.*;
import com.rcore.restapi.exceptions.*;
import com.rcore.restapi.web.api.response.ErrorApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestApiException.class, BaseApiException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleBadRequestApiException(BaseApiException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundApiException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleNotFountApiException(NotFoundApiException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
    @ExceptionHandler(value = TooManyRequestApiException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleTooManyRequestsApiException(TooManyRequestApiException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedRequestApiException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleUnauthorizedRequestApiException(UnauthorizedRequestApiException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleMaxUploadSizeException(Exception e) {
        return ErrorApiResponse.of(new MaxUploadSizeApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = InternalServerException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleInternalServerException(InternalServerException e) {
        return ErrorApiResponse.of(e.getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidPhoneNumberFormatException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleBadRequestApiException(Exception e) {
        return ErrorApiResponse.of(new InvalidPhoneNumberFormatApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoleIsRequiredException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleRoleIsRequiredException(RoleIsRequiredException e) {
        return ErrorApiResponse.of(new RoleIsRequiredApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PhoneIsRequiredException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handlePhoneIsRequiredException(PhoneIsRequiredException e) {
        return ErrorApiResponse.of(new PhoneIsRequiredApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidEmailException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidEmailException(InvalidEmailException e) {
        return ErrorApiResponse.of(new EmailIsRequiredApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleError(Exception e) {
        e.printStackTrace();
        return ErrorApiResponse.of(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message("Ошибка сервера. Попробуйте повторить позже")
                        .build())
                .domain("SERVER")
                .details(e.getMessage())
                .build()));
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public ErrorApiResponse<List<ExceptionDTO>> handleError(RuntimeException e) {
        log.error("Exception:", e);
        return ErrorApiResponse.of(Arrays.asList(ExceptionDTO.builder()
                .presentationData(ExceptionDTO.PresentationData.builder()
                        .message("Ошибка сервера. Попробуйте повторить позже")
                        .build())
                .domain("SERVER")
                .details(e.getMessage())
                .build()));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidOldPasswordException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidOldPasswordException(InvalidOldPasswordException e) {
        return ErrorApiResponse.of(new InvalidOldPasswordApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidNewPasswordException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidNewPasswordException(InvalidNewPasswordException e) {
        return ErrorApiResponse.of(new InvalidNewPasswordApiException().getErrors());
    }


}
