package com.rcore.domain.auth.authorization.usecase.all.commands;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InitTwoFactorAuthorizationCommand {
    private String address;
    private ConfirmationCodeEntity.Recipient.SendingType sendingType;
    private Long ttl;
}
