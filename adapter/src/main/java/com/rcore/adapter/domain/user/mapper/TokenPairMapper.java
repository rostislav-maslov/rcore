package com.rcore.adapter.domain.user.mapper;

import com.rcore.adapter.domain.token.mapper.AccessTokenMapper;
import com.rcore.adapter.domain.token.mapper.RefreshTokenMapper;
import com.rcore.adapter.domain.user.dto.TokenPairDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.token.entity.TokenPair;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenPairMapper implements ExampleDataMapper<TokenPair, TokenPairDTO> {

    private final AccessTokenMapper accessTokenMapper;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public TokenPairDTO map(TokenPair tokenPair) {
        return TokenPairDTO.builder()
                .accessToken(accessTokenMapper.map(tokenPair.getAccessToken()))
                .refreshToken(refreshTokenMapper.map(tokenPair.getRefreshToken()))
                .build();
    }

    @Override
    public TokenPair inverseMap(TokenPairDTO tokenPairDTO) {
        return TokenPair.builder()
                .accessToken(accessTokenMapper.inverseMap(tokenPairDTO.getAccessToken()))
                .refreshToken(refreshTokenMapper.inverseMap(tokenPairDTO.getRefreshToken()))
                .build();
    }
}
