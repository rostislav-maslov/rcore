package com.rcore.database.mongo.role.model;

import com.rcore.domain.role.entity.RoleEntity;
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
