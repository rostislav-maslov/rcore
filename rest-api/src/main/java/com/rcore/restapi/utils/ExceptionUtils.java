package com.rcore.restapi.utils;

import com.rcore.restapi.exceptions.*;
import com.rcore.restapi.security.exceptions.ApiAuthenticationException;
import com.rcore.restapi.security.exceptions.AuthForbiddenApiException;
import com.rcore.restapi.security.exceptions.AuthInternalServerException;

public class ExceptionUtils {

    public static void checkRestApiException(Exception e) {
        if (e instanceof BaseApiException) {
            BaseApiException baseApiException = (BaseApiException) e;
            if (baseApiException instanceof BadRequestApiException)
                throw new BadRequestApiException(baseApiException.getErrors());
            else if (baseApiException instanceof NotFoundApiException)
                throw new NotFoundApiException(baseApiException.getErrors());
            else if (baseApiException instanceof InternalServerException)
                throw new InternalServerException(baseApiException.getErrors());
            else if (baseApiException instanceof MaxUploadSizeApiException)
                throw new MaxUploadSizeApiException();
            else if (baseApiException instanceof TooManyRequestApiException)
                throw new TooManyRequestApiException(baseApiException.getErrors());
            else if (baseApiException instanceof UnauthorizedRequestApiException)
                throw new UnauthorizedRequestApiException(baseApiException.getErrors());
        } else if (e instanceof ApiAuthenticationException) {



        }
    }

    public static void checkCauseRestApiException(Exception e) {
        if (e.getCause() instanceof UnauthorizedRequestApiException)
            throw new UnauthorizedRequestApiException(((UnauthorizedRequestApiException) e).getErrors());
        if (e.getCause() instanceof BaseApiException) {
            BaseApiException baseApiException = (BaseApiException) e.getCause();
            if (baseApiException instanceof BadRequestApiException)
                throw new BadRequestApiException(baseApiException.getErrors());
            else if (baseApiException instanceof NotFoundApiException)
                throw new NotFoundApiException(baseApiException.getErrors());
            else if (baseApiException instanceof InternalServerException)
                throw new InternalServerException(baseApiException.getErrors());
            else if (baseApiException instanceof MaxUploadSizeApiException)
                throw new MaxUploadSizeApiException();
            else if (baseApiException instanceof TooManyRequestApiException)
                throw new TooManyRequestApiException(baseApiException.getErrors());
            else if (baseApiException instanceof UnauthorizedRequestApiException)
                throw new UnauthorizedRequestApiException(baseApiException.getErrors());
        } else if (e.getCause() instanceof ApiAuthenticationException) {
            ApiAuthenticationException apiAuthenticationException = (ApiAuthenticationException) e.getCause();
            if (apiAuthenticationException instanceof AuthForbiddenApiException)
                throw new AuthForbiddenApiException(apiAuthenticationException.getErrors());
        }
    }

}
