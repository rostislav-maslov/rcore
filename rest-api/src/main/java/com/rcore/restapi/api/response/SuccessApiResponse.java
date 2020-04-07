package com.rcore.restapi.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(" ")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SuccessApiResponse<Result> {

    protected Result result;

    public static <Result> SuccessApiResponse<Result> of(Result result) {
        return new SuccessApiResponse<>(result);
    }
}
