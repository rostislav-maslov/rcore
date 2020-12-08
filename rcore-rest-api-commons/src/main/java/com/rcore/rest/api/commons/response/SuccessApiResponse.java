package com.rcore.rest.api.commons.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessApiResponse<Result> {

    protected Result result;

    public static <Result> SuccessApiResponse<Result> of(Result result) {
        return new SuccessApiResponse<>(result);
    }

}
