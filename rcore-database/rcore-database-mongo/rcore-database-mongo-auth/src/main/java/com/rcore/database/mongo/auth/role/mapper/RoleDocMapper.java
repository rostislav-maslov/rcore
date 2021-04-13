package com.rcore.database.mongo.auth.role.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.database.mongo.auth.role.model.RoleDoc;
import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleIdGenerator;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleDocMapper implements ExampleDataMapper<RoleEntity, RoleDoc> {

    private final RoleIdGenerator<ObjectId> idGenerator;

    @Override
    public RoleDoc map(RoleEntity roleEntity) {
        return RoleDoc.builder()
                .id(idGenerator.parse(roleEntity.getId()))
                .name(roleEntity.getName())
                .updatedAt(roleEntity.getUpdatedAt())
                .availableAuthTypes(roleEntity.getAvailableAuthTypes())
                .availableUseCases(roleEntity.getAvailableUseCases())
                .createdAt(roleEntity.getCreatedAt())
                .hasBoundlessAccess(roleEntity.getHasBoundlessAccess())
                .build();
    }

    @Override
    public RoleEntity inverseMap(RoleDoc roleDoc) {
        RoleEntity entity = new RoleEntity();
        entity.setId(roleDoc.getId().toString());
        entity.setName(roleDoc.getName());
        entity.setUpdatedAt(roleDoc.getUpdatedAt());
        entity.setAvailableAuthTypes(roleDoc.getAvailableAuthTypes());
        entity.setAvailableUseCases(roleDoc.getAvailableUseCases());
        entity.setCreatedAt(roleDoc.getCreatedAt());
        entity.setHasBoundlessAccess(roleDoc.getHasBoundlessAccess());
        return entity;
    }
}
