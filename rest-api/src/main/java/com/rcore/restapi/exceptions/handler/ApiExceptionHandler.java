package com.rcore.restapi.exceptions.handler;

import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForCreateException;
import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForUpdateException;
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
    @ExceptionHandler({com.rcore.commons.exceptions.InvalidPhoneNumberFormatException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleBadRequestApiException(Exception e) {
        return ErrorApiResponse.of(new InvalidPhoneNumberFormatApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidPhoneFormatForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidPhoneNumberFormatException(InvalidPhoneFormatForUpdateException e) {
        return ErrorApiResponse.of(new InvalidPhoneFormatForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidPhoneFormatForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidPhoneFormatForCreateException(InvalidPhoneFormatForCreateException e) {
        return ErrorApiResponse.of(new InvalidPhoneFormatForCreateApiException().getErrors());
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

    /**
     * User create exceptions
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidFirstNameForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidFirstNameForCreateException(InvalidFirstNameForCreateException e) {
        return ErrorApiResponse.of(new InvalidFirstNameForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidLastNameForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidLastNameForCreateException(InvalidLastNameForCreateException e) {
        return ErrorApiResponse.of(new InvalidLastNameForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidAccountStatusForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidAccountStatusForCreateException(InvalidAccountStatusForCreateException e) {
        return ErrorApiResponse.of(new InvalidAccountStatusForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidRoleForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidRoleForCreateExceptionException(InvalidRoleForCreateException e) {
        return ErrorApiResponse.of(new RoleNotFoundForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {RoleIsRequiredForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleRoleIsRequiredForCreateException(RoleIsRequiredForCreateException e) {
        return ErrorApiResponse.of(new RoleIsRequiredForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {PhoneIsRequiredForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handlePhoneIsRequiredForCreateException(PhoneIsRequiredForCreateException e) {
        return ErrorApiResponse.of(new PhoneIsRequiredForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidEmailForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidEmailForCreateException(InvalidEmailForCreateException e) {
        return ErrorApiResponse.of(new InvalidEmailForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UserWithPhoneAlreadyExistForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleUserWithPhoneAlreadyExistForCreateException(UserWithPhoneAlreadyExistForCreateException e) {
        return ErrorApiResponse.of(new UserWithPhoneAlreadyExistForCreateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UserWithEmailAlreadyExistForCreateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleUserWithEmailAlreadyExistForCreateException(UserWithEmailAlreadyExistForCreateException e) {
        return ErrorApiResponse.of(new UserWithEmailAlreadyExistForCreateApiException().getErrors());
    }

    /**
     * User update exceptions
     */

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidFirstNameForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidFirstNameException(InvalidFirstNameForUpdateException e) {
        return ErrorApiResponse.of(new InvalidFirstNameForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidLastNameForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidLastNameException(InvalidLastNameForUpdateException e) {
        return ErrorApiResponse.of(new InvalidLastNameForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidAccountStatusForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidAccountStatusException(InvalidAccountStatusForUpdateException e) {
        return ErrorApiResponse.of(new InvalidAccountStatusForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({InvalidRoleForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleRoleNotFoundException(InvalidRoleForUpdateException e) {
        return ErrorApiResponse.of(new RoleNotFoundForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoleIsRequiredForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleRoleIsRequiredException(RoleIsRequiredForUpdateException e) {
        return ErrorApiResponse.of(new RoleIsRequiredForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PhoneIsRequiredForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handlePhoneIsRequiredException(PhoneIsRequiredForUpdateException e) {
        return ErrorApiResponse.of(new PhoneIsRequiredForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidEmailForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleInvalidEmailException(InvalidEmailForUpdateException e) {
        return ErrorApiResponse.of(new EmailIsRequiredApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UserWithPhoneAlreadyExistForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleUserWithPhoneAlreadyExistForUpdateException(UserWithPhoneAlreadyExistForUpdateException e) {
        return ErrorApiResponse.of(new UserWithPhoneAlreadyExistForUpdateApiException().getErrors());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {UserWithEmailAlreadyExistForUpdateException.class})
    public ErrorApiResponse<List<ExceptionDTO>> handleUserWithEmailAlreadyExistForUpdateException(UserWithEmailAlreadyExistForUpdateException e) {
        return ErrorApiResponse.of(new UserWithEmailAlreadyExistForUpdateApiException().getErrors());
    }
}
