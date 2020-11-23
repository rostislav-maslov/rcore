package com.rcore.domain.user.usecase.admin.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangeCurrentUserPasswordCommand {
    private String oldPassword;
    private String newPassword;
}
