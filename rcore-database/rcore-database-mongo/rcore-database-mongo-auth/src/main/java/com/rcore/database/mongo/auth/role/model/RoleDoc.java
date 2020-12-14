package com.rcore.database.mongo.auth.role.model;

import com.rcore.domain.auth.role.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@NoArgsConstructor
@Data
public class RoleDoc extends RoleEntity {
}
