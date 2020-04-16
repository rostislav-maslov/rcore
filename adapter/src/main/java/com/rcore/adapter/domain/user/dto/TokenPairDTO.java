package com.rcore.adapter.domain.user.dto;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenPairDTO {
    private AccessTokenDTO accessToken;
    private RefreshTokenDTO refreshToken;
}
