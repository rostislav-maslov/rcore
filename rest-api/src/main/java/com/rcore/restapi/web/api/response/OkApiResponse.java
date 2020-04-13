package com.rcore.restapi.web.api.response;

import lombok.Getter;

@Getter
public class OkApiResponse extends SuccessApiResponse<String> {

    public OkApiResponse() {
        this.result = "OK";
    }

    public static OkApiResponse of() {
        return new OkApiResponse();
    }
}
