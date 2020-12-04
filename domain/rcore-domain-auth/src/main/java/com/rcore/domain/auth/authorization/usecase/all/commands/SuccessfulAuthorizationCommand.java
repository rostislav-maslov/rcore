package com.rcore.domain.auth.authorization.usecase.all.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SuccessfulAuthorizationCommand {
    private String accessTokeId;
    private String refreshTokeId;
    private String authorizationId;
}
