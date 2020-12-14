package com.rcore.rest.api.commons.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SuccessApiResponse<Result> {

    protected Result result;

    public static <Result> SuccessApiResponse<Result> of(Result result) {
        return new SuccessApiResponse<>(result);
    }

    public static SuccessApiResponse of() {
        return new SuccessApiResponse<>();
    }

}
