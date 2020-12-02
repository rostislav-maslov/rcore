package com.rcore.domain.auth.authorization.usecase.all.commands;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class CreateAuthorizationCommand {
    private AuthorizationEntity.Type type;
    private AuthorizationEntity.AuthorizationData authorizationData;
    private AuthorizationEntity.Status status = AuthorizationEntity.Status.NEW;
    private String rejectionCause;
    private String accessTokenId;
    private String refreshTokenId;

    public static CreateAuthorizationCommand successfulPasswordAuthorization(String username, String password, String accessTokenId, String refreshTokenId) {
        return CreateAuthorizationCommand.builder()
                .type(AuthorizationEntity.Type.PASSWORD)
                .status(AuthorizationEntity.Status.SUCCESS)
                .accessTokenId(accessTokenId)
                .refreshTokenId(refreshTokenId)
                .authorizationData(AuthorizationEntity.AuthorizationData.passwordAuthenticationData(username, password))
                .build();
    }
}
