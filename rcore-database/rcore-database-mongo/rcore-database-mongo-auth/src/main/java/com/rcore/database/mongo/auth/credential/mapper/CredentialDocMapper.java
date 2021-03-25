package com.rcore.database.mongo.auth.credential.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.database.mongo.auth.credential.model.CredentialDoc;
import com.rcore.database.mongo.auth.role.mapper.RoleDocMapper;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CredentialDocMapper implements ExampleDataMapper<CredentialEntity, CredentialDoc> {

    private final CredentialIdGenerator<ObjectId> credentialIdGenerator;
    private final RoleDocMapper roleDocMapper;

    @Override
    public CredentialDoc map(CredentialEntity credentialEntity) {
        return CredentialDoc.builder()
                .id(credentialIdGenerator.parse(credentialEntity.getId()))
                .password(credentialEntity.getPassword())
                .email(credentialEntity.getEmail())
                .phone(credentialEntity.getPhone())
                .status(credentialEntity.getStatus())
                .username(credentialEntity.getUsername())
                .createdAt(credentialEntity.getCreatedAt())
                .updatedAt(credentialEntity.getUpdatedAt())
                .roles(Optional.ofNullable(credentialEntity.getRoles())
                        .map(roles -> roles
                                .stream()
                                .map(r -> new CredentialDoc.Role(roleDocMapper.map(r.getRole()), r.isBlocked()))
                                .collect(Collectors.toList()))
                        .orElse(new ArrayList<>()))
                .build();
    }

    @Override
    public CredentialEntity inverseMap(CredentialDoc credentialDoc) {
        CredentialEntity entity = new CredentialEntity();
        entity.setId(credentialDoc.getId().toString());
        entity.setEmail(credentialDoc.getEmail());
        entity.setPassword(credentialDoc.getPassword());
        entity.setPhone(credentialDoc.getPhone());
        entity.setStatus(credentialDoc.getStatus());
        entity.setUsername(credentialDoc.getUsername());
        entity.setCreatedAt(credentialDoc.getCreatedAt());
        entity.setUpdatedAt(credentialDoc.getUpdatedAt());
        entity.setRoles(Optional.ofNullable(credentialDoc.getRoles())
                .map(roles -> roles
                        .stream()
                        .map(r -> new CredentialEntity.Role(roleDocMapper.inverseMap(r.getRole()), r.isBlocked()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>()));
        return entity;
    }
}
