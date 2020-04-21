package com.rcore.admincore.exceptions.handler;

import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.restapi.exceptions.BaseApiException;
import com.rcore.restapi.exceptions.ExceptionDTO;
import com.rcore.restapi.web.api.response.ErrorApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(3)
@RestControllerAdvice
public class AdminCoreExceptionsHandler {


    @ExceptionHandler({UserAlreadyExistException.class})
    public ErrorApiResponse<ExceptionDTO> handleUserExistException(BaseApiException e) {
        return ErrorApiResponse.of(e.getError());
    }

}
