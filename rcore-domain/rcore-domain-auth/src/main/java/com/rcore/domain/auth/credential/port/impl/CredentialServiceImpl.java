package com.rcore.domain.auth.credential.port.impl;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.security.exceptions.AuthenticatedCredentialIsBlockedException;
import com.rcore.domain.security.exceptions.CredentialNotFoundException;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.port.CredentialService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;
    private final RoleRepository roleRepository;

    @Override
    public CredentialDetails getCredentialById(String id) {
        Optional<CredentialEntity> credentialEntity = credentialRepository.findById(id);
        if (credentialEntity.isEmpty())
            throw new CredentialNotFoundException();

        if (credentialEntity.get().isBlocked())
            throw new AuthenticatedCredentialIsBlockedException();

        return buildDetails(credentialEntity.get());
    }

    private CredentialDetails buildDetails(CredentialEntity credentialEntity) {

        List<CredentialDetails.Role> roles = credentialEntity.getRoles()
                .stream()
                .filter(role -> !role.isBlocked())
                .map(role -> roleRepository.findById(role.getRole().getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(role -> new CredentialDetails.Role(role.getName()))
                .collect(Collectors.toList());

        return new CredentialDetails(credentialEntity.getId(), roles);
    }
}
