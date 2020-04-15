package com.rcore.adapter.domain.token.mapper;

import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.token.entity.RefreshTokenEntity;

public class RefreshTokenMapper implements ExampleDataMapper<RefreshTokenEntity, RefreshTokenDTO> {

    @Override
    public RefreshTokenDTO map(RefreshTokenEntity entity) {
        return RefreshTokenDTO.builder()
                .createdAt(entity.getCreatedAt())
                .createFromTokenId(entity.getCreateFromTokenId())
                .createFromType(entity.getCreateFromType())
                .expireAt(entity.getExpireAt())
                .expireTimeAccessToken(entity.getExpireTimeAccessToken())
                .expireTimeRefreshToken(entity.getExpireTimeRefreshToken())
                .id(entity.getId())
                .salt(entity.getSalt())
                .status(entity.getStatus())
                .timeZone(entity.getTimeZone())
                .updatedAt(entity.getUpdatedAt())
                .userId(entity.getUserId())
                .build();
    }

    @Override
    public RefreshTokenEntity inverseMap(RefreshTokenDTO refreshTokenDTO) {
        return RefreshTokenEntity.builder()
                .createdAt(refreshTokenDTO.getCreatedAt())
                .createFromTokenId(refreshTokenDTO.getCreateFromTokenId())
                .createFromType(refreshTokenDTO.getCreateFromType())
                .expireAt(refreshTokenDTO.getExpireAt())
                .expireTimeAccessToken(refreshTokenDTO.getExpireTimeAccessToken())
                .expireTimeRefreshToken(refreshTokenDTO.getExpireTimeRefreshToken())
                .id(refreshTokenDTO.getId())
                .salt(refreshTokenDTO.getSalt())
                .status(refreshTokenDTO.getStatus())
                .timeZone(refreshTokenDTO.getTimeZone())
                .updatedAt(refreshTokenDTO.getUpdatedAt())
                .userId(refreshTokenDTO.getUserId())
                .build();
    }
}
