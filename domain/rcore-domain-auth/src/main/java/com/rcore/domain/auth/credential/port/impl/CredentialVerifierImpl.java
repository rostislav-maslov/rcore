package com.rcore.domain.auth.credential.port.impl;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.token.port.SessionTokenRepository;
import com.rcore.domain.security.exceptions.CredentialNotFoundException;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.CredentialVerifier;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CredentialVerifierImpl implements CredentialVerifier {

    private final CredentialRepository credentialRepository;
    private final SessionTokenRepository sessionTokenRepository;
    private final TokenConverter<AccessTokenData> tokenConverter;
    private final RoleRepository roleRepository;

    @Override
    public CredentialDetails getAuthorizedCredential() throws CredentialNotFoundException {
        Optional<String> sessionToken = sessionTokenRepository.getSessionToken();
        if (sessionToken.isPresent()) {
            AccessTokenData tokenData = tokenConverter.parse(sessionToken.get());
            return credentialRepository.findById(tokenData.getCredentialId())
                    .map(this::buildDetails)
                    .orElseThrow(CredentialNotFoundException::new);
        }

        throw new CredentialNotFoundException();
    }

    private CredentialDetails buildDetails(CredentialEntity credentialEntity) {
        List<String> accesses = credentialEntity.getRoles()
                .stream()
                .filter(role -> !role.isBlocked())
                .map(role -> roleRepository.findById(role.getRoleId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(role -> role.getAccesses().stream())
                .collect(Collectors.toList());

        return new CredentialDetails(credentialEntity.getId(), accesses);
    }
}
