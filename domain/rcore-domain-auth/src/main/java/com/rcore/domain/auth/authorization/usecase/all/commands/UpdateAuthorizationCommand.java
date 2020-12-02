package com.rcore.domain.auth.authorization.usecase.all.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class UpdateAuthorizationCommand extends CreateAuthorizationCommand {
    private String id;
}
