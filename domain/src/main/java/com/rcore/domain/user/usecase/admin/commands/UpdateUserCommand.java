package com.rcore.domain.user.usecase.admin.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UpdateUserCommand extends CreateUserCommand {
    private String id;
}
