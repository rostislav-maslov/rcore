package com.rcore.domain.auth.authorization.usecase.all.commands;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConfirmTwoFactorAuthorizationCommand {
    private String address;
    private ConfirmationCodeEntity.Recipient.SendingType sendingType;
    private String code;
}
