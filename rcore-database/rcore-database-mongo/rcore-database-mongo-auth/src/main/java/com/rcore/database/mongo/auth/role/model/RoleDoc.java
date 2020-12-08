package com.rcore.database.mongo.auth.role.model;

import com.rcore.domain.auth.role.entity.RoleEntity;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
public class RoleDoc extends RoleEntity {
}
