package com.rcore.rest.api.commons.response;

import lombok.Getter;

@Getter
public class OkApiResponse extends SuccessApiResponse<String> {

    public OkApiResponse() {
        super("OK");
    }

    public static OkApiResponse of() {
        return new OkApiResponse();
    }
}
