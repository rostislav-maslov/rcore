package com.rcore.domain.auth.credential.usecase.secured.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UpdateCredentialCommand extends CreateCredentialCommand {
    private String id;
}
