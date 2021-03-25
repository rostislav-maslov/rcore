package com.rcore.database.mongo.auth.role.model;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.rcore.domain.auth.role.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class RoleDoc extends BaseDocument {
    private String name;
    private List<String> availableUseCases;
    private Boolean hasBoundlessAccess;
    private List<RoleEntity.AuthType> availableAuthTypes;
}
