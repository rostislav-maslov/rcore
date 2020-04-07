package com.rcore.restapi.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(" ")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorApiResponse<Error> {

    protected Error error;

    public static <Error> ErrorApiResponse<Error> of(Error error) {
        return new ErrorApiResponse<>(error);
    }
}
