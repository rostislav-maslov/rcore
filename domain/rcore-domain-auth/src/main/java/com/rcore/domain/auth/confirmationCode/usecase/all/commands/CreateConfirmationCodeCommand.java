package com.rcore.domain.auth.confirmationCode.usecase.all.commands;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
@Builder
public class CreateConfirmationCodeCommand {
    private String authorizationId;
    private ConfirmationCodeEntity.Recipient recipient;
    private Long ttl;
}
