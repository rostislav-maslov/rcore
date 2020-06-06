package com.rcore.restapi.web.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorApiResponse<Error> {

    protected Error errors;

    public static <Error> ErrorApiResponse<Error> of(Error error) {
        return new ErrorApiResponse<>(error);
    }
}
