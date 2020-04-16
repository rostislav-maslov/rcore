package com.rcore.restapi.security.web.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthenticationDTO {
    private String accessToken;
    private String refreshToken;
}
