package com.rcore.rest.api.commons.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorApiResponse<Error> {

    protected Error error;

    public static <Error> ErrorApiResponse<Error> of(Error error) {
        return new ErrorApiResponse<>(error);
    }

}
