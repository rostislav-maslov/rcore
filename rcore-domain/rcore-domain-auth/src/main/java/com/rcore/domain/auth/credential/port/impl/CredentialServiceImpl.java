package com.rcore.domain.auth.credential.port.impl;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.token.port.SessionTokenRepository;
import com.rcore.domain.security.exceptions.CredentialNotFoundException;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.CredentialService;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;
    private final TokenConverter<AccessTokenData> tokenConverter;
    private final RoleRepository roleRepository;

    @Override
    public CredentialDetails getCredentialById(String id) {
        return credentialRepository.findById(id)
                .map(this::buildDetails)
                .orElseThrow(CredentialNotFoundException::new);
    }

    @Override
    public CredentialDetails getCredentialByToken(String token) throws CredentialNotFoundException {
        if (StringUtils.hasText(token)) {
            AccessTokenData tokenData = tokenConverter.parse(token);
            getCredentialById(tokenData.getCredentialId());
        }

        throw new CredentialNotFoundException();
    }

    private CredentialDetails buildDetails(CredentialEntity credentialEntity) {

        List<CredentialDetails.Role> roles = credentialEntity.getRoles()
                .stream()
                .filter(role -> !role.isBlocked())
                .map(role -> roleRepository.findById(role.getRoleId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(role -> new CredentialDetails.Role(role.getId(), role.getName(), role.getHasBoundlessAccess(), role.getAccesses()))
                .collect(Collectors.toList());

        return new CredentialDetails(credentialEntity.getId(), roles);
    }
}
