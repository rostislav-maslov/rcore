package com.rcore.database.mongo.domain.role.model;

import com.rcore.domain.role.entity.RoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document
@CompoundIndexes({
        @CompoundIndex(def = "{'name': 1}", name = "name", unique = true)
})
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoleDoc extends RoleEntity {



}
