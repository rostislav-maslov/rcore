package com.rcore.restapi.security.web.api;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
