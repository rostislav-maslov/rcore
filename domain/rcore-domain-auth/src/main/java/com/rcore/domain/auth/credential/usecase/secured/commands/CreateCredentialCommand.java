package com.rcore.domain.auth.credential.usecase.secured.commands;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateCredentialCommand {
    private String username;
    private String password;
    private String phone;
    private String email;
    private List<CredentialEntity.Role> roles;
    private CredentialEntity.Status status;
}
